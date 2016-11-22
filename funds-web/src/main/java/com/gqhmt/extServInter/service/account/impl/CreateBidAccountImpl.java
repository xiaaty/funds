package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.event.account.CreateAccountEvent;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.BidAccountDto;
import com.gqhmt.extServInter.dto.account.VerifiedAccountDto;
import com.gqhmt.extServInter.service.account.ICreateBidAccount;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.service.account.impl.CreateAccountImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:02
 * Description: 标的开户
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/11/22  keyulai      1.0     1.0 Version
 */
@Service
public class CreateBidAccountImpl implements ICreateBidAccount {
	@Resource
	private CreateAccountEvent createAccountEvent;

	/**
	 * 11020019:标的开户
	 */
	@APITradeTypeValid(value = "11020019")
    @Override
    public Response execute(SuperDto dto) {
		Response response=new Response();
    	try {
			BidAccountDto cDto = (BidAccountDto)dto;
			createAccountEvent.createBidAcocunt(cDto.getTrade_type(),cDto.getCust_no(),cDto.getCust_name(),cDto.getCert_no(),cDto.getMobile_phone(),cDto.getContract_no(),cDto.getSeq_no());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.debug(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
