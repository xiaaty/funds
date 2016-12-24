package com.gqhmt.extServInter.service.tender.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.tender.IBidTender;
import com.gqhmt.pay.service.tender.IFundsTender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月11日
 * Description:  投标
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月11日  柯禹来      1.0     1.0 Version
 */
@Service
public class BidTenderImpl implements IBidTender {
	
	@Resource
	private IFundsTender fundsTenderImpl;
	@Override
	@APITradeTypeValid(value = "11050001,11050002,11050003,11050004,11050005,11050006,11050007")
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			BidDto bidDto=(BidDto) dto;
			fundsTenderImpl.bid(bidDto.getTrade_type(), bidDto.getBid_id(),bidDto.getTender_no(),bidDto.getProduct_title(),bidDto.getCust_no(),bidDto.getInvest_type(),bidDto.getReal_Amount(),bidDto.getLoan_cust_id(),bidDto.getMoto_cust_id(),bidDto.getBonus_Amount(),bidDto.getBusi_bid_no(),bidDto.getBusi_no(),bidDto.getSeq_no());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
