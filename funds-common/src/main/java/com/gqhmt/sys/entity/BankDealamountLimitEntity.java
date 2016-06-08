package com.gqhmt.sys.entity;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月17日
 * Description:'银行交易限额实体';
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月17日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_bank_dealamount_limit")
public class BankDealamountLimitEntity implements java.io.Serializable{

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                    //int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "pay_channel")
    private int payChannel;                               // 支付渠道1：大钱 2：富友   bigint(11) DEFAULT NULL COMMENT 

    @Column(name="trade_type")
    private int tradeType;                              // 交易类型: 1-代扣，2-代付, int(11)

    @Column(name="bank_code")
    private String bankCode;                             // varchar(45) DEFAULT NULL COMMENT '银行别代码',

    @Column(name="limit_amount")
    private BigDecimal limitAmount;                           // decimal(16,2) DEFAULT NULL COMMENT 限制额度,

    @Column(name="creat_time")
    private Date createTime;                           // datetime DEFAULT NULL COMMENT '创建时间',

    @Column(name="create_user")
    private int createUser;                           // int(11) DEFAULT NULL COMMENT '创建者id',

    @Column(name="remark")
    private String remark;				//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public BigDecimal getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
  }