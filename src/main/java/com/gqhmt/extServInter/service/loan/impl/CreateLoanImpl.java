package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.pay.service.loan.ILoan;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 出借系统--开户
 * @author 柯禹来
 */
@Service
public class CreateLoanImpl implements ICreateLoan{
	@Resource
	private ILoan loanImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	LoanAccountResponse response = new LoanAccountResponse();
    	try {
    		String accNo=loanImpl.createLoanAccount((CreateLoanAccountDto)dto);
    		response.setAccNo(accNo);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
