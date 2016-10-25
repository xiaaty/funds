package com.gqhmt.extServInter.service.tender.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.tender.INewBidTender;
import com.gqhmt.pay.service.tender.IFundsTender;
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
 * Create at:   2016年7月11日
 * Description:  新手标投标
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月11日  jhz      1.0     1.0 Version
 */
@Service
public class NewBidTenderImpl implements INewBidTender {
	
	@Resource
	private IFundsTender fundsTenderImpl;

	/**
	 * 11051001 新手标web出借
	 * 11051002 新手标wap出借
	 * 11051003 新手标IOS出借
	 * 11051004 新手标安卓出借
	 * 11051005 新手标微信出借
	 * 11051006 新手标委托出借人出借
	 * 11051007 新手标新版wap出借
	 * @param dto
	 * @return
     */
	@Override
	@APITradeTypeValid(value = "11051001,11051002,11051003,11051004,11051005,11051006,11051007")
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			BidDto bidDto=(BidDto) dto;
			fundsTenderImpl.newHandBid(bidDto.getTrade_type(), bidDto.getBid_id(),bidDto.getTender_no(),bidDto.getProduct_title(),bidDto.getCust_no(),bidDto.getInvest_type(),bidDto.getReal_Amount(),bidDto.getLoan_cust_id(),bidDto.getMoto_cust_id(),bidDto.getBonus_Amount(),bidDto.getBusi_bid_no(),bidDto.getBusi_no());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
