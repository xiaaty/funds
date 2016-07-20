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
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description:转账接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
public class TransferDto extends SuperDto {

	@APIValidNull(errorCode = "90002006")
	private Integer from_cust_no;//出账客户编号	
	private Integer from_user_no;//出账用户编号
	private Integer from_cust_type;//出账用户编号

	@APIValidNull(errorCode = "90002006")
	private Integer to_cust_no;//入账客户编号
	private Integer to_user_no;//入账用户编号
	private Integer to_cust_type;//入账用户编号

	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt; //转账金额
	private Integer  funds_type;//交易子类型
	private Integer  busi_type;//业务类型
	private Long  busi_id;//账户类型
	private String loan_type;//借款、出借、或其他
	private String contract_no;//合同号

	public Integer getFrom_cust_no() {
		return from_cust_no;
	}

	public void setFrom_cust_no(Integer from_cust_no) {
		this.from_cust_no = from_cust_no;
	}

	public Integer getFrom_user_no() {
		return from_user_no;
	}

	public void setFrom_user_no(Integer from_user_no) {
		this.from_user_no = from_user_no;
	}

	public Integer getFrom_cust_type() {
		return from_cust_type;
	}

	public void setFrom_cust_type(Integer from_cust_type) {
		this.from_cust_type = from_cust_type;
	}

	public Integer getTo_cust_no() {
		return to_cust_no;
	}

	public void setTo_cust_no(Integer to_cust_no) {
		this.to_cust_no = to_cust_no;
	}

	public Integer getTo_user_no() {
		return to_user_no;
	}

	public void setTo_user_no(Integer to_user_no) {
		this.to_user_no = to_user_no;
	}

	public Integer getTo_cust_type() {
		return to_cust_type;
	}

	public void setTo_cust_type(Integer to_cust_type) {
		this.to_cust_type = to_cust_type;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Integer getBusi_type() {
		return busi_type;
	}

	public void setBusi_type(Integer busi_type) {
		this.busi_type = busi_type;
	}

	public Long getBusi_id() {
		return busi_id;
	}

	public void setBusi_id(Long busi_id) {
		this.busi_id = busi_id;
	}

	public Integer getFunds_type() {
		return funds_type;
	}

	public void setFunds_type(Integer funds_type) {
		this.funds_type = funds_type;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
}
