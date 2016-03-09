package com.gqhmt.extServInter.dto.trade;



import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description: 提现
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
public class WithdrawOrderDto extends SuperDto{

	@APIValidNull(errorCode = "90002006")
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号


	@APIValidNull(errorCode = "90004014")
    private BigDecimal amt;	//提现金额

	@APIValidNull(errorCode = "90004014")
    private BigDecimal charge_amt;		//交易手续费

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}


	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getCharge_amt() {
		return charge_amt;
	}

	public void setCharge_amt(BigDecimal charge_amt) {
		this.charge_amt = charge_amt;
	}
}