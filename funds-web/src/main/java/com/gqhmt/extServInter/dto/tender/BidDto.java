package com.gqhmt.extServInter.dto.tender;



import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月11日
 * Description: 投标接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月11日  柯禹来      1.0     1.0 Version
 */
public class BidDto extends SuperDto{


	@APIValidNull(errorCode = "90004014")
	private String bid_id;

	@APIValidNull(errorCode = "90004014")
	private String busi_bid_no;		//标的编号

	@APIValidNull(errorCode = "90004014")
	private String tender_no;			//投标编号

	@APIValidNull(errorCode = "90004014")
	private String cust_no;		//客户编号


	@APIValidNull(errorCode = "90004014")
	private String  loan_cust_id;

	private String  moto_cust_id;
	
	private String busi_no;		//出借业务编

	@APIValidNull(errorCode = "90004014")
	private BigDecimal invest_Amount;		//出借金额

	@APIValidNull(errorCode = "90004014")
	private BigDecimal real_Amount;		//实际出借金额

	@APIValidNull(errorCode = "90004014")
	private BigDecimal bonus_Amount;		//抵扣红包金额

	@APIValidNull(errorCode = "90004014")
	private int invest_type;				//投标类型 1,线上,2线下

	private String product_title;

	public BidDto() {
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


	public BigDecimal getInvest_Amount() {
		return invest_Amount;
	}

	public void setInvest_Amount(BigDecimal invest_Amount) {
		this.invest_Amount = invest_Amount;
	}

	public BigDecimal getReal_Amount() {
		return real_Amount;
	}

	public void setReal_Amount(BigDecimal real_Amount) {
		this.real_Amount = real_Amount;
	}


	public BigDecimal getBonus_Amount() {
		return bonus_Amount;
	}

	public void setBonus_Amount(BigDecimal bonus_Amount) {
		this.bonus_Amount = bonus_Amount;
	}


	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getBid_id() {
		return bid_id;
	}

	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}

	public int getInvest_type() {
		return invest_type;
	}

	public void setInvest_type(int invest_type) {
		this.invest_type = invest_type;
	}

	public String getLoan_cust_id() {
		return loan_cust_id;
	}

	public void setLoan_cust_id(String loan_cust_id) {
		this.loan_cust_id = loan_cust_id;
	}

	public String getMoto_cust_id() {
		return moto_cust_id;
	}

	public void setMoto_cust_id(String moto_cust_id) {
		this.moto_cust_id = moto_cust_id;
	}
}