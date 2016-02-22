package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.OrderWithdrawApplyDto;
import com.gqhmt.extServInter.dto.trade.OrderWithholdApplyDto;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.extServInter.dto.trade.WithholdDto;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.IFundsTrade;
import com.gqhmt.pay.service.PaySuperByFuiouTest;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.ThirdPartyType;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 交易相关api
 * @author lijunlong
 *
 */
@Service
public class FundsTradeImpl  implements IFundsTrade {


    @Resource
    private PaySuperByFuiouTest paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundOrderService fundOrderService;
    /**
     * 生成web提现订单
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    交易金额
     * @param chargeAmount              交易手续费
     * @param type                      交易类型 1.充值；2.提现
     * @return
     * @throws FssException
     */
    @Override
    public String webOrderNoWithdrawApply(OrderWithdrawApplyDto orderWithdrawApplyDto) throws FssException {

        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(orderWithdrawApplyDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
         this.hasEnoughBanlance(entity,orderWithdrawApplyDto.getAmount().add(orderWithdrawApplyDto.getProcedure_fee()));

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity,orderWithdrawApplyDto.getAmount(),2,0,0,"2");
        return fundOrderEntity.getOrderNo()+":"+ ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU).getValue("public.mchnt_cd.value")+":等待回调通知";
    }
    /**
     * 生成web代扣订单
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    交易金额
     * @param type                      交易类型 1.充值；2.提现
     * @return
     * @throws FssException
     */
    @Override
    public String webOrderNoWithholdApply(OrderWithholdApplyDto orderWithholdApplyDto) throws FssException {
    	
        FundAccountEntity entity = this.getFundAccount(Integer.parseInt(orderWithholdApplyDto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        
        this.hasEnoughBanlance(entity,orderWithholdApplyDto.getAmount());

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(entity,orderWithholdApplyDto.getAmount(),1,0,0,"2");
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
        paySuperByFuiou.withdraw(entity,withdrawDto.getAmount(),withdrawDto.getProcedure_fee(),0,0l,0);
        //资金处理
        return true;
    }
    /**
     * 代扣申请
     */
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
