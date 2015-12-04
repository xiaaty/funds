package com.gqhmt.fss.architect.trade.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_gq_withhold_apply")
public class WithholdApplyEntity implements java.io.Serializable {

	/**
	 * 代扣申请保持bean
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private Long id;                     //流水编号
	private Integer accountId;           //账户id(代扣账户)
	private Integer accountType;         //账户类型(1-借款客户;2-线下出借客户)
	private Integer custId;              //客户编号
	private String custName;             //客户名称
	private String custPhone;            //客户手机
	private BigDecimal drawAmount;       //代扣金额
	private BigDecimal factDrawAmount;   //已代扣金额
	private Integer bankId;              //银行编号(代扣)
	private Integer applyStatus;         //申请状态(1-默认审核中;2-已代扣;3-取消;4-代扣中;5-失败)
	private Integer applyUserId;         //申请人
	private Date applyTime;              //申请时间
	private Integer reviewUserId;        //审核人编号
	private Date reviewTime;             //审核人编号
	private String remark;               //备注
	private Integer bussinessId;         //业务ID
	private Integer bussinessType;       //业务类型(1-线下出借合同代扣；2-借款人还款代扣)
	private String bussinessContractNo;
	private String bussinessCompany;
    private Integer thirdPartyType; //第三方支付类型

	// Constructors
	/** default constructor */
	public WithholdApplyEntity() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "account_id")
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	
	@Column(name = "account_type")
	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
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

	@Column(name = "cust_Phone", length = 11)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
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



	@Column(name = "bank_id")
	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}


	@Column(name = "apply_status")
	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Column(name = "review_user_id")
	public Integer getReviewUserId() {
		return this.reviewUserId;
	}

	public void setReviewUserId(Integer reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	@Column(name = "apply_time", nullable = false, length = 19)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "apply_user_id")
	public Integer getApplyUserId() {
		return this.applyUserId;
	}

	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	@Column(name = "review_time", nullable = true, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
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

    @Column(name = "thirdparty_type")
    public Integer getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(Integer thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

}
