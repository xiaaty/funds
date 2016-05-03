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
 * 借款代偿实体类
 * @author keyulai
 */
@Entity
@Table(name = "t_gq_fss_accounting_loan_compensatory")
public class FssAccountingLoanCompensatory implements Serializable{

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
    
    @Column(name = "loan_no") 
    private String  loanNo;    
    
    @Column(name = "loan_acc_no") 
    private String  loanAccNo;    
    
    @Column(name = "fund_type") 
    private String  fundType;    
    
    @Column(name = "compensatory") 
    private String  compensatory;    
    
    @Column(name = "send_back")
    private Long sendBack; 

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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(String accountingNo) {
		this.accountingNo = accountingNo;
	}

	public String getLoanAccNo() {
		return loanAccNo;
	}

	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getCompensatory() {
		return compensatory;
	}

	public void setCompensatory(String compensatory) {
		this.compensatory = compensatory;
	}

	public Long getSendBack() {
		return sendBack;
	}

	public void setSendBack(Long sendBack) {
		this.sendBack = sendBack;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
}
