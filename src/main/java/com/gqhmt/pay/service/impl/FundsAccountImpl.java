package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.IFundsAccount;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户相关api
 * @author lijunlong
 *
 */
@Service
public class FundsAccountImpl implements IFundsAccount {

	@Resource
	private CustomerInfoService  customerInfoService  ;

	@Resource
	FundAccountService fundAccountService;

	@Resource
	private PaySuperByFuiou paySuperByFuiou;

	/**
     * 创建账户
     *
     * @param thirdPartyType 支付渠道
     * @param custId         客户id
     * @throws FssException
     */
	public boolean createAccount(String thirdPartyType, int custId) throws FssException {
		CustomerInfoEntity customerInfoEntity =  customerInfoService.queryCustomerById(custId);
		if(customerInfoEntity == null) throw new FssException("客户不存在");
		return this.createAccount(thirdPartyType,customerInfoEntity,"","");
	}

	/**
     * 创建账户
     *
     * @param thirdPartyType     支付渠道
     * @param customerInfoEntity 客户实体
     * @param pwd                支付渠道登陆密码
     * @param taradPwd           支付渠道交易密码
     * @throws FssException
     */
	public boolean createAccount(String thirdPartyType,CustomerInfoEntity customerInfoEntity,
						String pwd, String taradPwd) throws FssException {

		Integer cusId = customerInfoEntity.getId();

		Integer userId = customerInfoEntity.getUserId();

		FundAccountEntity primaryAccount = this.getPrimaryAccount(cusId);
	        if(primaryAccount == null){
				try {
					primaryAccount =  fundAccountService.createAccount(customerInfoEntity,userId);
				} catch (FssException e) {
				}
			}

		primaryAccount.setCustomerInfoEntity(customerInfoEntity);
		//富友
		paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
		return true;
	}

	/**
     * 销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FssException
     */
	public boolean dropAccount(String thirdPartyType, int custID) throws FssException {
		FundAccountEntity primaryAccount =this.getPrimaryAccount(custID);
		paySuperByFuiou.dropAccount(primaryAccount,"1");
		return true;
	}

	/**
     * 销户确认，客户已经发起销户申请，才可以完成销户确认，客户可以在富友发起申请，或者客户致电客服发起销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FssException
     */
	public boolean checkDropAccount(String thirdPartyType, int custID)
			throws FssException {
		FundAccountEntity primaryAccount =this.getPrimaryAccount(custID);
		paySuperByFuiou.dropAccount(primaryAccount,"2");
		return  true;
	}

	/**
     * 银行卡变更
     * @param thirdPartyType            支付渠道
     * @param changeCardEntity
     * @throws FssException
     */
	public boolean changeCard(String thirdPartyType,FssChangeCardEntity changeCardEntity) throws FssException {
		Integer cusId = changeCardEntity.getCustId().intValue();
		String cardNo = changeCardEntity.getCardNo();
		String bankCd = changeCardEntity.getBankType();
		String bankNm = changeCardEntity.getBankAdd();
		String cityId = changeCardEntity.getBankCity();
		String fileName = changeCardEntity.getFilePath().substring(changeCardEntity.getFilePath().lastIndexOf("/"));
		FundAccountEntity primaryAccount =this.getPrimaryAccount(cusId);
		paySuperByFuiou.changeCard(primaryAccount,cardNo,bankCd,bankNm,cityId,fileName);
		return true;
	}

	/**
     * 变更富友回调短信
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cztx                      充值提现
     * @param cz                        出账
     * @param rz                        入账
     * @param hz                        汇总
     * @throws FssException
     */
	public boolean setMms(String thirdPartyType, Integer cusId,
			String cztx, String cz, String rz, String hz) throws FssException {
		FundAccountEntity primaryAccount =this.getPrimaryAccount(cusId);
		paySuperByFuiou.setMms(primaryAccount,cztx,cz,rz,hz);
		/*CustomerInfoSendMsgBean bean = new CustomerInfoSendMsgBean();
		bean.setId(cusId);
		bean.setSendMsgRechargeWithdrawFouyou(Integer.valueOf(cztx));
		bean.setSendMsgTransferOutFouyou(Integer.valueOf(cz));
		bean.setSendMsgTransferInFouyou(Integer.valueOf(rz));
		bean.setSendMsgTransferAllFouyou(Integer.valueOf(hz));
		customerInfoService.updateCustSengMsgMode(bean, "0", thirdPartyType);*/
		return true;
	}


	private FundAccountEntity getPrimaryAccount(Integer cusId){
		FundAccountEntity primaryAccount = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		if(primaryAccount == null){
			throw new CommandParmException("主账户不存在");
		}
		return primaryAccount;
	}

}
