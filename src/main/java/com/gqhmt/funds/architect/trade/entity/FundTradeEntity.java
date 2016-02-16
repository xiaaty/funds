package com.gqhmt.funds.architect.trade.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.p2p.account.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 15:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fund_trade")
public class FundTradeEntity {

	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 交易号码
	 */
	@Column(name = "trade_no", length = 50)
	private String tradeNo;
	/**
	 * 交易类型（根据实际情况再定）
	 */
	@Column(name = "trade_type")
	private Integer tradeType;
	/**
	 * 交易用户ID
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 交易账户ID
	 */
	@Column(name = "account_id")
	private long accountId;
	/**
	 * 收入金额
	 */
	@Column(name = "income", precision = 18, scale = 2)
	private BigDecimal income;
	/**
	 * 支出金额
	 */
	@Column(name = "spending", precision = 18, scale = 2)
	private BigDecimal spending;
	/**
	 * 账户当前可用金额
	 */
	@Column(name = "usable_sum", precision = 18, scale = 2)
	private BigDecimal usableSum;
	/**
	 * 备注
	 */
	@Column(name = "remarks", length = 500)
	private String remarks;
	/**
	 * 交易时间
	 */
	@Column(name = "trade_time", length = 19)
	private Date tradeTime;
	/**
	 * 标的ID
	 */
	@Column(name = "bid_id")
	private Integer bidId;
	/**
	 * 还款ID
	 */
	@Column(name = "repayment_id")
	private Integer repaymentId;

	@Column(name = "bonus_amount")
	private Long bonusAmount;

	public FundTradeEntity() {
	}


	
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

	
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	
	public BigDecimal getSpending() {
		return this.spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	
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

	
	public Long getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
}
