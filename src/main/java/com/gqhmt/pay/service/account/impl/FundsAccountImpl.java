package com.gqhmt.pay.service.account.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardDto;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
	
	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@Resource
	private FundOrderService fundOrderService;
	
	@Resource
	private FssChangeCardService fssChangeCardService;
	
	@Resource
	private FssAccountService fssAccountService;

	/**
     * 创建账户
     *
     * @param createAccountDto         客户id
     * @throws FssException
     */
	public boolean createAccount(CreateAccountDto createAccountDto) throws FssException {
		CustomerInfoEntity customerInfoEntity =  customerInfoService.getCustomerById(Long.valueOf(createAccountDto.getCust_no()));
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
	public boolean createAccount(CustomerInfoEntity customerInfoEntity,String pwd, String taradPwd) throws FssException {
		Long cusId = customerInfoEntity.getId();
		Integer userId = customerInfoEntity.getUserId();
		FundAccountEntity primaryAccount = null;

		try {
			primaryAccount = this.getPrimaryAccount(cusId);
		} catch (FssException e) {
			if(e.getMessage() != null && "90002003".equals(e.getMessage()) ) {
				primaryAccount = fundAccountService.createAccount(customerInfoEntity, userId);
			}else{
				throw e;
			}


		}
		primaryAccount.setCustomerInfoEntity(customerInfoEntity);
		if (primaryAccount.getHasThirdAccount() ==1){//富友
				paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
				primaryAccount.setHasThirdAccount(2);
				fundAccountService.update(primaryAccount);
		}
		return true;
	}

	/**
     * 销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FssException
     */
	public boolean dropAccount(String thirdPartyType, Long custID) throws FssException {
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
	public boolean checkDropAccount(String thirdPartyType, Long custID)
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
		Long cusId = Long.valueOf(changeBankCardDto.getCust_no());
		String cardNo = changeBankCardDto.getBank_card();
		String bankCd = changeBankCardDto.getBank_id();
		String cityId = changeBankCardDto.getCity_id();
		String fileName = changeBankCardDto.getFile_path();
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
	public boolean setMms(String thirdPartyType, Long cusId,
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


	private FundAccountEntity getPrimaryAccount(Long cusId) throws FssException{
		FundAccountEntity primaryAccount = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		if (primaryAccount == null) {
			throw new FssException("90002003");
		}
		return primaryAccount;
	}

	/**
	 * 查询账户余额
	 */
	 public FundAccountEntity getAccountBanlance(AssetDto accessdto) throws FssException{
//		 FundAccountEntity primaryAccount = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
//		 FundAccountEntity account = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 Long cust_no=Long.valueOf(accessdto.getCust_no());
		 int busi_type=accessdto.getBusi_type();
		 FundAccountEntity account = fundAccountService.getAccountBanlance(cust_no,busi_type);
		 if(account==null){
			 throw new FssException("90002001");
		 }else{
			 if(account.getFreezeAmount()==BigDecimal.ZERO){
				 throw new FssException("90004007");
			 }
		 }
//		 account.setFreezeAmount(account.getFreezeAmount());
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
	
	/**
	 * 银行卡变更
	 */
	 public Response bankCardChange(CardChangeDto cardChangeDto)throws FssException{
		 Response response=new Response();
			BankCardInfoEntity bankCardInfoEntity=null;
			CustomerInfoEntity  customerInfoEntity=null;
			//1.根据账号查询该客户账户信息
			FssAccountEntity fssAccountEntity=fssAccountService.getFssAccountByAccNo(cardChangeDto.getAcc_no());
//		 	FundAccountEntity fundAccountEntity= fundAccountService.getFundAccountInfo(cardChangeDto.getAcc_no());
		 	if(fssAccountEntity==null) throw new FssException("90002001");
		    customerInfoEntity=customerInfoService.queryCustomeById(fssAccountEntity.getCustId());//查询该账户客户信息
		 	if(customerInfoEntity==null) throw new FssException("90002007");
 			//通过客户表中的bank_card查询该客户要变更的银行卡信息
 			bankCardInfoEntity = bankCardInfoService.getBankCardByBankNo(cardChangeDto.getBank_card());
 			if(bankCardInfoEntity==null) throw new FssException("90004027");
 			try {//将变更银行卡信息插入到银行卡变更表
				FssChangeCardEntity fssChangeCardEntity=fssChangeCardService.createChangeCardInstance(customerInfoEntity, cardChangeDto.getBank_card(), cardChangeDto.getBank_id(), "",cardChangeDto.getCity_id(), cardChangeDto.getFile_path(),cardChangeDto.getTrade_type(), cardChangeDto.getSeq_no(),cardChangeDto.getMchn(),cardChangeDto.getAcc_no());
				fssChangeCardService.insert(fssChangeCardEntity);
			//银行卡变更记录插入成功之后，进入跑批处理(后续处理)
			} catch (FssException e) {
			throw new FssException("90004001");
		}
		 return response;
	 }
	 
		/**
		 * 	银行卡变更完成，通知变更发起方（借款系统）
		 */
	    public ChangeCardResponse bankCardChangeCallBack(String seq_no,String mchn) throws FssException{
	    	ChangeCardResponse changeCardResponse=fssChangeCardService.queryChangeCardByParam(seq_no,mchn);
	    	if(changeCardResponse==null){
	    		throw new FssException("90004001");
	    	}
	    	String sixCode = Application.getInstance().getSixCode(changeCardResponse.getCity_id());
	    	changeCardResponse.setCity_ids(sixCode);
	    	return changeCardResponse;
	    }
	    
	    /**
	     * app开户、冠E通前台开户
	     */
		@Override
		public Integer createFundAccount(CreateAccountDto createAccountDto) throws FssException {
			CustomerInfoEntity customerInfoEntity =  customerInfoService.getCustomerById(Long.valueOf(createAccountDto.getCust_no()));
			if(customerInfoEntity == null) throw new FssException("90002007");
			customerInfoEntity.setParentBankCode(createAccountDto.getBank_id());
			customerInfoEntity.setBankNo(createAccountDto.getBank_card());
			customerInfoEntity.setCityCode(createAccountDto.getCity_id());
			customerInfoEntity.setCertNo(createAccountDto.getCert_no());
			customerInfoEntity.setMobilePhone(createAccountDto.getMobile());
			customerInfoEntity.setCustomerName(createAccountDto.getName());
			return this.createFundAccount(customerInfoEntity,"","",createAccountDto);
		}
	    
		public Integer createFundAccount(CustomerInfoEntity customerInfoEntity,String pwd, String taradPwd,CreateAccountDto createAccountDto) throws FssException {
			Long cusId = customerInfoEntity.getId();
			Integer userId = customerInfoEntity.getUserId();
			BankCardInfoEntity bankCardInfoEntity=null;
			FundAccountEntity primaryAccount = this.getPrimaryAccount(cusId);
		    if(primaryAccount == null){
				try {
					primaryAccount =  fundAccountService.createAccount(customerInfoEntity,userId);
				} catch (FssException e) {
			            throw new FssException("90002002");
				}
			}
			primaryAccount.setCustomerInfoEntity(customerInfoEntity);
			
			//富友
			if (primaryAccount.getHasThirdAccount() ==1){//未开通第三方账户
				paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
				primaryAccount.setHasThirdAccount(2);
				primaryAccount.setCustName(customerInfoEntity.getCustomerName());
				fundAccountService.update(primaryAccount);
			}
			//跟新所有与该cust_id相同的账户名称
			fundAccountService.updateAccountCustomerName(cusId,customerInfoEntity.getCustomerName(),customerInfoEntity.getCityCode(),customerInfoEntity.getParentBankCode(),customerInfoEntity.getBankNo());
//			customerInfoService.updateCustomer(cusId, createAccountDto.getName(), createAccountDto.getCert_no(),createAccountDto.getBank_id());
			//创建银行卡信息
			bankCardInfoEntity=bankCardInfoService.getInvestmentByCustId(Integer.valueOf(cusId.toString()));
			if(bankCardInfoEntity==null){
				bankCardInfoEntity=bankCardInfoService.createBankCardInfo(customerInfoEntity,primaryAccount);
			}else{
				bankCardInfoEntity=bankCardInfoService.getInvestmentByCustId(Integer.valueOf(cusId.toString()));
			}
			return bankCardInfoEntity.getId();
	}
		
		/**
		 * 银行卡变更接口
		 */
		public boolean changeBankCard(UpdateBankCardDto dto) throws FssException {
			try {
				fssChangeCardService.addChangeCard(dto.getCust_no(),dto.getBank_card(),dto.getBank_id(), dto.getBankAddr(), dto.getCity_id(), dto.getFile_path(),dto.getSeq_no(),Integer.valueOf(dto.getTrade_type()).intValue(),dto.getMchn());
			} catch (Exception e) {
				throw new FssException("90004001");
			}
			return true;
		}
		
		
		
		
		
		
		
}
