package com.gqhmt.fss.logicService.pay.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.exception.CreateAccountFailException;
import com.gqhmt.fss.architect.customer.bean.CustomerInfoSendMsgBean;
import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.logicService.pay.FundsResponse;
import com.gqhmt.fss.logicService.pay.IFundsAccount;
import com.gqhmt.fss.logicService.pay.exception.FundsException;
import com.gqhmt.fss.logicService.pay.util.CustomerConstants;
import com.gqhmt.fss.pay.core.PayCommondConstants;
import com.gqhmt.fss.pay.core.command.CommandResponse;
import com.gqhmt.fss.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.fss.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.LogUtil;
import org.springframework.stereotype.Service;

/**
 * 账户相关api
 * @author lijunlong
 *
 */
@Service
public class FundsAccountImpl extends AccountAbstractCommand implements IFundsAccount {

	/**
     * 创建账户
     *
     * @param thirdPartyType 支付渠道
     * @param custId         客户id
     * @throws FundsException
     */
	public FundsResponse createAccount(String thirdPartyType, int custId) throws FundsException {
		return null;
	}

	/**
     * 创建账户
     *
     * @param thirdPartyType     支付渠道
     * @param customerInfoEntity 客户实体
     * @param pwd                支付渠道登陆密码
     * @param taradPwd           支付渠道交易密码
     * @throws FundsException
     */
	public FundsResponse createAccount(String thirdPartyType,CustomerInfoEntity customerInfoEntity,
			String pwd, String taradPwd) throws FundsException {
		try {
			//富友
	        Integer cusId = customerInfoEntity.getId();
	        Integer userId = customerInfoEntity.getUserId();
	        //创建主账户
	        FundAccountEntity primaryAccount = super.getPrimaryAccount(cusId, false);
	        if(primaryAccount == null){
	            primaryAccount =  super.createPrimaryAccount(customerInfoEntity, userId);
	        }
	        super.createAccount(customerInfoEntity, userId, primaryAccount);
	//        primaryAccount.setCustomerInfoEntity(customerInfoEntity);
	
	        if(primaryAccount.getHasThirdAccount() == GlobalConstants.NO_CREATR_THIRD_ACCOUNT){
	            FundOrderEntity fundOrderEntity = super.createOrder(primaryAccount, BigDecimal.ZERO,GlobalConstants.ORDER_CREATE_ACCOUNT,0,0,thirdPartyType);
	            CommandResponse response  = ThirdpartyFactory.command(Integer.valueOf(thirdPartyType),PayCommondConstants.COMMAND_ACCOUNT_PRIVATE_CREATE,fundOrderEntity,primaryAccount,pwd,taradPwd);
	            if(response.getCode().equals("0000")){
	                primaryAccount.setHasThirdAccount(2);
	                super.updateAccount(primaryAccount);
	                super.updateOrder(fundOrderEntity,2,response.getThirdReturnCode(),response.getMsg());
	                return null;
	            }else if(response.getCode().equals("0009")){
	                super.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_THIRDERROR,response.getThirdReturnCode(),response.getMsg());
	                throw new ThirdpartyErrorAsyncException();
	            }else{
	                super.updateOrder(fundOrderEntity,3,response.getThirdReturnCode(),response.getMsg());
	                throw new CommandParmException(response.getMsg());
	            }
	        }
		} catch (CreateAccountFailException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * 销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FundsException
     */
	public FundsResponse dropAccount(String thirdPartyType, int custID) throws FundsException {
		FundAccountEntity primaryAccount =super.getPrimaryAccount(custID, true);
        //订单号
        //FundOrderEntity fundOrderEntity = super.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_DROP_USER,0,0,thirdPartyType);
        //CommandResponse response = null;//ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_DROP_ACCOUNT_PASS, fundOrderEntity, primaryAccount,String.valueOf(busiType));

        //execExction(response,fundOrderEntity);
        //super.updateOrder(fundOrderEntity,2,response.getCode(),response.getMsg());
		return null;
	}

	/**
     * 销户确认，客户已经发起销户申请，才可以完成销户确认，客户可以在富友发起申请，或者客户致电客服发起销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FundsException
     */
	public FundsResponse checkDropAccount(String thirdPartyType, int custID)
			throws FundsException {
		FundAccountEntity primaryAccount =super.getPrimaryAccount(custID, true);
        //订单号
        FundOrderEntity fundOrderEntity = super.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_DROP_USER,0,0,thirdPartyType);
        CommandResponse response = null;//ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_DROP_ACCOUNT_APPLY, fundOrderEntity, primaryAccount,String.valueOf(busiType));

        execExction(response,fundOrderEntity);
        super.updateOrder(fundOrderEntity,2,response.getCode(),response.getMsg());
		return null;
	}

	/**
     * 银行卡变更
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cardNo                    银行卡号
     * @param bankCd                    开户行行别
     * @param bankNm                    开户行支行名称
     * @param cityId                    开户区县代码
     * @param imagePath                 上传照片路径
     * @throws FundsException
     */
	public FundsResponse changeCard(String thirdPartyType, Integer cusId,
			String cardNo, String bankCd, String bankNm, String cityId,
			String imagePath) throws FundsException {
		LogUtil.info(this.getClass(),"updateCard");
        String code = "0000";
        String msg = "银行卡修改成功，24小时后生效";
        try {
        	CommandResponse response = null;//ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_DROP_ACCOUNT_APPLY, fundOrderEntity, primaryAccount,String.valueOf(busiType));
        	changeCardService.addChangeCard(cusId, cardNo, bankCd, bankNm,cityId,imagePath);
        }catch (Exception e) {
            code = "0001";
            String eMsg = e.getMessage();
            if("0020".equals(eMsg) || "0021".equals(eMsg)){
                msg = CustomerConstants.actionMap.get(eMsg);
            }else {
                msg = e.getMessage();
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        return null;
	}

	/**
     * 变更富友回调短信
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cztx                      充值提现
     * @param cz                        出账
     * @param rz                        入账
     * @param hz                        汇总
     * @throws FundsException
     */
	public FundsResponse setMms(String thirdPartyType, Integer cusId,
			String cztx, String cz, String rz, String hz) throws FundsException {
		CustomerInfoSendMsgBean bean = new CustomerInfoSendMsgBean();
		bean.setId(cusId);
		bean.setSendMsgRechargeWithdrawFouyou(Integer.valueOf(cztx));
		bean.setSendMsgTransferOutFouyou(Integer.valueOf(cz));
		bean.setSendMsgTransferInFouyou(Integer.valueOf(rz));
		bean.setSendMsgTransferAllFouyou(Integer.valueOf(hz));
		customerInfoService.updateCustSengMsgMode(bean, "0", thirdPartyType);
		return null;
	}
	
	/**
	 * 解析返回码
	 * @param response
	 * @param fundOrderEntity
	 * @throws CommandParmException
	 * @throws FundsException
	 */
	private void execExction(CommandResponse response,FundOrderEntity fundOrderEntity) throws  CommandParmException, FundsException {
        LogUtil.debug(FundsAccountImpl.class, "富有接口返回码：" + response.getCode());
		if(response.getCode().equals("0001")){
            throw new FundsException(fundOrderEntity.getOrderNo()+":验证码已发送");
        }else if(response.getCode().equals("0002")){
            throw new FundsException(fundOrderEntity.getOrderNo()+":等待回调通知");
        }else if(!"0000".equals(response.getCode())){
            super.updateOrder(fundOrderEntity,3,response.getCode(),response.getMsg());
            throw new CommandParmException(response.getMsg());
        }
    }

}
