package com.gqhmt.extServInter.dto.trade;



import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月29日
 * Description:债权转让接口参数
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月29日  柯禹来      1.0     1.0 Version
 */
public class BondTransferDto extends SuperDto{
	private String bid_id;//标的业务编号
	private String busi_bid_no;//标的编号
	private String tender_no;//投标编号
	@APIValidNull(errorCode = "90002006")
	private String cust_no;//接标人客户编号
	private String busi_no;//接标人出借业务编号
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;//转让总金额
	private String o_tender_no;//原投标编号
	private String o_cust_no;//转让人客户编号
	private String o_busi_no;//转让人出借业务编号

	public String getBid_id() {
		return bid_id;
	}

	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}

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

	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getO_tender_no() {
		return o_tender_no;
	}

	public void setO_tender_no(String o_tender_no) {
		this.o_tender_no = o_tender_no;
	}

	public String getO_cust_no() {
		return o_cust_no;
	}

	public void setO_cust_no(String o_cust_no) {
		this.o_cust_no = o_cust_no;
	}

	public String getO_busi_no() {
		return o_busi_no;
	}

	public void setO_busi_no(String o_busi_no) {
		this.o_busi_no = o_busi_no;
	}
}
