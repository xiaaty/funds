package com.gqhmt.extServInter.dto.trade;



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
 * Description: APP（wap）端提现交易
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
public class WithdrawDto extends SuperDto{
	
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号
	

    private BigDecimal amt;			//合同金额
    
    
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




	public BigDecimal getCharge_amt() {
		return charge_amt;
	}

	public void setCharge_amt(BigDecimal charge_amt) {
		this.charge_amt = charge_amt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
}