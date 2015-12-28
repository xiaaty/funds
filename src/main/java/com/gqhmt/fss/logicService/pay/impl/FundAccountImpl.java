package com.gqhmt.fss.logicService.pay.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.exception.CreateAccountFailException;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.logicService.pay.FundsResponse;
import com.gqhmt.fss.logicService.pay.IFundsAccount;
import com.gqhmt.fss.logicService.pay.exception.FundsException;
import com.gqhmt.fss.logicService.pay.util.CustomerConstants;
import com.gqhmt.fss.pay.core.command.CommandResponse;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.fss.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.LogUtil;

public class FundAccountImpl extends AccountAbstractCommand implements IFundsAccount {

	@Override
	public FundsResponse createAccount(String thirdPartyType, int custId) throws FundsException {
		return null;
	}

	@Override
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
	            CommandResponse response  = null;//ThirdpartyFactory.command(thirdPartyType,PayCommondConstants.COMMAND_ACCOUNT_PRIVATE_CREATE,fundOrderEntity,primaryAccount,pwd,taradPwd);
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

	@Override
	public FundsResponse dropAccount(String thirdPartyType, int custID) throws FundsException {
		FundAccountEntity primaryAccount =super.getPrimaryAccount(custID, true);
        //订单号
        FundOrderEntity fundOrderEntity = super.createOrder(primaryAccount,BigDecimal.ZERO,GlobalConstants.ORDER_DROP_USER,0,0,thirdPartyType);
        CommandResponse response = null;//ThirdpartyFactory.command(thirdPartyType, PayCommondConstants.COMMAND_ACCOUNT_DROP_ACCOUNT_PASS, fundOrderEntity, primaryAccount,String.valueOf(busiType));

        execExction(response,fundOrderEntity);
        super.updateOrder(fundOrderEntity,2,response.getCode(),response.getMsg());
		return null;
	}

	@Override
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

	@Override
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

	@Override
	public FundsResponse setMms(String thirdPartyType, Integer cusId,
			String cztx, String cz, String rz, String hz) throws FundsException {
		//customerInfoService.updateCustSengMsgMode();
		return null;
	}
	
	private void execExction(CommandResponse response,FundOrderEntity fundOrderEntity) throws  CommandParmException, FundsException {
        LogUtil.debug(FundAccountImpl.class, "富有接口返回码：" + response.getCode());
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
