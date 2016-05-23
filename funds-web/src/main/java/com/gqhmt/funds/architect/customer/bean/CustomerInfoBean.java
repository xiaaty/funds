package com.gqhmt.funds.architect.customer.bean;

import java.util.Date;

public class CustomerInfoBean {
	private Integer id;
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
	//创建时间
	private Date createTime;
	//创建者
	private Integer createUserId;
	//修改时间
	private Date modifyTime;
	//修改者
	private Integer modifyUserId;
	//支付通道
	private Integer payChannel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Integer getCertType() {
		return certType;
	}
	public void setCertType(Integer certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertAddress() {
		return certAddress;
	}
	public void setCertAddress(String certAddress) {
		this.certAddress = certAddress;
	}
	public Date getCertIssueDate() {
		return certIssueDate;
	}
	public void setCertIssueDate(Date certIssueDate) {
		this.certIssueDate = certIssueDate;
	}
	public Date getCertFailDate() {
		return certFailDate;
	}
	public void setCertFailDate(Date certFailDate) {
		this.certFailDate = certFailDate;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddrCountry() {
		return addrCountry;
	}
	public void setAddrCountry(String addrCountry) {
		this.addrCountry = addrCountry;
	}
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getAddrTown() {
		return addrTown;
	}
	public void setAddrTown(String addrTown) {
		this.addrTown = addrTown;
	}
	public String getAddrOther() {
		return addrOther;
	}
	public void setAddrOther(String addrOther) {
		this.addrOther = addrOther;
	}
	public String getEmerContact() {
		return emerContact;
	}
	public void setEmerContact(String emerContact) {
		this.emerContact = emerContact;
	}
	public String getEmerContactMobile() {
		return emerContactMobile;
	}
	public void setEmerContactMobile(String emerContactMobile) {
		this.emerContactMobile = emerContactMobile;
	}
	public Integer getEmerContactSex() {
		return emerContactSex;
	}
	public void setEmerContactSex(Integer emerContactSex) {
		this.emerContactSex = emerContactSex;
	}
	public String getEmerContactRela() {
		return emerContactRela;
	}
	public void setEmerContactRela(String emerContactRela) {
		this.emerContactRela = emerContactRela;
	}
	public String getEmerContactPhone() {
		return emerContactPhone;
	}
	public void setEmerContactPhone(String emerContactPhone) {
		this.emerContactPhone = emerContactPhone;
	}
	public String getEmerContactEduca() {
		return emerContactEduca;
	}
	public void setEmerContactEduca(String emerContactEduca) {
		this.emerContactEduca = emerContactEduca;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
    public Integer getPayChannel() {
        return payChannel;
    }
    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }
	
	
	
}
