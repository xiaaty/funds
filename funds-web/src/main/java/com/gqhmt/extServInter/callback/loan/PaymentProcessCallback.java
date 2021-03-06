package com.gqhmt.extServInter.callback.loan;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.callback.loan.ChangeBankCallback
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:32
 * Description:还款划扣通知
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  柯禹来      1.0     1.0 Version
 */
@Service
public class PaymentProcessCallback implements GetCallBack{
//	完成划扣流程后，通知借款系统 
	@Resource
	private FssTradeProcessService fssTradeProcessService;

    public RepaymentResponse getCallBack(String mchn,String seqNo) throws FssException{
    	RepaymentResponse response = new RepaymentResponse();
    	try {
    		response = fssTradeProcessService.rePaymentCallBack(seqNo);
		} catch (FssException e) {
			response.setResp_code(e.getMessage());
		}
    	return response;
    }
    
    
}
