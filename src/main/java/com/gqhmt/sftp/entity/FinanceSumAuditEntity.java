package com.gqhmt.sftp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_gq_sftp_finance_sum_audit")
public class FinanceSumAuditEntity implements Serializable {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键\n',

    @Column(name="org_target_id")
    private String orgTargetId;                              // varchar(45) DEFAULT NULL COMMENT '账户编号,唯一，不可更改',

    @Column(name = "org_terrace_id")
    private String orgTerraceId;                     // decimal(17,2) DEFAULT NULL COMMENT '账户余额',

    @Column(name = "cust_no")
    private String custNo;                      // decimal(17,2) DEFAULT NULL COMMENT '冻结金额',

    @Column(name = "cust_name")
    private String custName;                        // decimal(17,2) DEFAULT NULL COMMENT '可用余额',

    @Column(name = "cert_no")
    private String certNo;                      // decimal(17,2) DEFAULT NULL COMMENT '未转结余额',

    @Column(name = "target_state")
    private String targetState;                             // varchar(45) NOT NULL COMMENT '客户编号',

    @Column(name = "tender_time")
    private String tenderTime;                             // varchar(45) NOT NULL COMMENT '用户表编号',
    
    @Column(name = "full_scale_time")
    private String fullScaleTime;                           //datetime DEFAULT NULL COMMENT '创建时间',

    @Column(name = "t_re_captical")
    private BigDecimal tReCaptical;                           // datetime DEFAULT NULL COMMENT '最后修改时间',

    @Column(name = "t_re_interest")
    private BigDecimal tReInterest;                           // int(11) DEFAULT NULL COMMENT '账户类型，1借款账户；2线下出借账户；3线上账户；4抵押权人账户；5代偿人账户；99，冻结账户100公司账户',

    @Column(name = "l_rep_time")
    private String lRepaymentTime;                              // int(11) DEFAULT NULL COMMENT '账户状态：1，有效账户，2，账户锁定，3账户注销申请，4账户注销',

    @Column(name = "a_re_captical")
    private BigDecimal aReCaptical;                         // varchar(45) DEFAULT NULL COMMENT '渠道编号，绑定渠道',

    @Column(name = "a_re_interest")
    private BigDecimal aReInterest;                            // varchar(45) DEFAULT NULL COMMENT '对应的业务编号，出借编号，借款编号，互联网用户编号。。。。',

    @Column(name="today_re_captical")
    private BigDecimal todayReCaptical;             // varchar(45) NOT NULL COMMENT '大商户号',

    @Column(name="today_re_interest")
    private BigDecimal todayReInterest;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
    
    @Column(name="e_re_captical")
    private BigDecimal eReCaptical;              // 跟t_gq_custinfo_info表中的id对应

	@Column(name="e_re_interest")
	private BigDecimal eReInterest;			//第三方账户编号

	@Column(name = "paid_sum")
	private BigDecimal paidSum;
	
	@Column(name="credit_sum")
	private BigDecimal creditSum;			//第三方账户编号

	@Column(name = "debt_sum")
	private BigDecimal debtSum;

	@Column(name="t_credit_sum")
	private BigDecimal tCreditSum;

	@Column(name = "audit_state")
	private String auditState;

	@Column(name="freeze_sum")
	private BigDecimal freezeSum;
	
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public BigDecimal getFreezeSum() {
		return freezeSum;
	}

	public void setFreezeSum(BigDecimal freezeSum) {
		this.freezeSum = freezeSum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgTargetId() {
		return orgTargetId;
	}

	public void setOrgTargetId(String orgTargetId) {
		this.orgTargetId = orgTargetId;
	}

	public String getOrgTerraceId() {
		return orgTerraceId;
	}

	public void setOrgTerraceId(String orgTerraceId) {
		this.orgTerraceId = orgTerraceId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getTargetState() {
		return targetState;
	}

	public void setTargetState(String targetState) {
		this.targetState = targetState;
	}

	public String getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}

	public String getFullScaleTime() {
		return fullScaleTime;
	}

	public void setFullScaleTime(String fullScaleTime) {
		this.fullScaleTime = fullScaleTime;
	}

	public BigDecimal gettReCaptical() {
		return tReCaptical;
	}

	public void settReCaptical(BigDecimal tReCaptical) {
		this.tReCaptical = tReCaptical;
	}

	public BigDecimal gettReInterest() {
		return tReInterest;
	}

	public void settReInterest(BigDecimal tReInterest) {
		this.tReInterest = tReInterest;
	}

	public String getlRepaymentTime() {
		return lRepaymentTime;
	}

	public void setlRepaymentTime(String lRepaymentTime) {
		this.lRepaymentTime = lRepaymentTime;
	}

	public BigDecimal getaReCaptical() {
		return aReCaptical;
	}

	public void setaReCaptical(BigDecimal aReCaptical) {
		this.aReCaptical = aReCaptical;
	}

	public BigDecimal getaReInterest() {
		return aReInterest;
	}

	public void setaReInterest(BigDecimal aReInterest) {
		this.aReInterest = aReInterest;
	}

	public BigDecimal getTodayReCaptical() {
		return todayReCaptical;
	}

	public void setTodayReCaptical(BigDecimal todayReCaptical) {
		this.todayReCaptical = todayReCaptical;
	}

	public BigDecimal getTodayReInterest() {
		return todayReInterest;
	}

	public void setTodayReInterest(BigDecimal todayReInterest) {
		this.todayReInterest = todayReInterest;
	}

	public BigDecimal geteReCaptical() {
		return eReCaptical;
	}

	public void seteReCaptical(BigDecimal eReCaptical) {
		this.eReCaptical = eReCaptical;
	}

	public BigDecimal geteReInterest() {
		return eReInterest;
	}

	public void seteReInterest(BigDecimal eReInterest) {
		this.eReInterest = eReInterest;
	}

	public BigDecimal getPaidSum() {
		return paidSum;
	}

	public void setPaidSum(BigDecimal paidSum) {
		this.paidSum = paidSum;
	}

	public BigDecimal getCreditSum() {
		return creditSum;
	}

	public void setCreditSum(BigDecimal creditSum) {
		this.creditSum = creditSum;
	}

	public BigDecimal getDebtSum() {
		return debtSum;
	}

	public void setDebtSum(BigDecimal debtSum) {
		this.debtSum = debtSum;
	}

	public BigDecimal gettCreditSum() {
		return tCreditSum;
	}

	public void settCreditSum(BigDecimal tCreditSum) {
		this.tCreditSum = tCreditSum;
	}	

}
