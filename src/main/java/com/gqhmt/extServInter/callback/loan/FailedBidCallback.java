package com.gqhmt.extServInter.callback.loan;

import javax.annotation.Resource;

import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.fss.architect.loan.service.FssLoanService;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月8日
 * Description:	流标回盘接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月8日  jhz      1.0     1.0 Version
 */
public class FailedBidCallback {
	@Resource
	private FssLoanService fssLoanService;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到流标回调对象
	 */
	public FailedBidResponse getResponse(String mchnNo,String seqNo){
		
		 FailedBidResponse failedBidResponse = fssLoanService.getFailedBidResponse(mchnNo, seqNo);
		 if(failedBidResponse!=null){
			 failedBidResponse.setResp_code("00000000");
		 }else{
			 
		 }
		 return failedBidResponse;
	}
	
}
