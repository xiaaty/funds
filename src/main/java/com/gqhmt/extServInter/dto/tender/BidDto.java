package com.gqhmt.extServInter.dto.tender;



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
 * Description: APP（wap）端提现交易
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  jhz      1.0     1.0 Version
 */
public class BidDto extends SuperDto{
	
	private String busi_bid_no;		//标的编号
	
	private String tender_no;			//投标编号
	
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号
	
	private String busi_no;		//出借业务编
	
	private BigDecimal amount;		//出借金额
	
	private BigDecimal fact_amount;		//实际出借金额
	
	private BigDecimal red_packet;		//抵扣红包金额

	public String getBusi_bid_no() {
		return busi_bid_no;
	}

	public void setBusi_bid_no(String busi_bid_no) {
		this.busi_bid_no = busi_bid_no;
	}

	public String getTender_no() {
		return tender_no;
	}

	public void setTender_no(String tender_no) {
		this.tender_no = tender_no;
	}

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

	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFact_amount() {
		return fact_amount;
	}

	public void setFact_amount(BigDecimal fact_amount) {
		this.fact_amount = fact_amount;
	}

	public BigDecimal getRed_packet() {
		return red_packet;
	}

	public void setRed_packet(BigDecimal red_packet) {
		this.red_packet = red_packet;
	}

	
	
    


    
    
}