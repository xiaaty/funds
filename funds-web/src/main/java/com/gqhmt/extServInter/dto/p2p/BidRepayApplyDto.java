package com.gqhmt.extServInter.dto.p2p;


import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;
/**
 *
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月23日
 * Description:
 * <p>	冠e通后台 满标,流标
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日  jhz      1.0     1.0 Version
 */
public class BidRepayApplyDto extends SuperDto{

	private String busi_bid_no;	//标的编号

	private String user_id;	//借款人客户id

	private String mortgagee_user_id;	//抵押权人客户id

	private String contract_no;	//合同号

	private BigDecimal contract_amt;	//合同金额

	private BigDecimal payment_amt;	//放款金额

	private BigDecimal contract_interest;	//合同利息


	public String getBusi_bid_no() {
		return busi_bid_no;
	}

	public void setBusi_bid_no(String busi_bid_no) {
		this.busi_bid_no = busi_bid_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMortgagee_user_id() {
		return mortgagee_user_id;
	}

	public void setMortgagee_user_id(String mortgagee_user_id) {
		this.mortgagee_user_id = mortgagee_user_id;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public BigDecimal getContract_amt() {
		return contract_amt;
	}

	public void setContract_amt(BigDecimal contract_amt) {
		this.contract_amt = contract_amt;
	}

	public BigDecimal getPayment_amt() {
		return payment_amt;
	}

	public void setPayment_amt(BigDecimal payment_amt) {
		this.payment_amt = payment_amt;
	}

	public BigDecimal getContract_interest() {
		return contract_interest;
	}

	public void setContract_interest(BigDecimal contract_interest) {
		this.contract_interest = contract_interest;
	}

}