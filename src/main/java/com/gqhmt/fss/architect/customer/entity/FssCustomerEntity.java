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
@Entity
@Table(name="t_gq_fss_customer")
public class FssCustomerEntity implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                             // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name="name")
    private String name;                                         // varchar(45) DEFAULT NULL COMMENT '客户姓名',

    @Column(name="mobile")
    private String mobile;                                       //` varchar(45) DEFAULT NULL COMMENT '客户手机号',

    @Column(name="cert_type")
    private Integer cert_type;                                    // varchar(45) DEFAULT NULL COMMENT '证件类型，1身份证',


    @Column(name="cert_no")
    private String cert_no;                                      // varchar(45) DEFAULT NULL COMMENT '证件号码',

    @Column(name="user_id")
    private String user_id;                                      // bigint(20) DEFAULT NULL COMMENT '冠e通用户表id',

    @Column(name="create_time")
    private Date create_time;                                    // varchar(45) DEFAULT NULL COMMENT '创建时间',

    @Column(name="modify_time")
    private Date modify_time;                                    // varchar(45) DEFAULT NULL COMMENT '最后修改时间',

    @Column(name="cust_no")
    private String cust_no;                                      // varchar(45) DEFAULT NULL COMMENT '客户编号唯一',

    @Column(name="user_no")
    private String user_no;                                      // varchar(45) DEFAULT NULL COMMENT '用户编号 唯一',

    @Column(name="is_auth_real_name")
    private Integer is_auth_real_name;                               // varchar(45) DEFAULT NULL COMMENT '是否实名验证，0未验证，1富友方式验证，2接入身份证验证系统验证，4面对面验证，8图片上传验证',

    @Column(name="create_user_id")
    private Long create_user_id;                                 // bigint(20) DEFAULT NULL,

    @Column(name="modify_user_id")
    private Long modifyUserId;                                 // bigint(20) DEFAULT NULL,

    @Column(name="modify_user_id")
    private String mchnParent;                             // varchar(45) DEFAULT NULL COMMENT '主商户号',

    @Column(name="modify_user_id")
    private String mchnChild;                                  // varchar(45) DEFAULT NULL COMMENT '子商户号',

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCert_type() {
		return cert_type;
	}

	public void setCert_type(Integer cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public Integer getIs_auth_real_name() {
		return is_auth_real_name;
	}

	public void setIs_auth_real_name(Integer is_auth_real_name) {
		this.is_auth_real_name = is_auth_real_name;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMchnParent() {
		return mchnParent;
	}

	public void setMchnParent(String mchnParent) {
		this.mchnParent = mchnParent;
	}

	public String getMchnChild() {
		return mchnChild;
	}

	public void setMchnChild(String mchnChild) {
		this.mchnChild = mchnChild;
	}

	
}
