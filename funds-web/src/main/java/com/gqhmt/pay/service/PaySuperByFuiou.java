package com.gqhmt.pay.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.pay.exception.CommandParmException;
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
    public FundOrderEntity createAccountByPersonal(FundAccountEntity primaryAccount,String pwd,String taradPwd,String tradeType) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人开户:"+primaryAccount.getAccountNo());
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount, BigDecimal.ZERO, GlobalConstants.ORDER_CREATE_ACCOUNT,0,0,"",tradeType);
        CommandResponse response  = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_PRIVATE_CREATE,fundOrderEntity,primaryAccount,pwd,taradPwd);
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
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
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_DROP_USER,0,0,"","");
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
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_SET_FUIOU_MMS,0,0,"","");
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
                                String cityId, String fileName,String tradeType) throws FssException {
        LogUtil.info(this.getClass(),"银行卡变更:"+primaryAccount.getAccountNo()+":"+cardNo+":"+bankCd+":"+bankNm+":"+cityId+":"+fileName);
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD,0,0,"",tradeType);
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
    public CommandResponse changeCardResult(FundAccountEntity primaryAccount,String  orderNo,Long busiId) throws FssException {
        LogUtil.info(this.getClass(),"第三方个人提现规则设置:"+primaryAccount.getAccountNo()+":"+orderNo+":"+busiId);
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD_QUERY,busiId,GlobalConstants.BUSINESS_UPDATE_CARE,"","");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_QUERYCARD, fundOrderEntity, primaryAccount,primaryAccount.getUserName(),orderNo);
        execExction(response,fundOrderEntity);
        return response;
    }
    /*=============================================账户结束==============================================*/



    /*=============================================充   值==============================================*/

    public FundOrderEntity withholding(FundAccountEntity entity,BigDecimal amount,int orderType,long busiId,int  busiType,final String newOrderType,final String tradeType,final String lendNo,final String loanNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方充值:"+entity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,null,amount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,null,null,loanNo);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, entity, amount,"充值 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }
    public FundOrderEntity withholding(FundAccountEntity entity,BigDecimal amount,int orderType,long busiId,int  busiType,final String newOrderType,final String tradeType,final String lendNo,final String loanNo,final String orderNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方充值:"+entity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,null,amount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,null,null,loanNo,orderNo);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_WITHHOLDING, fundOrderEntity, entity, amount,"充值 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }

    /*=============================================充值结束==============================================*/


    /*=============================================提   现==============================================*/
    public FundOrderEntity  withdraw(FundAccountEntity entity,BigDecimal amount,BigDecimal chargeAmount,int orderType,Long busiId,int busiType,final String newOrderType,final String tradeType,final String lendNo,final String loanNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方提现:"+entity.getAccountNo()+":"+amount+":"+chargeAmount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = fundOrderService.createOrder(entity,null,amount,chargeAmount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,null,null,loanNo);
        //提现手续费记录
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_AGENT_WITHDRAW, fundOrderEntity, entity,amount,"提现 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }
    public FundOrderEntity  withdraw(FundAccountEntity entity,BigDecimal amount,BigDecimal chargeAmount,int orderType,Long busiId,int busiType,final String newOrderType,final String tradeType,final String lendNo,final String loanNo,final String orderNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方提现:"+entity.getAccountNo()+":"+amount+":"+chargeAmount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = fundOrderService.createOrder(entity,null,amount,chargeAmount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,null,null,loanNo,orderNo);
        //提现手续费记录
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_AGENT_WITHDRAW, fundOrderEntity, entity,amount,"提现 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }
    /*=============================================提现结束==============================================*/



    /*=============================================转   账==============================================*/
    public FundOrderEntity transerer(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int orderType,Long busiId,Integer busiType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo) throws FssException {
        CommandResponse response = this.transerer(fromEntity,toEntity,amount,orderType,busiId==null?0:busiId,busiType==null?0:busiType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo,"",null);
        execExction(response,response.getFundOrderEntity());
        return response.getFundOrderEntity();
    }

    public CommandResponse transerer(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int orderType,Long busiId,Integer busiType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo,String contractNo) throws FssException {
        CommandResponse response= this.transerer(fromEntity,toEntity,amount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo,contractNo,null);
        return response;
    }
    public FundOrderEntity transefer(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int orderType,Long busiId,Integer busiType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo,String orderNo) throws FssException {
        CommandResponse response = this.transerer(fromEntity,toEntity,amount,orderType,busiId==null?0:busiId,busiType==null?0:busiType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo,"",orderNo);
        execExction(response,response.getFundOrderEntity());
        return response.getFundOrderEntity();
    }

    public CommandResponse transerer(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int orderType,Long busiId,Integer busiType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo,String contractNo,String orderNo) throws FssException {
        LogUtil.info(this.getClass(),"第三方转账:"+fromEntity.getAccountNo()+":"+toEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity=null;
        if(orderNo==null){
            fundOrderEntity= this.createOrder(fromEntity,toEntity,amount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo);
        }else {
            fundOrderEntity = this.createOrder(fromEntity,toEntity,amount,orderType,busiId,busiType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo,orderNo);
        }

        CommandResponse response = null;
        if(fromEntity.getCustId() < 100 || toEntity.getCustId() < 100){
            response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_CHARGE_WITHDRAW, fundOrderEntity, fromEntity.getUserName(),toEntity.getUserName(),amount);
        }else {
            response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_TRADE_TRANSFER, fundOrderEntity, fromEntity, toEntity, amount,contractNo);
        }

        if("0000".equals(response.getCode())){
            this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_SUCCESS,response.getThirdReturnCode(),response.getMsg());
        }else{
            this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_FAILED,response.getThirdReturnCode(),response.getMsg());
            throw new FssException(toLocalCode(response.getThirdReturnCode()));
        }
        response.setFundOrderEntity(fundOrderEntity);

        return response;
    }

    /*=============================================转账结束==============================================*/


    /*=============================================预  授 权==============================================*/

    public CommandResponse preAuth(FundAccountEntity fromEntity,FundAccountEntity toSFEntity,BigDecimal amount,int  orderType,Long busiId,int  busiType,String tradeType,String busi_bid_no,String busi_no,String loan_cust_id) throws FssException {
        LogUtil.info(this.getClass(),"第三方预授权:"+fromEntity.getAccountNo()+":"+toSFEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType+":"+busi_bid_no+":"+busi_no+":"+loan_cust_id);
//        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity, amount, orderType, busiId,busiType,"1105",tradeType);
        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity, null, amount, orderType, busiId,busiType,"1105",tradeType,busi_no,null,loan_cust_id==null?null:Long.valueOf(loan_cust_id),busi_bid_no);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_INVEST_BID, fundOrderEntity, fromEntity, String.valueOf(busiId), amount, "投标预授权", toSFEntity);
        execExction(response,fundOrderEntity);
        response.setFundOrderEntity(fundOrderEntity);
        return response;
    }

    public FundOrderEntity canclePreAuth(FundAccountEntity fromEntity,FundAccountEntity toSFEntity,BigDecimal amount,int orderType,Long busiId,int busiType,String contactNo,String lendNo,String loanNo,Long loanCustId) throws FssException {
        LogUtil.info(this.getClass(),"第三方预授权:"+fromEntity.getAccountNo()+":"+toSFEntity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(fromEntity,null, amount, orderType, busiId, busiType,"1114","",lendNo,null,loanCustId,loanNo);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_INVEST_BID_CANCLE, fundOrderEntity, fromEntity, String.valueOf(busiId), amount, contactNo, toSFEntity);
        execExction(response,fundOrderEntity);
        return fundOrderEntity;
    }

    /*=============================================预授权结束==============================================*/

    /*=============================================收   费==============================================*/

    public FundOrderEntity chargeAmount(FundAccountEntity entity,FundAccountEntity toEntity,BigDecimal chargeAmount) throws CommandParmException, FssException {
        if(chargeAmount == null || BigDecimal.ZERO.compareTo(chargeAmount)>=0){
            return null;
        }
        //this.unfreezeByThird(entity.getCustId(),fundOrderEntity.getChargeAmount());
        FundOrderEntity fundOrderEntityCharge =this.createOrder(entity,chargeAmount,GlobalConstants.ORDER_WITHDRAW_CHARGE_AMOUNT,0,0,"","");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_CHARGE_WITHDRAW, fundOrderEntityCharge, entity.getUserName(),toEntity.getUserName(),chargeAmount,"收取账户手续费 "+chargeAmount+"元");
        execExction(response,fundOrderEntityCharge);

        return fundOrderEntityCharge;
    }
    public FundOrderEntity chargeAmount(FundAccountEntity entity,FundAccountEntity toEntity,BigDecimal chargeAmount,String orderNo) throws CommandParmException, FssException {
        if(chargeAmount == null || BigDecimal.ZERO.compareTo(chargeAmount)>=0){
            return null;
        }
        //this.unfreezeByThird(entity.getCustId(),fundOrderEntity.getChargeAmount());
        FundOrderEntity fundOrderEntityCharge =this.createOrder(entity,chargeAmount,GlobalConstants.ORDER_WITHDRAW_CHARGE_AMOUNT,0,0,"","",orderNo);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_CHARGE_WITHDRAW, fundOrderEntityCharge, entity.getUserName(),toEntity.getUserName(),chargeAmount,"收取账户手续费 "+chargeAmount+"元");
        execExction(response,fundOrderEntityCharge);

        return fundOrderEntityCharge;
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
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_UPDATE_CARD,0,0,"","");
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
    public CommandResponse banlance(FundAccountEntity primaryAccount ) throws FssException {
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_BALANCE,0,0,"","");
        Date date = new Date();
        DateFormat df =new SimpleDateFormat("yyyyMMdd");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_BALANCE, fundOrderEntity,primaryAccount.getUserName(),df.format(date));
        execExction(response,fundOrderEntity);
        return response;
    }

    /**
     * 交易查询
     * @param tradeType  PWPC 转账  PW13 预授权 PWCF 预授权撤销 PW03 划拨 PW14 转账冻结 PW15 划拨冻结  PWDJ 冻结	 PWJD 解冻PW19 冻结付款到冻结
     * @param primaryAccount
     * @param orderNo
     * @param startTime   20100101
     * @param endTime     20100101
     * * @param pageNo      起始页,默认值为1,一次最多返回100条记录
     * @return
     * @throws FssException
     */
    public CommandResponse tradeQuery(String  tradeType,FundAccountEntity primaryAccount ,String orderNo,String startTime,String endTime,Integer pageNo) throws FssException {
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_TRADE_QUERY,0,0,"","");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_TRADE, fundOrderEntity,tradeType, startTime,endTime,orderNo,primaryAccount.getUserName(),"1",null,String.valueOf(pageNo),"100");
        execExction(response,fundOrderEntity);
        return response;
    }

    /**
     * 充值提现查询
     * @param tradeType  PW11 充值  PWTX 提现
     * @param primaryAccount
     * @param startTime   起始时间  2010-01-01
     * @param endTime     结束时间  2010-01-01
     * @param pageNo      起始页,默认值为1,一次最多返回100条记录
     * @return
     * @throws FssException
     */
    public CommandResponse tradeCZZTXQuery(String  tradeType,FundAccountEntity primaryAccount ,String startTime,String endTime,Integer pageNo) throws FssException {
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_CZTX_QUERY,0,0,"","");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_CZTX, fundOrderEntity,tradeType, startTime+" 00:00:00",endTime+" 23:59:59",primaryAccount.getUserName(),"1",String.valueOf(pageNo),"100");
        execExction(response,fundOrderEntity);
        return response;
    }

    public CommandResponse userInfoQuery(FundAccountEntity primaryAccount ) throws FssException {
        FundOrderEntity fundOrderEntity = this.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_USER_INFO_QUERY,0,0,"","");
        Date date = new Date();
        DateFormat df =new SimpleDateFormat("yyyyMMdd");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_FUIOU_USER_INFO, fundOrderEntity, df.format(date),primaryAccount.getUserName());
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
                throw new FssException("90007009");
            default:
                this.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_FAILED,response.getThirdReturnCode(),response.getMsg());
                throw new FssException(toLocalCode(response.getThirdReturnCode()));
        }
    }


    private String toLocalCode(String code){
        if(code == null )  return "90099999";
        if(code.length() == 4){
            return "9100"+code;
        }else if(code.length() == 5){
            return "910"+code;
        }else if(code.length() == 6){
            return "91"+code;
        }

        return "90099999";

    }

    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount, final BigDecimal amount,final BigDecimal chargeAmount, final int orderType, final long sourceID, final Integer sourceType,final String newOrderType,String tradeType) throws CommandParmException, FssException {
        return fundOrderService.createOrder(primaryAccount,null,amount,chargeAmount,orderType,sourceID,sourceType,newOrderType,tradeType,null,null,null,null);

    }
    /**
     * 创建订单
     * @param primaryAccount
     * @param amount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @param newOrderType
     * @param tradeType
     * @return
     * @throws CommandParmException
     * @throws FssException
     */
    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount, final BigDecimal amount, final int orderType, final long sourceID, final Integer sourceType,final String newOrderType,String tradeType) throws CommandParmException, FssException {
        return this.createOrder(primaryAccount, null, amount, orderType, sourceID, sourceType,newOrderType,tradeType,null,null,null,null);
    }
    /**
     * 创建订单
     * @param primaryAccount
     * @param amount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @param newOrderType
     * @param tradeType
     * @return
     * @throws CommandParmException
     * @throws FssException
     */
    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount, final BigDecimal amount, final int orderType, final long sourceID, final Integer sourceType,final String newOrderType,String tradeType,String orderNo) throws CommandParmException, FssException {
        return this.createOrder(primaryAccount, null, amount, orderType, sourceID, sourceType,newOrderType,tradeType,null,null,null,null,orderNo);
    }
    /**
     * 创建订单
     * @param primaryAccount
     * @param toAccountEntity
     * @param amount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @param newOrderType
     * @param tradeType
     * @param lendNo
     * @param toLendNo
     * @param loanCustId
     * @param loanNo
     * @return
     * @throws CommandParmException
     * @throws FssException
     */
    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount,final  FundAccountEntity toAccountEntity,final BigDecimal amount,final int orderType,final Long sourceID, final Integer sourceType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo) throws CommandParmException, FssException {
        return fundOrderService.createOrder(primaryAccount,toAccountEntity,amount,BigDecimal.ZERO,orderType,sourceID,sourceType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo);
    }
    public final FundOrderEntity createOrder(final FundAccountEntity primaryAccount,final  FundAccountEntity toAccountEntity,final BigDecimal amount,final int orderType,final Long sourceID, final Integer sourceType,final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo,String orderNo) throws CommandParmException, FssException {
        return fundOrderService.createOrder(primaryAccount,toAccountEntity,amount,BigDecimal.ZERO,orderType,sourceID,sourceType,newOrderType,tradeType,lendNo,toLendNo,loanCustId,loanNo,orderNo);
    }

    /**
     * 修改订单状态
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws CommandParmException
     */
    public final void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws CommandParmException {
        fundOrderEntity.setOrderState(status);
        fundOrderEntity.setRetCode(code);
        fundOrderEntity.setRetMessage(msg);
        try {
            fundOrderService.update(fundOrderEntity);
        } catch (Exception e) {
            throw new CommandParmException(e.getMessage(),e);
        }
    }

    /**
     * 线下充值
     * @param entity
     * @param amount
     * @param orderType
     * @param busiId
     * @param busiType
     * @return
     * @throws FssException
     */
    public CommandResponse offlineRecharge(FundAccountEntity entity,BigDecimal amount,int orderType,long busiId,int  busiType) throws FssException {
        LogUtil.info(this.getClass(),"第三方充值:"+entity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,amount,orderType,busiId,busiType,"1103","");
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_OFFLINE_RECHARGE_REFUND, fundOrderEntity, entity, amount,"充值 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        response.setFundOrderEntity(fundOrderEntity);
        this.updateOrder(fundOrderEntity,1,response.getThirdReturnCode(),response.getMsg());
        return response;
    }

    /**
     * pos充值订单创建
     * @param entity
     * @param amount
     * @param orderType
     * @param busiId
     * @param busiType
     * @return
     * @throws FssException
     */
    public CommandResponse posOrderCreate(FundAccountEntity entity,BigDecimal amount,int orderType,long busiId,int busiType,String trade_type) throws FssException {
        LogUtil.info(this.getClass(),"POS充值订单创建:"+entity.getAccountNo()+":"+amount+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,amount,orderType,busiId,busiType,"1103",trade_type);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType,PayCommondConstants.COMMAND_PAYMENT_ORDER, fundOrderEntity, entity, amount,"pos充值订单金额 "+amount.toPlainString()+"元");
        execExction(response,fundOrderEntity);
        response.setFundOrderEntity(fundOrderEntity);
        this.updateOrder(fundOrderEntity,1,response.getThirdReturnCode(),response.getMsg());
        return response;
    }

    /**
     * pos签约订单创建
     * @param entity
     * @param orderType
     * @param busiId
     * @param busiType
     * @return
     * @throws FssException
     */
    public CommandResponse posSigned(FundAccountEntity entity,int orderType,long busiId,int busiType,String trade_type) throws FssException {
        LogUtil.info(this.getClass(),"签约:"+entity.getAccountNo()+":"+orderType+":"+busiId+":"+busiType);
        FundOrderEntity fundOrderEntity = this.createOrder(entity,null,orderType,busiId,busiType,"",trade_type);
        CommandResponse response = ThirdpartyFactory.command(thirdPartyType,PayCommondConstants.COMMAND_SIGNED, fundOrderEntity, entity,"pos签约订单创建");
        execExction(response,fundOrderEntity);
        response.setFundOrderEntity(fundOrderEntity);
        this.updateOrder(fundOrderEntity,1,response.getThirdReturnCode(),response.getMsg());
        return response;
    }
}
