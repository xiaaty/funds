package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.LoanApplyDto;
import com.gqhmt.extServInter.dto.trade.PosCallBackResponse;
import com.gqhmt.extServInter.dto.trade.PosSignedDto;
import com.gqhmt.extServInter.dto.trade.PosSignedResponse;
import com.gqhmt.extServInter.service.trade.IPosSigned;
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
 * Create at:   2016年10月18日
 * Description:  签约
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月17日  keyulai      1.0     1.0 Version
 */
@Service
public class PosSignedImpl implements IPosSigned {
	@Resource
	private IFundsTrade fundsTradeImpl;
	@APITradeTypeValid(value = "11020018")//11020018:签约
    @Override
    public Response execute(SuperDto dto) {
		PosSignedResponse response = new PosSignedResponse();
    	try {
			PosSignedDto cDto = (PosSignedDto) dto;
			response=fundsTradeImpl.PosSigned(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getCust_id(),cDto.getCust_type());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
