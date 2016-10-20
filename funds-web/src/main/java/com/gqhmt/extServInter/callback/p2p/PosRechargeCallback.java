package com.gqhmt.extServInter.callback.p2p;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.GetCallBack;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
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
 * Description:银联卡充值成功回盘
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 *2016年10月17日 柯禹来      1.0     1.0 Version
 */
@Service
public class PosRechargeCallback implements GetCallBack{
	@Resource
	private FssOfflineRechargeService fssOfflineRechargeService;
	/**
	 * author:柯禹来
	 * time:2016年10月17日
	 * function富友回盘成功失败的消息
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
