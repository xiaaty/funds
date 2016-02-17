package com.gqhmt.fss.architect.customer.bean;



import java.util.Date;

/**
 * Filename: com.gqhmt.fss.architect.customer.entity.FssCustomerEntity
 * Copyright: Copyright (c)2015 Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7 Create at: 16/1/4 14:43 Description:
 *         <p>
 *         新版客户表
 *         </p>
 *         Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         16/1/4 于泳 1.0 1.0 Version
 */
/**
 * 
 * @author jhz time:2016年1月26日  function:展示客户银行卡信息
 */
public class CustomerAndUser {

	private Long id;

	private String name; // 客户姓名

	private String mobile; // 客户手机号

	private Integer certType; // 证件类型，1身份证

	private String certNo; // 证件号码

	private String userNo; // 用户编号

	private Integer bankId; // 银行类型

	private String cardNo; // 银行卡号

	private Integer area; // 所属地区

	private Date createTime; // 创建时间

	private Date modifyTime; // 最后修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCertType() {
		return certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
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
