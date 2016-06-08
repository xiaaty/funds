package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.extServInter.service.loan.IMarginSendBack;
import com.gqhmt.pay.service.loan.ILoan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 保证金退还
 * @author 柯禹来
 */
@Service
public class MarginSendBackImpl implements IMarginSendBack{
	@Resource
	private ILoan loanImpl;
	
    @Override
	@APITradeTypeValid(value = "11099003")
	@APISignature
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		boolean marginSendBack = loanImpl.marginSendBack((MarginDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
