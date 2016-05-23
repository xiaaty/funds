package com.gqhmt.fss.architect.account.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 账户查询数据传输对象
 * com.gq.p2p.account.Bean
 * @className BussAndAccountBean<br/> 
 * @author 柯禹来
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class FssFuiouAccountBean implements Serializable{
	 	private Long id;                        // int(11) NOT NULL AUTO_INCREMENT,
	    private String cusNo;                  // varchar(45) NOT NULL COMMENT '客户编号',
	    private String userNo;                 //varchar(45) DEFAULT NULL COMMENT '用户编号',
	    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',
	    private String accUserName;           // varchar(200) DEFAULT NULL COMMENT '客户姓名',
	    private String bankCardNo;            // varchar(45) DEFAULT NULL COMMENT '绑定银行卡 编号',
	    private Date createTime;               // datetime NOT NULL,
	    private Date modifyTime;               // datetime NOT NULL,
	    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',
	    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
	    private Integer hasOpenAccFuiou;
//	=======================================
	    private String name;                                         // varchar(45) DEFAULT NULL COMMENT '客户姓名',
	    private String mobile;                                       //` varchar(45) DEFAULT NULL COMMENT '客户手机号',
	    private Integer certType;                                    // varchar(45) DEFAULT NULL COMMENT '证件类型，1身份证',
	    private String certNo;                                      // varchar(45) DEFAULT NULL COMMENT '证件号码',
	    private String userId;                                      // bigint(20) DEFAULT NULL COMMENT '冠e通用户表id',
	    private String custNo;                                      // varchar(45) DEFAULT NULL COMMENT '客户编号唯一',
	    private Integer isAuthRealName;                               // varchar(45) DEFAULT NULL COMMENT '是否实名验证，0未验证，1富友方式验证，2接入身份证验证系统验证，4面对面验证，8图片上传验证',
	    private Long createUserId;                                 // bigint(20) DEFAULT NULL,
	    private Long modifyUserId;                                 // bigint(20) DEFAULT NULL,
	    private Long custId;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCusNo() {
			return cusNo;
		}
		public void setCusNo(String cusNo) {
			this.cusNo = cusNo;
		}
		public String getUserNo() {
			return userNo;
		}
		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}
		public String getAccNo() {
			return accNo;
		}
		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}
		public String getAccUserName() {
			return accUserName;
		}
		public void setAccUserName(String accUserName) {
			this.accUserName = accUserName;
		}
		public String getBankCardNo() {
			return bankCardNo;
		}
		public void setBankCardNo(String bankCardNo) {
			this.bankCardNo = bankCardNo;
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
		public Integer getHasOpenAccFuiou() {
			return hasOpenAccFuiou;
		}
		public void setHasOpenAccFuiou(Integer hasOpenAccFuiou) {
			this.hasOpenAccFuiou = hasOpenAccFuiou;
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
		public Integer getCertType() {
			return certType;
		}
		public void setCertType(Integer certType) {
			this.certType = certType;
		}
		public String getCertNo() {
			return certNo;
		}
		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getCustNo() {
			return custNo;
		}
		public void setCustNo(String custNo) {
			this.custNo = custNo;
		}
		public Integer getIsAuthRealName() {
			return isAuthRealName;
		}
		public void setIsAuthRealName(Integer isAuthRealName) {
			this.isAuthRealName = isAuthRealName;
		}
		public Long getCreateUserId() {
			return createUserId;
		}
		public void setCreateUserId(Long createUserId) {
			this.createUserId = createUserId;
		}
		public Long getModifyUserId() {
			return modifyUserId;
		}
		public void setModifyUserId(Long modifyUserId) {
			this.modifyUserId = modifyUserId;
		}
		public Long getCustId() {
			return custId;
		}
		public void setCustId(Long custId) {
			this.custId = custId;
		}     
}
