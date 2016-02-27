package com.gqhmt.extServInter.dto.tender;


import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;

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
	private Long busi_id;		
	private Long busi_no;		
	private String state;
	private Integer  fromCusID;
	private Integer  fromTyp;
	private Integer  toCusID;
	private Integer  toType;
	private BigDecimal amount;
	private Integer sourceId;
	private Integer sourceType;
	private Integer accountType;
	public Long getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(Long busi_id) {
		this.busi_id = busi_id;
	}
	public Long getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(Long busi_no) {
		this.busi_no = busi_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getFromCusID() {
		return fromCusID;
	}
	public void setFromCusID(Integer fromCusID) {
		this.fromCusID = fromCusID;
	}
	public Integer getFromTyp() {
		return fromTyp;
	}
	public void setFromTyp(Integer fromTyp) {
		this.fromTyp = fromTyp;
	}
	public Integer getToCusID() {
		return toCusID;
	}
	public void setToCusID(Integer toCusID) {
		this.toCusID = toCusID;
	}
	public Integer getToType() {
		return toType;
	}
	public void setToType(Integer toType) {
		this.toType = toType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}     
	
}
