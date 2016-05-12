package com.gqhmt.fss.architect.accounting.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gqhmt.annotations.AutoDate;
/**
 * 出借应付款明细表
 * @author keyulai
 */
@Entity
@Table(name = "t_gq_fss_accounting_lend_payable_detail")
public class FssAccountingLendPayableDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name = "cust_no") 
	private String  custNo;    
	
	@Column(name = "cust_name") 
	private String  custName;    
	
    @Column(name = "acc_no") 
    private String  accNo;    
    
    @Column(name = "accounting_no") 
    private String  accountingNo;    
    
    @Column(name = "lend_no") 
    private String  lendNo;    
    
    @Column(name = "fund_type") 
    private String  fundType;    
    
    @Column(name = "payable_amount")
    private Long payableAmount; 
    
    @Column(name = "paid_amount")
    private Long paidAmount; 
    
    @Column(name = "balance_amount")
    private Long balanceAmount ; 
    
    @Column(name = "create_time")
    @AutoDate
    private Date createTime;   

    @Column(name = "modify_time")
    @AutoDate
    private Date modifyTime;   

    @Column(name = "summary")
    private String summary;   

    @Column(name = "trade_date")
    private String tradeDate;
    
    @Column(name = "trade_time")
    private String tradeTime ;

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

	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public Long getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Long payableAmount) {
		this.payableAmount = payableAmount;
	}

	public Long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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
    
}
