package com.gqhmt.extServInter.callback.loan;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;
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
 * Create at:   2016年3月7日
 * Description:	借款人放款回盘接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class EnterAccountCallback {
	@Resource
	private FssLoanService fssLoanService;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到入账回调对象
	 */
	public Response getResponse(String mchnNo,String seqNo){

		Response response = null;
		 try {
			 response = fssLoanService.getResponse(mchnNo,seqNo);
			response.setResp_code("0000");
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
		    	response.setResp_code(e.getMessage());
			}
		 return response;
	}
	
}
