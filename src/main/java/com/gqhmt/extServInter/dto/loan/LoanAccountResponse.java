package com.gqhmt.extServInter.dto.loan;
import com.gqhmt.extServInter.dto.Response;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  于泳      1.0     1.0 Version
 */
public class LoanAccountResponse  extends Response {
	private String accNo;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	
}
