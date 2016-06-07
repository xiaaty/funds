package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import com.gqhmt.pay.service.cost.impl.CostImpl;
import com.gqhmt.pay.service.loan.ILoan;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:
 * <p>放款给借款人
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来     1.0     1.0 Version
 */
@Service
public class LoanImpl implements ILoan {

    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    @Resource
    private FssLoanService loanService;
    @Resource
    private CustomerInfoService customerInfoService;
	@Resource
	private FundsAccountImpl fundsAccountImpl;
	@Resource
	private FssAccountService fssAccountService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FssCustomerService fssCustomerService;
	@Resource
	private CostImpl costImpl;
//	@Resource
//	private FssAccountFileService fssAccountFileService;
	
	/**
	 * 借款系统开户
	 */
    @Override
    public String createLoanAccount(CreateLoanAccountDto dto) throws FssException {
        //富友
    	FssAccountEntity  fssAccount=null; //新版账户体系
    	CustomerInfoEntity customerInfoEntity=null;//旧版客户信息
    	FssCustomerEntity fssCustomerEntity=null;//新版客户信息
    	String accNo=null;
    	Long custId=null;
    	/**
    	 * 借款人（纯线下）开户  ：11020011
    	 * 放款(纯线下) ：11090003
    	 * 还款代扣（纯线下）：11093002
    	 * 入账清结算（纯线下）：11099002
    	 */

		customerInfoEntity=customerInfoService.searchCustomerInfoByCertNo(dto.getCert_no());
		try{
		if(customerInfoEntity == null && !"11020011".equals(dto.getTrade_type())){
			customerInfoEntity=customerInfoService.createLoanAccount(dto);
			customerInfoEntity.setCityCode(Application.getInstance().getFourCode(dto.getCity_id()));
			customerInfoEntity.setParentBankCode(dto.getBank_id());
			customerInfoEntity.setBankLongName("");
			customerInfoEntity.setBankNo(dto.getBank_card());
			try {
				fundsAccountImpl.createAccount(customerInfoEntity, "", "");//创建富友账户
			} catch (FssException e) {
				LogUtil.error(this.getClass(), e);
				throw e;
			}
		}
		}catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			throw e;
		}
		custId = customerInfoEntity == null?null: customerInfoEntity.getId();
//    	3,既有线上的又有纯线下的，要先把线下的转为线上的，再走富友
    	fssAccount=fssAccountService.createAccount(dto, custId);
    	accNo=fssAccount.getAccNo();
//    	 FundAccountEntity fundAccount = fundAccountService.getFundAccount(custId, GlobalConstants.ACCOUNT_TYPE_LOAN);
//    	 if(fundAccount!=null&&customerInfoEntity!=null){
    	 //创建需要报备的 P2P个人平台开户文件
//    	 fssAccountFileService.creatAccountFile(dto.getSeq_no(), fundAccount.getUserName(), fundAccount.getUserName(), 0, customerInfoEntity.getCustomerName(), customerInfoEntity.getCertType(), customerInfoEntity.getCertNo(), customerInfoEntity.getSex(), customerInfoEntity.getMobilePhone(), customerInfoEntity.getAddress(), customerInfoEntity.getCustomerType(), customerInfoEntity.getCreateTime(), "0", "ADD", customerInfoEntity.getRemark());
//    	 }
    	 return accNo;
    }
    
    /**
     * 保证金退还
     */
	@Override
	public boolean marginSendBack(MarginDto dto) throws FssException {
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(dto.getAcc_no());
			//Integer integer = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(GlobalConstants.TRADETYPE_ACCOUNT_MAPPING.get(dto.getTrade_type()));

		costImpl.costReturn("10040001","10990006",dto.getAcc_no(),dto.getRefund_amt(),0l,0);
		return true;
		}
		
	}
  
