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
 * 出借应付款主表
 * @author keyulai
 */
@Entity
@Table(name = "t_gq_fss_accounting_lend_payable")
public class FssAccountingLendPayable implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name = "cust_no") 
	private String  custNo;    
	
    @Column(name = "acc_no") 
    private String  accNo;    
    
    @Column(name = "accounting_no") 
    private String  accountingNo;    
    
    @Column(name = "lend_no") 
    private String  lendNo;    
    
    @Column(name = "balance")
    private Long balance; 
    
    @Column(name = "pay_total")
    private Long payTotal; 
    
    @Column(name = "paid_totle")
    private Long paidTotle ; 
    
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

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(Long payTotal) {
		this.payTotal = payTotal;
	}

	public Long getPaidTotle() {
		return paidTotle;
	}

	public void setPaidTotle(Long paidTotle) {
		this.paidTotle = paidTotle;
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
