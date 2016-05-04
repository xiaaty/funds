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
 * <p>	冠e通后台 回款
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日  jhz      1.0     1.0 Version
 */
public class RePaymentDto extends SuperDto{
	
	private String busi_bid_no;	//标的编号
	
	private String repayment_no;	//回款编号
	
	private String user_id;	//借款人客户id
	
	private String mortgagee_user_id;	//抵押权人客户id
	
	private String contract_no;	//合同号
	
	private String period;	//期数
	
	private BigDecimal payment_amt;	//回款金额
	
	private String remark;	//备注
	
	private String payment_type;	//回款类型

	public String getBusi_bid_no() {
		return busi_bid_no;
	}

	public void setBusi_bid_no(String busi_bid_no) {
		this.busi_bid_no = busi_bid_no;
	}

	public String getRepayment_no() {
		return repayment_no;
	}

	public void setRepayment_no(String repayment_no) {
		this.repayment_no = repayment_no;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getPayment_amt() {
		return payment_amt;
	}

	public void setPayment_amt(BigDecimal payment_amt) {
		this.payment_amt = payment_amt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}	
	
}