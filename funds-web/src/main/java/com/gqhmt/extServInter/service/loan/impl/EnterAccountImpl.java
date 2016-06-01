package com.gqhmt.extServInter.service.loan.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.service.loan.IEnterAccount;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
 * Description:	入账
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class EnterAccountImpl implements IEnterAccount {

	@Resource
	private FssEnterAccountService fssEnterAccountService;

	@APITradeTypeValid(value = "11099001,11099002")
	@APISignature
	public Response execute(SuperDto dto) throws APIExcuteErrorException{
    	Response response = new Response();
    	try {
    		fssEnterAccountService.insertEnterAccount((EnterAccountDto) dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
