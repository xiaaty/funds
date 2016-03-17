package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.pay.service.loan.ILoan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 出借系统--开户
 * @author 柯禹来
 */
@Service
public class CreateLoanImpl implements ICreateLoan{
	@Resource
	private ILoan loanImpl;

	@APITradeTypeValid(value = "11020010,11020007,11020009")
	@APISignature
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
