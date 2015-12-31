package com.gqhmt.fss.logicService.pay.impl;

import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.exception.ChargeAmountNotenoughException;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.logicService.pay.FundsResponse;
import com.gqhmt.fss.logicService.pay.IFundsTrade;
import com.gqhmt.fss.logicService.pay.event.trade.RechargeEvent;
import com.gqhmt.fss.logicService.pay.exception.FundsException;
import com.gqhmt.fss.pay.core.PayCommondConstants;
import com.gqhmt.fss.pay.core.command.CommandResponse;
import com.gqhmt.fss.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.fss.pay.exception.LazyDealException;
import com.gqhmt.fss.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.LogUtil;
import com.gqhmt.util.ThirdPartyType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 交易相关api
 * @author lijunlong
 *
 */
@Service
public class FundsTradeImpl extends AccountAbstractCommand implements IFundsTrade {


    @Resource
    private ApplicationContext context;

	/**
     * 生成web充值提现订单
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    交易金额
     * @param chargeAmount              交易手续费
     * @param type                      交易类型 1.充值；2.提现
     * @return
     * @throws FundsException
     */
	public FundsResponse webOrderNo(String thirdPartyType, int custID,
			BigDecimal amount, BigDecimal chargeAmount, int type) throws FundsException {
		// 充值只生成订单
		FundAccountEntity entity = super.getFundAccount(custID,type,true);
		// 订单号
		FundOrderEntity fundOrderEntity = super.createOrder(entity,amount,GlobalConstants.ORDER_CHARGE,0,0,thirdPartyType);
        context.publishEvent(new RechargeEvent(""));
        return null;
	}

    /**
     * 线上代扣充值
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    充值金额
     * @param sourceType                充值来源  1，web端，2wap端，3手机app
     * @return
     */
	public FundsResponse withholding(String thirdPartyType, int custID,
			BigDecimal amount, int sourceType) {
		FundAccountEntity primaryAccount = super.getPrimaryAccount(custID, true);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许代扣");
        }
        FundAccountEntity entity = super.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_PRIMARY, true);
        //订单号
        FundOrderEntity fundOrderEntity = super.createOrder(entity,amount,GlobalConstants.ORDER_WITHHOLDING,0,GlobalConstants.BUSINESS_WITHHOLDING,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(Integer.valueOf(thirdPartyType), PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, entity, amount,"充值 "+amount.toPlainString()+"元");
        if(response.getCode().equals("0009")){
            super.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_THIRDERROR,response.getThirdReturnCode(),response.getMsg());
            throw new ThirdpartyErrorAsyncException();
        }else if(response.getCode().equals("0002") || response.getCode().equals("0001")){
            throw new LazyDealException(fundOrderEntity.getOrderNo()+":等待回调通知");
        }else if(!"0000".equals(response.getCode())){
            super.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_FAILED,response.getCode(),response.getMsg());
            throw  new CommandParmException(response.getMsg());
        }
        try {
            //第三方操作成功，本地流水增加充值成功记录
//            Map<Integer, Long> mapping = new HashMap<>();
//            mapping.put(GlobalConstants.BUSINESS_MAPPINF_CUSTOMER, Long.valueOf(custID));
            //交易记录
            sequenceService.charge(entity, 1002, amount, thirdPartyType, fundOrderEntity);
            super.createFundTrade(entity, amount, BigDecimal.ZERO, 1002, "代扣成功，充值金额 " + amount + "元");
//            super.sendNotice(NoticeService.NoticeType.FUND_WITHHOLDING, entity, amount);
            super.updateOrder(fundOrderEntity, GlobalConstants.ORDER_STATUS_SUCCESS, response.getCode(), response.getMsg());

//            super.callback(fundOrderEntity,1,"成功",objects);
//            if(entity.getBusiType().intValue() == GlobalConstants.ACCOUNT_TYPE_LEND_ON) {
//                promoteService.saveFirstRecharge(entity.getCustId(), entity.getUserId());
//            }
        }catch (RuntimeException e){
//            super.callback(fundOrderEntity,2,"第三方成功，本地处理失败",objects);
        } catch (ChargeAmountNotenoughException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     *线上提现，目前已直连富友代付接口，未来改为异步，存入数据库，定时跑批提现
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    提现金额
     * @param chargeAmount              手续费
     * @param sourceType                来源1，web端，2wap端，3手机app
     * @return
     * @throws FundsException
     */
	public FundsResponse withdraw(String thirdPartyType, int custID,
			BigDecimal amount, BigDecimal chargeAmount, int sourceType) throws FundsException {
		//获取主账户信息，主账户与打钱账户关联，
        FundAccountEntity primaryAccount =super.getPrimaryAccount(custID, true);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许提现");
        }
        int type = 0;
        //来源账户，本地实际出账账户
        FundAccountEntity entity = super.getFundAccount(custID,type,false);
        if(type == 3){
            boolean checkWithdraw = super.fundOrderService.checkWithdrawNumber(entity.getId());
            if(checkWithdraw){
                throw new CommandParmException("今日提现超过3次，请于明日提现");
            }
        }
        this.cashWithSetReq(custID,1);           //线上客户实时提现，T+1 到账
        //去掉账户余额查询和解冻操作20150829 于泳 直接将提现金额发给富友
        FundOrderEntity fundOrderEntity = super.createOrder(entity,null,amount,chargeAmount,GlobalConstants.ORDER_WITHDRAW,0,GlobalConstants.BUSINESS_WITHDRAW,thirdPartyType);
        //提现手续费记录
        if(chargeAmount.compareTo(BigDecimal.ZERO)>0){
            fundWithrawChargeService.add(fundOrderEntity.getOrderNo(),entity,amount,chargeAmount);
        }
        //发送到富友
        CommandResponse response = ThirdpartyFactory.command(Integer.valueOf(thirdPartyType), PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, primaryAccount,amount,"提现 "+amount.toPlainString()+"元");
        if(response.getCode().equals("0001")){
            throw new FundsException(fundOrderEntity.getOrderNo()+":验证码已发送");
        }else if(response.getCode().equals("0002")){
            throw new LazyDealException(fundOrderEntity.getOrderNo()+":等待回调通知");
        }else if(!"0000".equals(response.getCode())){
            super.updateOrder(fundOrderEntity,3,response.getCode(),response.getMsg());
            throw  new CommandParmException(response.getMsg());
        }

        super.updateOrder(fundOrderEntity, 2, response.getCode(), response.getMsg());
//        //提现
        sequenceService.refund(entity,1012,amount,thirdPartyType,fundOrderEntity,0l);
        super.createFundTrade(entity, BigDecimal.ZERO, amount, 1012, "提现成功");
//        super.sendNotice(NoticeService.NoticeType.FUND_WITHDRAW,entity,amount);

        try {
            this.chargeAmount(entity.getUserName(), fundOrderEntity);
        }catch (Exception e){
            LogUtil.debug(this.getClass(),e.getMessage(),e);
        }
//        super.fundWithrawChargeService.updateSrate(fundOrderEntity.getOrderNo(),2);
        return null;
	}

    /**
     * 代扣申请
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param businessType              业务类型，1出借客户投资代扣；2借款客户还款代扣；3抵押权人代扣；4代偿人代扣；99其他代扣
     * @param contractNo                业务合同，出借和借款合同号，出借客户必须提供出借合同号，借款还款，必须提供借款合同号，需保证每种类型合同号唯一
     * @param amount                    代扣金额
     * @return
     * @throws FundsException
     */
	public FundsResponse withholdingApply(String thirdPartyType, int custID,
			int businessType, String contractNo, BigDecimal amount, Long bid) throws FundsException {
		return null;
	}

    /**
    *
    * 代付申请
    * @param thirdPartyType            支付渠道
    * @param custID                    客户id
    * @param businessType              业务类型1，出借赎回代付，2借款放款代付；3借款其他资金代付；4抵押权人资金代付；5代偿人资金代付；99，其他代付
    * @param contractNo
    * @param amount
    * @return
    * @throws FundsException
    */
	public FundsResponse withdrawApply(String thirdPartyType, int custID,
			int businessType, String contractNo, BigDecimal amount, Long bid) throws FundsException {
		return null;
	}

    /**
     * 费用接口
     * @param thirdPartyType           支付渠道
     * @param custID                   客户id
     * @param businessType             业务类型，1，线下出借，2借款，3债权转让
     * @param costType                 费用类型
     * @param costMode                 费用方式，0收取，1退回
     * @param contractNo
     * @param amount
     * @param bid
     * @return
     * @throws FundsException
     */
	public FundsResponse cost(String thirdPartyType, int custID,
			int businessType, int costType, int costMode, String contractNo,
			BigDecimal amount, Long bid) throws FundsException  {
		return null;
	}

    /**
    *
    * 出借端补差额
    * @param thirdPartyType            支付渠道
    * @param custID                    客户id
    * @param mode                      补偿模式0，低息补偿；1高息收回
    * @param contractNo                合同编号
    * @param amount                    补偿金额
    * @param bid
    * @return
    * @throws FundsException
    */
	public FundsResponse compensateByLend(String thirdPartyType, int custID,
			int mode, String contractNo, BigDecimal amount, Long bid) throws FundsException {
		return null;
	}


    /**
     * 借款端逾期代偿
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param costMode                  代偿模式：0，逾期代偿；1逾期还款代偿退回
     * @param contractNo
     * @param amount
     * @param bid
     * @return
     * @throws FundsException
     */
	public FundsResponse compensateByLoad(String thirdPartyType, int custID,
			int costMode, String contractNo, BigDecimal amount, Long bid) throws FundsException {
		return null;
	}
	
    /**
     * 设置提现 时效方法
     * @param cusId
     * @param cashWithSet
     */

    private void cashWithSetReq(int cusId,int cashWithSet){
        FundAccountEntity primaryAccount =super.getPrimaryAccount(cusId, true);
        if( primaryAccount.getSettleType() == null){
            primaryAccount.setSettleType(0);
        }
        if(cashWithSet == primaryAccount.getSettleType()){
            return;
        }
        //订单号
        FundOrderEntity fundOrderEntity = super.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD,0,0,String.valueOf(ThirdPartyType.FUIOU.getKey()));
        CommandResponse response = ThirdpartyFactory.command(ThirdPartyType.FUIOU.getKey(),PayCommondConstants.COMMAND_TRADE_AGENT_WITHDRAW, fundOrderEntity, primaryAccount,String.valueOf(cashWithSet));
        if(!"0000".equals(response.getCode())){
            super.updateOrder(fundOrderEntity,3,response.getCode(),response.getMsg());
            throw  new CommandParmException(response.getMsg());
        }
        primaryAccount.setSettleType(cashWithSet);
        super.updateAccount(primaryAccount);
        super.updateOrder(fundOrderEntity, 2, "0000","成功");
    }
    
    /**
     *  提现手续费收取实现方法
     * @param userName
     * @param fundOrderEntity
     * @throws CommandParmException
     */
    private void chargeAmount(String  userName,FundOrderEntity fundOrderEntity) throws CommandParmException {
        if(fundOrderEntity.getChargeAmount() == null || BigDecimal.ZERO.compareTo(fundOrderEntity.getChargeAmount())>=0){
            return;
        }
        FundAccountEntity entity = super.getFundAccount(userName,GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundAccountEntity toEntity  = super.getFundAccount(99,0,false);
        FundOrderEntity fundOrderEntityCharge = super.createOrder(entity,fundOrderEntity.getChargeAmount(),GlobalConstants.ORDER_WITHDRAW_CHARGE_AMOUNT,0,0,String.valueOf(ThirdPartyType.FUIOU.getKey()));
        CommandResponse response = ThirdpartyFactory.command(ThirdPartyType.FUIOU.getKey(), PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntityCharge, entity.getUserName(),toEntity.getUserName(),fundOrderEntity.getChargeAmount(),"收取账户手续费 "+fundOrderEntity.getOrderAmount()+"元");
        if(!"0000".equals(response.getCode())){
            super.updateOrder(fundOrderEntityCharge,3,response.getCode(),"失败");
            throw new CommandParmException("手续费收取失败");
        }
        sequenceService.transfer(entity, toEntity, fundOrderEntity.getChargeAmount(), 22,4010,String.valueOf(ThirdPartyType.FUIOU.getKey()), fundOrderEntityCharge);
        super.createFundTrade(entity, BigDecimal.ZERO,fundOrderEntity.getChargeAmount(), 4010, "收取提现手续费");
        super.fundWithrawChargeService.updateSrate(fundOrderEntity.getOrderNo(),3);
        super.updateOrder(fundOrderEntityCharge, 2, "0000","成功");
    }
    
}
