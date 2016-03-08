package com.gqhmt.funds.architect.customer.entity;

import javax.persistence.*;
import java.util.Date;

/**
* 用户表实体
*
* @author 郭福 2015-01-17 21:18
*
*/
@Entity
@Table(name = "t_gq_user")
public class UserEntity implements java.io.Serializable {

private static final long serialVersionUID = 1L;
/**
* 主键ID
*/
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
/**
* 用户Uuid
*/
private String userUuid;
/**
* 用户名
*/
private String userName;
/**
* 手机号码
*/
private String mobilePhone;
/**
* 邮箱
*/
private String email;
/**
* 密码
*/
private String password;
/**
* 头像
*/
private String headImg;
/**
* 最后投标时间
*/
private Date lastBidTime;
/**
* 最后登录时间
*/
private Date lastLoginTime;
/**
* 最后登录IP
*/
private String lastLoginIp;
/**
* 登录次数
*/
private Integer loginCount;
/**
* 锁定时间
*/
private Date lockTime;
/**
* 1:可用，2:禁用
*/
private String disable;
/**
* 创建时间
*/
private Date createTime;
/**
* 修改时间
*/
private Date modifyTime;
/**
* 积分
*/
private Integer integral;
/**
* 信用等级
*/
private Integer creditLevel;
/**
* 推荐人
*/
private String recommender;

private Integer custId;

private String emailVerifiCode;

private Date emailVerifiTime;

private Integer referenceId;

private Integer isFirstDebt;
/**
* 用户来源 0：线上注册，1线下自动
*/
private Integer userFrom;

private String source;//用户来源，主要针对推广。

private String IsFirstCast;//是否是首投，0:首投，1:非首投

private String yrtUserId;

private String nickname;

private Integer userType;

private Integer isVerify;

private String openId;

private Integer riskScore;

private Date riskEvalTime;
// Constructors

/** default constructor */
public UserEntity() {
}

public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

@Column(name = "user_uuid", length = 36)
public String getUserUuid() {
return this.userUuid;
}

public void setUserUuid(String userUuid) {
this.userUuid = userUuid;
}

@Column(name = "user_name", length = 50)
public String getUserName() {
return this.userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

@Column(name = "mobile_phone", length = 20)
public String getMobilePhone() {
return this.mobilePhone;
}

public void setMobilePhone(String mobilePhone) {
this.mobilePhone = mobilePhone;
}

@Column(name = "email", length = 100)
public String getEmail() {
return this.email;
}

public void setEmail(String email) {
this.email = email;
}

@Column(name = "password", length = 50)
public String getPassword() {
return this.password;
}

public void setPassword(String password) {
this.password = password;
}

@Column(name = "head_img", length = 200)
public String getHeadImg() {
return this.headImg;
}

public void setHeadImg(String headImg) {
this.headImg = headImg;
}

@Column(name = "last_bid_time", length = 19)
public Date getLastBidTime() {
return this.lastBidTime;
}

public void setLastBidTime(Date lastBidTime) {
this.lastBidTime = lastBidTime;
}

@Column(name = "last_login_time", length = 19)
public Date getLastLoginTime() {
return this.lastLoginTime;
}

public void setLastLoginTime(Date lastLoginTime) {
this.lastLoginTime = lastLoginTime;
}

@Column(name = "last_login_ip", length = 30)
public String getLastLoginIp() {
return this.lastLoginIp;
}

public void setLastLoginIp(String lastLoginIp) {
this.lastLoginIp = lastLoginIp;
}

@Column(name = "login_count")
public Integer getLoginCount() {
return this.loginCount;
}

public void setLoginCount(Integer loginCount) {
this.loginCount = loginCount;
}

@Column(name = "lock_time", length = 19)
public Date getLockTime() {
return this.lockTime;
}

public void setLockTime(Date lockTime) {
this.lockTime = lockTime;
}

@Column(name = "disable", length = 1)
public String getDisable() {
return disable;
}

public void setDisable(String disable) {
this.disable = disable;
}

@Column(name = "create_time", length = 19)
public Date getCreateTime() {
return this.createTime;
}

public void setCreateTime(Date createTime) {
this.createTime = createTime;
}

@Column(name = "modify_time", length = 19)
public Date getModifyTime() {
return this.modifyTime;
}

public void setModifyTime(Date modifyTime) {
this.modifyTime = modifyTime;
}

@Column(name = "integral")
public Integer getIntegral() {
return this.integral;
}

public void setIntegral(Integer integral) {
this.integral = integral;
}

@Column(name = "credit_level")
public Integer getCreditLevel() {
return this.creditLevel;
}

public void setCreditLevel(Integer creditLevel) {
this.creditLevel = creditLevel;
}

@Column(name = "recommender", length = 50)
public String getRecommender() {
return recommender;
}

public void setRecommender(String recommender) {
this.recommender = recommender;
}

@Column(name = "cust_id")
public Integer getCustId() {
return custId;
}

public void setCustId(Integer custId) {
this.custId = custId;
}

@Column(name = "email_verifi_code")
public String getEmailVerifiCode() {
return emailVerifiCode;
}

public void setEmailVerifiCode(String emailVerifiCode) {
this.emailVerifiCode = emailVerifiCode;
}

@Column(name = "email_verifi_time")
public Date getEmailVerifiTime() {
return emailVerifiTime;
}

public void setEmailVerifiTime(Date emailVerifiTime) {
this.emailVerifiTime = emailVerifiTime;
}

@Column(name = "user_from")
public Integer getUserFrom() {
return this.userFrom;
}

public void setUserFrom(Integer userFrom) {
this.userFrom = userFrom;
}

@Column(name = "reference_id")
public Integer getReferenceId() {
return referenceId;
}

public void setReferenceId(Integer referenceId) {
this.referenceId = referenceId;
}

@Column(name = "is_first_debt")
public Integer getIsFirstDebt() {
return isFirstDebt;
}

public void setIsFirstDebt(Integer isFirstDebt) {
this.isFirstDebt = isFirstDebt;
}





@Column(name = "source")
public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}
@Column(name = "first_cast")
public String getIsFirstCast() {
	return IsFirstCast;
}

public void setIsFirstCast(String isFirstCast) {
	IsFirstCast = isFirstCast;
}
@Column(name = "yrt_user_id")
public String getYrtUserId() {
	return yrtUserId;
}

public void setYrtUserId(String yrtUserId) {
	this.yrtUserId = yrtUserId;
}
@Column(name = "nickname")
public String getNickname() {
	return nickname;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}
@Column(name = "user_type")
public Integer getUserType() {
	return userType;
}

public void setUserType(Integer userType) {
	this.userType = userType;
}

@Column(name = "is_verify")
public Integer getIsVerify() {
	return isVerify;
}

public void setIsVerify(Integer isVerify) {
	this.isVerify = isVerify;
}
@Column(name = "openId")
public String getOpenId() {
	return openId;
}

public void setOpenId(String openId) {
	this.openId = openId;
}
@Column(name = "risk_score")
public Integer getRiskScore() {
	return riskScore;
}

public void setRiskScore(Integer riskScore) {
	this.riskScore = riskScore;
}
@Column(name = "risk_eval_time")
public Date getRiskEvalTime() {
	return riskEvalTime;
}

public void setRiskEvalTime(Date riskEvalTime) {
	this.riskEvalTime = riskEvalTime;
}

}
