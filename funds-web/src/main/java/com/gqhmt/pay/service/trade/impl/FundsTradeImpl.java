package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.trade.entity.FssBondTransferEntity;
import com.gqhmt.fss.architect.trade.entity.FssOfflineRechargeEntity;
import com.gqhmt.fss.architect.trade.service.FssBondTransferService;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.entity.WithdrawApplyEntity;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.funds.architect.trade.service.WithdrawApplyService;
import com.gqhmt.funds.architect.trade.service.WithholdApplyService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.fuiou.util.HttpClientUtil;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private FssBackplateService fssBackplateService;


    @Resource
    private FundWithrawChargeService fundWithrawChargeService;

    @Resource
    private FssAccountService fssAccountService;

    @Resource
    private NoticeService noticeService;
    @Resource
    private FssOfflineRechargeService fssOfflineRechargeService;
    @Resource
    private FundSequenceService fundSequenceService;
    @Resource
    private FundTradeService fundTradeService;
    @Resource
    private FssBondTransferService fssBondTransferService;

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
        checkwithholdingOrWithDraw(entity,2);//银行卡变更中不允许提现
        this.cashWithSetReq(entity.getCustId(),1);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity, withdrawOrderDto.getAmt(),withdrawOrderDto.getCharge_amt(),GlobalConstants.ORDER_WITHDRAW,0,0,"1104",withdrawOrderDto.getTrade_type());
        return entity.getUserName()+":"+ fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
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
        if(Integer.valueOf(rechargeOrderDto.getTrade_type()).intValue()!=11030001){
        	checkwithholdingOrWithDraw(entity,1);//银行卡变更中可以进行网银充值，但是不允许代扣充值
        }
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity, rechargeOrderDto.getAmt(),GlobalConstants.ORDER_CHARGE,0,0,"1103",rechargeOrderDto.getTrade_type());
        return entity.getUserName()+":"+fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
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
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,withholdDto.getAmt(),GlobalConstants.ORDER_WITHHOLDING,0,0,"1103",withholdDto.getTrade_type(),null,null);
        //资金处理
        tradeRecordService.recharge(entity,fundOrderEntity.getOrderAmount(),fundOrderEntity,1002,withholdDto.getTrade_type());
        return true;
    }

    /**
     * 提现
     * @param withdrawDto
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
        FundAccountEntity freezeEntity = this.getFundAccount(Integer.parseInt(withdrawDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        BigDecimal withdrawAmt = withdrawDto.getAmt().add(withdrawDto.getCharge_amt() == null?BigDecimal.ZERO:withdrawDto.getCharge_amt());

        if(withdrawAmt.equals(BigDecimal.ZERO)){
            throw new FssException("90004014");
        }
        this.hasEnoughBanlance(entity,withdrawAmt);
        tradeRecordService.frozen(entity,freezeEntity,withdrawDto.getAmt() ,1012,null,"提现成功，提现金额 " + withdrawDto.getAmt() + "元,收取提现手续费"+withdrawDto.getCharge_amt(),BigDecimal.ZERO,withdrawDto.getTrade_type());
//        if(withdrawDto.getCharge_amt().compareTo(BigDecimal.ZERO)>0) {//处理提现手续费
//            tradeRecordService.frozen(entity, freezeEntity, withdrawDto.getCharge_amt(), 4010, null, "收取提现手续费", BigDecimal.ZERO, withdrawDto.getTrade_type());
//        }
        //二次校验账户状态，如果账户为负数，则提现存在问题，无法提现
        entity = this.getFundAccount(Integer.parseInt(withdrawDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        if(entity.getAmount().compareTo(BigDecimal.ZERO)<0){
            tradeRecordService.unFrozen(freezeEntity,entity,withdrawAmt,1004,null,"提现失败，退回金额"+ withdrawAmt + "元",BigDecimal.ZERO,withdrawDto.getTrade_type());
            throw new FssException("90004007");
        }

        //校验账户无问题，则进行真正的交易，发送提现指令到富友
        try {
            FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(freezeEntity, withdrawDto.getAmt(), withdrawDto.getCharge_amt() == null ? BigDecimal.ZERO : withdrawDto.getCharge_amt(), GlobalConstants.ORDER_AGENT_WITHDRAW, 0l, 0, "1104", withdrawDto.getTrade_type(), null, null);
            tradeRecordService.withdrawByFroze(freezeEntity,fundOrderEntity.getOrderAmount(),fundOrderEntity,2003);
            this.chargeAmount(fundOrderEntity);
        }catch(Exception e) {
            //资金处理
            tradeRecordService.unFrozen(freezeEntity,entity,withdrawAmt,1004,null,"提现失败，退回金额"+ withdrawAmt + "元",BigDecimal.ZERO,withdrawDto.getTrade_type());
            throw new FssException("90004007");
        }


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
        FundOrderEntity fundOrderEntity = this.withholdingApply(custID,businessType,busiNo,amount,busiId,GlobalConstants.BUSINESS_WITHHOLDING,null,null);
        return  fundOrderEntity != null;

    }

    public FundOrderEntity withholdingApplyNew(int custID, int businessType, String contractNo, BigDecimal amount, Long busiId,Integer tradeType,Integer tradeTypeChild) throws FssException {
        FundOrderEntity fundOrderEntity = this.withholdingApply(custID,businessType,contractNo,amount,busiId,GlobalConstants.NEW_BUSINESS_WITHHOLDING,tradeType,tradeTypeChild);
        return  fundOrderEntity;
    }

    public FundOrderEntity withholdingApplyNew(String accNo, String contractNo, BigDecimal amount, Long busiId,Integer tradeType,Integer tradeTypeChild) throws FssException {

        FssAccountEntity fssAccountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
        if (fssAccountEntity == null){
            throw new CommandParmException("90004006");
        }
        int accType = fssAccountEntity.getAccType();
        int businessType = this.tradeRecordService.parseBusinessType(accType);
        FundOrderEntity fundOrderEntity = this.withholdingApply(fssAccountEntity.getCustId().intValue(),businessType,contractNo,amount,busiId,GlobalConstants.NEW_BUSINESS_WITHHOLDING,tradeType,tradeTypeChild);
        return  fundOrderEntity;
    }

    /**
     * 代扣
     * @param custID
     * @param businessType
     * @param contractNo
     * @param amount
     * @param busiId
     * @param busiTyep
     * @return
     * @throws FssException
     */
    private FundOrderEntity withholdingApply(int custID, int businessType, String contractNo, BigDecimal amount, Long busiId,int busiTyep,Integer newOrderType,Integer tradeType) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        checkwithholdingOrWithDraw(entity,1);
        String lendNo=null;
        String loanNo=null;
        if(businessType==1){//借款账户
            loanNo= contractNo;//投标，满标，回款，等等对应借款合同编号
        }else if(businessType==2){//线下出借账户
            lendNo=contractNo;
        }else if(businessType==96){//应付账户（出借）
            lendNo=contractNo;
        }
        FundOrderEntity fundOrderEntity =null;

         fundOrderEntity = paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_WITHHOLDING,busiId,busiTyep,String.valueOf(newOrderType),String.valueOf(tradeType),lendNo,loanNo);
        //资金处理
        tradeRecordService.recharge(entity,amount,fundOrderEntity,1002,tradeType==null?null:tradeType.toString());
        
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
        checkwithholdingOrWithDraw(entity,2);
        this.cashWithSetReq(entity.getCustId(),selletType);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_AGENT_WITHDRAW,busiId,GlobalConstants.BUSINESS_WITHHOLDING,null,null,null,null);
        //资金处理
        tradeRecordService.withdrawByFroze(entity,amount,fundOrderEntity,2003);
        return true;
    }

    /**
     * 转账接口
     */
   @Override
    public boolean transfer(String mchn,String seq_no,String trade_type,Integer from_cust_no,Integer from_user_no,Integer from_cust_type,Integer to_cust_no,Integer to_user_no,Integer to_cust_type,BigDecimal amt,Integer fund_type,Integer  busi_type,Long  busi_id,Integer actionType,String contract_no,String loan_type) throws FssException {

       String lend_no=null;
       String loan_no=null;
       if("3".equals(loan_type)){//出借 lend_no
           lend_no=contract_no;
       }else if("1".equals(loan_type)){//借款 loan_no
           loan_no=contract_no;
       }else{//其他
           lend_no=null;
           loan_no=null;
       }
       this.transfer(from_cust_no,from_cust_type,to_cust_no,to_cust_type,amt,GlobalConstants.ORDER_TRANSFER,null,null, "1119", trade_type,lend_no,null,null, loan_no);
       return true;
    }

    /**
     *资金后台转账处理
     */
    @Override
    public boolean transefer(Integer fromCusID, Integer fromType, Integer toCusID, Integer toType, BigDecimal amount, Integer orderType, Long busiId, int busiType,String tradeType,String contractNo,Integer fund_type,Integer actionType) throws FssException {
       // return this.(null,null,tradeType,null,contractNo,null,String.valueOf(fromCusID),null,amount,null,String.valueOf(toCusID),null,fromType,toType,fund_type,actionType);
        this.transfer(fromCusID,fromType,toCusID,toType,amount,orderType,busiType,busiId, "1119", tradeType,null,null,null, contractNo);
        return true;
    }


    /**
     * 借款系统抵押标转账
     * @param fromAccNo
     * @param toAccno
     * @param amount
     * @param orderType
     * @param busiId
     * @param busiType
     * @param tradeType
     * @param contractNo
     * @return
     * @throws FssException
     */
    public boolean transefer(String fromAccNo,String toAccno,BigDecimal amount,Integer orderType,Long busiId,int busiType,String tradeType,String contractNo,Integer fund_type,Integer actionType) throws FssException{
        FssAccountEntity fromAccount  = this.fssAccountService.getFssAccountByAccNo(fromAccNo);
        FssAccountEntity toAccount  = this.fssAccountService.getFssAccountByAccNo(toAccno);
        Integer from_acc_type = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(fromAccount.getAccType());
        Integer to_acc_type = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(toAccount.getAccType());
        this.transfer(fromAccount.getCustId().intValue(),from_acc_type,toAccount.getCustId().intValue(),to_acc_type, amount,orderType,busiType,busiId, "1119", tradeType,null,null,toAccount.getCustId(), contractNo);
        return true;
    }

    /**
     * 债权转让、转账通用接口
     * @param mchn 商户号
     * @param seq_no 流水号
     * @param trade_type 交易类型
     * @param bid_id 标的业务编号
     * @param busi_bid_no 借款合同编号，用于获取账户信息等
     * @param tender_no 投标编号，此笔投标业务编号，方便数据统计 用于更新债权列表
     * @param cust_no 接标人客户编号
     * @param busi_no 接标人出借业务编号
     * @param amt 转账金额
     * @param o_tender_no 原投标编号
     * @param o_cust_no 转让人客户编号
     * @param o_busi_no 转让人出借业务编号
     * @param acc_type 对于债权转让，是接标人账户类型；普通转账是出账人的账户类型（0,1,2,3,96）
     * @param to_acc_type 对于债权转让，是转让人账户类型；普通转账是入账人的账户类型(0,1,2,3,96)
     * @param fundType  调用端传递过来（如：1005,"一般转账:转出"；3007,"购买债权.....）
     * @param actionType (1,"充值";2,"提现";3,"转账";4,"冻结";5,"解冻";6,"投标成功";7,"还款";10,"债权转让")
     * @return
     * @throws FssException
     */
    public boolean bondTransfer(String mchn,String seq_no,String trade_type,String bid_id,String busi_bid_no,String tender_no,String cust_no,String busi_no,BigDecimal amt,String o_tender_no,String o_cust_no,String o_busi_no,Integer acc_type,Integer to_acc_type,Integer fundType,Integer actionType) throws FssException {
        //根据交易类型验证接标人出借业务编号是否为空，线下出借，必须传递此编号，线上客户为空
        if("11052006".equals(trade_type)){//委托购买债权(线下)
            if(busi_no==null || "".equals(busi_no)) throw new FssException("90002021");
        }
        FundAccountEntity  fromEntity = this.getFundAccount(Integer.valueOf(cust_no),acc_type);//转出账户
        FundAccountEntity  toEntity = this.getFundAccount(Integer.valueOf(o_cust_no),to_acc_type);//转入账户
        //债权转让的添加债权转让记账信息
        FssBondTransferEntity bondEntity = fssBondTransferService.createBondTransferInfo(mchn,seq_no,trade_type,bid_id,busi_bid_no,cust_no,o_cust_no,busi_no,fromEntity.getCustName(),fromEntity.getAccountNo(),acc_type,to_acc_type,tender_no,o_tender_no,o_busi_no);
        String tradeState=bondEntity.getTradeState();
        FundOrderEntity fundOrderEntity=null;
        try {
            this.hasEnoughBanlance(fromEntity, amt);
            //第三方交易
            if(fromEntity.getCustId().longValue() != toEntity.getCustId().longValue()) {
            //判断是否是债权转让11052001,11052002,11052003,11052004,11052005,11052006
                int orderType=GlobalConstants.ORDER_TRANSFER;
                if(("11052001").equals(trade_type) || ("11052002").equals(trade_type) || ("11052003").equals(trade_type) || ("11052004").equals(trade_type) || ("11052005").equals(trade_type) || ("11052006").equals(trade_type)){
                    orderType=GlobalConstants.ORDER_DEBT;//债权转让
                }else{
                    orderType=GlobalConstants.ORDER_TRANSFER;//转账
                }
                fundOrderEntity = this.paySuperByFuiou.transerer(fromEntity, toEntity, amt,orderType, bondEntity.getId(), GlobalConstants.ORDER_DEBT, trade_type.substring(0, 4), trade_type, busi_no, o_tender_no, Long.valueOf(cust_no), busi_bid_no);
            }
                //资金处理
            tradeRecordService.transfer(fromEntity,toEntity,amt,fundType,fundOrderEntity,actionType,null,trade_type.substring(0,4),trade_type,busi_no,Long.valueOf(o_cust_no),o_tender_no,Long.valueOf(cust_no),busi_bid_no);
            fssBondTransferService.updateBandTransfer(bondEntity,amt,fundOrderEntity==null?null:fundOrderEntity.getOrderNo(),"10080002","0000");
            //添加交易记录
            fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,amt,fundType, "转账成功，资金转出："+amt+"元",BigDecimal.ZERO);
            fundTradeService.addFundTrade(toEntity,amt, BigDecimal.ZERO,fundType,"转账成功，资金转入："+amt+"元");
        }catch (Exception e){
              fssBondTransferService.updateBandTransfer(bondEntity,amt,null,"10080010",e.getMessage());
//            throw new FssException(e.getMessage());
        }
        return true;
    }


    public void transfer(Integer fromCustid,Integer fromType,Integer toCustId,Integer toType,BigDecimal amt,Integer orderType,Integer sourceType,Long SourceId,String newOrderType,String tradeType,String lendNo,String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FundAccountEntity  fromEntity = this.getFundAccount(fromCustid,fromType);//转出账户
        FundAccountEntity  toEntity = this.getFundAccount(toCustId,toType);//转入账户

        FundOrderEntity fundOrderEntity=null;
        try {
            this.hasEnoughBanlance(fromEntity, amt);
            //第三方交易
            if(fromEntity.getCustId().longValue() != toEntity.getCustId().longValue()) {
                fundOrderEntity = this.paySuperByFuiou.transerer(fromEntity, toEntity, amt, orderType, SourceId, sourceType,newOrderType, tradeType, lendNo, toLendNo,loanCustId, loanNo);
            }
            int fundType = 1005;
            if(GlobalConstants.ORDER_DEBT == orderType){
                 fundType = 3008;
            }else if(toCustId ==  2){
                fundType = 4003;
            }else if(toCustId ==  5 || toCustId ==  11 || toCustId ==  8){

                fundType = 4006;
            }else if(toCustId ==  1 || toCustId ==  6 || toCustId ==  9){
                fundType = 4002;
            }else if(fromCustid == 4){
                fundType = 1013;
            }
            //资金处理
            tradeRecordService.transfer(fromEntity,toEntity,amt,fundType,fundOrderEntity,8,null,newOrderType,tradeType,lendNo,toCustId != null ? toCustId.longValue():0l,toLendNo,loanCustId,loanNo);
            //添加交易记录
            fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,amt,fundType, "转账成功，资金转出："+amt+" 元",BigDecimal.ZERO);
            fundTradeService.addFundTrade(toEntity,amt, BigDecimal.ZERO,fundType,"转账成功，资金转入："+amt+" 元");
        }catch (Exception e){
            throw new FssException(e.getMessage());
        }
    }

    /**
	 * 资金冻结
	 */
    @Override
    public boolean froze(Long custId,Integer busiType,BigDecimal amt,String tradeType) throws FssException {
        FundAccountEntity fromEntity = this.getFundAccount(Integer.valueOf(custId.toString()),busiType);
        this.hasEnoughBanlance(fromEntity,amt);
        FundAccountEntity toEntity = this.getFundAccount(Integer.valueOf(custId.toString()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        tradeRecordService.frozen(fromEntity,toEntity,amt,1007,null,"资金冻结，冻结金额："+amt+"元",BigDecimal.ZERO,tradeType);
        return true;
    }

    @Override
    public boolean unFroze(String mchn,String seq_no,String trade_type,String cust_no,String user_no,BigDecimal amt,Integer type) throws FssException {
        Integer busi_type=0;
        if(String.valueOf(type).length()>2){
            busi_type= GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(busi_type);
        }else{
            busi_type =type;
        }
        FundAccountEntity fromEntity = this.getFundAccount(Integer.parseInt(cust_no),GlobalConstants.ACCOUNT_TYPE_FREEZE);
        this.hasEnoughBanlance(fromEntity,amt);
        FundAccountEntity toEntity = this.getFundAccount(Integer.parseInt(cust_no), busi_type);
        tradeRecordService.unFrozen(fromEntity,toEntity,amt,1007,null,"资金解冻，解冻金额："+amt+"元",BigDecimal.ZERO,trade_type);
        return true;
    }

    /**
     * 数据校验
     * @param entity
     * @param type
     * @throws FssException
     */
    private void checkwithholdingOrWithDraw(FundAccountEntity entity,int type) throws FssException{
        FundAccountEntity primaryAccount = entity == null || entity.getBusiType() !=0? this.getPrimaryAccount(entity.getCustId().intValue()):entity;
        if (primaryAccount.getIshangeBankCard()==1){
//            throw new CommandParmException("银行卡变更中,不允许"+(type == 1?"代扣":"提现"));
        	if(type == 1){
        		throw new FssException("90004009");
        	}else{
        		throw new FssException("90004004");
        	}
        }
        //提现次数限制 ,暂时去掉限制功能,未来更具需要进行添加
//        if (busiType==GlobalConstants.ACCOUNT_TYPE_LEND_ON){
//            boolean checkWithdraw = this.fundOrderService.checkWithdrawNumber(entity.getId());
//            if(checkWithdraw){
//                throw new CommandParmException("90004005");
//            }
//        }
    }

    public FundAccountEntity getFundAccount(int cusID, int type) throws CommandParmException {
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
//        FundAccountEntity entity=this.getFundAccount(Integer.parseInt(rechargeSuccessDto.getCust_no()),  GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity=fundOrderService.findfundOrder(rechargeSuccessDto.getOrder_no());
        FundAccountEntity entity=fundAccountService.getFundAccountInfo(fundOrderEntity.getAccountId());
        if("0000".equals(rechargeSuccessDto.getRespCode())) {
            tradeRecordService.recharge(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1001,rechargeSuccessDto.getTrade_type());
            fundOrderEntity.setOrderState(2);
            fundOrderService.update(fundOrderEntity);
          //发送站内通知短信
            this.sendNotice(CoreConstants.FUND_CHARGE_TEMPCODE,NoticeService.NoticeType.FUND_CHARGE,entity,fundOrderEntity.getOrderAmount(),BigDecimal.ZERO);

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
//            tradeRecordService.withdraw(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 1003);
            tradeRecordService.withdraw(entity, fundOrderEntity.getOrderAmount(), fundOrderEntity, 2003,withdrawSuccessDto.getTrade_type());
            fundOrderEntity.setOrderState(2);
            fundOrderService.update(fundOrderEntity);
            this.chargeAmount(fundOrderEntity);
            //提现成功发送站内短信
            this.sendNotice(CoreConstants.FUND_WITHDRAW_TEMPCODE,NoticeService.NoticeType.FUND_WITHDRAW,entity,fundOrderEntity.getOrderAmount(),fundOrderEntity.getChargeAmount());
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
     * 批量代付
     */
    @Override
    public FundOrderEntity withdrawApplyNew(String accNo,String custID, Integer businessType, String contractNo, BigDecimal amount, Long busiId,int selletType,Integer newOrderType,Integer tradeType) throws FssException {
    	FundOrderEntity fundOrderEntity=null;
    	if(accNo!=null &&!"".equals(accNo)){//账号不为空
    		FssAccountEntity fssAccountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
    	      if (fssAccountEntity == null){
    	           throw new CommandParmException("90004006");
    	       }

            businessType = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(fssAccountEntity.getAccType());
            custID=String.valueOf(fssAccountEntity.getCustId());
    	}
    	FundAccountEntity entity = this.getFundAccount(Integer.valueOf(custID).intValue(), businessType);
        checkwithholdingOrWithDraw(entity,2);
        this.cashWithSetReq(entity.getCustId(),selletType);
        String lendNo=null;
        String loanNo=null;
        if(businessType==1){//借款账户
            loanNo= contractNo;//投标，满标，回款，等等对应借款合同编号
        }else if(businessType==2){//线下出借账户
            lendNo=contractNo;
        }else if(businessType==96){//应付账户（出借）
            lendNo=contractNo;
        }
        fundOrderEntity = paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_AGENT_WITHDRAW,busiId,GlobalConstants.BUSINESS_WITHHOLDING,String.valueOf(newOrderType),String.valueOf(tradeType),lendNo,loanNo);
        //资金处理
        tradeRecordService.withdrawByFroze(entity,amount,fundOrderEntity,2003);
        return fundOrderEntity;
    }



    /**
	 * 充值提现金额变动通知
	 *
	 * @param noticeType
	 * @param entity
	 * @param amount
	 */
    public void sendNotice(String tempCode,NoticeService.NoticeType noticeType, FundAccountEntity entity, BigDecimal amount,BigDecimal chargeAmount) {
		List<Map<String, String>> noticeList = new ArrayList<Map<String, String>>();
		Map<String, String> noticeMap = new HashMap<String, String>();
		noticeMap.put("sysCode", CoreConstants.SYS_CODE);// 商户系统编码，在平台系统查看
		noticeList.add(noticeMap);
        if(chargeAmount == null){
            chargeAmount = BigDecimal.ZERO;
        }
        if(entity.getBusiType() != 3) return;
		Date date = new Date();
		//String dateFormat = String.format("%tF",date)+" "+String.format("%t",date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		noticeService.packSendNotice(noticeList,tempCode,CoreConstants.SMS_NOTICE,noticeType, entity.getUserId().intValue(), entity.getCustId().intValue(),df.format(date), amount.toPlainString(),chargeAmount.toPlainString());
		HttpClientUtil.sendMsgOrNotice(noticeList, CoreConstants.SMS_NOTICE);
	}

    /**
     * 线下充值
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param cust_id
     * @param cust_type
     * @param busi_no
     * @param amt
     * @return
     * @throws FssException
     */
        public OfflineRechargeResponse OfflineRechargeApply(String mchn,String seq_no,String trade_type,String cust_id,String cust_type,String busi_no,BigDecimal amt) throws FssException{
        OfflineRechargeResponse offlineRechargeResponse=new OfflineRechargeResponse();
        FundAccountEntity primaryAccount = this.getFundAccount(Integer.parseInt(cust_id),Integer.valueOf(cust_type));
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("90004009");
        }
       //创建充值记录信息
        FssOfflineRechargeEntity  fssOfflineRechargeEntity=fssOfflineRechargeService.createOfflineRecharge("1103", primaryAccount.getCustId(), primaryAccount.getCustName(),cust_type,amt,trade_type,seq_no,mchn,busi_no);
        CommandResponse response = paySuperByFuiou.offlineRecharge(primaryAccount,amt,GlobalConstants.ORDER_CHARGE,fssOfflineRechargeEntity.getId(),0);
        //根据返回码判断是否成功，修改线下充值记录状态
        if("0000".equals(response.getCode())){//成功
            fssOfflineRechargeService.updateSuccess(fssOfflineRechargeEntity.getId(),response.getMap().get("fy_acc_no"),response.getMap().get("fy_acc_nm"),response.getMap().get("fy_bank"),response.getMap().get("fy_bank_branch"),response.getMap().get("chg_cd"),response.getMap().get("chg_dt"),amt,response.getFundOrderEntity().getOrderNo());
            offlineRechargeResponse.setChg_cd(String.valueOf(response.getMap().get("chg_cd")));
            offlineRechargeResponse.setAmt(amt);
            offlineRechargeResponse.setChg_dt(String.valueOf(response.getMap().get("chg_dt")));
            offlineRechargeResponse.setFy_acc_nm(String.valueOf(response.getMap().get("fy_acc_nm")));
            offlineRechargeResponse.setFy_acc_no(String.valueOf(response.getMap().get("fy_acc_no")));
            offlineRechargeResponse.setFy_bank(String.valueOf(response.getMap().get("fy_bank")));
            offlineRechargeResponse.setFy_bank_branch(String.valueOf(response.getMap().get("fy_bank_branch")));
        }else{//失败
            fssOfflineRechargeService.updateFiled(fssOfflineRechargeEntity.getId(),response.getFundOrderEntity().getOrderNo());
        }
        return offlineRechargeResponse;
    }

    /**
     * 个人账户之间转账
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param bid_id
     * @param busi_bid_no
     * @param tender_no
     * @param cust_no
     * @param busi_no
     * @param amt
     * @param o_tender_no
     * @param o_cust_no
     * @param o_busi_no
     * @param acc_type
     * @param to_acc_type
     * @param fundType
     * @param actionType
     * @return
     * @throws FssException
     */
    /*public boolean personalTransfer(String mchn,String seq_no,String trade_type,String bid_id,String busi_bid_no,String tender_no,String cust_no,String busi_no,BigDecimal amt,String o_tender_no,String o_cust_no,String o_busi_no,Integer acc_type,Integer to_acc_type,Integer fundType,Integer actionType) throws FssException {
        if(!o_cust_no.equals(cust_no)){
            throw new FssException("");
        }
        FundAccountEntity  fromEntity = this.getFundAccount(Integer.valueOf(cust_no),acc_type);//转出账户
        FundAccountEntity  toEntity = this.getFundAccount(Integer.valueOf(o_cust_no),to_acc_type);//转入账户

        //债权转让的添加债权转让记账信息
        FssBondTransferEntity bondEntity = fssBondTransferService.createBondTransferInfo(mchn,seq_no,trade_type,bid_id,busi_bid_no,cust_no,o_cust_no,busi_no,fromEntity.getCustName(),fromEntity.getAccountNo(),acc_type,to_acc_type,tender_no,o_tender_no,o_busi_no);
        String tradeState=bondEntity.getTradeState();
        FundOrderEntity fundOrderEntity=null;
        try {
            this.hasEnoughBanlance(fromEntity, amt);
            //第三方交易
//            fundOrderEntity = this.paySuperByFuiou.transerer(fromEntity,toEntity,amt,8,bondEntity.getId(),GlobalConstants.ORDER_DEBT,trade_type.substring(0,4),trade_type,busi_no,o_tender_no,Long.valueOf(cust_no),busi_bid_no);
            //资金处理
            tradeRecordService.transfer(fromEntity,toEntity,amt,fundType,fundOrderEntity,actionType);
            fssBondTransferService.updateBandTransfer(bondEntity,amt,fundOrderEntity.getOrderNo(),"10080002","0000");
        }catch (Exception e){
            fssBondTransferService.updateBandTransfer(bondEntity,amt,null,"10080010",e.getMessage());
            throw new FssException(e.getMessage());
        }
        return true;
    }*/
}