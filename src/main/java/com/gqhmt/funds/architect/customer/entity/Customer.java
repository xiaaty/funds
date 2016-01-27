package com.gqhmt.funds.architect.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户信息实体bean
 * 
 */
@Entity
@Table(name = "t_gq_fss_customer")
public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	//客户ID主键
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//客户姓名
	private String name;
	
	//客户手机号
	private String mobile;
	
	////证件类型 1：身份证 2：护照 3：驾照 4：军人证
	private Integer cert_type;
	
	//证件号码
	private String cert_no;
	//冠e通用户表id
	private Integer user_id;
	//创建日期
	private Date create_time;
	
	//修改日期
	private Date modify_time;
	
	//客户唯一编号
	private String cust_no;
	
	//用户唯一编号
	private String user_no;
	
	//是否实名验证
	private String is_auth_real_name;
	
	private Integer create_user_id;
	private Integer modify_user_id;
	//主商户号
	private String mchn_parent;
	//子商户号
	private String mchn_child;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
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
	public String getIs_auth_real_name() {
		return is_auth_real_name;
	}
	public void setIs_auth_real_name(String is_auth_real_name) {
		this.is_auth_real_name = is_auth_real_name;
	}
	public Integer getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}
	public Integer getModify_user_id() {
		return modify_user_id;
	}
	public void setModify_user_id(Integer modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	public String getMchn_parent() {
		return mchn_parent;
	}
	public void setMchn_parent(String mchn_parent) {
		this.mchn_parent = mchn_parent;
	}
	public String getMchn_child() {
		return mchn_child;
	}
	public void setMchn_child(String mchn_child) {
		this.mchn_child = mchn_child;
	}
	
	
	
}