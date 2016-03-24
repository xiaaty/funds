package com.gqhmt.extServInter.dto.loan;

import java.math.BigDecimal;

import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;
/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:退还保证金接口参数
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  柯禹来      1.0     1.0 Version
 */
public class MarginDto extends SuperDto {
	
	@APIValidNull(errorCode = "90002016")
    private String contract_id; //合同Id
	
	@APIValidNull(errorCode = "90002022")
	private String contract_no;			//合同编号
	
	@APIValidNull(errorCode = "90004023")
    private String acc_no;       //资金平台账号
	
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
    private BigDecimal refund_amt;   //退还保证金金额
	
	
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	public BigDecimal getRefund_amt() {
		return refund_amt;
	}
	public void setRefund_amt(BigDecimal refund_amt) {
		this.refund_amt = refund_amt;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	
}
