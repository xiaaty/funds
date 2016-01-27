package com.gqhmt.funds.architect.account.entity;



import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.p2p.account.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @since: JDK 1.7
 * Create at:   2016/1/25 11:38
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/65 keyulai     1.2     1.2 Version
 */
@Entity
@Table(name = "t_gq_fss_account")
public class HxAccountEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	//账号
	@Column(name = "account_no",updatable = false)
    private String accountNo;
	
	//账户余额
	@Column(name = "acc_balance")
    private BigDecimal accBalance;
	
	//冻结资金
	@Column(name = "acc_freeze")
	private BigDecimal accFreeze;
	
	//可用余额
	@Column(name = "acc_avai")
	private BigDecimal accAvai;
	
	//未转接余额
	@Column(name = "acc_notran")
	private BigDecimal accNotran;
	
	//客户编号
	@Column(name = "cust_no")
	private String custNo;
	
	//用户表编号
	@Column(name = "user_no")
	private String userNo;
	
	//创建时间
	@Column(name = "create_time")
	private Date createTime;
	
	//最后修改时间
	@Column(name = "modify_time")
    private Date modifyTime;
	
	//账户类型，1借款账户；2线下出借账户；3线上账户；4抵押权人账户；5代偿人账户；99，冻结账户100公司账户
	@Column(name = "acc_type")
	private Integer accType;
	
	//账户状态：1，有效账户，2，账户锁定，3账户注销申请，4账户注销
	@Column(name = "state")
	private Integer state;
	
	//渠道编号，绑定渠道
	@Column(name = "channel_no")
	private String channelNo;
	
	//对应的业务编号，出借编号，借款编号，互联网用户编号
	@Column(name = "busi_no")
	private String busiNo;
	
	@Column(name = "mchn_parent")
	private String mchnParent;
	
	@Column(name = "mchn_child")
	private String mchnChild;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
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
