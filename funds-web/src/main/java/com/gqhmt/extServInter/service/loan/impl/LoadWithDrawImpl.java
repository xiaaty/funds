package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.service.loan.ILoadWithDraw;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 出借系统--借款人提现
 * @author 柯禹来
 */
@Service
public class LoadWithDrawImpl implements ILoadWithDraw{


	@Resource
	private FssTradeApplyService fssTradeApplyService ;

	@APITradeTypeValid(value = "11091001")
	@APISignature
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {

			LoanWithDrawApplyDto loanDto = (LoanWithDrawApplyDto)dto;

			fssTradeApplyService.createLoanWithdrawApply(loanDto.getTrade_type(),loanDto.getSeq_no(),loanDto.getMchn(), loanDto.getAcc_no(),loanDto.getContract_no(),loanDto.getContract_id(),loanDto.getPay_amt(),loanDto.getBespoke_date());

			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
