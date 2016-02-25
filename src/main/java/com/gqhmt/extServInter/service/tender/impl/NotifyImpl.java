package com.gqhmt.extServInter.service.tender.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.dto.tender.BidNotifyDto;
import com.gqhmt.extServInter.service.tender.INotify;
import com.gqhmt.pay.service.IFundsTender;
import com.gqhmt.pay.service.INotifyTender;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
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
 * Description: 标的通知
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
@Service
public class NotifyImpl implements INotify{
	
	@Resource
	private INotifyTender notifyTenderImpl;
	
    public Response excute(SuperDto dto) {
    	Response response = new Response();
    	try {
    		notifyTenderImpl.bignotify((BidNotifyDto)dto);
			 response.setResp_code("00000000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
