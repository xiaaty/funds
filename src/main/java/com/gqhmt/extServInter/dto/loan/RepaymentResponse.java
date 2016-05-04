package com.gqhmt.extServInter.dto.loan;
import java.util.List;

import com.gqhmt.extServInter.dto.Response;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  柯禹来      1.0     1.0 Version
 */
public class RepaymentResponse  extends Response {
	
	private List<RepaymentChildDto> repay_list;

	public List<RepaymentChildDto> getRepay_list() {
		return repay_list;
	}

	public void setRepay_list(List<RepaymentChildDto> repay_list) {
		this.repay_list = repay_list;
	}

	
}
