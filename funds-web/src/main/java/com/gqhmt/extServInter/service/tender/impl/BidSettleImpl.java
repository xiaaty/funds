package com.gqhmt.extServInter.service.tender.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.tender.FullBidDto;
import com.gqhmt.extServInter.service.tender.IBidSettle;
import com.gqhmt.pay.service.tender.IFundFullTender;
import org.springframework.stereotype.Service;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description:  满标
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
@Service
public class BidSettleImpl implements IBidSettle {
	
//	@Resource
	private IFundFullTender fundFullTenderImpl;
	
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
    		fundFullTenderImpl.settle((FullBidDto)dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
