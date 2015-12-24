package com.gqhmt.fss.architect.account.bean;

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
	private Integer id;
	/**
	 * 交易号码
	 */
	private String tradeNo;
	/**
	 * 交易类型（根据实际情况再定）
	 */
	private Integer tradeType;
	/**
	 * 交易用户ID
	 */
	private Integer userId;
	/**
	 * 交易账户ID
	 */
	private long accountId;
	/**
	 * 收入金额
	 */
	private BigDecimal income;
	/**
	 * 支出金额
	 */
	private BigDecimal spending;
	/**
	 * 账户当前可用金额
	 */
	private BigDecimal usableSum;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 交易时间
	 */
	private Date tradeTime;
	/**
	 * 标的ID
	 */
	private Integer bidId;
	/**
	 * 还款ID
	 */
	private Integer repaymentId;


	private Long bonusAmount;

	public FundTradeEntity() {
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "trade_no", length = 50)
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "trade_type")
	public Integer getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "account_id")
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "income", precision = 18, scale = 2)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "spending", precision = 18, scale = 2)
	public BigDecimal getSpending() {
		return this.spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	@Column(name = "usable_sum", precision = 18, scale = 2)
	public BigDecimal getUsableSum() {
		return this.usableSum;
	}

	public void setUsableSum(BigDecimal usableSum) {
		this.usableSum = usableSum;
	}

	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "trade_time", length = 19)
	public Date getTradeTime() {
		return this.tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name = "bid_id")
	public Integer getBidId() {
		return this.bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	@Column(name = "repayment_id")
	public Integer getRepaymentId() {
		return this.repaymentId;
	}

	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}

	@Column(name = "bonus_amount")
	public Long getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
}
