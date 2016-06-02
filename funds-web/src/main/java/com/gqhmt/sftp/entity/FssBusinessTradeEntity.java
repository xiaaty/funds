package com.gqhmt.sftp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:
 * <p>P2P商户交易实体类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_depos_fuiou_busi_trade")
public class FssBusinessTradeEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "parent_id")
	private Long  parentId;


	@Column(name = "mchn")
	private String  mchn;  //商户号

	@Column(name = "third_party_payment_id")
	private String thirdPartyPaymentId;

	@Column(name = "trade_date")
	private String  tradeDate;

	@Column(name = "trade_type")
	private String tradeType;

	@Column(name = "item_no")
	private String itemNo;

	@Column(name = "contract_no")
	private String contractNo;

	@Column(name = "out_fuiou_username")
	private String outFuiouUsername;

	@Column(name = "out_platform_username")
	private String outPlatformUsername;

	@Column(name = "amt")
	private BigDecimal amt;

	@Column(name = "charge")
	private BigDecimal charge;

	@Column(name = "this_repayment_principal")
	private BigDecimal thisRepaymentPrincipal;

	@Column(name = "this_repayment_interest")
	private BigDecimal thisRepaymentInterest;

	@Column(name = "come_fuiou_username")
	private String comeFuiouUsername;

	@Column(name = "come_platform_username")
	private String comePlatformUsername;

	@Column(name = "loan_username")
	private String loanUsername;

	@Column(name = "loan_cert_type")
	private String loanCertType;

	@Column(name = "loan_cert_no")
	private String loanCertNo;

	@Column(name = "lend_username")
	private String lendUsername;

	@Column(name = "lend_fuiou_username")
	private String lendFuiouUsername;

	@Column(name = "lend_name")
	private String lendName;

	@Column(name = "lend_cert_type")
	private String lendCertType;

	@Column(name = "lend_cert_no")
	private String lendCertNo;

	@Column(name = "busi_type")
	private String busiType;

	@Column(name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMchn() {
		return mchn;
	}

	public void setMchn(String mchn) {
		this.mchn = mchn;
	}

	public String getThirdPartyPaymentId() {
		return thirdPartyPaymentId;
	}

	public void setThirdPartyPaymentId(String thirdPartyPaymentId) {
		this.thirdPartyPaymentId = thirdPartyPaymentId;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOutFuiouUsername() {
		return outFuiouUsername;
	}

	public void setOutFuiouUsername(String outFuiouUsername) {
		this.outFuiouUsername = outFuiouUsername;
	}

	public String getOutPlatformUsername() {
		return outPlatformUsername;
	}

	public void setOutPlatformUsername(String outPlatformUsername) {
		this.outPlatformUsername = outPlatformUsername;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getThisRepaymentPrincipal() {
		return thisRepaymentPrincipal;
	}

	public void setThisRepaymentPrincipal(BigDecimal thisRepaymentPrincipal) {
		this.thisRepaymentPrincipal = thisRepaymentPrincipal;
	}

	public BigDecimal getThisRepaymentInterest() {
		return thisRepaymentInterest;
	}

	public void setThisRepaymentInterest(BigDecimal thisRepaymentInterest) {
		this.thisRepaymentInterest = thisRepaymentInterest;
	}

	public String getComeFuiouUsername() {
		return comeFuiouUsername;
	}

	public void setComeFuiouUsername(String comeFuiouUsername) {
		this.comeFuiouUsername = comeFuiouUsername;
	}

	public String getComePlatformUsername() {
		return comePlatformUsername;
	}

	public void setComePlatformUsername(String comePlatformUsername) {
		this.comePlatformUsername = comePlatformUsername;
	}

	public String getLoanUsername() {
		return loanUsername;
	}

	public void setLoanUsername(String loanUsername) {
		this.loanUsername = loanUsername;
	}

	public String getLoanCertType() {
		return loanCertType;
	}

	public void setLoanCertType(String loanCertType) {
		this.loanCertType = loanCertType;
	}

	public String getLoanCertNo() {
		return loanCertNo;
	}

	public void setLoanCertNo(String loanCertNo) {
		this.loanCertNo = loanCertNo;
	}

	public String getLendUsername() {
		return lendUsername;
	}

	public void setLendUsername(String lendUsername) {
		this.lendUsername = lendUsername;
	}

	public String getLendFuiouUsername() {
		return lendFuiouUsername;
	}

	public void setLendFuiouUsername(String lendFuiouUsername) {
		this.lendFuiouUsername = lendFuiouUsername;
	}

	public String getLendName() {
		return lendName;
	}

	public void setLendName(String lendName) {
		this.lendName = lendName;
	}

	public String getLendCertType() {
		return lendCertType;
	}

	public void setLendCertType(String lendCertType) {
		this.lendCertType = lendCertType;
	}

	public String getLendCertNo() {
		return lendCertNo;
	}

	public void setLendCertNo(String lendCertNo) {
		this.lendCertNo = lendCertNo;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}