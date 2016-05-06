package com.gqhmt.sftp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月6日
 * Description:
 * <p>标的放款明细文件实体
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_sftp_credit_info")
public class FssCreditInfoEntity implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_number") 
    private int  totalNumber;  //总笔数',
    
    @Column(name = "target_id") 
    private Long targetId;	//标的号(项目id)',

    @Column(name = "trade_time")
    private String  tradeTime;   //交易时间
    
    @Column(name = "trade_type")
    private String tradeType;		//交易类型
    
    @Column(name = "cust_name")
    private String custName;		//投资人姓名,
    
    @Column(name = "cert_type")
    private String certType;		//投资人证件类型
    
    @Column(name = "cert_no")
    private String certNo;			//投资人证件号码
    
    @Column(name = "loan_id")
    private String loanId;		//支付机构借款人ID
    
    @Column(name = "loan_name")
    private String loanName;		//借款人姓名
    
    @Column(name = "loan_cert_no")
    private String loanCertNo;			//借款人身份证号码
    
    @Column(name = "pay_amount")
    private BigDecimal payAmount;		//该笔放款金额'
    
    @Column(name = "repayment_capital")
    private BigDecimal repaymentCapital;		//该笔还款本金，单位：元（保留两位小数）'
    
    @Column(name = "repayment_interest")
    private BigDecimal repaymentInterest;		//该笔还款利息，单位：元（保留两位小数）'

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanCertNo() {
		return loanCertNo;
	}

	public void setLoanCertNo(String loanCertNo) {
		this.loanCertNo = loanCertNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getRepaymentCapital() {
		return repaymentCapital;
	}

	public void setRepaymentCapital(BigDecimal repaymentCapital) {
		this.repaymentCapital = repaymentCapital;
	}

	public BigDecimal getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(BigDecimal repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}
    
    
    
}