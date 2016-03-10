package com.gqhmt.extServInter.callback.loan;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public List<EnterAccountResponse> getResponse(List<Map<String,String>> maps){
		
		List<EnterAccountResponse> responses = fssLoanService.getResponse(maps);
		 return responses;
	}
	
}
