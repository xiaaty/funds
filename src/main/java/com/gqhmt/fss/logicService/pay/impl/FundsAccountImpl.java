package com.gqhmt.fss.logicService.pay.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.logicService.pay.IFundsAccount;
import com.gqhmt.fss.logicService.pay.PaySuperByFuiou;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户相关api
 * @author lijunlong
 *
 */
@Service
public class FundsAccountImpl extends AccountAbstractCommand implements IFundsAccount {

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
		CustomerInfoEntity customerInfoEntity = super.getCustomerInfo(custId);
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

		FundAccountEntity primaryAccount = super.getPrimaryAccount(cusId);
	        if(primaryAccount == null){
				try {
					primaryAccount =  super.createPrimaryAccount(customerInfoEntity, userId);
				} catch (FssException e) {
				}
			}

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
		FundAccountEntity primaryAccount =super.getPrimaryAccount(custID);
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
		FundAccountEntity primaryAccount =super.getPrimaryAccount(custID);
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
		FundAccountEntity primaryAccount =super.getPrimaryAccount(cusId);
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
		FundAccountEntity primaryAccount =super.getPrimaryAccount(cusId);
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
	


}
