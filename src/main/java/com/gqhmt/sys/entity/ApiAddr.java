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
//资金商户ip表
@Entity
@Table(name="t_gq_fss_api_addr")
public class ApiAddr {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 商户标识
    @Column(name = "busi_code")
    private String busiCode;
    // 校验方式(0:不校验 1:IP段匹配)
    @Column(name = "api_addr")
    private String apiAddr;
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
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getApiAddr() {
		return apiAddr;
	}
	public void setApiAddr(String apiAddr) {
		this.apiAddr = apiAddr;
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
