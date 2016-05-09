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
    private Long id;                                 //主键id

    @Column(name="org_target_id")
    private String orgTargetId;                      //支付机构标的id

    @Column(name = "org_terrace_id")
    private String orgTerraceId;                     //支付机构平台id

    @Column(name = "cust_no")                     
    private String custNo;                           //放款客户号

    @Column(name = "cust_name")       
    private String custName;                        //客户姓名

    @Column(name = "cert_no")
    private String certNo;                          //客户身份证号

    @Column(name = "target_state")
    private String targetState;                    //标的状态  

    @Column(name = "tender_time")
    private String tenderTime;                     //发标日期
    
    @Column(name = "full_scale_time")
    private String fullScaleTime;                  //实际满标日期

    @Column(name = "t_re_captical")
    private BigDecimal tReCaptical;                 //应还款总本金

    @Column(name = "t_re_interest")
    private BigDecimal tReInterest;                 //应还款总利息          

	@Column(name = "l_rep_time")
    private String lRepaymentTime;                  //最后还款日期
    
    @Column(name = "a_square_time")
    private String aSquareTime;                      //实际结清日期
    
    @Column(name = "a_re_captical")
    private BigDecimal aReCaptical;                 //至结清日累计已还款日期           

    @Column(name = "a_re_interest")
    private BigDecimal aReInterest;                  //至结清日累计已还款利息          

    @Column(name="today_re_captical")
    private BigDecimal todayReCaptical;             //本日还款本金

    @Column(name="today_re_interest")
    private BigDecimal todayReInterest;             //本日还款利息
    
    @Column(name="e_re_captical")
    private BigDecimal eReCaptical;                //截止当日累计已还款本金

	@Column(name="e_re_interest")
	private BigDecimal eReInterest;		           //截止当日累计已还款利息

	@Column(name = "paid_sum")
	private BigDecimal paidSum;                    //已垫资总金额
	
	@Column(name="credit_sum")
	private BigDecimal creditSum;			       //剩余未偿垫资金额

	@Column(name = "debt_sum")                     
	private BigDecimal debtSum;                      //放款金额

	@Column(name="t_credit_sum")
	private BigDecimal tCreditSum;                 //截止当日累计放款
	
	@Column(name = "audit_state")                 
	private String auditState;                      //放款审核状态

	@Column(name="freeze_sum")
	private BigDecimal freezeSum;                    //冻结资金

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

	public String getaSquareTime() {
		return aSquareTime;
	}

	public void setaSquareTime(String aSquareTime) {
		this.aSquareTime = aSquareTime;
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



