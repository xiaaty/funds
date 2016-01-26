package com.gqhmt.fss.architect.customer.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.customer.entity.FssCustomerEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 14:43
 * Description:
 * <p>新版客户表</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
public class CustomerAndUser {
		private Long id;
	    private String userNo;                             // varchar(45) DEFAULT NULL COMMENT '客户表编号，系统唯一，不可更改',

	    private Integer name;                          // int(11) NOT NULL COMMENT '证件类型',

	    private String certNo;                             // varchar(45) NOT NULL COMMENT '证件号码',

	    private Integer mobile;                            // int(11) NOT NULL COMMENT '所属银行',

	    private String certType;                             ///varchar(45) NOT NULL COMMENT '银行卡号',

	    private String custNo;                           // varchar(45) DEFAULT NULL COMMENT '开户支行',

	    private Integer bankId;                               // int(11) DEFAULT NULL COMMENT '开户地区',

	    private String cardNo;                             // varchar(45) DEFAULT NULL COMMENT '用户编号，系统唯一，不可更改',

	    @Column(name = "bank_id_third")
	    private Integer area;                       // varchar(45) DEFAULT NULL COMMENT '第三方银行编码',

	    private String  createTime;                         // varchar(45) DEFAULT NULL COMMENT '第三方 地区码',

	    private String  modifyTime;                       // varchar(45) DEFAULT NULL COMMENT '银行卡编号，系统唯一，不可更改',

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserNo() {
			return userNo;
		}

		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}

		public Integer getName() {
			return name;
		}

		public void setName(Integer name) {
			this.name = name;
		}

		public String getCertNo() {
			return certNo;
		}

		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}

		public Integer getMobile() {
			return mobile;
		}

		public void setMobile(Integer mobile) {
			this.mobile = mobile;
		}

		public String getCertType() {
			return certType;
		}

		public void setCertType(String certType) {
			this.certType = certType;
		}

		public String getCustNo() {
			return custNo;
		}

		public void setCustNo(String custNo) {
			this.custNo = custNo;
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

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(String modifyTime) {
			this.modifyTime = modifyTime;
		}

	    
	    }
