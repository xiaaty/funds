package com.gqhmt.fss.architect.depos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:
 * <p>1.P2P个人平台开户文件实体
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_depos_fuiou_account")
public class FssAccountFileEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "mchn")
	private String  mchn;  //商户号

	@Column(name = "registered_seq_no")
	private String registeredSeqNo;			//平台注册流水，持续唯一',

	@Column(name = "platform_username")
	private String  platformUsername;   	//'平台用户名',

	@Column(name = "login_username")
	private String loginUsername;		//登陆用户名

	@Column(name = "age")
	private Integer age;

	@Column(name = "acc_name")
	private String accName;		//户名

	@Column(name = "cert_type")
	private String certType;

	@Column(name = "cert_no")
	private String certNo;

	@Column(name = "sex")
	private Integer sex;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "address")
	private String address;

	@Column(name = "user_properties")
	private Integer userProperties;  //'用户属性（1:借款人，2：贷款人）',

	@Column(name = "registration_date")
	private String registrationDate;

	@Column(name = "third_party_payment_id")
	private String thirdPartyPaymentId;

	@Column(name = "action_type")
	private String actionType;			//'操作类型ADD增加 ，MOD修改 ，DEL删除',

	@Column(name = "remark")
	private String remark;

	@Column(name = "status")
	private String status;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "modify_time")
	private Date modifyTime;


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

	public String getRegisteredSeqNo() {
		return registeredSeqNo;
	}

	public void setRegisteredSeqNo(String registeredSeqNo) {
		this.registeredSeqNo = registeredSeqNo;
	}

	public String getPlatformUsername() {
		return platformUsername;
	}

	public void setPlatformUsername(String platformUsername) {
		this.platformUsername = platformUsername;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(Integer userProperties) {
		this.userProperties = userProperties;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}