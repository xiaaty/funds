package com.gqhmt.pay.service.loan.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;
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
	
	/**
	 * 开户
	 */
    @Override
    public String createLoanAccount(CreateLoanAccountDto dto) throws FssException {
        //富友
    	FssAccountEntity  fssAccount=null; //新版账户体系
    	CustomerInfoEntity customerInfoEntity=null;
    	FssFuiouAccountEntity fssFuiouAccountEntity=null;
    	String accNo=null;
    	//1.根据借款系统传入的手机号码，查询资金平台有没有此客户信息
    	customerInfoEntity=customerInfoService.searchCustomerInfoByMobile(dto);
    	if(customerInfoEntity!=null){
    		if(dto.getTrade_type().equals("11020009") || dto.getTrade_type().equals("11029004") ){ //线下开户不走富友
    			fssFuiouAccountEntity=fssAccountService.createAccount(dto);
    			fssAccount=fssAccountService.createFssAccountEntity(dto, customerInfoEntity,fssFuiouAccountEntity.getAccNo());
    		}else{
				fundsAccountImpl.createAccount(customerInfoEntity, "", "");
				fssFuiouAccountEntity = fssAccountService.createAccount(dto);
    			fssAccount=fssAccountService.createFssAccountEntity(dto, customerInfoEntity,fssFuiouAccountEntity.getAccNo());

    		}
    	}else{
    		try {
    			customerInfoEntity=customerInfoService.createLoanAccount(dto);
    			customerInfoEntity.setCityCode(dto.getCity_id());
    	    	customerInfoEntity.setParentBankCode(dto.getBank_id());
    			customerInfoEntity.setBankLongName("");
    			customerInfoEntity.setBankNo(dto.getBank_card());
    			if(dto.getTrade_type().equals("11020009") || dto.getTrade_type().equals("11029004") ){ //线下开户不走富友
    				fssFuiouAccountEntity=fssAccountService.createAccount(dto);
    				fssAccount=fssAccountService.createFssAccountEntity(dto, customerInfoEntity,fssFuiouAccountEntity.getAccNo());
    			}else{
    				fundsAccountImpl.createAccount(customerInfoEntity, "", "");
    				fssFuiouAccountEntity=fssAccountService.createAccount(dto);
    				fssAccount=fssAccountService.createFssAccountEntity(dto, customerInfoEntity,fssFuiouAccountEntity.getAccNo());
    			}
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
				throw new FssException("91004013");
			}
    	}
    	accNo=fssAccount.getAccNo();
    	return accNo;
    }
    
    /**
     * 保证金退还
     */
	@Override
	public boolean marginSendBack(MarginDto dto) throws FssException {
		
		
		return true;
	}
  
	}