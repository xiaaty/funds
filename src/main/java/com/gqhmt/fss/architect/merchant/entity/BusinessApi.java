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
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/21 18:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/02/19  jhz    1.0     1.0 Version
 */
//资金商户api表
@Entity
@Table(name="t_gq_fss_mchn_api")
public class BusinessApi {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //商户号
    @Column(name = "mchn_no")
    private String mchnNo;
    // ip地址
    @Column(name = "api_no")
    private String apiNo;
    // 回调地址
    @Column(name = "return_url")
    private String returnUrl;
    
    // 是否回调
    @Column(name = "is_return")
    private int isReturn;
    
    // 创建日期
    @Column(name = "create_time")
    private Date createTime;
    // 修改日期
    @Column(name = "modify_time")
    private Date modifyTime;
	public String getMchnNo() {
		return mchnNo;
	}
	public void setMchnNo(String mchnNo) {
		this.mchnNo = mchnNo;
	}
	public String getApiNo() {
		return apiNo;
	}
	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public int getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
	
	
}
