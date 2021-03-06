package com.gqhmt.extServInter.callback.p2p;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.GetCallBack;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.OfflineRechargeResponse;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Filename:
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月14日
 * Description:线下充值回盘
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 *2016年6月14日 柯禹来      1.0     1.0 Version
 */
@Service
public class OfflineRechargeCallback implements GetCallBack{
	@Resource
	private FssOfflineRechargeService fssOfflineRechargeService;
	/**
	 * author:柯禹来
	 * time:2016年6月14日
	 * function 商户银行充值成功后，富友回盘成功失败的消息
	 */
	public Response getCallBack(String mchn, String seqNo) throws FssException{
		Response response =new Response();
			 try {
				 response=fssOfflineRechargeService.getOfflineRechargeResponse(mchn,seqNo);
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
				response.setResp_code(e.getMessage());
			}
		 return response;
	}
	
}
