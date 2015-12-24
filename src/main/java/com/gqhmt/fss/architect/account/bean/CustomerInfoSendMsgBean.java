package com.gqhmt.fss.architect.account.bean;


public class CustomerInfoSendMsgBean {
	private Integer id;
	//客户姓名
	private String customerName;
	//客户类型 1：借款用户 2:借款共借人 3：线下出借用户 4：线上出借注册用户 9：A0公司内用用户
	private Integer customerType;
	//移动电话
	private String mobilePhone;

	
	//批处理是否已经调用 0-默认未处理；1-已经处理过
	private Integer isBatchSendmsgCalled;
	//充值提现是否发短信0-发送；1-不发送
	private Integer sendMsgRechargeWithdrawFouyou;
	//出账是否发短信0-发送；1-不发送
	private Integer sendMsgTransferOutFouyou;
	//入账是否发短信0-发送；1-不发送
	private Integer sendMsgTransferInFouyou;
	//汇总是否发短信0-发送；1-不发送
	private Integer sendMsgTransferAllFouyou;


	//充值提现是否发短信0-发送；1-不发送 （批处理用）
	private Integer sendMsgRechargeWithdrawFouyouBatch;
	//出账是否发短信0-发送；1-不发送（批处理用）
	private Integer sendMsgTransferOutFouyouBatch;
	//入账是否发短信0-发送；1-不发送（批处理用）
	private Integer sendMsgTransferInFouyouBatch;
	//汇总是否发短信0-发送；1-不发送（批处理用）
	private Integer sendMsgTransferAllFouyouBatch;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerType
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the isBatchSendmsgCalled
	 */
	public Integer getIsBatchSendmsgCalled() {
		return isBatchSendmsgCalled;
	}
	/**
	 * @param isBatchSendmsgCalled the isBatchSendmsgCalled to set
	 */
	public void setIsBatchSendmsgCalled(Integer isBatchSendmsgCalled) {
		this.isBatchSendmsgCalled = isBatchSendmsgCalled;
	}
	/**
	 * @return the sendMsgRechargeWithdrawFouyou
	 */
	public Integer getSendMsgRechargeWithdrawFouyou() {
		return sendMsgRechargeWithdrawFouyou;
	}
	/**
	 * @param sendMsgRechargeWithdrawFouyou the sendMsgRechargeWithdrawFouyou to set
	 */
	public void setSendMsgRechargeWithdrawFouyou(
			Integer sendMsgRechargeWithdrawFouyou) {
		this.sendMsgRechargeWithdrawFouyou = sendMsgRechargeWithdrawFouyou;
	}
	/**
	 * @return the sendMsgTransferOutFouyou
	 */
	public Integer getSendMsgTransferOutFouyou() {
		return sendMsgTransferOutFouyou;
	}
	/**
	 * @param sendMsgTransferOutFouyou the sendMsgTransferOutFouyou to set
	 */
	public void setSendMsgTransferOutFouyou(Integer sendMsgTransferOutFouyou) {
		this.sendMsgTransferOutFouyou = sendMsgTransferOutFouyou;
	}
	/**
	 * @return the sendMsgTransferInFouyou
	 */
	public Integer getSendMsgTransferInFouyou() {
		return sendMsgTransferInFouyou;
	}
	/**
	 * @param sendMsgTransferInFouyou the sendMsgTransferInFouyou to set
	 */
	public void setSendMsgTransferInFouyou(Integer sendMsgTransferInFouyou) {
		this.sendMsgTransferInFouyou = sendMsgTransferInFouyou;
	}
	/**
	 * @return the sendMsgTransferAllFouyou
	 */
	public Integer getSendMsgTransferAllFouyou() {
		return sendMsgTransferAllFouyou;
	}
	/**
	 * @param sendMsgTransferAllFouyou the sendMsgTransferAllFouyou to set
	 */
	public void setSendMsgTransferAllFouyou(Integer sendMsgTransferAllFouyou) {
		this.sendMsgTransferAllFouyou = sendMsgTransferAllFouyou;
	}
	/**
	 * @return the sendMsgRechargeWithdrawFouyouBatch
	 */
	public Integer getSendMsgRechargeWithdrawFouyouBatch() {
		return sendMsgRechargeWithdrawFouyouBatch;
	}
	/**
	 * @param sendMsgRechargeWithdrawFouyouBatch the sendMsgRechargeWithdrawFouyouBatch to set
	 */
	public void setSendMsgRechargeWithdrawFouyouBatch(
			Integer sendMsgRechargeWithdrawFouyouBatch) {
		this.sendMsgRechargeWithdrawFouyouBatch = sendMsgRechargeWithdrawFouyouBatch;
	}
	/**
	 * @return the sendMsgTransferOutFouyouBatch
	 */
	public Integer getSendMsgTransferOutFouyouBatch() {
		return sendMsgTransferOutFouyouBatch;
	}
	/**
	 * @param sendMsgTransferOutFouyouBatch the sendMsgTransferOutFouyouBatch to set
	 */
	public void setSendMsgTransferOutFouyouBatch(
			Integer sendMsgTransferOutFouyouBatch) {
		this.sendMsgTransferOutFouyouBatch = sendMsgTransferOutFouyouBatch;
	}
	/**
	 * @return the sendMsgTransferInFouyouBatch
	 */
	public Integer getSendMsgTransferInFouyouBatch() {
		return sendMsgTransferInFouyouBatch;
	}
	/**
	 * @param sendMsgTransferInFouyouBatch the sendMsgTransferInFouyouBatch to set
	 */
	public void setSendMsgTransferInFouyouBatch(Integer sendMsgTransferInFouyouBatch) {
		this.sendMsgTransferInFouyouBatch = sendMsgTransferInFouyouBatch;
	}
	/**
	 * @return the sendMsgTransferAllFouyouBatch
	 */
	public Integer getSendMsgTransferAllFouyouBatch() {
		return sendMsgTransferAllFouyouBatch;
	}
	/**
	 * @param sendMsgTransferAllFouyouBatch the sendMsgTransferAllFouyouBatch to set
	 */
	public void setSendMsgTransferAllFouyouBatch(
			Integer sendMsgTransferAllFouyouBatch) {
		this.sendMsgTransferAllFouyouBatch = sendMsgTransferAllFouyouBatch;
	}
	
}
