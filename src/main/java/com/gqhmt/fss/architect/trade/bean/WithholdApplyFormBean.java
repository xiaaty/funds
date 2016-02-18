package com.gqhmt.fss.architect.trade.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

public class WithholdApplyFormBean implements java.io.Serializable {

	/**
	 * 代扣申请画面表示bean
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private String id;                     //流水编号
	private Integer accountId;           //账户id(代扣账户)
	private Integer accountType;         //账户类型(1-借款客户;2-线下出借客户)
	private Integer custId;              //客户编号
	private String custName;             //客户名称
	private String custPhone;            //客户手机
	private BigDecimal drawAmount;       //代扣金额
	private BigDecimal factDrawAmount;       //实际代扣金额
	private Integer bankId;              //银行编号(代扣)
	private Integer applyStatus;         //申请状态(1-默认审核中;2-已代扣;3-取消;4-代扣中;5-失败)
	private Integer applyUserId;         //申请人
	private String applyTimeForm;              //申请时间
	private Integer reviewUserId;        //审核人编号
	private String reviewTimeFrom;             //审核时间
	private String remark;               //备注
	private Integer bussinessId;         //业务ID
	private Integer bussinessType;       //业务类型(1-线下出借合同代扣；2-借款人还款代扣)
    private Integer thirdPartyType; //第三方支付类型
    private BigDecimal limitAmount; //最大限额
    
    private ArrayList<BigDecimal> drawAmountSplit;
    
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the accountType
	 */
	public Integer getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the custId
	 */
	public Integer getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the custPhone
	 */
	public String getCustPhone() {
		return custPhone;
	}
	/**
	 * @param custPhone the custPhone to set
	 */
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	/**
	 * @return the drawAmount
	 */
	public BigDecimal getDrawAmount() {
		return drawAmount;
	}
	/**
	 * @param drawAmount the drawAmount to set
	 */
	public void setDrawAmount(BigDecimal drawAmount) {
		this.drawAmount = drawAmount;
	}
	/**
	 * @return the bankId
	 */
	public Integer getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the applyStatus
	 */
	public Integer getApplyStatus() {
		return applyStatus;
	}
	/**
	 * @param applyStatus the applyStatus to set
	 */
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	/**
	 * @return the applyUserId
	 */
	public Integer getApplyUserId() {
		return applyUserId;
	}
	/**
	 * @param applyUserId the applyUserId to set
	 */
	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	/**
	 * @return the reviewUserId
	 */
	public Integer getReviewUserId() {
		return reviewUserId;
	}
	/**
	 * @param reviewUserId the reviewUserId to set
	 */
	public void setReviewUserId(Integer reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	
	/**
	 * @return the applyTimeForm
	 */
	public String getApplyTimeForm() {
		return applyTimeForm;
	}
	/**
	 * @param applyTimeForm the applyTimeForm to set
	 */
	public void setApplyTimeForm(String applyTimeForm) {
		this.applyTimeForm = applyTimeForm;
	}
	/**
	 * @return the reviewTimeFrom
	 */
	public String getReviewTimeFrom() {
		return reviewTimeFrom;
	}
	/**
	 * @param reviewTimeFrom the reviewTimeFrom to set
	 */
	public void setReviewTimeFrom(String reviewTimeFrom) {
		this.reviewTimeFrom = reviewTimeFrom;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the bussinessId
	 */
	public Integer getBussinessId() {
		return bussinessId;
	}
	/**
	 * @param bussinessId the bussinessId to set
	 */
	public void setBussinessId(Integer bussinessId) {
		this.bussinessId = bussinessId;
	}
	/**
	 * @return the bussinessType
	 */
	public Integer getBussinessType() {
		return bussinessType;
	}
	/**
	 * @param bussinessType the bussinessType to set
	 */
	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}
	/**
	 * @return the thirdPartyType
	 */
	public Integer getThirdPartyType() {
		return thirdPartyType;
	}
	/**
	 * @param thirdPartyType the thirdPartyType to set
	 */
	public void setThirdPartyType(Integer thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}
	/**
	 * @return the limitAmount
	 */
	public BigDecimal getLimitAmount() {
		return limitAmount;
	}
	/**
	 * @param limitAmount the limitAmount to set
	 */
	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}
	/**
	 * @return the drawAmountSplit
	 */
	public ArrayList<BigDecimal> getDrawAmountSplit() {
		return drawAmountSplit;
	}
	/**
	 * @param drawAmountSplit the drawAmountSplit to set
	 */
	public void setDrawAmountSplit(ArrayList<BigDecimal> drawAmountSplit) {
		this.drawAmountSplit = drawAmountSplit;
	}
	/**
	 * @return the factDrawAmount
	 */
	public BigDecimal getFactDrawAmount() {
		return factDrawAmount;
	}
	/**
	 * @param factDrawAmount the factDrawAmount to set
	 */
	public void setFactDrawAmount(BigDecimal factDrawAmount) {
		this.factDrawAmount = factDrawAmount;
	}

}
