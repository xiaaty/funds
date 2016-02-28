package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Filename:    com.gqhmt.pay.service.PaySuperByFuiouTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/16 10:16
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/16  于泳      1.0     1.0 Version
 */
@Service
public class PaySuperByFuiou {


    @Resource
    private FundOrderService fundOrderService;

    private final String thirdPartyType = PayCommondConstants.PAY_CHANNEL_FUIOU;

    /*=========================================账户相关第三方调用===================================================*/
    /**
     * 创建第三方账户
     * @param primaryAccount
     * @return
     */
    public boolean createAccountByPersonal(FundAccountEntity primaryAccount,String pwd,String taradPwd) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人开户:"+primaryAccount.getAccountNo());
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount, BigDecimal.ZERO, GlobalConstants.ORDER_CREATE_ACCOUNT,0,0,thirdPartyType);
        CommandResponse response  = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_PRIVATE_CREATE,fundOrderEntity,primaryAccount,pwd,taradPwd);
        return execExction(response,fundOrderEntity);
    }

    /**
     * 销户确认，客户已经发起销户申请，才可以完成销户确认，客户可以在富友发起申请，或者客户致电客服发起销户申请
     *
     * @param primaryAccount 账户
     * @param type  业务类型;   1:用户申请注销,   2:商户确认用户注销申请通过
     * @throws FssException
     */
    public boolean dropAccount(final FundAccountEntity primaryAccount,String type)
            throws FssException {
        LogUtil.info(this.getClass(),"第三方个人销户:"+primaryAccount.getAccountNo()+":"+type);
        if ("1".equals(type) && "2".equals(type)){
            throw new CommandParmException("类型错误");
        }
        //订单号
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_DROP_USER,0,0,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_DROP_ACCOUNT_APPLY, fundOrderEntity, primaryAccount,type);
        return execExction(response,fundOrderEntity);
    }

    /**
     * 修改富友短信发送规则 1:不发送,0:发送
     * @param primaryAccount            账户
     * @param cztx                      充值提现
     * @param cz                        出账
     * @param rz                        入账
     * @param hz                        汇总
     */
    public boolean setMms(FundAccountEntity primaryAccount,String cztx,String cz ,String rz,String hz) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人短信设置:"+primaryAccount.getAccountNo()+":"+cztx+":"+cz+":"+rz+":"+hz);
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_SET_FUIOU_MMS,0,0,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_MMS, fundOrderEntity, primaryAccount,cztx,cz,rz,hz);
        return execExction(response,fundOrderEntity);
    }

    /**
     * 银行卡变更
     * @param primaryAccount
     * @param cardNo
     * @param bankCd
     * @param bankNm
     * @param cityId
     * @param fileName
     * @param bankNm 
     * @return
     * @throws FssException
     */
    public boolean changeCard(FundAccountEntity primaryAccount,String cardNo, String bankCd, String bankNm,
                                String cityId, String fileName) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人提现规则设置:"+primaryAccount.getAccountNo()+":"+cardNo+":"+bankCd+":"+bankNm+":"+cityId+":"+fileName);
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD,0,0,thirdPartyType);
        CommandResponse response =ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_CARD, fundOrderEntity, primaryAccount,cardNo,bankNm,bankCd,cityId,fileName);
        return execExction(response,fundOrderEntity);

    }

    /**
     * 银行卡变更结果查询
     * @param primaryAccount
     * @param orderNo
     * @param busiId
     * @return
     * @throws FssException
     */
    public boolean changeCardResult(FundAccountEntity primaryAccount,String  orderNo,Long busiId) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人提现规则设置:"+primaryAccount.getAccountNo()+":"+orderNo+":"+busiId);
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD_QUERY,busiId,GlobalConstants.BUSINESS_UPDATE_CARE,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_QUERYCARD, fundOrderEntity, primaryAccount,primaryAccount.getUserName(),orderNo);
        return execExction(response,fundOrderEntity);
    }
    /*=============================================账户结束==============================================*/



    /*=============================================充   值==============================================*/

    public FundOrderEntity withholding(FundAccountEntity entity,BigDecimal amount,int orderType,long busiId,int  busiType) throws FssException {
        LogUtil.info(this.getClass(),"第三方充值:"+entity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,amount,orderType,busiId,busiType,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, entity, amount,"充值 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }

    /*=============================================充值结束==============================================*/


    /*=============================================提   现==============================================*/
    public FundOrderEntity withdraw(FundAccountEntity entity,BigDecimal amount,BigDecimal chargeAmount,int orderType,Long busiId,int busiType) throws FssException {
        LogUtil.info(this.getClass(),"第三方充值:"+entity.getAccountNo()+":"+amount+":"+chargeAmount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = fundOrderService.createOrder(entity,null,amount,chargeAmount,orderType,busiId,busiType,thirdPartyType);
        //提现手续费记录
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, entity,amount,"提现 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }
    /*=============================================提现结束==============================================*/



    /*=============================================转   账==============================================*/
    public FundOrderEntity transerer(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int orderType,Long busiId,int busiType) throws FssException {
        LogUtil.info(this.getClass(),"第三方转账:"+fromEntity.getAccountNo()+":"+toEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity,toEntity,amount,orderType,busiId,busiType,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_TRANSFER, fundOrderEntity, fromEntity,toEntity,amount);
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }

    /*=============================================转账结束==============================================*/

    /*=============================================预  授 权==============================================*/

    public CommandResponse preAuth(FundAccountEntity fromEntity,FundAccountEntity toSFEntity,BigDecimal amount,int  orderType,Long busiId,int  busiType) throws FssException {
        LogUtil.info(this.getClass(),"第三方预授权:"+fromEntity.getAccountNo()+":"+toSFEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity, amount, orderType, busiId,busiType, thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_INVEST_BID, fundOrderEntity, fromEntity, String.valueOf(busiId), amount, "投标预授权", toSFEntity);
        execExction(response,fundOrderEntity);
        response.setFundOrderEntity(fundOrderEntity);
        return response;
    }

    public boolean canclePreAuth(FundAccountEntity fromEntity,FundAccountEntity toSFEntity,BigDecimal amount,int orderType,Long busiId,int busiType,String contactNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方预授权:"+fromEntity.getAccountNo()+":"+toSFEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity, amount, orderType, busiId, busiType, thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_INVEST_BID_CANCLE, fundOrderEntity, fromEntity, String.valueOf(busiId), amount, contactNo, toSFEntity);
        return execExction(response,fundOrderEntity);
    }

    /*=============================================预授权结束==============================================*/

    /*=============================================收   费==============================================*/

    private void chargeAmount(FundAccountEntity entity,FundAccountEntity toEntity,BigDecimal chargeAmount) throws CommandParmException, FssException {
        if(chargeAmount == null || BigDecimal.ZERO.compareTo(chargeAmount)>=0){
            return;
        }

        //this.unfreezeByThird(entity.getCustId(),fundOrderEntity.getChargeAmount());
        FundOrderEntity fundOrderEntityCharge =this.createOrder(entity,chargeAmount,GlobalConstants.ORDER_WITHDRAW_CHARGE_AMOUNT,0,0,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_MMS, fundOrderEntityCharge, entity.getUserName(),toEntity.getUserName(),chargeAmount,"收取账户手续费 "+chargeAmount+"元");
        execExction(response,fundOrderEntityCharge);
    }

     /**
     * 设置提现 时效方法
     * @param primaryAccount
     * @param cashWithSet
     */

    public boolean cashWithSetReq(FundAccountEntity primaryAccount ,int cashWithSet) throws FssException {
        if( primaryAccount.getSettleType() == null){
            primaryAccount.setSettleType(0);
        }
        if(  cashWithSet == primaryAccount.getSettleType()){
            return false;
        }
        //订单号
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD,0,0,thirdPartyType);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_CASHWITHSET, fundOrderEntity, primaryAccount,String.valueOf(cashWithSet));
        return execExction(response,fundOrderEntity);
    }

    /*=============================================收费结束==============================================*/


    /*=============================================余 额 查 询============================================*/
    /**
     * 富友账户余额查询
     * @param primaryAccount
     * @return
     */
    private CommandResponse banlance(FundAccountEntity primaryAccount ) throws FssException {
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_BALANCE,0,0,thirdPartyType);
        Date date = new Date();
        DateFormat df =new SimpleDateFormat("yyyyMMdd");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_BALANCE, fundOrderEntity,primaryAccount.getUserName(),df.format(date));
        execExction(response,fundOrderEntity);
        return response;
    }
    /*=============================================余额查询结束============================================*/




    /**
     * 解析返回码
     * @param response
     * @param fundOrderEntity
     * @throws CommandParmException
     * @throws FssException
     */
    private boolean  execExction(CommandResponse response,FundOrderEntity fundOrderEntity) throws  CommandParmException, FssException {
        LogUtil.info(this.getClass(), "第三方执行完成返回:"+fundOrderEntity.getOrderNo()+":"+fundOrderEntity.getAccountId()+":"+response.getCode());

        switch (response.getCode()){
            case "0000":
                this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_SUCCESS,response.getThirdReturnCode(),response.getMsg());
                return true;
            case "90007001":
                throw new FssException("90007001:"+fundOrderEntity.getOrderNo()+":验证码已发送");
            case "90007002":
                throw new FssException("90007002:"+fundOrderEntity.getOrderNo()+":等待回调通知");
            case "90007009":
                this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_THIRDERROR,response.getThirdReturnCode(),response.getMsg());
                throw new ThirdpartyErrorAsyncException("90007009");
            default:
                this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_FAILED,response.getThirdReturnCode(),response.getMsg());
                throw new CommandParmException(toLocalCode(response.getThirdReturnCode()));
        }
    }


    private String toLocalCode(String code){
        if(code == null )  return "90099999";
        if(code.length() == 4){
            return "9100"+code;
        }else if(code.length() == 5){
            return "910"+code;
        }else if(code.length() == 4){
            return "91"+code;
        }

        return "90099999";

    }

    /**
     * 创建订单
     * @param primaryAccount
     * @param amount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @param thirdPartyType
     * @return
     * @throws CommandParmException
     */

    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount, final BigDecimal amount, final int orderType, final long sourceID, final Integer sourceType,final String thirdPartyType) throws CommandParmException, FssException {
        return this.createOrder(primaryAccount, null, amount, orderType, sourceID, sourceType, thirdPartyType);
    }

    private final FundOrderEntity createOrder(final FundAccountEntity primaryAccount,final  FundAccountEntity toAccountEntity,final BigDecimal amount,final int orderType,final long sourceID, final int sourceType, final String thirdPartyType) throws CommandParmException, FssException {
        return fundOrderService.createOrder(primaryAccount,toAccountEntity,amount,BigDecimal.ZERO,orderType,sourceID,sourceType,thirdPartyType);
    }

    /**
     * 修改订单状态
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws CommandParmException
     */
    private final void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws CommandParmException {
        fundOrderEntity.setOrderState(status);
        fundOrderEntity.setRetCode(code);
        fundOrderEntity.setRetMessage(msg);
        try {
            fundOrderService.update(fundOrderEntity);
        } catch (Exception e) {
            throw new CommandParmException(e.getMessage(),e);
        }
    }
}
