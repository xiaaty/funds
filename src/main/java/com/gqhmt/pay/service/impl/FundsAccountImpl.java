package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.asset.AccountAccessDto;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
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
	private FundAccountService fundAccountService;

	@Resource
	private PaySuperByFuiou paySuperByFuiou;

	/**
     * 创建账户
     *
     * @param createAccountDto         客户id
     * @throws FssException
     */
	public boolean createAccount(CreateAccountDto createAccountDto) throws FssException {
		CustomerInfoEntity customerInfoEntity =  customerInfoService.queryCustomerById(Integer.parseInt(createAccountDto.getCust_no()));
		if(customerInfoEntity == null) throw new FssException("90002007");
		customerInfoEntity.setParentBankCode(createAccountDto.getBank_id());
		customerInfoEntity.setBankNo(createAccountDto.getBank_card());
		customerInfoEntity.setCityCode(createAccountDto.getCity_id());
		customerInfoEntity.setCertNo(createAccountDto.getCert_no());
		customerInfoEntity.setMobilePhone(createAccountDto.getMobile());
		customerInfoEntity.setCustomerName(createAccountDto.getName());
		return this.createAccount(customerInfoEntity,"","");
	}

	/**
     * 创建账户
     *
     * @param customerInfoEntity 客户实体
     * @param pwd                支付渠道登陆密码
     * @param taradPwd           支付渠道交易密码
     * @throws FssException
     */
	public boolean createAccount(CustomerInfoEntity customerInfoEntity,
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
     * @param changeBankCardDto            支付渠道
     * @throws FssException
     */
	public boolean changeCard(ChangeBankCardDto changeBankCardDto) throws FssException {
		Integer cusId = Integer.parseInt(changeBankCardDto.getCust_no());
		String cardNo = changeBankCardDto.getBank_card();
		String bankCd = changeBankCardDto.getBank_id();
		String cityId = changeBankCardDto.getCity_id();
		String fileName = changeBankCardDto.getImage();
		FundAccountEntity primaryAccount =this.getPrimaryAccount(cusId);
		boolean changeCard = paySuperByFuiou.changeCard(primaryAccount,cardNo,bankCd,bankCd,cityId,fileName);
		if(!changeCard) throw new FssException("90002001");
		return changeCard;
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
			throw new CommandParmException("90002003");
		}
		return primaryAccount;
	}

	/**
	 * 查询账户余额
	 */
	 public FundAccountEntity getAccountAccByCustId(AssetDto accessdto) throws FssException{
		 FundAccountEntity primaryAccount = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		 FundAccountEntity account = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 account.setFreezeAmount(primaryAccount.getFreezeAmount());
		 return account;
	 }
	
	/**
	 * 账户资产
	 */
	@Override
	public FssAssetEntity getAccountAsset(AssetDto asset) throws FssException {
		FssAssetEntity assetEntity = fundAccountService.getAccountAsset(asset.getUser_no(),asset.getAcc_no(),asset.getCust_no());
		return assetEntity;
	}
	
	
	
}
