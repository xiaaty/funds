package com.gqhmt.extServInter.callback.loan;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
/**
 * Filename:    com.gqhmt.extServInter.callback.loan.ChangeBankCallback
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:32
 * Description:借款人提现通知
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
@Service
public class WithDrawApplyCallback implements GetCallBack{
//	完成抵押标借款人提现后，通知借款系统 
	@Resource
	private FssTradeApplyService fssTradeApplyService;
    
    public WithDrawApplyResponse getCallBack(String seqNo,String mchn) throws FssException{
    	WithDrawApplyResponse response = new WithDrawApplyResponse();
    	try {
    		response=fssTradeApplyService.withDrasApplyCallBack(seqNo,mchn);
    		response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			response.setResp_code(e.getMessage());
		}
    	return response;
    }
    
    
}
