package com.gqhmt.fss.architect.customer.bean;


public class CustomerInfoDetialBean {
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
	private String certIssueDateView;
	//失效日期
	private String certFailDateView;
	//性别 1-男 2-女
	private Integer sex;
	//出生日期
	private String birthdateView;
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
	
	
	//支付渠道1：大钱 2：富友
	private Integer payChannel;
	
	//富有开户关联用银行卡id
	private Integer bankId;
	
	
	//开户行地区代码(富友开户用)
	private String cityCode;
	
	//是否创建账户 0-未开户 1-已创建
	private Integer hasAcount;
	
	//开户行行别(富友开户用)
	private String parentBankCode;
	
	//开户银行具体地址
	private String bankLongName;
	
	//开户银行名称
	private String bankSortName;

	//银行卡号
	private String bankNo;
	
	//是否变更富友银行卡
	private String isChangeBankCard;

	private String imageFileName;


	
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
	public String getCertIssueDateView() {
		return certIssueDateView;
	}
	public void setCertIssueDateView(String certIssueDateView) {
		this.certIssueDateView = certIssueDateView;
	}
	public String getCertFailDateView() {
		return certFailDateView;
	}
	public void setCertFailDateView(String certFailDateView) {
		this.certFailDateView = certFailDateView;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirthdateView() {
		return birthdateView;
	}
	public void setBirthdateView(String birthdateView) {
		this.birthdateView = birthdateView;
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
	
	
	
	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	/**
	 * @return the jobPost
	 */
	public String getJobPost() {
		return jobPost;
	}
	/**
	 * @param jobPost the jobPost to set
	 */
	public void setJobPost(String jobPost) {
		this.jobPost = jobPost;
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
	/**
	 * @return the payChannel
	 */
	public Integer getPayChannel() {
		return payChannel;
	}
	/**
	 * @param payChannel the payChannel to set
	 */
	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
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
	/**
	 * @return the bankSortName
	 */
	public String getBankSortName() {
		return bankSortName;
	}
	/**
	 * @param bankSortName the bankSortName to set
	 */
	public void setBankSortName(String bankSortName) {
		this.bankSortName = bankSortName;
	}
	/**
	 * @return the hasAcount
	 */
	public Integer getHasAcount() {
		return hasAcount;
	}
	/**
	 * @param hasAcount the hasAcount to set
	 */
	public void setHasAcount(Integer hasAcount) {
		this.hasAcount = hasAcount;
	}
	/**
	 * @return the isChangeBankCard
	 */
	public String getIsChangeBankCard() {
		return isChangeBankCard;
	}
	/**
	 * @param isChangeBankCard the isChangeBankCard to set
	 */
	public void setIsChangeBankCard(String isChangeBankCard) {
		this.isChangeBankCard = isChangeBankCard;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
