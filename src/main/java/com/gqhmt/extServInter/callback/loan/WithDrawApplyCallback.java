package com.gqhmt.extServInter.callback.loan;

import javax.annotation.Resource;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.pay.service.loan.IWithDrawApply;
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
public class WithDrawApplyCallback{
//	完成抵押标借款人提现后，通知借款系统 返回 seqNo 流水号 和 mchn  商户号 
	@Resource
	private IWithDrawApply withDrawApplyImpl;
    
    public Response withDrasApplyCallBack() throws FssException{
    	Response response = new Response();
    	try {
    		LoanWithDrawApplyDto dto=new LoanWithDrawApplyDto();
			String seqNo=dto.getSeq_no();
			String mchn=dto.getMchn();
			withDrawApplyImpl.withDrasApplyCallBack(seqNo,mchn);
			response.setResp_code("0000");
		} catch (FssException e) {
			response.setResp_code(e.getMessage());
		}
    	return response;
    }
    
    
}
