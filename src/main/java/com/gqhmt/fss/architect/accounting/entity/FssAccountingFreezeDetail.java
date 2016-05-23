package com.gqhmt.fss.architect.accounting.entity;

import com.gqhmt.annotations.AutoDate;

import javax.persistence.*;
import java.io.Serializable;
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
 * Create at:   2016年5月3日
 * Description:
 * <p>冻结资金详情实体
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月3日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_accounting_freeze_detail")
public class FssAccountingFreezeDetail implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // bigint
    
    @Column(name="parent_id")
    private Long parentId;                        // bigint

    @Column(name="cust_no")
    private String custNo;                  // '客户编号',

    @Column(name="acc_no")
    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',
  
    @Column(name="accounting_no")
    private String accountingNo;           // varchar(200) DEFAULT NULL COMMENT '账务编号，唯一',
    
    @Column(name="water_no")
    private String waterNo;           // varchar(200) DEFAULT NULL COMMENT '记账编号，唯一',
    
    @Column(name="fund_type")
    private String fundType;           // varchar(200) DEFAULT NULL COMMENT '资金类型',
    
    @Column(name="trade_type")
    private String tradeType;           // varchar(200) DEFAULT NULL COMMENT '交易类型',
    
    @Column(name="freeze_amount")
    private Long freezeAmount;            // bigint 冻结余额',
    
    @Column(name="unfreeze_amount")
    private Long unfreezeAmount;            // bigint 解冻金额，',
    
    @Column(name="freeze_balance")
    private Long freezeBalance;            // bigint 冻结余额，',

    @Column(name="currency")
    private String currency;           // varchar(200) DEFAULT NULL COMMENT '币种：人民币、美元.....',
   
    @Column(name="trade_date")
    private String tradeDate;     // '交易日期,
    
    @Column(name="trade_time")
    private String tradeTime;     // '交易时间,
    
    @Column(name="summary")
    private String summary;     // '交易摘要,
    
    @Column(name="pay_channel")
    private String payChannel;     // '交易渠道,
    
    @Column(name="create_time")
    @AutoDate
    private Date createTime;               // datetime NOT NULL,

    @Column(name="modify_time")
    @AutoDate
    private Date modifyTime;               // datetime NOT NULL,


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(String accountingNo) {
		this.accountingNo = accountingNo;
	}


	public Long getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(Long freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getWaterNo() {
		return waterNo;
	}

	public void setWaterNo(String waterNo) {
		this.waterNo = waterNo;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getUnfreezeAmount() {
		return unfreezeAmount;
	}

	public void setUnfreezeAmount(Long unfreezeAmount) {
		this.unfreezeAmount = unfreezeAmount;
	}

	public Long getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(Long freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


}