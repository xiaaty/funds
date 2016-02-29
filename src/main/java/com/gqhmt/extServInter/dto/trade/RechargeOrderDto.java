package com.gqhmt.extServInter.dto.trade;



import java.math.BigDecimal;

import com.gqhmt.annotations.APIValidNull;
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
 * Description: PC端网银充值订单生成
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
public class RechargeOrderDto extends SuperDto{
	
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号
	
    @APIValidNull(errorCode = "90004014")
    private BigDecimal amount;	//代扣金额
    
    private String bank_id;		//银行类型

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

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
    


}
