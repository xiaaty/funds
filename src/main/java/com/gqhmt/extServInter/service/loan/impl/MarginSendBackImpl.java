package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.extServInter.dto.loan.MarginResponse;
import com.gqhmt.extServInter.service.loan.IMarginSendBack;
import com.gqhmt.pay.service.loan.IMarginReturn;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
/**
 * 保证金退还
 * @author 柯禹来
 */
@Service
public class MarginSendBackImpl implements IMarginSendBack{
	@Resource
	private IMarginReturn marginReturnImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	MarginResponse response = new MarginResponse();
    	try {
    		marginReturnImpl.marginSendBack((MarginDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
