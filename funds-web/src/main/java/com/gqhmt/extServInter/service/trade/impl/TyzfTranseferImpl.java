package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.TyzfTransferDto;
import com.gqhmt.extServInter.service.trade.ITyzfTransefer;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年11月10日
 * Description:  统一支付转账接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月10日  柯禹来      1.0     1.0 Version
 */
@Service
public class TyzfTranseferImpl implements ITyzfTransefer{
	
	@Resource
	private IFundsTrade fundsTrade;

	@APITradeTypeValid(value = "11080004")
	@Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			TyzfTransferDto cDto=(TyzfTransferDto)dto;
			fundsTrade.tyzfTransfer(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getFromAccountId(),cDto.getToAccountId(),cDto.getAmount(),
					cDto.getCrdrFlag(),cDto.getPostingCurrency(),cDto.getRate(),cDto.getAccountType(),cDto.getPostingType());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
