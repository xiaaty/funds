package com.gqhmt.extServInter.service.loan.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.extServInter.service.loan.IMortgageeWithDraw;
import com.gqhmt.pay.service.loan.ILoan;

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
 * Description:	抵押权人提现接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class MortgageeWithDrawImpl implements IMortgageeWithDraw {

	@Resource
	private ILoan loanImpl;
	
    public Response excute(SuperDto dto) {
    	Response response = new Response();
    	try {
    		loanImpl.mortgageeWithDraw((MortgageeWithDrawDto)dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
