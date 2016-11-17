package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.PosCallBackDto;
import com.gqhmt.extServInter.dto.trade.SignedCallBackDto;
import com.gqhmt.extServInter.service.trade.IPosCallBack;
import com.gqhmt.extServInter.service.trade.IPosSignedCallBack;
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
 * Description: POS签约成功回调
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月2日  keyulai      1.0     1.0 Version
 */
@Service
public class PosSignedCallbackImpl implements IPosSignedCallBack {
	@Resource
	private IFundsTrade fundsTradeImpl;
	
	@APITradeTypeValid(value = "11020018")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			SignedCallBackDto cDto = (SignedCallBackDto) dto;
			fundsTradeImpl.PosSignedCallback(cDto.getOrder_no(),cDto.getRespCode());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
	}
}
