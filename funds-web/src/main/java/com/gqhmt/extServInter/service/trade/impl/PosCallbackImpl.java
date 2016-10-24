package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.PosCallBackDto;
import com.gqhmt.extServInter.dto.trade.RechargeSuccessDto;
import com.gqhmt.extServInter.service.trade.IPosCallBack;
import com.gqhmt.extServInter.service.trade.IRechargeCallback;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年10月20日
 * Description: POS充值成功回调
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月20日  keyulai      1.0     1.0 Version
 */
@Service
public class PosCallbackImpl implements IPosCallBack {
	@Resource
	private IFundsTrade fundsTradeImpl;
	
	@APITradeTypeValid(value = "11030020")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			PosCallBackDto cDto = (PosCallBackDto) dto;
			response=fundsTradeImpl.PosRechargeCallback(cDto.getTrade_type(),cDto.getOrder_no());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
	}
}
