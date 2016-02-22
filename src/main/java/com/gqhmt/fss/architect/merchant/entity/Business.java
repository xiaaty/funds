package com.gqhmt.fss.architect.merchant.entity;

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
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/02/02 11:44
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/02/02 jhz      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_mchn")
public class Business {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 商户名称
    @Column(name = "mchn_name")
    private String mchnName;
    // 商户号，系统自动生产，8位数字+字母
    @Column(name = "mchn_no")
    private String mchnNo;
    // 父商户id
    @Column(name = "parent_id")
    private String parentId;
    // 父商户号
    @Column(name = "parent_no")
    private String parentNo;
    // 商户密钥，系统自动生成
    @Column(name = "mchn_key")
    private String mchnKey;
    // 商户密钥，系统自动生成
    @Column(name = "mchn_key_end_date")
    private String mchnKeyEndDate;
    // 创建日期
    @Column(name = "create_time")
    private Date createTime;
    // 修改日期
    @Column(name = "modify_time")
    private Date modifyTime;
    // 状态
    @Column(name = "state")
    private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMchnName() {
		return mchnName;
	}
	public void setMchnName(String mchnName) {
		this.mchnName = mchnName;
	}
	public String getMchnNo() {
		return mchnNo;
	}
	public void setMchnNo(String mchnNo) {
		this.mchnNo = mchnNo;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	public String getMchnKey() {
		return mchnKey;
	}
	public void setMchnKey(String mchnKey) {
		this.mchnKey = mchnKey;
	}
	public String getMchnKeyEndDate() {
		return mchnKeyEndDate;
	}
	public void setMchnKeyEndDate(String mchnKeyEndDate) {
		this.mchnKeyEndDate = mchnKeyEndDate;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
	
    
}
