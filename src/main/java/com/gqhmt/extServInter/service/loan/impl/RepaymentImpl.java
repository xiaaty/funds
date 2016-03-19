package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.extServInter.service.loan.IRepayment;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 还款划扣
 * @author 柯禹来
 */
@Service
public class RepaymentImpl implements IRepayment{
	@Resource
	private FssRepaymentService fssRepaymentService;
	
    @Override
	@APITradeTypeValid(value = "11093001,11093002")
	@APISignature
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		response=fssRepaymentService.createRefundDraw((RepaymentDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
