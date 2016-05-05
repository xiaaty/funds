package com.gqhmt.fss.architect.accounting.entity;

import com.gqhmt.annotations.AutoDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月28日
 * Description:资金流水实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_accounting_funds_flow")
public class FssFundsFlowEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // bigint

    @Column(name="cust_no")
    private String custNo;                  // '客户编号',

    @Column(name="cust_name")
    private String custName;                  // varchar(45) NOT NULL COMMENT '客户姓名,

    @Column(name="acc_no")
    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',

    @Column(name="accounting_no")
    private String accountingNo;           // varchar(200) DEFAULT NULL COMMENT '账务编号，唯一',

    @Column(name="fund_type")
    private String fundType;            // 资金类型,
    
    @Column(name="trade_type")
    private String tradeType;            //交易类型,

    @Column(name="income")
    private Long income;     // 收入金额,
    
    @Column(name="spending")
    private Long spending;     // '支出金额
    
    @Column(name="balance")
    private Long balance;     // '账户余额
    
    @Column(name="currency")
    private String currency;     // 币种：人民币、美元...
    
    @Column(name="lend_no")
    private String lendNo;     // 出借编号
    
    @Column(name="loan_no")
    private String loanNo;     // 借款编号
    
    @Column(name="trade_date")
    private String tradeDate;     // '交易日期,
    
    @Column(name="trade_time")
    private String tradeTime;     // '交易时间,
    
    @Column(name="summary")
    private String summary;     // '交易摘要
    
    @Column(name="order_no")
    private String orderNo;     // '交易流水订单号
    
    @Column(name="pay_channel")
    private String payChannel;     // '交易渠道
    
    @Column(name="create_time")
    @AutoDate
    private Date createTime;               // datetime NOT NULL,

    @Column(name="modify_time")
    @AutoDate
    private Date modifyTime;               // datetime NOT NULL,


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(String accountingNo) {
		this.accountingNo = accountingNo;
	}


	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public Long getSpending() {
		return spending;
	}

	public void setSpending(Long spending) {
		this.spending = spending;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	

}