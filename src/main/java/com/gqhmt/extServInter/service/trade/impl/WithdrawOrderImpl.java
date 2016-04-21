package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.WebOrderResponse;
import com.gqhmt.extServInter.dto.trade.WithdrawOrderDto;
import com.gqhmt.extServInter.service.trade.IWithdrawOrder;
import com.gqhmt.pay.service.trade.IFundsTrade;
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
 * Create at:   2016年2月20日
 * Description: PC端提现订单生成
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
@Service
public class WithdrawOrderImpl implements IWithdrawOrder {
	@Resource
	private IFundsTrade fundsTradeImpl;
	
	@APITradeTypeValid(value = "11040001")//web提现
//	@APISignature
    @Override
    public Response execute(SuperDto dto) {
		WebOrderResponse response = null;
		try {
			response = GenerateBeanUtil.GenerateClassInstance(WebOrderResponse.class,dto);
		} catch (Exception e) {
			response = new WebOrderResponse();
		}
    	try {

			String result = fundsTradeImpl.webWithdrawOrder((WithdrawOrderDto)dto);
			response.setResp_code("0000");
			String[] tmp = result.split(":");
			response.setUsername(tmp[0]);
			response.setOrder_no(tmp[1]);
			response.setMchnt(tmp[2]);
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
