package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.extServInter.service.loan.ILoadWithDraw;
import com.gqhmt.pay.service.loan.IWithDrawApply;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 出借系统--借款人提现
 * @author 柯禹来
 */
@Service
public class LoadWithDrawImpl implements ILoadWithDraw{
	@Resource
	private IWithDrawApply withDrawApplyImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	WithDrawApplyResponse response = new WithDrawApplyResponse();
    	try {
    		withDrawApplyImpl.createWithDrawApply((LoanWithDrawApplyDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
