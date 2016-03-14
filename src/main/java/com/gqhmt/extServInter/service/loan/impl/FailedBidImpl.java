package com.gqhmt.extServInter.service.loan.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.service.loan.IFailedBid;
import com.gqhmt.fss.architect.loan.service.FssLoanService;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:	流标
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class FailedBidImpl implements IFailedBid {

	@Resource
	private FssLoanService loanService;
	
    public Response excute(SuperDto dto) {
    	Response response = new Response();
    	try {
    		loanService.insertfailedBidDto((FailedBidDto)dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
