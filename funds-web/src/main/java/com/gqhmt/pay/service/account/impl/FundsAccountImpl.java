package com.gqhmt.pay.service.account.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.event.account.CreateAccountEvent;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardDto;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
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
import java.util.Date;

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
			if(primaryAccount == null){
				primaryAccount = fundAccountService.createAccount(customerInfoEntity, userId);
			}
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
     * @param changeCardEntity
     * @throws FssException
     */
	public boolean changeCard(FssChangeCardEntity changeCardEntity) throws FssException {
		Long cusId = Long.valueOf(changeCardEntity.getCustId());
		String cardNo = changeCardEntity.getCardNo();
		String bankCd = changeCardEntity.getBankType();
		String cityId = changeCardEntity.getBankCity();
		String fileName = changeCardEntity.getFilePath();
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
	 public boolean bankCardChange(CardChangeDto cardChangeDto)throws FssException{
			CustomerInfoEntity  customerInfoEntity=null;
			//1.根据账号查询该客户账户信息
			FssAccountEntity fssAccountEntity=fssAccountService.getFssAccountByAccNo(cardChangeDto.getAcc_no());
		 	if(fssAccountEntity==null) throw new FssException("90002001");
		    customerInfoEntity=customerInfoService.queryCustomeById(fssAccountEntity.getCustId());//查询该账户客户信息
		 	if(customerInfoEntity==null) throw new FssException("90002007");
 			try {
 				fssChangeCardService.addChangeCard(customerInfoEntity, cardChangeDto.getBank_card(), cardChangeDto.getBank_id(), "", cardChangeDto.getCity_id(), cardChangeDto.getFile_path(),1, cardChangeDto.getSeq_no(), cardChangeDto.getMchn(), cardChangeDto.getTrade_type());
			//银行卡变更记录插入成功之后，进入跑批处理(后续处理)
			} catch (FssException e) {
				throw e;
			}
		 return true;
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
				//判断输入的银行卡号是否已经存在
				bankCardInfoEntity=bankCardInfoService.queryBankCardByBankNo(customerInfoEntity.getBankNo());
				if(bankCardInfoEntity!=null){
					throw new FssException("90002038");//该银行卡号已经存在
				}
				
				bankCardInfoEntity=bankCardInfoService.createBankCardInfo(customerInfoEntity,createAccountDto.getTrade_type());
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
				fssChangeCardService.addChangeCard(dto.getCust_no(),dto.getBank_card(),dto.getBank_id(), dto.getBankAddr(), dto.getCity_id(), dto.getFile_path(),dto.getSeq_no(),dto.getTrade_type(),dto.getMchn());
			} catch (Exception e) {
				throw new FssException("90004001");
			}
			return true;
		}
}
