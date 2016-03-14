package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.extServInter.service.loan.IRepayment;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
/**
 * 还款划扣
 * @author 柯禹来
 */
@Service
public class RepaymentImpl implements IRepayment{
	@Resource
	private FssRepaymentService fssRepaymentService;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	RepaymentResponse response = new RepaymentResponse();
    	try {
    		fssRepaymentService.createRefundDraw((RepaymentDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
