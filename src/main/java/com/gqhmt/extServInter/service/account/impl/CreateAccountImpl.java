package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.account.CreateAccountResponse;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

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
	
	/**
	 * 11020001:wap开户
	 * 11020002:web开户
	 * 11020003:安卓开户
	 * 11020004:微信开户
	 * 11020005:ios开户
	 * 11020006:委托出借开户
	 * 11020007:借款人开户（冠e通）
	 * 11020014:开互联网账户
	 */
	@APITradeTypeValid(value = "11020001,11020002,11020003,11020004,11020005,11020006,11020007,11020014")
    @Override
    public Response execute(SuperDto dto) {
    	CreateAccountResponse response = new CreateAccountResponse();
    	try {
    		Integer id=fundsAccountImpl.createFundAccount((CreateAccountDto)dto);
    		response.setId(id);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.debug(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
