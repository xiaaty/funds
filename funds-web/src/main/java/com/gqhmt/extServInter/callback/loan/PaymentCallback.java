package com.gqhmt.extServInter.callback.loan;


import javax.annotation.Resource;

import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
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
public class PaymentCallback implements GetCallBack{
//	完成划扣流程后，通知借款系统 
	@Resource
	private FssRepaymentService fssRepaymentService;
	@Resource
	private PaymentProcessCallback paymentProcessCallback;

    public RepaymentResponse getCallBack(String mchn,String seqNo) throws FssException{
    	RepaymentResponse response = new RepaymentResponse();
    	try {
    		response = fssRepaymentService.rePaymentCallBack(seqNo,mchn);
		} catch (FssException e) {
			if(StringUtils.equals("9999",e.getMessage())){
    			response = paymentProcessCallback.getCallBack(mchn,seqNo);
			}else{
				response.setResp_code(e.getMessage());
			}
		}
    	return response;
    }
    
    
}
