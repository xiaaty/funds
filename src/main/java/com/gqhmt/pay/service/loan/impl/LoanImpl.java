package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.pay.service.PaySuperByFuiou;
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
    @Override
    public void createAccount(CreateLoanAccountDto dto) throws FssException {
        //富友
        FundAccountEntity primaryAccount = new FundAccountEntity();
        primaryAccount.setId(-1l);
        CustomerInfoEntity customerInfoEntity = new CustomerInfoEntity();
        customerInfoEntity.setCustomerName(dto.getName());
        customerInfoEntity.setCertNo(dto.getCert_no());
        customerInfoEntity.setMobilePhone(dto.getMobile());
        customerInfoEntity.setCityCode(dto.getCity_id());
        customerInfoEntity.setParentBankCode(dto.getBank_id());
        customerInfoEntity.setBankLongName("");
        customerInfoEntity.setBankNo(dto.getBank_card());
        primaryAccount.setCustomerInfoEntity(customerInfoEntity);
        paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");

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
		try {
			 loanService.insertmortgageeWithDraw(dto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
		}
		
		 return true;
	}
	/**
	 * .
	 * author:jhz
	 * time:2016年3月7日
	 * function：流标接口
	 */
	public boolean failedBid(FailedBidDto dto)throws FssException {
		try {
			 loanService.insertfailedBidDto(dto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
		}
		
		return false;
	}
}
