package com.gqhmt.extServInter.callback.p2p;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.GetCallBack;
import com.gqhmt.extServInter.dto.p2p.WithHoldApplyResponse;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月25日
 * Description:冠e通委托充值交易回盘
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月25日  柯禹来      1.0     1.0 Version
 */
@Service
public class WithHoldApplyCallback implements GetCallBack{
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	/**
	 * author:柯禹来
	 * time:2016年4月25日
	 * function：得到冠E通代扣申请回调对象
	 */
	public WithHoldApplyResponse getCallBack(String mchn,String seqNo) throws FssException{
			WithHoldApplyResponse response =new WithHoldApplyResponse();
			 try {
				 response=fssTradeApplyService.getWhithHoldApplyResponse(mchn, seqNo);
//				 String resp_msg = Application.getInstance().getDictName(response.getResp_code());
//				 response.setResp_msg(resp_msg);
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
				response.setResp_code(e.getMessage());
			}
		 return response;
	}
	
}
