package com.gqhmt.funds.architect.trade.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AbstractTGqWithdrawApply entity provides the base persistence definition of
 * the TGqWithdrawApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_gq_withdraw_apply")
public class WithdrawApplyEntity implements java.io.Serializable {

	/**
	 * property
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "cust_id")
	private Integer custId;
	
	@Column(name = "cust_name", length = 30)
	private String custName;
	
	@Column(name = "cell_phone", length = 11)
	private String cellPhone;
	
	@Column(name = "draw_amount", precision = 18, scale = 2)
	private BigDecimal drawAmount;
	
	@Column(name = "fact_draw_amount", precision = 18, scale = 2)
	private BigDecimal factDrawAmount;   //已代扣金额
	
	@Column(name = "procedure_fee", precision = 18, scale = 2)
	private BigDecimal procedureFee = BigDecimal.ZERO;
	
	@Column(name = "bank_id")
	private Integer bankId;
	
	@Column(name = "cust_type")
	private Integer custType;
	
	@Column(name = "ipaddr", length = 50)
	private String ipaddr;
	
	@Column(name = "apply_status")
	private Integer applyStatus;
	
	@Column(name = "apply_time", nullable = false, length = 19)
	private Date applyTime;
	
	@Column(name = "review_user_id")
	private Integer reviewUserId;
	
	@Column(name = "review_time", nullable = true, length = 19)
	private Date reviewTime;
	
	@Column(name = "bid_id")
	private Integer bidId;
	
	@Column(name = "remark", length = 200)
	private String remark;
	
	@Column(name = "bussiness_id")
	private Integer bussinessId;
	
	@Column(name = "bussiness_type")
	private Integer bussinessType;
	
	@Column(name = "bussiness_contract_no", length = 30)
	private String bussinessContractNo;
	
	@Column(name = "bussiness_company", length = 100)
	private String bussinessCompany;
	
	@Column(name = "thirdparty_type",updatable = false,nullable = false)
    private Integer thirdPartyType;
	
	 @Column(name = "debt_id")
	private Integer debtId;
	
    // 结算类型，0 T+1; 1 T+0; 
	 @Column(name = "settle_type")
 	private Integer settleType = 1;
	 
	 // 交易流水号
	 @Column(name = "seq_no")
	 private String seqNo;

	// Constructors
	/** default constructor */
	public WithdrawApplyEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	
	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	
	public BigDecimal getDrawAmount() {
		return this.drawAmount;
	}

	public void setDrawAmount(BigDecimal drawAmount) {
		this.drawAmount = drawAmount;
	}
	
	
	public BigDecimal getFactDrawAmount() {
		return this.factDrawAmount;
	}

	public void setFactDrawAmount(BigDecimal factDrawAmount) {
		this.factDrawAmount = factDrawAmount;
	}

	
	public BigDecimal getProcedureFee() {
		return this.procedureFee;
	}

	public void setProcedureFee(BigDecimal procedureFee) {
		this.procedureFee = procedureFee;
	}

	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	
	public Integer getCustType() {
		return this.custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	
	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	
	public Integer getReviewUserId() {
		return this.reviewUserId;
	}

	public void setReviewUserId(Integer reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	/**
	 * @return the bidId
	 */
	
	public Integer getBidId() {
		return bidId;
	}

	/**
	 * @param bidId
	 *            the bidId to set
	 */
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public Integer getBussinessId() {
		return bussinessId;
	}

	public void setBussinessId(Integer bussinessId) {
		this.bussinessId = bussinessId;
	}


	public Integer getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}
	
	

    /**
	 * @return the bussinessContractNo
	 */
	
	public String getBussinessContractNo() {
		return bussinessContractNo;
	}

	/**
	 * @param bussinessContractNo the bussinessContractNo to set
	 */
	public void setBussinessContractNo(String bussinessContractNo) {
		this.bussinessContractNo = bussinessContractNo;
	}

	/**
	 * @return the bussinessCompany
	 */
	
	public String getBussinessCompany() {
		return bussinessCompany;
	}

	/**
	 * @param bussinessCompany the bussinessCompany to set
	 */
	public void setBussinessCompany(String bussinessCompany) {
		this.bussinessCompany = bussinessCompany;
	}
   
    public Integer getThirdPartyType() {
		return thirdPartyType;
	}

	public void setThirdPartyType(Integer thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}

	public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }
    
   
	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	
	public String getSeqNo() {
		
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
}
