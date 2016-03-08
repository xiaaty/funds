package com.gqhmt.extServInter.callback.loan;

import javax.annotation.Resource;

import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
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
 * Description:	抵押权人提现回盘
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public class LendWithDrawCallback {
	@Resource
	private FssLoanService fssLoanService;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到抵押权人提现回盘对象
	 */
	public MortgageeWithDrawRespons getResponse(String mchnNo,String seqNo){
		
		 MortgageeWithDrawRespons response = fssLoanService.getMortgageeWithDrawRespons(mchnNo,seqNo) ;
		 if(response!=null){
			 response.setResp_code("00000000");
			 response.setResp_code("成功");
		 }else{
			 
			 response.setResp_code("90002001");
			 response.setResp_msg("账户不存在");
		 }
		 return response;
	}
	
}
