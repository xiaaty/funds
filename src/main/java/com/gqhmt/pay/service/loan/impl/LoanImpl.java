package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import com.gqhmt.pay.service.loan.ILoan;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * Filename:    com.gqhmt.pay.service.loan.impl.LoanImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:52
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class LoanImpl implements ILoan {

    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    @Resource
    private CustomerInfoService customerInfoService;
	@Resource
	private FundsAccountImpl fundsAccountImpl;
	@Resource
	private FssAccountService fssAccountService;
    
    @Override
    public String createLoanAccount(CreateLoanAccountDto loanAccountDto) throws FssException {
        //富友
    	FssAccountEntity  fssAccountEntity=null;
    	String accNo=null;
    	CustomerInfoEntity customerInfoEntity=customerInfoService.searchCustomerInfoByMobile(loanAccountDto);
    	if(customerInfoEntity==null){
    		customerInfoService.createLoanAccount(loanAccountDto);
    		if(true){
    			fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    		}
    	}
    	fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    	if(true){
//    	  fssAccountEntity=fssAccountService.createFssAccount(loanAccountDto,customerInfoEntity);
    	  fssAccountEntity=fssAccountService.createFssAccountEntity(loanAccountDto,customerInfoEntity);
    	  if(null!=fssAccountEntity){
    		   accNo=fssAccountEntity.getAccNo();
    	  }
    	}
    	//通知富有开户
//        paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
        
        //5.返回  acc_no; return acc_no
    	return accNo;
    }
}
