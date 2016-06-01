package com.gqhmt.funds.architect.trade.entity;

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
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                     //流水编号
	
	@Column(name = "account_id")
	private Integer accountId;           //账户id(代扣账户)
	
	@Column(name = "account_type")
	private Integer accountType;         //账户类型(1-借款客户;2-线下出借客户)
	
	@Column(name = "cust_id")
	private Integer custId;              //客户编号
	
	@Column(name = "cust_name", length = 30)
	private String custName;             //客户名称
	
	@Column(name = "cust_Phone", length = 11)
	private String custPhone;            //客户手机
	
	@Column(name = "draw_amount", precision = 18, scale = 2)
	private BigDecimal drawAmount;       //代扣金额
	
	@Column(name = "fact_draw_amount", precision = 18, scale = 2)
	private BigDecimal factDrawAmount;   //已代扣金额
	
	@Column(name = "bank_id")
	private Integer bankId;              //银行编号(代扣)
	
	@Column(name = "apply_status")
	private Integer applyStatus;         //申请状态(1-默认审核中;2-已代扣;3-取消;4-代扣中;5-失败)
	
	@Column(name = "apply_user_id")
	private Integer applyUserId;         //申请人
	
	@Column(name = "apply_time", nullable = false, length = 19)
	private Date applyTime;              //申请时间
	
	@Column(name = "review_user_id")
	private Integer reviewUserId;        //审核人编号
	
	@Column(name = "review_time", nullable = true, length = 19)
	private Date reviewTime;             //审核人编号
	
	@Column(name = "remark", length = 200)
	private String remark;               //备注
	
	@Column(name = "bussiness_id")
	private Integer bussinessId;         //业务ID
	
	@Column(name = "bussiness_type")
	private Integer bussinessType;       //业务类型(1-线下出借合同代扣；2-借款人还款代扣)
	
	@Column(name = "bussiness_contract_no", length = 30)
	private String bussinessContractNo;  //业务_合同编号
	
	@Column(name = "bussiness_company", length = 100)
	private String bussinessCompany; //分公司名称
	
	
	@Column(name = "thirdparty_type")
    private Integer thirdPartyType; //第三方支付类型
	
	@Column(name = "bussiness_area")
	private String bussinessArea; //大区
	
	@Column(name = "counselor_name")
	private String counselorName; //业务_顾问名称
	
	@Column(name = "seq_no")
	private String seqNo; 		// 交易流水号
	
	
	
	
	// Constructors
	/** default constructor */
	public WithholdApplyEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	
	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
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

	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
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



	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}


	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	public Integer getReviewUserId() {
		return this.reviewUserId;
	}

	public void setReviewUserId(Integer reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	
	public Date getApplyTime() {
		return this.applyTime;
	}

	
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApplyUserId() {
		return this.applyUserId;
	}
	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
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

	public String getBussinessArea() {
		return bussinessArea;
	}

	public void setBussinessArea(String bussinessArea) {
		this.bussinessArea = bussinessArea;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	
    
}
