package com.gqhmt.pay.service.account.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.account.IFundsAccount;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;

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
	
	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@Resource
	private FundOrderService fundOrderService;
	
	@Resource
	private FssChangeCardService fssChangeCardService;

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
	 public FundAccountEntity getAccountBanlance(AssetDto accessdto) throws FssException{
//		 FundAccountEntity primaryAccount = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
//		 FundAccountEntity account = fundAccountService.getFundAccount(Integer.parseInt(accessdto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 int cust_no=Integer.parseInt(accessdto.getCust_no());
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
			BankCardInfoEntity bankCardInfoEntity=null;
			CustomerInfoEntity  customerInfoEntity=null;
			//1.根据账号查询该客户账户信息
		 	FssAccountEntity fssAccountEntity= fundAccountService.getFssFundAccountInfo(cardChangeDto.getAcc_no());
		 	if(null!=fssAccountEntity){
		 		//查询该账户客户信息
		 		customerInfoEntity=customerInfoService.queryCustomeById(fssAccountEntity.getCustId());
		 		if(null!=customerInfoEntity){
		 			//通过客户表中的bankid查询该客户要变更的银行卡信息
		 			bankCardInfoEntity = bankCardInfoService.getBankCardByBankId(customerInfoEntity.getBankId());
		 			if(bankCardInfoEntity!=null){
		 				try {
		 					//将变更银行卡信息插入到银行卡变更表
							FssChangeCardEntity fssChangeCardEntity=this.createChangeCardInstance(customerInfoEntity, bankCardInfoEntity.getBankNo(), cardChangeDto.getBank_id(), "", cardChangeDto.getCity_id(), cardChangeDto.getFileName(), 4, cardChangeDto.getSeq_no(),cardChangeDto.getMchn());
							fssChangeCardService.insert(fssChangeCardEntity);
							//银行卡变更记录插入成功之后，进入跑批处理
		 				} catch (FssException e) {
							throw new FssException("银行卡变更记录插入失败");
						}
		 			}else{
		 				throw new FssException("未查到得到该客户银行卡信息");
		 			}
		 		}else{
		 			throw new FssException("未查到得到该户信息");
		 		}
		 	}else{
		 		throw new FssException("资金平台未查到该账户信息");
		 	}
		 return true;
	 }
	
		/**
		 * 	银行卡变更完成，通知变更发起方（借款系统）
		 */
	    public FssChangeCardEntity bankCardChangeCallBack(String seq_no,String mchn) throws FssException{
	    	FssChangeCardEntity changeCardEntity=null;
	    	changeCardEntity=fssChangeCardService.queryChangeCardByParam(seq_no,mchn);
	    	if(changeCardEntity==null){
	    		throw new FssException("90004001");
	    	}
	    	return changeCardEntity;
	    }
	    
	    /**
	     * 创建银行卡变更实体类型
	     * @param cus
	     * @param bankNo
	     * @param bankId
	     * @param bankAddr
	     * @param bankCity
	     * @param filePath
	     * @param type
	     * @param seqNo
	     * @return
	     */
	    public FssChangeCardEntity createChangeCardInstance(CustomerInfoEntity cus, String bankNo, String bankId, String bankAddr, String bankCity, String filePath, int type, String seqNo,String mchn){
	        FssChangeCardEntity entity = new FssChangeCardEntity();
	        entity.setCustId(cus.getId().longValue());
	        entity.setCardNo(bankNo);
	        entity.setBankType(bankId);
	        entity.setBankAdd(bankAddr);
	        entity.setBankCity(bankCity);
	        entity.setFilePath(filePath);

	        entity.setbBankInfoId(cus.getBankId().longValue());
	        entity.setCertNo(cus.getCertNo());
	        entity.setCustName(cus.getCustomerName());
	        entity.setCreateUserId(-1l);
	        entity.setCreateTime(new Date());
	        entity.setModifyTime(new Date());
	        entity.setState(1);
	        entity.setTradeState(1);
	        entity.setCertType(cus.getCertType());
	        entity.setMobile(cus.getMobilePhone());
	        entity.setType(type);
	        if(seqNo != null){
	            entity.setSeqNo(seqNo);
	        }
	        if(mchn != null){
	        	entity.setMchn(mchn);
	        }
	        return  entity;
	    }
	    
}
