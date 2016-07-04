package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.CompensationDto;
import com.gqhmt.extServInter.service.trade.ICompensation;
import com.gqhmt.pay.service.cost.ICost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月28日
 * Description:  代偿接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月28日  keyulai      1.0     1.0 Version
 */
@Service
public class CompensationImpl implements ICompensation {
	@Resource
	private ICost costImpl;


	/**
	 * 1107000:借款人逾期代偿
	 * 11070002:借款人逾期代偿资金退回
	 * 11070003:委托出借人代偿
	 * 11070004:委托出借代偿退回
	 * @param dto
	 * @return
     */
	@APITradeTypeValid(value = "11070001,11070002,11070003,11070004")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			CompensationDto cDto = (CompensationDto)dto;
			costImpl.compensation(cDto.getTrade_type(),cDto.getCust_no(),
					cDto.getCust_type(),cDto.getAmt(),cDto.getFunds_type(),cDto.getBusi_type(),cDto.getBusi_id(),cDto.getMark());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
