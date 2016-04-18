package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import com.gqhmt.pay.service.loan.ILoan;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.gqhmt.pay.service.cost.impl.CostImpl;

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
	private FssCustomerService fssCustomerService;
	@Resource
	private CostImpl costImpl;
	
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
    	if(!dto.getTrade_type().equals("11020011")){//根据交易类型判断是否为线上或线下
    		customerInfoEntity=customerInfoService.searchCustomerInfoByCertNo(dto.getCert_no());
    		if(customerInfoEntity==null){//不存在就创建旧版客户信息
    			customerInfoEntity=customerInfoService.createLoanAccount(dto);
    			customerInfoEntity.setCityCode(Application.getInstance().getFourCode(dto.getCity_id()));
    			customerInfoEntity.setParentBankCode(dto.getBank_id());
    			customerInfoEntity.setBankLongName("");
    			customerInfoEntity.setBankNo(dto.getBank_card());
    		}
    		try {
				fundsAccountImpl.createAccount(customerInfoEntity, "", "");//创建富友账户
			} catch (FssException e) {
				LogUtil.error(this.getClass(), e);
				throw new FssException("91004013");
			}
    		custId=customerInfoEntity.getId();
		}else{//纯线下的就不在旧版里创建客户信息、用户信息和银行卡信息，而在新版账户创建这些信息
			fssCustomerEntity=fssCustomerService.getFssCustomerEntityByCertNo(dto.getCert_no());
			if(fssCustomerEntity==null){
				fssCustomerEntity=fssCustomerService.createFssAccountInfo(dto);
				custId=fssCustomerEntity.getId();
			}
		}
//    	3,既有线上的又有纯线下的，要先把线下的转为线上的，再走富友
    	fssAccount=fssAccountService.createFssAccountEntity(dto, custId);
    	accNo=fssAccount.getAccNo();
    	return accNo;
    }
    
    /**
     * 保证金退还
     */
	@Override
	public boolean marginSendBack(MarginDto dto) throws FssException {
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(dto.getAcc_no());
		try {
			costImpl.cost("10990006", fssAccountByAccNo.getCustId(), GlobalConstants.TRADETYPE_ACCOUNT_MAPPING.get(dto.getTrade_type()), dto.getRefund_amt(),null , Integer.parseInt(dto.getTrade_type()));
			return true;
		} catch (Exception e) { 
			LogUtil.info(this.getClass(), e.getMessage());
			return false;
		}
		
	}
  
	}