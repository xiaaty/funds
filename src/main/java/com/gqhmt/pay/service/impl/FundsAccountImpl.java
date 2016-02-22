package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.account.AccountAccessDto;
import com.gqhmt.extServInter.dto.account.AssetDto;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.ChangeBankCardResultDto;
import com.gqhmt.extServInter.dto.account.CreateAccountByFuiouDto;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.IFundsAccount;
import com.gqhmt.pay.service.PaySuperByFuiouTest;
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
	private PaySuperByFuiouTest paySuperByFuiou;
	
	/**
     * 创建账户
     *
     * @param thirdPartyType 支付渠道
     * @param custId         客户id
     * @throws FssException
     */
	public boolean createAccount(CreateAccountByFuiouDto  createAccountByFuiouDto) throws FssException {
		CustomerInfoEntity customerInfoEntity =  customerInfoService.queryCustomerById(Integer.parseInt(createAccountByFuiouDto.getCust_no()));
		customerInfoEntity.setParentBankCode(createAccountByFuiouDto.getBank_id());
		customerInfoEntity.setBankNo(createAccountByFuiouDto.getBank_card());
		customerInfoEntity.setCityCode(createAccountByFuiouDto.getCity_id());
		customerInfoEntity.setCertNo(createAccountByFuiouDto.getCert_no());
		customerInfoEntity.setMobilePhone(createAccountByFuiouDto.getMobile());
		customerInfoEntity.setCustomerName(createAccountByFuiouDto.getName());
		if(customerInfoEntity == null) throw new FssException("90002001");
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
     * @param thirdPartyType            支付渠道
     * @param changeCardEntity
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
     * 银行卡变更结果查询
     */
	@Override
	public boolean changeCardResult(ChangeBankCardResultDto changeBankCardResultDto) throws FssException {
		FundAccountEntity fundAccountEntity=this.getPrimaryAccount(Integer.parseInt(changeBankCardResultDto.getCust_no()));
		fundAccountEntity.setCityId(changeBankCardResultDto.getCity_id());
		fundAccountEntity.setUserId(Integer.parseInt(changeBankCardResultDto.getUser_no()));
		fundAccountEntity.setBankNo(changeBankCardResultDto.getBank_card());
		paySuperByFuiou.changeCardResult(fundAccountEntity, changeBankCardResultDto.getSeq_no(),Long.parseLong(changeBankCardResultDto.getTrade_type()));
		return false;
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
	 public FundAccountEntity getAccountAccByCustId(AccountAccessDto accessdto) throws FssException{
		 FundAccountEntity primaryAccount = fundAccountService.getFundAccount(accessdto.getCust_id(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		 return primaryAccount;
	 }
	
	/**
	 * 账户资产
	 */
	@Override
	public FssAssetEntity getAccountAsset(AssetDto asset) throws FssException {
		FssAssetEntity assetEntity = fundAccountService.getAccountAsset(asset.getCust_no());
		return assetEntity;
	}
	
	
	
}
