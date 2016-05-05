package com.gqhmt.business.architect.loan.bean;

import java.math.BigDecimal;

public class RepaymentBean {

	// 1:A0,2:AX
	private int customerId;
	private BigDecimal repaymentPrincipal;              //本金
	private BigDecimal repaymentInterest;               //利息
	private BigDecimal repaymentAmount;                 //本息
	private BigDecimal repaymentExtrinterest;			//额外利息
	// 投标类型(1.线上,2.线下)
	private int investType;
	
	//线下出借id
	private int investmentId;
	
	//还款期数
	private int thePeriod;
	
	//往线下出借应付款账户打钱的金额
	private BigDecimal  payableAmount = BigDecimal.ZERO;

	private Integer tenderId;
	
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getRepaymentPrincipal() {
		return repaymentPrincipal;
	}

	public void setRepaymentPrincipal(BigDecimal repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}

	public BigDecimal getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(BigDecimal repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public int getInvestType() {
		return investType;
	}

	public void setInvestType(int investType) {
		this.investType = investType;
	}

	/**
	 * @return the investmentId
	 */
	public int getInvestmentId() {
		return investmentId;
	}

	/**
	 * @param investmentId the investmentId to set
	 */
	public void setInvestmentId(int investmentId) {
		this.investmentId = investmentId;
	}

	/**
	 * @return the thePeriod
	 */
	public int getThePeriod() {
		return thePeriod;
	}

	/**
	 * @param thePeriod the thePeriod to set
	 */
	public void setThePeriod(int thePeriod) {
		this.thePeriod = thePeriod;
	}

	/**
	 * @return the payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * @param payableAmount the payableAmount to set
	 */
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}


	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getRepaymentExtrinterest() {
		return repaymentExtrinterest;
	}

	public void setRepaymentExtrinterest(BigDecimal repaymentExtrinterest) {
		this.repaymentExtrinterest = repaymentExtrinterest;
	}
}
