package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.event.account.CreateAccountEvent;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.account.CreateAccountResponse;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
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
	private CreateAccountEvent createAccountEvent;
	

	/**
	 * 11020001:wap开户
	 * 11020002:web开户
	 * 11020003:安卓开户
	 * 11020004:微信开户
	 * 11020005:ios开户
	 * 11020006:委托出借开户
	 * 11020007:借款人开户（冠e通）
	 * 11020008:代偿人开户
	 * 11020009:抵押权人开户
	 * 11020010:保理合同开户
	 * 11020014:开互联网账户
	 * 11020015:app开户
	 * 11020017:新版wap开户
	 */
	@APITradeTypeValid(value = "11020001,11020002,11020003,11020004,11020005,11020006,11020007,11020008,11020009,11020010,11020014,11020015,11020017")
    @Override
    public Response execute(SuperDto dto) {
    	CreateAccountResponse response = new CreateAccountResponse();
    	try {
			CreateAccountDto cDto = (CreateAccountDto)dto;
			FssAccountEntity fssAccountEntity = createAccountEvent.createAccount(cDto.getTrade_type(),cDto.getName(),cDto.getMobile(),cDto.getCert_no(),
					Long.parseLong(cDto.getCust_no()),cDto.getMchn(),cDto.getBank_id(),cDto.getBank_card(),cDto.getCity_id(),cDto.getBusi_no(),null);
    		response.setId(fssAccountEntity.getBankId().intValue());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.debug(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
