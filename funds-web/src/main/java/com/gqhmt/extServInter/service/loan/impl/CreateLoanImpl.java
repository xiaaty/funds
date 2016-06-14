package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.event.account.CreateAccountEvent;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.pay.service.loan.ILoan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 出借系统--开户
 * @author 柯禹来
 */
@Service
public class CreateLoanImpl implements ICreateLoan{
//	@Resource
//	private ILoan loanImpl;


	@Resource(mappedName="event.account.create")
	private CreateAccountEvent createAccountEvent;


	/**
	 * 11020001:wap开户
	 * 11020002:web开户
	 * 11020003:安卓开户
	 * 11020004:微信开户
	 * 11020005:ios开户
	 * 11020006:委托出借开户
	 * 11020007:借款人开户（冠e通）
	 * 11020008:代偿人开户
	 * 11020009:抵押权人开户
	 * 11020010:保理合同开户
	 * 11020011:借款人（纯线下）开户
	 * 11020012:借款人开户（借款系统）
	 * 11020013:借款代还人开户
	 * 11020014:开互联网账户
	 * 11020015:app开户
	 */
	@APITradeTypeValid(value = "11020001,11020002,11020003,11020004,11020005,11020006,11020007,11020008,11020009,11020010,11020011,11020012,11020013,11020014,11020015")
	@APISignature
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	LoanAccountResponse response = new LoanAccountResponse();
    	try {
			CreateLoanAccountDto loanDto = 	(CreateLoanAccountDto)dto;
    		//String accNo=loanImpl.createLoanAccount((CreateLoanAccountDto)dto);
			FssAccountEntity fssAccountEntity = createAccountEvent.createAccount(loanDto.getTrade_type(),loanDto.getName(),loanDto.getMobile(),loanDto.getCert_no(),
					null,loanDto.getMchn(),loanDto.getBank_id(),loanDto.getBank_card(),loanDto.getCity_id(),loanDto.getContract_no(),null);
    		response.setAccNo(fssAccountEntity.getAccNo());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
