package com.gqhmt.fss.logicService.pay.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.logicService.pay.IFundsTrade;
import com.gqhmt.fss.logicService.pay.PaySuperByFuiou;
import com.gqhmt.fss.pay.core.PayCommondConstants;
import com.gqhmt.fss.pay.core.factory.ConfigFactory;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 交易相关api
 * @author lijunlong
 *
 */
public class FundsTradeImpl extends AccountAbstractCommand implements IFundsTrade {


    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    /**
     * 生成web充值提现订单
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    交易金额
     * @param chargeAmount              交易手续费
     * @param type                      交易类型 1.充值；2.提现
     * @return
     * @throws FssException
     */
    @Override
    public String webOrderNo(String thirdPartyType, int custID, BigDecimal amount, BigDecimal chargeAmount, int type) throws FssException {

        if(type != 1 || type != 2){
            throw new CommandParmException("交易类型错误");
        }
        FundAccountEntity entity = super.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        if(type == 2){
            super.hasEnoughBanlance(entity,amount.add(chargeAmount));
        }

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity,amount,type==1?GlobalConstants.ORDER_CHARGE:GlobalConstants.ORDER_WITHDRAW,0,0,thirdPartyType);
        return fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
    }


    /**
     * 线上代扣充值
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    充值金额
     * @param sourceType                充值来源  1，web端，2wap端，3手机app
     * @return
     */
    @Override
    public boolean withholding(String thirdPartyType, int custID, BigDecimal amount, int sourceType) throws FssException {
        FundAccountEntity primaryAccount = super.getPrimaryAccount(custID);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许代扣");
        }
        FundAccountEntity entity = super.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        return true;
    }

    @Override
    public boolean withdraw(String thirdPartyType, int custID, BigDecimal amount, BigDecimal chargeAmount, int sourceType) throws FssException {
        FundAccountEntity primaryAccount = super.getPrimaryAccount(custID);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许提现");
        }
        FundAccountEntity entity = super.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        super.hasEnoughBanlance(entity,amount.add(chargeAmount));
        paySuperByFuiou.withdraw(entity,amount,chargeAmount,0,0l,0);
        //资金处理
        return true;
    }

    @Override
    public boolean withholdingApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long bid) throws FssException {
        FundAccountEntity entity = super.getFundAccount(custID, businessType);
        checkwithholdingOrWithDraw(entity,1,businessType);
        paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        return true;
    }

    @Override
    public boolean withdrawApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long busiId) throws FssException {
        FundAccountEntity entity = super.getFundAccount(custID, businessType);
        super.hasEnoughBanlance(entity,amount);
        checkwithholdingOrWithDraw(entity,2,businessType);
        paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_WITHHOLDING,busiId,GlobalConstants.BUSINESS_WITHHOLDING);
        //资金处理
        return true;
    }

    @Override
    public boolean transefer(String thirdPartyType, Integer fromCusID, Integer fromType, Integer toCusID, Integer toType, BigDecimal amount, Integer orderType, Long busiId, int busiType) throws FssException {
        FundAccountEntity fromEntity = super.getFundAccount(fromCusID, fromType);
        super.hasEnoughBanlance(fromEntity,amount);
        FundAccountEntity toEntity = super.getFundAccount(toCusID, toType);

        paySuperByFuiou.transerer(fromEntity,toEntity,amount,orderType,busiId,busiType);
        //资金处理
        return true;
    }


    /**
     * 数据校验
     * @param entity
     * @param type
     * @throws FssException
     */
    private void checkwithholdingOrWithDraw(FundAccountEntity entity,int type,int busiType) throws FssException{
        FundAccountEntity primaryAccount = entity == null || entity.getBusiType() !=0? super.getPrimaryAccount(entity.getCustId()):entity;
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许"+(type == 1?"代扣":"提现"));
        }
        //提现次数限制
        if (busiType==GlobalConstants.ACCOUNT_TYPE_LEND_ON){
            boolean checkWithdraw = super.fundOrderService.checkWithdrawNumber(entity.getId());
            if(checkWithdraw){
                throw new CommandParmException("今日提现超过3次，请于明日提现");
            }
        }
    }
}
