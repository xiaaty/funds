package com.gqhmt.business.architect.loan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 产品包装表
 * 
 * @author 张春来
 */
@Entity
@Table(name = "t_gq_product_sales_pack")
public class ProductSalesPackEntity implements java.io.Serializable {

	private static final long serialVersionUID = -8018310611989617074L;

	// 主键ID
	private Integer id;
	// 营销产品id
	private Integer productId;
	// 产品类型：1-优选，2-稳盈，3-直盈，4-新手标，5-散标，9-活动专区
	private Integer productType;
	// 产品编号
	private String productNo;
	// 产品名称
	private String productName;
	// 产品标的金额
	private BigDecimal productAmount;
	// 产品标的利率
	private BigDecimal productYearIrr;
	// 产品状态 1 待审核，2-审核中，3-审核通过， 4-待发布， 5-招标中 6-已满标
	private Integer productState;
	// 预计开标时间
	private Date productPreTime;
	// 开标时间
	private Date productStartTime;
	// 满标时间
	private Date productFullTime;
	// 还款方式 等额本息-1 先息后本 -2，还本付息 -7
	private Integer repaymentType;
	// 合同的还款方式 先息后本 -2，等额等息 -3 ，信用阶梯还款 -4 ，抵押2%阶梯还款 -5 ，抵押5%阶梯还款 -6
	private Integer contractRepaymentType;
	// 期数
	private Integer period;
	// 对应标的合同编号
	private String bidContractNo;
	//对应标的编号
	private String bidNo;
	//对应表的名称
	private String bidTitle;
	// 关联标的表主键
	private Integer bidId;
	// 标的个数
	private Integer bidCounts;
	//借款人姓名
	private String loanCustomerName;
	//分公司名称
	private String filialeName;
	//所属大区
	private String regionName;
	// 贷款类型 1:信用借款,2:抵押借款,3:质押借款,4:混合借款
	private Integer loanType;
	// 已资比例
	private BigDecimal alreadyTenderScale;
	// 剩余投标金额
	private BigDecimal productSurplusAmount;
	// 是否定时发布 默认 0-手动及时 1-自动定时
	private Integer isTimer;
	// 描述表id
	private Integer descriptionId;
	// 产品包装描述
	private String description;
	//营销活动id 对应活动定制表id 
	private Integer saleEventId;
	// 审核人
	private Integer auditId;
	// 审核时间
	private Date auditTime;
	// 发布人
	private Integer publishId;
	//是否有效 0-无效  1-有效
	private Integer isValid;
	//是否已经组合  0-为组合  1-已经组合
	private Integer isGroup;
	// 发布时间
	private Date publishTime;
	// 备注
	private String remark;
	// 创建时间
	private Date createTime;
	// 创建人ID
	private long createUserId;
	// 修改时间
	private Date modifyTime;
	// 修改人ID
	private Integer modifyUserId;
	//是否置顶 0-no，1-yes
	private Integer isTop;
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "product_type")
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	@Column(name = "product_no", length = 32)
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Column(name = "product_name", length = 64)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "product_amount", precision = 18, scale = 2)
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	@Column(name = "product_year_irr", precision = 18, scale = 2)
	public BigDecimal getProductYearIrr() {
		return productYearIrr;
	}

	public void setProductYearIrr(BigDecimal productYearIrr) {
		this.productYearIrr = productYearIrr;
	}

	@Column(name = "product_state")
	public Integer getProductState() {
		return productState;
	}

	public void setProductState(Integer productState) {
		this.productState = productState;
	}

	@Column(name = "product_start_time")
	public Date getProductStartTime() {
		return productStartTime;
	}

	public void setProductStartTime(Date productStartTime) {
		this.productStartTime = productStartTime;
	}

	@Column(name = "product_full_time")
	public Date getProductFullTime() {
		return productFullTime;
	}

	public void setProductFullTime(Date productFullTime) {
		this.productFullTime = productFullTime;
	}

	@Column(name = "repayment_type")
	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	@Column(name = "contract_repayment_type")
	public Integer getContractRepaymentType() {
		return contractRepaymentType;
	}

	public void setContractRepaymentType(Integer contractRepaymentType) {
		this.contractRepaymentType = contractRepaymentType;
	}

	@Column(name = "period")
	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Column(name = "bid_id")
	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	@Column(name = "bid_counts")
	public Integer getBidCounts() {
		return bidCounts;
	}

	public void setBidCounts(Integer bidCounts) {
		this.bidCounts = bidCounts;
	}

	@Column(name = "loan_customer_name", length = 30)
	public String getLoanCustomerName() {
		return loanCustomerName;
	}

	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}

	@Column(name = "filiale_name", length = 100)
	public String getFilialeName() {
		return filialeName;
	}

	public void setFilialeName(String filialeName) {
		this.filialeName = filialeName;
	}

	@Column(name = "region_name", length = 100)
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "loan_type")
	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	@Column(name = "already_tender_scale", precision = 18, scale = 2,insertable = false)
	public BigDecimal getAlreadyTenderScale() {
		return alreadyTenderScale;
	}

	public void setAlreadyTenderScale(BigDecimal alreadyTenderScale) {
		this.alreadyTenderScale = alreadyTenderScale;
	}

	@Column(name = "product_surplus_amount", precision = 18, scale = 2)
	public BigDecimal getProductSurplusAmount() {
		return productSurplusAmount;
	}

	public void setProductSurplusAmount(BigDecimal productSurplusAmount) {
		this.productSurplusAmount = productSurplusAmount;
	}

	@Column(name = "is_timer")
	public Integer getIsTimer() {
		return isTimer;
	}
	public void setIsTimer(Integer isTimer) {
		this.isTimer = isTimer;
	}

	@Column(name = "is_valid")
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "sale_event_id")
	public Integer getSaleEventId() {
		return saleEventId;
	}

	public void setSaleEventId(Integer saleEventId) {
		this.saleEventId = saleEventId;
	}

	@Column(name = "description_id")
	public Integer getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(Integer descriptionId) {
		this.descriptionId = descriptionId;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "audit_id")
	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	@Column(name = "audit_time")
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "publish_id")
	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	@Column(name = "publish_time")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_user_id")
	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "modify_user_id")
	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Column(name = "product_pre_time")
	public Date getProductPreTime() {
		return productPreTime;
	}

	public void setProductPreTime(Date productPreTime) {
		this.productPreTime = productPreTime;
	}
	@Column(name = "is_group")
	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}
	@Column(name = "bid_contract_no", length = 32)
	public String getBidContractNo() {
		return bidContractNo;
	}

	public void setBidContractNo(String bidContractNo) {
		this.bidContractNo = bidContractNo;
	}
	@Column(name = "bid_no", length = 32)
	public String getBidNo() {
		return bidNo;
	}

	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}
	@Column(name = "bid_title", length = 64)
	public String getBidTitle() {
		return bidTitle;
	}
	public void setBidTitle(String bidTitle) {
		this.bidTitle = bidTitle;
	}
	@Column(name = "is_top")
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
}
