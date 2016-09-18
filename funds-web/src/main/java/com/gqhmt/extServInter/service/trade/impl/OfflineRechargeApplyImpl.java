package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.trade.OfflineRechargeApplyDto;
import com.gqhmt.extServInter.dto.trade.OfflineRechargeResponse;
import com.gqhmt.extServInter.service.trade.IOfflineRechargeApply;
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
 * Create at:   2016年6月12日
 * Description:  线下充值
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月12日  keyulai      1.0     1.0 Version
 */
@Service
public class OfflineRechargeApplyImpl implements IOfflineRechargeApply {
	@Resource
	private IFundsTrade fundsTradeImpl;
	@APITradeTypeValid(value = "11030015,11030017")
    @Override
    public Response execute(SuperDto dto) {
    	OfflineRechargeResponse response = new OfflineRechargeResponse();
    	try {
			OfflineRechargeApplyDto cDto = (OfflineRechargeApplyDto)dto;
			response=fundsTradeImpl.OfflineRechargeApply(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getCust_id(),cDto.getCust_type(),cDto.getBusi_no(),cDto.getAmt());
			 response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
