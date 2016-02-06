package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.account.entity.FssAccountEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 15:49
 * Description:
 * <p>新版账户表</>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_account")
public class FssAccountEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键\n',

    @Column(name="acc_no")
    private String accNo;                              // varchar(45) DEFAULT NULL COMMENT '账户编号,唯一，不可更改',

    @Column(name = "acc_balance")
    private BigDecimal accBalance;                     // decimal(17,2) DEFAULT NULL COMMENT '账户余额',

    @Column(name = "acc_freeze")
    private BigDecimal accFreeze;                      // decimal(17,2) DEFAULT NULL COMMENT '冻结金额',

    @Column(name = "acc_avai")
    private BigDecimal accAvai;                        // decimal(17,2) DEFAULT NULL COMMENT '可用余额',

    @Column(name = "acc_notran")
    private BigDecimal accNotran;                      // decimal(17,2) DEFAULT NULL COMMENT '未转结余额',

    @Column(name = "cust_no")
    private String custNo;                             // varchar(45) NOT NULL COMMENT '客户编号',

    @Column(name = "user_no")
    private String userNo;                             // varchar(45) NOT NULL COMMENT '用户表编号',

    @Column(name = "create_time")
    private Date createTime;                           //datetime DEFAULT NULL COMMENT '创建时间',

    @Column(name = "modify_time")
    private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',


    @Column(name = "acc_type")
    private Integer accType;                           // int(11) DEFAULT NULL COMMENT '账户类型，1借款账户；2线下出借账户；3线上账户；4抵押权人账户；5代偿人账户；99，冻结账户100公司账户',

    @Column(name = "state")
    private Integer state;                              // int(11) DEFAULT NULL COMMENT '账户状态：1，有效账户，2，账户锁定，3账户注销申请，4账户注销',

    @Column(name = "channel_no")
    private Integer channelNo;                         // varchar(45) DEFAULT NULL COMMENT '渠道编号，绑定渠道',

    @Column(name = "busi_no")
    private String busiNo;                            // varchar(45) DEFAULT NULL COMMENT '对应的业务编号，出借编号，借款编号，互联网用户编号。。。。',

    @Column(name="mchn_parent")
    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',

    @Column(name="mchn_child")
    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
