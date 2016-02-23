package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.CreateAccountByFuiouDto;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.IFundsAccount;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * Filename:    com.gqhmt.extServInter.service.account.impl.CreateAccountImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
@Service
public class CreateAccountImpl implements ICreateAccount{
	@Resource
	private IFundsAccount fundsAccountImpl;
	
    @Override
    public Response excute(SuperDto dto) {
    	Response response = new Response();
    	try {
    		fundsAccountImpl.createAccount((CreateAccountByFuiouDto)dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
