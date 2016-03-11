package com.gqhmt.extServInter.dto.loan;
import java.util.List;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  于泳      1.0     1.0 Version
 */
public class RepaymentResponse  extends Response {
	private List<FssRepaymentEntity> repaymentlist;

	public List<FssRepaymentEntity> getRepaymentlist() {
		return repaymentlist;
	}

	public void setRepaymentlist(List<FssRepaymentEntity> repaymentlist) {
		this.repaymentlist = repaymentlist;
	}
	
}
