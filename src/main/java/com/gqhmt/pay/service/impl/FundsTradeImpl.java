package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.trade.RechargeOrderDto;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.extServInter.dto.trade.WithdrawOrderDto;
import com.gqhmt.extServInter.dto.trade.WithholdDto;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    /**
     * 生成web提现订单
     * @param withdrawOrderDto            支付渠道
     * @return
     * @throws FssException
     */
    @Override
    public String webWithdrawOrder(WithdrawOrderDto withdrawOrderDto) throws FssException {
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withdrawOrderDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.hasEnoughBanlance(entity, withdrawOrderDto.getAmount().add(withdrawOrderDto.getProcedure_fee()));
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity, withdrawOrderDto.getAmount(),2,0,0,"2");
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
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity, rechargeOrderDto.getAmount(),1,0,0,"2");
        return fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
    }


    /**
     * 线上代扣充值
     * @param withholdDto
     * @return
     */
    @Override
    public boolean withholding(WithholdDto withholdDto) throws FssException {
        FundAccountEntity primaryAccount = this.getPrimaryAccount(Integer.parseInt(withholdDto.getCust_no()));
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("90004009");
        }
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withholdDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,withholdDto.getAmount(),GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        tradeRecordService.recharge(entity,withholdDto.getAmount(),fundOrderEntity,1002);
        return true;
    }
    
    /**
     * 提现
     */
    @Override
    public boolean withdraw(WithdrawDto withdrawDto) throws FssException {
        FundAccountEntity primaryAccount = this.getPrimaryAccount(Integer.parseInt(withdrawDto.getCust_no()));
        if (primaryAccount.getIshangeBankCard()==1){
            throw new CommandParmException("90004004");
        }
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(withdrawDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        this.hasEnoughBanlance(entity,withdrawDto.getAmount().add(withdrawDto.getProcedure_fee()));
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(entity,withdrawDto.getAmount(),withdrawDto.getProcedure_fee(),0,0l,0);
        //资金处理
        tradeRecordService.withdraw(entity,withdrawDto.getAmount(),fundOrderEntity,1003);
        return true;
    }
    /**
     * 代扣申请
     */
    @Override
    public boolean withholdingApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long bid) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        checkwithholdingOrWithDraw(entity,1,businessType);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withholding(entity,amount,GlobalConstants.ORDER_CHARGE,0,0);
        //资金处理
        tradeRecordService.recharge(entity,amount,fundOrderEntity,1002);
        return true;
    }

    @Override
    public boolean withdrawApply(String thirdPartyType, int custID, int businessType, String contractNo, BigDecimal amount, Long busiId) throws FssException {
        FundAccountEntity entity = this.getFundAccount(custID, businessType);
        this.hasEnoughBanlance(entity,amount);
        checkwithholdingOrWithDraw(entity,2,businessType);
        FundOrderEntity fundOrderEntity = paySuperByFuiou.withdraw(entity,amount,BigDecimal.ZERO,GlobalConstants.ORDER_WITHHOLDING,busiId,GlobalConstants.BUSINESS_WITHHOLDING);
        //资金处理
        tradeRecordService.withdrawByFroze(entity,amount,fundOrderEntity,2003);
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
                throw new CommandParmException("90004005");
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
        FundAccountEntity primaryAccount = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        if(primaryAccount == null){
            throw new CommandParmException("90004008");
        }
        return primaryAccount;
    }
}
