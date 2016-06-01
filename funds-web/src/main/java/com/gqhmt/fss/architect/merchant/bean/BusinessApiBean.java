package com.gqhmt.fss.architect.merchant.bean;

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
public class BusinessApiBean {
	// 主键
    private Long id;
    //商户号
    private String mchnNo;
    //商户名称
    private String mchnName;
    // ip编号
    private String apiNo;
    // 回调地址
    private String returnUrl;
    
    //api名称
    private String apiName;
    
    //api地址
    private String apiUrl;
    // 是否回调
    private Integer isReturn;
    
    // 创建日期
    private Date createTime;
    // 修改日期
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
	public Integer getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}
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
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
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
