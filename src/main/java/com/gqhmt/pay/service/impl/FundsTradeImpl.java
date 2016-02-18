package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.IFundsTrade;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 交易相关api
 * @author lijunlong
 *
 */
public class FundsTradeImpl  implements IFundsTrade {


    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundOrderService fundOrderService;
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
        FundAccountEntity entity = this.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        if(type == 2){
            this.hasEnoughBanlance(entity,amount.add(chargeAmount));
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
        FundAccountEntity primaryAccount = this.getPrimaryAccount(custID);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许代扣");
        }
        FundAccountEntity entity = this.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        tradeRecordService.recharge(entity,amount,fundOrderEntity,1002);
        return true;
    }

    @Override
    public boolean withdraw(String thirdPartyType, int custID, BigDecimal amount, BigDecimal chargeAmount, int sourceType) throws FssException {
        FundAccountEntity primaryAccount = this.getPrimaryAccount(custID);
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许提现");
        }
        FundAccountEntity entity = this.getFundAccount(custID, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.hasEnoughBanlance(entity,amount.add(chargeAmount));
        paySuperByFuiou.withdraw(entity,amount,chargeAmount,0,0l,0);
        //资金处理
        return true;
    }

    @Override
    public boolean withholdingApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long bid) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        checkwithholdingOrWithDraw(entity,1,businessType);
        paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        return true;
    }

    @Override
    public boolean withdrawApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long busiId) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        this.hasEnoughBanlance(entity,amount);
        checkwithholdingOrWithDraw(entity,2,businessType);
        paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_WITHHOLDING,busiId,GlobalConstants.BUSINESS_WITHHOLDING);
        //资金处理
        return true;
    }

    @Override
    public boolean transefer(String thirdPartyType, Integer fromCusID, Integer fromType, Integer toCusID, Integer toType, BigDecimal amount, Integer orderType, Long busiId, int busiType) throws FssException {
        FundAccountEntity fromEntity = this.getFundAccount(fromCusID, fromType);
        this.hasEnoughBanlance(fromEntity,amount);
        FundAccountEntity toEntity = this.getFundAccount(toCusID, toType);

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
        FundAccountEntity primaryAccount = entity == null || entity.getBusiType() !=0? this.getPrimaryAccount(entity.getCustId()):entity;
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("银行卡变更中,不允许"+(type == 1?"代扣":"提现"));
        }
        //提现次数限制
        if (busiType==GlobalConstants.ACCOUNT_TYPE_LEND_ON){
            boolean checkWithdraw = this.fundOrderService.checkWithdrawNumber(entity.getId());
            if(checkWithdraw){
                throw new CommandParmException("今日提现超过3次，请于明日提现");
            }
        }
    }

    private FundAccountEntity getFundAccount(int cusID, int type) throws CommandParmException {
        FundAccountEntity entity = null;
        if (cusID < 100) {
            entity = fundAccountService.getFundAccount(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        } else {
            entity = fundAccountService.getFundAccount(cusID, type);
        }

        if (entity == null) {
            throw new CommandParmException("账户不存在");
        }
        return entity;
    }

    private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
        BigDecimal bigDecimal = entity.getAmount();
        if (bigDecimal.compareTo(amount) < 0) {
            throw new CommandParmException("账户余额不足");
        }
    }

    private FundAccountEntity getPrimaryAccount(Integer cusId){
        FundAccountEntity primaryAccount = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        if(primaryAccount == null){
            throw new CommandParmException("主账户不存在");
        }
        return primaryAccount;
    }
}
