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
 * 公司收入实体类
 * @author keyulai
 */
@Entity
@Table(name = "t_gq_fss_accounting_company_income")
public class FssAccountingCompanyIncome implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name = "cust_source") 
	private String  custSource;    
	
	@Column(name = "cust_name") 
	private String  custName;    
	
    @Column(name = "acc_no") 
    private String  accNo;    
    
    @Column(name = "free_acc_no") 
    private String  freeAccNo;    
    
    @Column(name = "accounting_no") 
    private String  accountingNo;    
    
    @Column(name = "fund_type") 
    private String  fundType;    
    
    @Column(name = "busi_no") 
    private String  busiNo;    
    
    @Column(name = "busi_type") 
    private String  busiType;    
    
    @Column(name = "income") 
    private Long  income;    
    
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

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public Long getSendBack() {
		return sendBack;
	}

	public void setSendBack(Long sendBack) {
		this.sendBack = sendBack;
	}

	public String getCustSource() {
		return custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	public String getFreeAccNo() {
		return freeAccNo;
	}

	public void setFreeAccNo(String freeAccNo) {
		this.freeAccNo = freeAccNo;
	}

	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}
	
}
