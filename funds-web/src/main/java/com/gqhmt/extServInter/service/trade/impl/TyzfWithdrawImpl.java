package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.TyzfWithdrawDto;
import com.gqhmt.extServInter.service.trade.ITyzfWithdraw;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年11月10日
 * Description:  统一支付提现
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月10日  keyulai      1.0     1.0 Version
 */
@Service
public class TyzfWithdrawImpl implements ITyzfWithdraw {
	@Resource
	private IFundsTrade iFundsTrade;
	
	@APITradeTypeValid(value = "11040010")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			TyzfWithdrawDto cDto=(TyzfWithdrawDto) dto;
			iFundsTrade.tyzfWithdraw(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getWithdrawAccountId(),cDto.getWithdrawCrdrFlag(),
					cDto.getCapitalAccountId(),cDto.getCapitalCrdrFlag(),cDto.getPostingAmount(),cDto.getPsotingCurrency(),cDto.getRate(),cDto.getAccountType(),cDto.getPostingType());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }

}
