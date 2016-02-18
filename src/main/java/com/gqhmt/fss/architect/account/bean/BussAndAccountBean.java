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
    private String bussinessname;                                         // varchar(45) DEFAULT NULL COMMENT '客户姓名',
    private String bussinessmobile;                                       //` varchar(45) DEFAULT NULL COMMENT '客户手机号',
    private Integer bussinesscerttype;                                    // varchar(45) DEFAULT NULL COMMENT '证件类型，1身份证',
    private String bussinesscertno;                                      // varchar(45) DEFAULT NULL COMMENT '证件号码',
    private String bussinessuserid;                                      // bigint(20) DEFAULT NULL COMMENT '冠e通用户表id',
    private Date bussinesscreatetime;                                    // varchar(45) DEFAULT NULL COMMENT '创建时间',
    private Date bussinessmodifytime;                                    // varchar(45) DEFAULT NULL COMMENT '最后修改时间',
    private String custNo;                       // varchar(45) DEFAULT NULL COMMENT '客户编号唯一',
    private String bussinessuserno;         
    
  //账户信息
	private String accNo;                              // varchar(45) DEFAULT NULL COMMENT '账户编号,唯一，不可更改',
	private BigDecimal accBalance;                     // decimal(17,2) DEFAULT NULL COMMENT '账户余额',
	private BigDecimal accFreeze;                      // decimal(17,2) DEFAULT NULL COMMENT '冻结金额',
	private BigDecimal accAvai;                        // decimal(17,2) DEFAULT NULL COMMENT '可用余额',
	private BigDecimal accNotran;                      // decimal(17,2) DEFAULT NULL COMMENT '未转结余额',
	private String userNo;                             // varchar(45) NOT NULL COMMENT '用户表编号',
	private Date accCreateTime;                           //datetime DEFAULT NULL COMMENT '创建时间',
	private Date accModifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',
	private Integer accType;                           // int(11) DEFAULT NULL COMMENT '账户类型，1借款账户；2线下出借账户；3线上账户；4抵押权人账户；5代偿人账户；99，冻结账户100公司账户',
	private Integer state;                              // int(11) DEFAULT NULL COMMENT '账户状态：1，有效账户，2，账户锁定，3账户注销申请，4账户注销',
	private Integer channelNo;                         // varchar(45) DEFAULT NULL COMMENT '渠道编号，绑定渠道',
	private String busiNo;                            // varchar(45) DEFAULT NULL COMMENT '对应的业务编号，出借编号，借款编号，互联网用户编号。。。。',
    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',
    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
	public String getBussinessname() {
		return bussinessname;
	}
	public void setBussinessname(String bussinessname) {
		this.bussinessname = bussinessname;
	}
	public String getBussinessmobile() {
		return bussinessmobile;
	}
	public void setBussinessmobile(String bussinessmobile) {
		this.bussinessmobile = bussinessmobile;
	}
	public Integer getBussinesscerttype() {
		return bussinesscerttype;
	}
	public void setBussinesscerttype(Integer bussinesscerttype) {
		this.bussinesscerttype = bussinesscerttype;
	}
	public String getBussinesscertno() {
		return bussinesscertno;
	}
	public void setBussinesscertno(String bussinesscertno) {
		this.bussinesscertno = bussinesscertno;
	}
	public String getBussinessuserid() {
		return bussinessuserid;
	}
	public void setBussinessuserid(String bussinessuserid) {
		this.bussinessuserid = bussinessuserid;
	}
	public Date getBussinesscreatetime() {
		return bussinesscreatetime;
	}
	public void setBussinesscreatetime(Date bussinesscreatetime) {
		this.bussinesscreatetime = bussinesscreatetime;
	}
	public Date getBussinessmodifytime() {
		return bussinessmodifytime;
	}
	public void setBussinessmodifytime(Date bussinessmodifytime) {
		this.bussinessmodifytime = bussinessmodifytime;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getBussinessuserno() {
		return bussinessuserno;
	}
	public void setBussinessuserno(String bussinessuserno) {
		this.bussinessuserno = bussinessuserno;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
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
	public BigDecimal getAccNotran() {
		return accNotran;
	}
	public void setAccNotran(BigDecimal accNotran) {
		this.accNotran = accNotran;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getAccCreateTime() {
		return accCreateTime;
	}
	public void setAccCreateTime(Date accCreateTime) {
		this.accCreateTime = accCreateTime;
	}
	public Date getAccModifyTime() {
		return accModifyTime;
	}
	public void setAccModifyTime(Date accModifyTime) {
		this.accModifyTime = accModifyTime;
	}
	public Integer getAccType() {
		return accType;
	}
	public void setAccType(Integer accType) {
		this.accType = accType;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
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
