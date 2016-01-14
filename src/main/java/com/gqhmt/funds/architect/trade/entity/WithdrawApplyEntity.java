package com.gqhmt.funds.architect.trade.entity;

import com.gqhmt.util.ThirdPartyType;

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
	private Integer accountId;
	private Integer custId;
	private String custName;
	private String cellPhone;
	private BigDecimal drawAmount;
	private BigDecimal factDrawAmount;   //已代扣金额
	private BigDecimal procedureFee = BigDecimal.ZERO;
	private Integer bankId;
	private Integer custType;
	private String ipaddr;
	private Integer applyStatus;
	private Date applyTime;
	private Integer reviewUserId;
	private Date reviewTime;
	private Integer bidId;
	private String remark;
	private Integer bussinessId;
	private Integer bussinessType;
	private String bussinessContractNo;
	private String bussinessCompany;
    private ThirdPartyType thirdPartyType;
    private Integer debtId;
    // 结算类型，0 T+1; 1 T+0; 
 	private Integer settleType = 1;

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

	@Column(name = "account_id")
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "cust_id")
	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	@Column(name = "cust_name", length = 30)
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "cell_phone", length = 11)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "draw_amount", precision = 18, scale = 2)
	public BigDecimal getDrawAmount() {
		return this.drawAmount;
	}

	public void setDrawAmount(BigDecimal drawAmount) {
		this.drawAmount = drawAmount;
	}
	
	@Column(name = "fact_draw_amount", precision = 18, scale = 2)
	public BigDecimal getFactDrawAmount() {
		return this.factDrawAmount;
	}

	public void setFactDrawAmount(BigDecimal factDrawAmount) {
		this.factDrawAmount = factDrawAmount;
	}

	@Column(name = "procedure_fee", precision = 18, scale = 2)
	public BigDecimal getProcedureFee() {
		return this.procedureFee;
	}

	public void setProcedureFee(BigDecimal procedureFee) {
		this.procedureFee = procedureFee;
	}

	@Column(name = "bank_id")
	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	@Column(name = "cust_type")
	public Integer getCustType() {
		return this.custType;
	}

	public void setCustType(Integer custType) {
		this.custType = custType;
	}

	@Column(name = "ipaddr", length = 50)
	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	@Column(name = "apply_status")
	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	@Column(name = "apply_time", nullable = false, length = 19)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "review_user_id")
	public Integer getReviewUserId() {
		return this.reviewUserId;
	}

	public void setReviewUserId(Integer reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	@Column(name = "review_time", nullable = true, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	/**
	 * @return the bidId
	 */
	@Column(name = "bid_id")
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

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "bussiness_id")
	public Integer getBussinessId() {
		return bussinessId;
	}

	public void setBussinessId(Integer bussinessId) {
		this.bussinessId = bussinessId;
	}

	@Column(name = "bussiness_type")
	public Integer getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}
	
	

    /**
	 * @return the bussinessContractNo
	 */
	@Column(name = "bussiness_contract_no", length = 30)
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
	@Column(name = "bussiness_company", length = 100)
	public String getBussinessCompany() {
		return bussinessCompany;
	}

	/**
	 * @param bussinessCompany the bussinessCompany to set
	 */
	public void setBussinessCompany(String bussinessCompany) {
		this.bussinessCompany = bussinessCompany;
	}

	@Column(name = "thirdparty_type",updatable = false,nullable = false)
    public ThirdPartyType getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(ThirdPartyType thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }
    
    @Column(name = "debt_id")
    public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }
    
    @Column(name = "settle_type")
	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	
}
