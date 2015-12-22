package com.gqhmt.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Filename:    com.gqhmt.sys.entity.Menu
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/21 18:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/18  李俊龙      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_api_business")
public class Business {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 商户名称
    @Column(name = "busi_name")
    private String busiName;
    // 商户标识
    @Column(name = "busi_code")
    private String busiCode;
    // 校验方式(0:不校验 1:IP段匹配)
    @Column(name = "auth_ip_type")
    private String authIpType;
    // 校验方式(0:不校验API 1:API段匹配)
    @Column(name = "auth_api_type")
    private String authApiType;
    // 备注
    @Column(name = "remark")
    private String remark;
    // 创建日期
    @Column(name = "create_time")
    private Date createTime;
    // 修改日期
    @Column(name = "modify_time")
    private Date modifyTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBusiName() {
		return busiName;
	}
	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getAuthIpType() {
		return authIpType;
	}
	public void setAuthIpType(String authIpType) {
		this.authIpType = authIpType;
	}
	public String getAuthApiType() {
		return authApiType;
	}
	public void setAuthApiType(String authApiType) {
		this.authApiType = authApiType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
