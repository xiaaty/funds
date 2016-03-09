package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import com.gqhmt.pay.service.loan.ILoan;

import java.util.List;

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
 * <p>放款给借款人
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
    private FssLoanService loanService;

    @Resource
    private CustomerInfoService customerInfoService;
	@Resource
	private FundsAccountImpl fundsAccountImpl;
	@Resource
	private FssAccountService fssAccountService;
    

    @Override
    public String createLoanAccount(CreateLoanAccountDto dto) throws FssException {
    	FssAccountEntity  fssAccountEntity=null;
    	String accNo=null;
    	CustomerInfoEntity customerInfoEntity=customerInfoService.searchCustomerInfoByMobile(dto);
    	if(customerInfoEntity==null){
    		customerInfoService.createLoanAccount(dto);
    		if(true){
    			fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    		}
    	}
    	fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    	if(true){
//    	  fssAccountEntity=fssAccountService.createFssAccount(loanAccountDto,customerInfoEntity);
    	  fssAccountEntity=fssAccountService.createFssAccountEntity(dto,customerInfoEntity);
    	  if(null!=fssAccountEntity){
    		   accNo=fssAccountEntity.getAccNo();
    	  }
    	}
    	//通知富有开户
//        paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
        
        //5.返回  acc_no; return acc_no
    	return accNo;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月7日
     * function：放款给借款人
     */
    @Override
	public boolean lending(LendingDto dto) throws FssException {
		try {
			loanService.insertLending(dto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
		}
		 return true;
		
	}
	
	/**
	 * 
	 *author:jhz
     * time:2016年3月7日
     * function：抵押权人提现接口
     */
	@Override
	public boolean mortgageeWithDraw(MortgageeWithDrawDto dto) throws FssException {
			 loanService.insertmortgageeWithDraw(dto);
		 return true;
	}
	/**
	 * .
	 * author:jhz
	 * time:2016年3月7日
	 * function：流标接口
	 */
	public boolean failedBid(FailedBidDto dto)throws FssException {
			 loanService.insertfailedBidDto(dto);
		return true;
	}
	/**
	 * .
	 * author:jhz
	 * time:2016年3月9日
	 * function：入账接口
	 */
	@Override
	public boolean enterAccount(List<EnterAccountDto> enterAccountDtos) throws FssException {
		if(enterAccountDtos==null){
			return false;
		}else{
			loanService.insertEnterAccount(enterAccountDtos);
			}
			return true;
		}
	}
