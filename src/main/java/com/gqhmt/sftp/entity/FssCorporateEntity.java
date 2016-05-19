package com.gqhmt.sftp.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月17日
 * Description:
 * <p>P2P法人平台开户文件实体类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_sftp_corporate")
public class FssCorporateEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mchn") 
    private String  mchn;  
    
    @Column(name = "seq_no") 
    private String seqNo;		

    @Column(name = "company_name")
    private String  companyName;   
    
    @Column(name = "registered_date")
    private String registeredDate;		
    
    @Column(name = "legalperson_name") 
    private String legalpersonName;
    
    @Column(name = "cert_no")
    private Integer certNo;		
    
    @Column(name = "mobile")
    private String mobile;			
    
    @Column(name = "email")
    private String email;		
    
    @Column(name = "third_party_payment_id")
    private String thirdPartyPaymentId;		
    
    @Column(name = "action_type")
    private String actionType;			
    
    @Column(name = "license_number")
    private String licenseNumber;		
    
    @Column(name = "tax_no")
    private String taxNo;		
    
    @Column(name = "organization_code")
    private String organizationCode;		
    
    @Column(name = "platform_username")
    private String platformUsername;		
    
    @Column(name = "gold_acc_login_name")
    private String goldAccLoginName;		
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "user_attributes")
    private String userAttributes;
    
    @Column(name = "bank_license")
    private String bankLicense;
    
    @Column(name = "org_credit_code")
    private String orgCreditCode;
    
    @Column(name = "unite_credit_code")
    private String uniteCreditCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMchn() {
		return mchn;
	}

	public void setMchn(String mchn) {
		this.mchn = mchn;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Integer getCertNo() {
		return certNo;
	}

	public void setCertNo(Integer certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getThirdPartyPaymentId() {
		return thirdPartyPaymentId;
	}

	public void setThirdPartyPaymentId(String thirdPartyPaymentId) {
		this.thirdPartyPaymentId = thirdPartyPaymentId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getGoldAccLoginName() {
		return goldAccLoginName;
	}

	public void setGoldAccLoginName(String goldAccLoginName) {
		this.goldAccLoginName = goldAccLoginName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserAttributes() {
		return userAttributes;
	}

	public void setUserAttributes(String userAttributes) {
		this.userAttributes = userAttributes;
	}

	public String getBankLicense() {
		return bankLicense;
	}

	public void setBankLicense(String bankLicense) {
		this.bankLicense = bankLicense;
	}

	public String getOrgCreditCode() {
		return orgCreditCode;
	}

	public void setOrgCreditCode(String orgCreditCode) {
		this.orgCreditCode = orgCreditCode;
	}

	public String getUniteCreditCode() {
		return uniteCreditCode;
	}

	public void setUniteCreditCode(String uniteCreditCode) {
		this.uniteCreditCode = uniteCreditCode;
	}

	public String getLegalpersonName() {
		return legalpersonName;
	}

	public void setLegalpersonName(String legalpersonName) {
		this.legalpersonName = legalpersonName;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getPlatformUsername() {
		return platformUsername;
	}

	public void setPlatformUsername(String platformUsername) {
		this.platformUsername = platformUsername;
	}
	
}