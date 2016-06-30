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
 * Create at:   2016年6月28日
 * Description:代偿接口参数
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月28日  柯禹来      1.0     1.0 Version
 */
public class CompensationDto extends SuperDto{

	@APIValidNull(errorCode = "90002006")
	private Integer cust_no;		//出账客户编号
	@APIValidNull(errorCode = "90004033")
	private Integer cust_type;		//出账账户类型
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;		//代偿金额
	private Integer  funds_type;//交易子类型
	private Integer  busi_type;//业务类型
	private Long  busi_id;//账户类型
	private String mark;//从对公账户进还是出

	public Integer getCust_no() {
		return cust_no;
	}

	public void setCust_no(Integer cust_no) {
		this.cust_no = cust_no;
	}

	public Integer getCust_type() {
		return cust_type;
	}

	public void setCust_type(Integer cust_type) {
		this.cust_type = cust_type;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Integer getFunds_type() {
		return funds_type;
	}

	public void setFunds_type(Integer funds_type) {
		this.funds_type = funds_type;
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
