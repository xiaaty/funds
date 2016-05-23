package com.gqhmt.fss.architect.account.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 客户资金账户查询数据传输对象
 * com.gq.p2p.account.Bean
 * @className BussAndAccountBean<br/> 
 * @author kyl
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class BussAndAccountBean implements Serializable{
	//客户信息
	private String accNo;                              // varchar(45) DEFAULT NULL COMMENT '账户编号,唯一，不可更改',
	private String custNo;                              // varchar(45) DEFAULT NULL COMMENT '账户编号,唯一，不可更改',
    private String custName;                                         // varchar(45) DEFAULT NULL COMMENT '客户姓名',
    private String customerType;
    private String certType;                                    // varchar(45) DEFAULT NULL COMMENT '证件类型，1身份证',
    private String certNo;                                      // varchar(45) DEFAULT NULL COMMENT '证件号码',
    private String userNo;
    private String channelNo;                                  //交易渠道
    private Integer custId;
    private Integer tradeType;                               //来源
    private String mobile;                                       //` varchar(45) DEFAULT NULL COMMENT '客户手机号',
    private String state;                              // int(11) DEFAULT NULL COMMENT '账户状态：1，有效账户，2，账户锁定，3账户注销申请，4账户注销',
    private String accType;                           // int(11) DEFAULT NULL COMMENT '账户类型，1借款账户；2线下出借账户；3线上账户；4抵押权人账户；5代偿人账户；99，冻结账户100公司账户',
    private BigDecimal accBalance;                     // decimal(17,2) DEFAULT NULL COMMENT '账户余额',
    private BigDecimal accFreeze;                      // decimal(17,2) DEFAULT NULL COMMENT '冻结金额',
    private BigDecimal accAvai;                        // decimal(17,2) DEFAULT NULL COMMENT '可用余额',
    private String busiNo;                            // varchar(45) DEFAULT NULL COMMENT '对应的业务编号，出借编号，借款编号，互联网用户编号。。。。',
    private String thirdAccNo;
    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',
    private Integer userId ;
    private String isAuthRealName;   //是否实名验证
    private String createUserId;
    private String modifyUserId;
	private Date createTime;                           //datetime DEFAULT NULL COMMENT '创建时间',
	private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public BigDecimal getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(BigDecimal accBalance) {
		this.accBalance = accBalance;
	}
	public BigDecimal getAccFreeze() {
		return accFreeze;
	}
	public void setAccFreeze(BigDecimal accFreeze) {
		this.accFreeze = accFreeze;
	}
	public BigDecimal getAccAvai() {
		return accAvai;
	}
	public void setAccAvai(BigDecimal accAvai) {
		this.accAvai = accAvai;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getThirdAccNo() {
		return thirdAccNo;
	}
	public void setThirdAccNo(String thirdAccNo) {
		this.thirdAccNo = thirdAccNo;
	}
	public String getMchnChild() {
		return mchnChild;
	}
	public void setMchnChild(String mchnChild) {
		this.mchnChild = mchnChild;
	}
	public String getMchnParent() {
		return mchnParent;
	}
	public void setMchnParent(String mchnParent) {
		this.mchnParent = mchnParent;
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
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIsAuthRealName() {
		return isAuthRealName;
	}
	public void setIsAuthRealName(String isAuthRealName) {
		this.isAuthRealName = isAuthRealName;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
}
