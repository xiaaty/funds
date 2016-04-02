package com.gqhmt.extServInter.callback.loan;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;

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
 * Description:	入账回盘
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Service
public class EnterAccountCallback {
	@Resource
	private FssEnterAccountService fssEnterAccountService;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到入账回调对象
	 */
	public EnterAccountResponse getResponse(String mchnNo,String seqNo){

		EnterAccountResponse enterAccountResponse = null;
		 try {
			 enterAccountResponse = fssEnterAccountService.getResponse(mchnNo,seqNo);
			 enterAccountResponse.setResp_code("0000");
			} catch (FssException e) {
				LogUtil.info(this.getClass(), e.getMessage());
				enterAccountResponse.setResp_code(e.getMessage());
			}
		 return enterAccountResponse;
	}
	
}
