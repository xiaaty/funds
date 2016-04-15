package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.entity.WithdrawApplyEntity;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithdrawApplyService;
import com.gqhmt.funds.architect.trade.service.WithholdApplyService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsTrade;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 交易相关api
 * @author lijunlong
 *
 */
@Service
public class FundsTradeImpl  implements IFundsTrade {


    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundOrderService fundOrderService;

    @Resource
    private WithholdApplyService withholdApplyService;

    @Resource
    private WithdrawApplyService withdrawApplyService;


    @Resource
    private FundWithrawChargeService fundWithrawChargeService;

    @Resource
    private FssAccountService fssAccountService;

    /**
     * 生成web提现订单
     * @param withdrawOrderDto            支付渠道
     * @return
     * @throws FssException
     */
    @Override
    public String webWithdrawOrder(WithdrawOrderDto withdrawOrderDto) throws FssException {
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withdrawOrderDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.hasEnoughBanlance(entity, withdrawOrderDto.getAmt().add(withdrawOrderDto.getCharge_amt()));
        this.cashWithSetReq(entity.getCustId(),1);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrderByRefund(entity, withdrawOrderDto.getAmt(),withdrawOrderDto.getCharge_amt(),GlobalConstants.ORDER_WITHDRAW,0,0,"2");
        return fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
    }
    /**
     * 生成web代扣订单
     * @param rechargeOrderDto            支付渠道
     * @return
     * @throws FssException
     */
    @Override
    public String webRechargeOrder(RechargeOrderDto rechargeOrderDto) throws FssException {
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(rechargeOrderDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity, rechargeOrderDto.getAmt(),GlobalConstants.ORDER_CHARGE,0,0,"2");
        return fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
    }


    /**
     * 线上代扣充值
     * @param withholdDto
     * 
     */
    @Override
    public boolean withholding(WithholdDto withholdDto) throws FssException {
        FundAccountEntity primaryAccount = this.getPrimaryAccount(Integer.parseInt(withholdDto.getCust_no()));
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("90004009");
        }
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withholdDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,withholdDto.getAmt(),GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        tradeRecordService.recharge(entity,fundOrderEntity.getOrderAmount(),fundOrderEntity,1002);
        return true;
    }

    /**
     * 提现
     * @param WithdrawDto
     * @return OrderNo
     */
    @Override
    public boolean withdraw(WithdrawDto withdrawDto) throws FssException {
        FundAccountEntity primaryAccount = this.getPrimaryAccount(Integer.parseInt(withdrawDto.getCust_no()));
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("90004004");
        }
        this.cashWithSetReq(primaryAccount.getCustId(),1);
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withdrawDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.hasEnoughBanlance(entity,withdrawDto.getAmt().add(withdrawDto.getCharge_amt() == null?BigDecimal.ZERO:withdrawDto.getCharge_amt()));
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(entity,withdrawDto.getAmt(),withdrawDto.getCharge_amt() == null?BigDecimal.ZERO:withdrawDto.getCharge_amt(),GlobalConstants.ORDER_WITHDRAW,0l,0);
        //资金处理
        tradeRecordService.withdraw(entity,fundOrderEntity.getOrderAmount(),fundOrderEntity,1012);
        this.chargeAmount(fundOrderEntity);
        return true;
    }
    /**
     * 线上代扣申请
     */
    @Override
    public boolean withholdingApply(RechargeApplyDto rechargeApplyDto) throws FssException {
        WithholdApplyEntity withholdApplyEntity=new WithholdApplyEntity();
        withholdApplyEntity.setCustId(Integer.parseInt(rechargeApplyDto.getCust_no()));
        withholdApplyEntity.setDrawAmount(rechargeApplyDto.getAmount());
        withholdApplyEntity.setBussinessId(Integer.parseInt(rechargeApplyDto.getBusi_no()));
        withholdApplyEntity.setBussinessArea(rechargeApplyDto.getRegion());
        withholdApplyEntity.setBussinessCompany(rechargeApplyDto.getFiliale());
        withholdApplyEntity.setApplyTime(new Date());
        try {
            withholdApplyService.insert(withholdApplyEntity);
        } catch (Exception e) {
            LogUtil.error(this.getClass(), e);
        }
        return true;
    }
    /**
     * 线上提现申请
     */
    @Override
    public boolean withdrawApply(WithdrawApplyDto withdrawApplyDto) throws FssException {
        WithdrawApplyEntity withdrawApplyEntity=new WithdrawApplyEntity();

        withdrawApplyEntity.setSettleType(Integer.parseInt(withdrawApplyDto.getSettle_type()));
        withdrawApplyEntity.setSeqNo(withdrawApplyDto.getSeq_no());
        withdrawApplyEntity.setCustId(Integer.parseInt(withdrawApplyDto.getCust_no()));
        withdrawApplyEntity.setBussinessId(Integer.parseInt(withdrawApplyDto.getBusi_no()));
        withdrawApplyEntity.setDrawAmount(withdrawApplyDto.getAmount());
        withdrawApplyEntity.setApplyTime(new Date());
        try {
            withdrawApplyService.insert(withdrawApplyEntity);
        } catch (Exception e) {
            LogUtil.error(this.getClass(), e);
        }
        return true;
    }
    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：线下代扣充值
     */
    @Override
    public boolean withholdingApply(int custID, int businessType, String busiNo, BigDecimal amount, Long busiId) throws FssException {
        FundOrderEntity fundOrderEntity = this.withholdingApply(custID,businessType,busiNo,amount,busiId,GlobalConstants.BUSINESS_WITHHOLDING);

        return  fundOrderEntity != null;

    }

    public FundOrderEntity withholdingApplyNew(int custID, int businessType, String contractNo, BigDecimal amount, Long busiId) throws FssException {
        FundOrderEntity fundOrderEntity = this.withholdingApply(custID,businessType,contractNo,amount,busiId,GlobalConstants.NEW_BUSINESS_WITHHOLDING);
        return  fundOrderEntity;
    }

    public FundOrderEntity withholdingApplyNew(String accNo, String busiNo, BigDecimal amount, Long busiId) throws FssException {

        FssAccountEntity fssAccountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
        if (fssAccountEntity == null){
            throw new CommandParmException("90004006");
        }

        int accType = fssAccountEntity.getAccType();
        int businessType = this.tradeRecordService.parseBusinessType(accType);

        FundOrderEntity fundOrderEntity = this.withholdingApply(fssAccountEntity.getCustId().intValue(),businessType,busiNo,amount,busiId,GlobalConstants.NEW_BUSINESS_WITHHOLDING);
        return  fundOrderEntity;
    }


    private FundOrderEntity withholdingApply(int custID, int businessType, String busiNo, BigDecimal amount, Long busiId,int busiTyep) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        checkwithholdingOrWithDraw(entity,1,businessType);

        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_WITHHOLDING,busiId,busiTyep);
        //资金处理
        tradeRecordService.recharge(entity,amount,fundOrderEntity,1002);

        return  fundOrderEntity;
    }


    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：线下提现代付
     */
    @Override
    public boolean withdrawApply(int custID, int businessType, String contractNo, BigDecimal amount, Long busiId,int selletType) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        this.hasEnoughBanlance(entity,amount);
        checkwithholdingOrWithDraw(entity,2,businessType);
        this.cashWithSetReq(entity.getCustId(),selletType);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_WITHHOLDING,busiId,GlobalConstants.BUSINESS_WITHHOLDING);
        //资金处理
        tradeRecordService.withdrawByFroze(entity,amount,fundOrderEntity,2003);
        return true;
    }

    /**
     * 转账
     */
    @Override
    public boolean transfer(TransferDto transferDto) throws FssException {
        //90004017
        if(transferDto.getFrom_cust_no().intValue() == transferDto.getTo_cust_no().intValue()){
            throw  new FssException("90004017");
        }

        FundAccountEntity fromEntity = this.getFundAccount(transferDto.getFrom_cust_no(), transferDto.getFrom_cust_type().intValue());
        this.hasEnoughBanlance(fromEntity,transferDto.getAmt());
        FundAccountEntity toEntity = this.getFundAccount(transferDto.getTo_cust_no(),transferDto.getTo_cust_type().intValue());
        //第三方交易
        FundOrderEntity fundOrderEntity = this.paySuperByFuiou.transerer(fromEntity,toEntity,transferDto.getAmt(),transferDto.getBusi_type(),transferDto.getBusi_id(),transferDto.getBusi_type());
        //资金处理
        this.tradeRecordService.transfer(fromEntity,toEntity,fundOrderEntity.getOrderAmount(),transferDto.getFunds_type(),fundOrderEntity);
        return true;
    }

    /**
     *
     */
    @Override
    public FundOrderEntity transefer( Integer fromCusID, Integer fromType, Integer toCusID, Integer toType, BigDecimal amount, Integer orderType, Long busiId, int busiType) throws FssException {
    	Integer string = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(fromType);
    	FundAccountEntity fromEntity = this.getFundAccount(fromCusID, string);
        this.hasEnoughBanlance(fromEntity,amount);
        Integer to = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(toType);
        FundAccountEntity toEntity = this.getFundAccount(toCusID, to);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.transerer(fromEntity,toEntity,amount,orderType,busiId,busiType);
        //资金处理
        tradeRecordService.transfer(fromEntity,toEntity,amount,busiType,fundOrderEntity);
        return fundOrderEntity;
    }

    public FundOrderEntity transefer(String fromAccNo,String toAccno,BigDecimal amount,Integer orderType,Long busiId,int busiType) throws FssException{

        FssAccountEntity fromAccount  = this.fssAccountService.getFssAccountByAccNo(fromAccNo);
        FssAccountEntity toAccount  = this.fssAccountService.getFssAccountByAccNo(toAccno);
        if (fromAccount == null || toAccount == null){
            throw new CommandParmException("90004006");
        }

        int fromAccountAccType = fromAccount.getAccType();
        int toAccountAccType = toAccount.getAccType();

        FundOrderEntity fundOrderEntity = this.transefer(fromAccount.getCustId().intValue(),fromAccountAccType,toAccount.getCustId().intValue(),toAccountAccType,amount,orderType,busiId,busiType);

        return fundOrderEntity;
    }

	/**
	 * 资金冻结
	 */
    @Override
    public boolean froze(Long custId,Integer busiType,BigDecimal amt) throws FssException {
        FundAccountEntity fromEntity = this.getFundAccount(Integer.valueOf(custId.toString()), Integer.valueOf(busiType));
        this.hasEnoughBanlance(fromEntity,amt);
        FundAccountEntity toEntity = this.getFundAccount(Integer.valueOf(custId.toString()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        tradeRecordService.frozen(fromEntity,toEntity,amt,1007,null,"",BigDecimal.ZERO);
        return true;
    }
    
    
    @Override
    public boolean froze(FreezeDto dto) throws FssException {
        FundAccountEntity fromEntity = this.getFundAccount(Integer.parseInt(dto.getCust_no()), dto.getBusi_type());
        this.hasEnoughBanlance(fromEntity,dto.getAmt());
        FundAccountEntity toEntity = this.getFundAccount(Integer.parseInt(dto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        tradeRecordService.frozen(fromEntity,toEntity,dto.getAmt(),1007,null,"",BigDecimal.ZERO);
        return true;
    }

    @Override
    public boolean unFroze(UnFreezeDto dto) throws FssException {
        FundAccountEntity fromEntity = this.getFundAccount(Integer.parseInt(dto.getCust_no()),GlobalConstants.ACCOUNT_TYPE_FREEZE);
        this.hasEnoughBanlance(fromEntity,dto.getAmt());
        FundAccountEntity toEntity = this.getFundAccount(Integer.parseInt(dto.getCust_no()), dto.getBusi_type());
        tradeRecordService.frozen(fromEntity,toEntity,dto.getAmt(),1007,null,"",BigDecimal.ZERO);
        return true;
    }




    /**
     * 数据校验
     * @param entity
     * @param type
     * @throws FssException
     */
    private void checkwithholdingOrWithDraw(FundAccountEntity entity,int type,int busiType) throws FssException{
        FundAccountEntity primaryAccount = entity == null || entity.getBusiType() !=0? this.getPrimaryAccount(entity.getCustId().intValue()):entity;
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许"+(type == 1?"代扣":"提现"));
        }
        //提现次数限制
        if (busiType==GlobalConstants.ACCOUNT_TYPE_LEND_ON){
            boolean checkWithdraw = this.fundOrderService.checkWithdrawNumber(entity.getId());
            if(checkWithdraw){
                throw new CommandParmException("90004005");
            }
        }
    }

    private FundAccountEntity getFundAccount(int cusID, int type) throws CommandParmException {
        FundAccountEntity entity = null;
        if (cusID < 100) {
            entity = fundAccountService.getFundAccount(Long.valueOf(cusID), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        } else {
            entity = fundAccountService.getFundAccount(Long.valueOf(cusID), type);
        }

        if (entity == null) {
            throw new CommandParmException("90004006");
        }
        return entity;
    }

    private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
        BigDecimal bigDecimal = entity.getAmount();
        if (bigDecimal.compareTo(amount) < 0) {
            throw new CommandParmException("90004007");
        }
    }

    private FundAccountEntity getPrimaryAccount(Integer cusId){
        FundAccountEntity primaryAccount = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        if(primaryAccount == null){
            throw new CommandParmException("90004008");
        }
        return primaryAccount;
    }

    /**
     * 查询交易记录
     * @param tradrecord
     * @return
     */
    public List<FundTradeBean> queryFundTrade(FundTradeDto tradrecord) throws FssException{

		/*FundAccountEntity primaryAccount = fundAccountService.getFundAccount(tradrecord.getCust_no(), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 if(primaryAccount==null){
			 throw new FssException("90002001");//账户信息不存在
		 }*/
        List<FundTradeBean> tradelist= tradeRecordService.queryFundTrade(tradrecord.getCust_no(),tradrecord.getStr_trade_time(),tradrecord.getEnd_trade_time(),tradrecord.getTradeFilters());
        return tradelist;
    }

    public void cashWithSetReq(Long cusId,int cashWithSet) throws FssException {
        FundAccountEntity primaryAccount =this.getPrimaryAccount(cusId.intValue());
        if( primaryAccount.getSettleType() == null){
            primaryAccount.setSettleType(0);
        }
        if(  cashWithSet == primaryAccount.getSettleType()){
            return;
        }
        paySuperByFuiou.cashWithSetReq(primaryAccount,cashWithSet);
        primaryAccount.setSettleType(cashWithSet);
        fundAccountService.update(primaryAccount);
    }

    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：充值成功入账
     */
    public void recharge(RechargeSuccessDto rechargeSuccessDto) throws FssException {
        FundAccountEntity entity=this.getFundAccount(Integer.parseInt(rechargeSuccessDto.getCust_no()),  GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity=fundOrderService.findfundOrder(rechargeSuccessDto.getOrder_no());
        if("0000".equals(rechargeSuccessDto.getRespCode())) {
            tradeRecordService.recharge(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1001);
            fundOrderEntity.setOrderState(2);
            fundOrderService.update(fundOrderEntity);
        }else{
            fundOrderEntity.setOrderState(3);
            fundOrderService.update(fundOrderEntity);
        }
    }
    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：提现成功入账
     */
    public void withdraw(WithdrawSuccessDto withdrawSuccessDto) throws FssException {
        FundOrderEntity fundOrderEntity=fundOrderService.findfundOrder(withdrawSuccessDto.getOrder_no());
        FundAccountEntity entity=fundAccountService.getFundAccountInfo(fundOrderEntity.getAccountId());
        if("0000".equals(withdrawSuccessDto.getRespCode())) {
            tradeRecordService.withdraw(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1003);
            fundOrderEntity.setOrderState(2);
            fundOrderService.update(fundOrderEntity);
            this.chargeAmount(fundOrderEntity);
        }else{
            fundOrderEntity.setOrderState(3);
            fundOrderService.update(fundOrderEntity);
        }
        //收取账户管理费

    }


    private void chargeAmount(FundOrderEntity fundOrderEntity) throws CommandParmException, FssException {
        if(fundOrderEntity.getChargeAmount() == null || BigDecimal.ZERO.compareTo(fundOrderEntity.getChargeAmount())>=0){
            return;
        }
        FundAccountEntity entity = fundAccountService.getFundAccountInfo(fundOrderEntity.getAccountId());
        this.chargeAmount(entity,fundOrderEntity);

    }

    /*private void chargeAmount(String  userName,FundOrderEntity fundOrderEntity) throws CommandParmException, FssException {
        if(fundOrderEntity.getChargeAmount() == null || BigDecimal.ZERO.compareTo(fundOrderEntity.getChargeAmount())>=0){
            return;
        }
        FundAccountEntity entity = fundAccountService.getFundAccount(userName,GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.chargeAmount(entity,fundOrderEntity);

    }*/


    private void chargeAmount(FundAccountEntity entity,FundOrderEntity fundOrderEntity) throws FssException {
        FundAccountEntity toEntity  = this.getFundAccount(99,0);
        //this.unfreezeByThird(entity.getCustId(),fundOrderEntity.getChargeAmount());
        FundOrderEntity fundOrderEntityCharge =  paySuperByFuiou.chargeAmount(entity,toEntity,fundOrderEntity.getChargeAmount());
//        sequenceService.transfer(entity, 2003, fundOrderEntity.getOrderAmount(), null,ThirdPartyType.FUIOU,fundOrderEntity);
        if(fundOrderEntityCharge == null) return;
        fundWithrawChargeService.add(fundOrderEntity.getOrderNo(), entity, fundOrderEntity.getOrderAmount(), fundOrderEntity.getChargeAmount());
        tradeRecordService.chargeAmount(entity,toEntity,fundOrderEntity,fundOrderEntityCharge);
    }

    /**
     * 实时提现
     */
   /* @Override
    public boolean sstxBusiness(SstxDto sstx) throws FssException {
        this.withdrawApply(sstx.getCust_no().intValue(), sstx.getBusi_type().intValue(), "",sstx.getAmt(), sstx.getBusi_id(),sstx.getSettleType());
        return true;
    }

    @Override
    public boolean ssdkBusiness(SsdkDto ssdk) throws FssException {
        this.withholdingApply(ssdk.getCust_no().intValue(), ssdk.getBusi_type().intValue(),"", ssdk.getAmt(), ssdk.getBusi_id());
        return true;
    }
*/
/*
    @Override
	public FundOrderEntity withholdingApplyNew(Long custId, String busiNo, BigDecimal amount, Long busiId)throws FssException {
        FssAccountEntity fssAccountEntity  = this.fssAccountService.getAccountEntityByCustid(custId);
        if (fssAccountEntity == null){
            throw new CommandParmException("90004006");
        }
        int accType = fssAccountEntity.getAccType();
        int businessType = this.tradeRecordService.parseBusinessType(accType);

        FundOrderEntity fundOrderEntity = this.withholdingApply(custId.intValue(),businessType,busiNo,amount,busiId,GlobalConstants.NEW_BUSINESS_WITHHOLDING);
        return  fundOrderEntity;
    	
	}
*/
    /**
     * 批量代付
     */
    @Override
    public FundOrderEntity withdrawApplyNew(String accNo,String custID, Integer businessType, String contractNo, BigDecimal amount, Long busiId,int selletType) throws FssException {
    	FundOrderEntity fundOrderEntity=null;
    	if(accNo!=null &&!"".equals(accNo)){//账号不为空
    		FssAccountEntity fssAccountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
    	      if (fssAccountEntity == null){
    	           throw new CommandParmException("90004006");
    	       }
    	      int accType = fssAccountEntity.getAccType();
    	      businessType = this.tradeRecordService.parseBusinessType(accType);
    	      custID=String.valueOf(fssAccountEntity.getCustId());
    	}
    	FundAccountEntity entity = this.getFundAccount(Integer.valueOf(custID).intValue(), businessType);
        this.hasEnoughBanlance(entity,amount);
        checkwithholdingOrWithDraw(entity,2,businessType);
        this.cashWithSetReq(entity.getCustId(),selletType);
        fundOrderEntity = paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_WITHHOLDING,busiId,GlobalConstants.BUSINESS_WITHHOLDING);
        //资金处理
        tradeRecordService.withdrawByFroze(entity,amount,fundOrderEntity,2003);
        return fundOrderEntity;
    }  
    
    
    
    
    
    
    
    
    
}