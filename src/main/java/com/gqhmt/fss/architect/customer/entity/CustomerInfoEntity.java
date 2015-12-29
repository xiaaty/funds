package com.gqhmt.fss.architect.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 客户信息实体bean
 * 
 */
@Entity
@Table(name = "t_gq_customer_info")
public class CustomerInfoEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	//客户ID主键
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//用户id
	private Integer userId;
	//客户姓名
	private String customerName;
	//客户类型 1：借款用户 2:借款共借人 3：线下出借用户 4：线上出借注册用户 9：A0公司内用用户
	private Integer customerType;
	//移动电话
	private String mobilePhone;
	//国籍
	private String nationality;
	//证件类型 1：身份证 2：护照 3：驾照 4：军人证
	private Integer certType;
	//证件号码
	private String certNo;
	//发证机关所在地
	private String certAddress;
	//签发日期
	private Date certIssueDate;
	//失效日期
	private Date certFailDate;
	//性别 1-男 2-女
	private Integer sex;
	//出生日期
	private Date birthdate;
	//学历
	private String education;
	//婚姻状况 1：未婚 2：已婚  3：离异 4：丧偶
	private Integer maritalStatus;
	//电子邮箱
	private String email;
	//邮编编码
	private String zipCode;
	//通讯地址
	private String address;
	//通讯地址_国家
	private String addrCountry;
	//通讯地址_省（直辖市）
	private String addrProvince;
	//通讯地址_市（州）
	private String addrCity;
	//通讯地址_县（区）
	private String addrTown;
	//通讯地址_其他
	private String addrOther;
	//所属行业
	private String industry;
	//所属职务
	private String jobPost;
	
	
	//紧急联系人
	private String emerContact;
	//紧急联系人移动电话
	private String emerContactMobile;
	//性别 1-男 2-女
	private Integer emerContactSex;
	//紧急联系人关系
	private String emerContactRela;
	//紧急联系人固定电话
	private String emerContactPhone;
	//紧急联系人学历
	private String emerContactEduca;
	//备注
	private String remark;
	//操作人员编号
	private String employeeNo;
	//是否实名认证 0：未认证 1:已认证
	private Integer nameIdentification;
	//是否电话认证 0：未认证 1:已认证
	private Integer phoneIdentification;
	//是否emai认证 0：未认证 1:已认证
	private Integer emailIdentification;
	
	//判断当前客户是否是有效客户 0 有效 1无效
	private Integer isvalid;
	
	//是否签署第三方协议 0 没有 1 有
	private Integer hasThirdAgreement;
	
	//是否已经创建账户
	private Integer hasAcount;
	
	//创建时间
	private Date createTime;
	//创建者
	private Integer createUserId;
	//修改时间
	private Date modifyTime;
	//修改者
	private Integer modifyUserId;
	
	//支付渠道1：大钱 2：富友
	private Integer payChannel;
	
	//富有开户关联用银行卡id
	private Integer bankId;
	
	
	//开户行地区代码(富友开户用)
	@Transient
	private String cityCode;
	
	//开户行行别(富友开户用)
	@Transient
	private String parentBankCode;
	
	//开户银行名称
	@Transient
	private String bankLongName;

	//银行卡号
	@Transient
	private String bankNo;
	
	
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

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NAME", length = 30)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "CUSTOMER_TYPE")
	public Integer getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	@Column(name = "MOBILE_PHONE", length = 20)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "NATIONALITY", length = 30)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "CERT_TYPE")
	public Integer getCertType() {
		return this.certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	@Column(name = "CERT_NO", length = 20)
	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	@Column(name = "CERT_ADDRESS", length = 100)
	public String getCertAddress() {
		return this.certAddress;
	}

	public void setCertAddress(String certAddress) {
		this.certAddress = certAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CERT_ISSUE_DATE", length = 10)
	public Date getCertIssueDate() {
		return this.certIssueDate;
	}

	public void setCertIssueDate(Date certIssueDate) {
		this.certIssueDate = certIssueDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CERT_FAIL_DATE", length = 10)
	public Date getCertFailDate() {
		return this.certFailDate;
	}

	public void setCertFailDate(Date certFailDate) {
		this.certFailDate = certFailDate;
	}

	@Column(name = "SEX")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 10)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "EDUCATION", length = 10)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "MARITAL_STATUS")
	public Integer getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ZIP_CODE", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "ADDRESS", length = 250)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDR_COUNTRY", length = 50)
	public String getAddrCountry() {
		return this.addrCountry;
	}

	public void setAddrCountry(String addrCountry) {
		this.addrCountry = addrCountry;
	}

	@Column(name = "ADDR_PROVINCE", length = 50)
	public String getAddrProvince() {
		return this.addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	@Column(name = "ADDR_CITY", length = 50)
	public String getAddrCity() {
		return this.addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	@Column(name = "ADDR_TOWN", length = 50)
	public String getAddrTown() {
		return this.addrTown;
	}

	public void setAddrTown(String addrTown) {
		this.addrTown = addrTown;
	}
	
	@Column(name = "ADDR_OTHER", length = 50)
	public String getAddrOther() {
		return this.addrOther;
	}

	public void setAddrOther(String addrOther) {
		this.addrOther = addrOther;
	}

	@Column(name = "industry", length = 100)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Column(name = "job_post", length = 100)
	public String getJobPost() {
		return this.jobPost;
	}

	public void setJobPost(String jobPost) {
		this.jobPost = jobPost;
	}
	

	@Column(name = "EMER_CONTACT", length = 20)
	public String getEmerContact() {
		return this.emerContact;
	}

	public void setEmerContact(String emerContact) {
		this.emerContact = emerContact;
	}

	@Column(name = "EMER_CONTACT_MOBILE", length = 20)
	public String getEmerContactMobile() {
		return this.emerContactMobile;
	}

	public void setEmerContactMobile(String emerContactMobile) {
		this.emerContactMobile = emerContactMobile;
	}

	@Column(name = "EMER_CONTACT_SEX")
	public Integer getEmerContactSex() {
		return this.emerContactSex;
	}

	public void setEmerContactSex(Integer emerContactSex) {
		this.emerContactSex = emerContactSex;
	}

	@Column(name = "EMER_CONTACT_RELA", length = 10)
	public String getEmerContactRela() {
		return this.emerContactRela;
	}

	public void setEmerContactRela(String emerContactRela) {
		this.emerContactRela = emerContactRela;
	}

	@Column(name = "EMER_CONTACT_PHONE", length = 20)
	public String getEmerContactPhone() {
		return this.emerContactPhone;
	}

	public void setEmerContactPhone(String emerContactPhone) {
		this.emerContactPhone = emerContactPhone;
	}

	@Column(name = "EMER_CONTACT_EDUCA", length = 20)
	public String getEmerContactEduca() {
		return this.emerContactEduca;
	}

	public void setEmerContactEduca(String emerContactEduca) {
		this.emerContactEduca = emerContactEduca;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "EMPLOYEE_NO", length = 20)
	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	
	

	/**
	 * @return the nameIdentification
	 */
	@Column(name = "NAME_IDENTIFICATION",insertable = false)
	public Integer getNameIdentification() {
		return nameIdentification;
	}

	/**
	 * @param nameIdentification the nameIdentification to set
	 */
	public void setNameIdentification(Integer nameIdentification) {
		this.nameIdentification = nameIdentification;
	}

	/**
	 * @return the phoneIdentification
	 */
	@Column(name = "PHONE_IDENTIFICATION",insertable = false)
	public Integer getPhoneIdentification() {
		return phoneIdentification;
	}

	/**
	 * @param phoneIdentification the phoneIdentification to set
	 */
	public void setPhoneIdentification(Integer phoneIdentification) {
		this.phoneIdentification = phoneIdentification;
	}

	/**
	 * @return the emailIdentification
	 */
	@Column(name = "EMAIL_IDENTIFICATION",insertable = false)
	public Integer getEmailIdentification() {
		return emailIdentification;
	}

	/**
	 * @param emailIdentification the emailIdentification to set
	 */
	public void setEmailIdentification(Integer emailIdentification) {
		this.emailIdentification = emailIdentification;
	}

	
	
	/**
	 * @return the userId
	 */
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the isvalid
	 */
	@Column(name = "ISVALID",insertable = false)
	
	public Integer getIsvalid() {
		return isvalid;
	}

	/**
	 * @param isvalid the isvalid to set
	 */
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}
	
	
	/**
	 * @return the hasThirdAgreement
	 */
	@Column(name = "has_third_agreement",insertable = false)
	
	public Integer getHasThirdAgreement() {
		return hasThirdAgreement;
	}

	/**
	 * @param isvalid the hasThirdAgreement to set
	 */
	public void setHasThirdAgreement(Integer hasThirdAgreement) {
		this.hasThirdAgreement = hasThirdAgreement;
	}
	
	
	/**
	 * @return the hasAcount
	 */
	@Column(name = "has_acount",insertable = false)
	
	public Integer getHasAcount() {
		return hasAcount;
	}

	/**
	 * @param isvalid the hasAcount to set
	 */
	public void setHasAcount(Integer hasAcount) {
		this.hasAcount = hasAcount;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "MODIFY_TIME", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
	@Column(name = "MODIFY_USER_ID")
	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Column(name = "pay_channel")
	public Integer getPayChannel() {
		return this.payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
	
	@Column(name = "bank_id")
	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	
	/**
	 * @return the isBatchSendmsgCalled
	 */
	@Column(name = "is_batch_sendmsg_called")
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
	@Column(name = "send_msg_recharge_withdraw_fouyou")
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
	@Column(name = "send_msg_transfer_out_fouyou")
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
	@Column(name = "send_msg_transfer_in_fouyou")
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
	@Column(name = "send_msg_transfer_all_fouyou")
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
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the parentBankCode
	 */
	public String getParentBankCode() {
		return parentBankCode;
	}

	/**
	 * @param parentBankCode the parentBankCode to set
	 */
	public void setParentBankCode(String parentBankCode) {
		this.parentBankCode = parentBankCode;
	}

	/**
	 * @return the bankLongName
	 */
	public String getBankLongName() {
		return bankLongName;
	}

	/**
	 * @param bankLongName the bankLongName to set
	 */
	public void setBankLongName(String bankLongName) {
		this.bankLongName = bankLongName;
	}

	/**
	 * @return the bankNo
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * @param bankNo the bankNo to set
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
}