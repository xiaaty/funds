package com.gqhmt.fss.architect.merchant.entity;

import javax.persistence.*;
import java.util.Date;

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
//api表
@Entity
@Table(name="t_gq_fss_api")
public class ApiAddrEntity {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //api编号
    @Column(name = "api_no")
    private String apiNo;
    //api名称
    @Column(name = "api_name")
    private String apiName;
    //api地址
    @Column(name = "api_url")
    private String apiUrl;
    //是否公共API(0：否，1：是)
    @Column(name = "pulic")
    private String pulic;
    // 创建日期
    @Column(name = "create_time")
    private Date createTime;
    // 修改日期
    @Column(name = "modify_time")
    private Date modifyTime;
    // 创建用户ID
    @Column(name = "create_user_id")
    private Long createUserId;
    // 修改人ID
    @Column(name = "modify_id")
    private Long modifyId;
	
    // api的类名
    @Column(name = "class_name")
    private String className;
    
    // api的方法名
    @Column(name = "method_name")
    private String methodName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApiNo() {
		return apiNo;
	}
	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
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
	public String getPulic() {
		return pulic;
	}
	public void setPulic(String pulic) {
		this.pulic = pulic;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getModifyId() {
		return modifyId;
	}
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}
    
	
	
}
