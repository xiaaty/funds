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
 * Create at:   2016年2月27日
 * Description:PC端网银充值成功入账
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月27日  jhz      1.0     1.0 Version
 */
public class RechargeSuccessDto extends SuperDto{
	
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号
	
	private String order_no;		//第三方交易流水号
	
	private String tradeDate;		//第三方交易日期
	
	private String respCode;		//第三方交易返回码
	
	private String mobile_no;		//第三方交易返回手机号
	
    private BigDecimal amount;	//充值金额
    
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

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
    


}
