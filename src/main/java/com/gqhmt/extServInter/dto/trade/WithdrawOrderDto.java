package com.gqhmt.extServInter.dto.trade;



import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;
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
 * Description: PC端提现订单生成
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
public class WithdrawOrderDto extends SuperDto{
	
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号
	
    
    private BigDecimal amount;	//提现金额
    
    private BigDecimal procedure_fee;		//交易手续费

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getProcedure_fee() {
		return procedure_fee;
	}

	public void setProcedure_fee(BigDecimal procedure_fee) {
		this.procedure_fee = procedure_fee;
	}

    
    
}