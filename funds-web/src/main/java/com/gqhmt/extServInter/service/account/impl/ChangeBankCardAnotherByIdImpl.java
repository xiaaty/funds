package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardAnotherDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardByIdDto;
import com.gqhmt.extServInter.service.account.IChangeBankCardAnotherById;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年07月26日
 * Description:	银行卡变更
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年07月26日  xdw      1.0     1.0 Version
 */
@Service
public class ChangeBankCardAnotherByIdImpl implements IChangeBankCardAnotherById {
	
	@Resource
	private FssChangeCardService fssChangeCardService;
	
	@APITradeTypeValid(value = "11029003")//互联网账户银行卡变更
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
			if(dto instanceof UpdateBankCardAnotherDto){
				UpdateBankCardAnotherDto updateBankCardAnotherDto=(UpdateBankCardAnotherDto) dto;
				fssChangeCardService.changeBankCardAnotherById(updateBankCardAnotherDto.getBankId(),updateBankCardAnotherDto.getBankNo(),updateBankCardAnotherDto.getCityId(),updateBankCardAnotherDto.getBankSortName(),updateBankCardAnotherDto.getFilePath());
				response.setResp_code("0000");
			}
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
