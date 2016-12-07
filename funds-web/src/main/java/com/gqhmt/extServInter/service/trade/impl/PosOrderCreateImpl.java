package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.extServInter.service.trade.IOfflineRechargeApply;
import com.gqhmt.extServInter.service.trade.IPosOrderCreate;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年10月17日
 * Description:  pos充值订单创建
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月17日  keyulai      1.0     1.0 Version
 */
@Service
public class PosOrderCreateImpl implements IPosOrderCreate {
	@Resource
	private IFundsTrade fundsTradeImpl;
	@APITradeTypeValid(value = "11030019")//11030019:pos充值
    @Override
    public Response execute(SuperDto dto) {
		PosCallBackResponse response = new PosCallBackResponse();
    	try {
			PosOrderCreateDto cDto = (PosOrderCreateDto)dto;
			response=fundsTradeImpl.PosOrderCreateApply(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getCert_no(),cDto.getBusi_no(),cDto.getAmt(),cDto.getBusi_type());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
