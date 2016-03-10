package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.*;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import com.gqhmt.pay.service.loan.ILoan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        //富友
    	//paySuperByFuiou.createAccountByPersonal(primaryAccount,"","");
        //5.返回  acc_no; return acc_no
    	CustomerInfoEntity customerInfoEntity=null;
    	FssAccountEntity  fssAccountEntity=null;
    	String accNo=null;
    	 customerInfoEntity=customerInfoService.searchCustomerInfoByMobile(dto);
    	if(customerInfoEntity==null){
    		customerInfoService.createLoanAccount(dto);
    		if(true){
    			fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    	//1.查询是否已经开通资金账号
    	 customerInfoEntity=customerInfoService.searchCustomerInfoByMobile(dto);
    	if(customerInfoEntity!=null){
    		//资金平台已经开通账户，则返回已经开户的信息给借款系统
    		Integer custId=customerInfoEntity.getId();
    		fssAccountEntity=fssAccountService.getFssAccountByCustId(custId);
    		accNo=fssAccountEntity.getAccNo();
    		throw new FssException("账户已经开通！");
    	}else{//资金平台未开通账户，则开通，并将借款系统传来的数据发送给富友作为开通账户信息
    		CustomerInfoEntity fssCustomerEntity = customerInfoService.createLoanAccount(dto);
    		if(true){//customer、用户、银行卡创建成功后，最后创建账户信息
    			fundsAccountImpl.createAccount(fssCustomerEntity,"","");
    		}
    		fssAccountEntity=fssAccountService.createFssAccount(dto, fssCustomerEntity);
    		accNo=fssAccountEntity.getAccNo();
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
    		} 
        //5.返回  acc_no; return acc_no
    	}
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
