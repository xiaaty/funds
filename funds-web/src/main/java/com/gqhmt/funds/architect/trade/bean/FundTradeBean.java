package com.gqhmt.funds.architect.trade.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gqhmt.core.json.BigDecimalSerialize;
import com.gqhmt.core.util.GlobalConstants;

import java.math.BigDecimal;
import java.util.Date;
/**
 * Filename:    com.gq.p2p.account.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 15:48
 * Description:交易记录数据传输bean
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@SuppressWarnings("serial")
public class FundTradeBean  implements java.io.Serializable{
	private Integer id;
	private String tradeNo;
	private Integer tradeType;
	private Integer userId;
	private long accountId;
	private BigDecimal income;
	private BigDecimal spending;
	private BigDecimal usableSum;
	private String remarks;
	private Date tradeTime;
	private Integer bidId;
	private Integer repaymentId;
	private BigDecimal bonusAmount;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}


	public String getTradeTypeName(){
		return GlobalConstants.fundsType.get(this.getTradeType());


	}

	public Integer getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@JsonSerialize(using = BigDecimalSerialize.class)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@JsonSerialize(using = BigDecimalSerialize.class)
	public BigDecimal getSpending() {
		return this.spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	@JsonSerialize(using = BigDecimalSerialize.class)
	public BigDecimal getUsableSum() {
		return this.usableSum;
	}

	public void setUsableSum(BigDecimal usableSum) {
		this.usableSum = usableSum;
	}

	
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public Date getTradeTime() {
		return this.tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	
	public Integer getBidId() {
		return this.bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	
	public Integer getRepaymentId() {
		return this.repaymentId;
	}

	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}

	
	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
}
