package com.gqhmt.funds.architect.trade.bean;


/**
 * 代扣数据传输对象
 * com.gq.funds.bean
 * @className WithholdApplyBean<br/> 
 * @author guofu
 * @createDate 2015-04-08 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class WithholdApplyBean implements java.io.Serializable{

	private long id;                     //流水编号
	private Integer accountType;         //账户类型(1-借款客户;2-线下出借客户)
	private Integer custId;              //客户编号
	private String custName;             //客户名称
	private String custPhone;            //客户手机
	private Integer applyStatus;         //申请状态(1-默认审核中;2-已代扣;3-取消;4-代扣中;5-失败)
	private Integer applyUserId;         //申请人
	private Integer bussinessId;         //业务ID
	private Integer bussinessType;       //业务类型(1-线下出借合同代扣；2-借款人还款代扣)
    private Integer thirdPartyType; //第三方支付类型
    private String  startTime;      //申请时间(开始)
    private String  endTime;        //申请时间(结束)
    private String bussinessContractNo;//合同编号
    private String bussinessArea; //大区
    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBussinessContractNo() {
		return bussinessContractNo;
	}
	public void setBussinessContractNo(String bussinessContractNo) {
		this.bussinessContractNo = bussinessContractNo;
	}
	public String getBussinessArea() {
		return bussinessArea;
	}
	public void setBussinessArea(String bussinessArea) {
		this.bussinessArea = bussinessArea;
	}
	
}
