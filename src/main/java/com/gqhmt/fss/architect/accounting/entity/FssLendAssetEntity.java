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
 * Create at:   2016年4月28日
 * Description:出借资产实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_accounting_lend_asset")
public class FssLendAssetEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // bigint

    @Column(name="cust_no")
    private String custNo;                  // '客户编号',

    @Column(name="acc_no")
    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',

    @Column(name="accounting_no")
    private String accountingNo;           // varchar(200) DEFAULT NULL COMMENT '账务编号，唯一',

    @Column(name="receive_balance")
    private Long receiveBalance;            // bigint '应收余额',

    @Column(name="receive_principal")
    private Long receivePrincipal;     // '应收本金,
    
    @Column(name="receive_interest")
    private Long receiveInterest;     // '应收利息,
    
    @Column(name="return_principal")
    private Long returnPrincipal;     // '已收本金,
    
    @Column(name="return_interest")
    private Long returnInterest;     // '已收利息,
    
    @Column(name="trade_date")
    private String tradeDate;     // '交易日期,
    
    @Column(name="trade_time")
    private String tradeTime;     // '交易时间,
    
    @Column(name="balance")
    private Long balance;     // '账户余额,
    
    @Column(name="freeze_amount")
    private Long freezeAmount;     // '冻结金额,
    
    @Column(name="available_amount")
    private Long availableAmount;     // '可用金额,
    
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

	public Long getReceiveBalance() {
		return receiveBalance;
	}

	public void setReceiveBalance(Long receiveBalance) {
		this.receiveBalance = receiveBalance;
	}

	public Long getReceivePrincipal() {
		return receivePrincipal;
	}

	public void setReceivePrincipal(Long receivePrincipal) {
		this.receivePrincipal = receivePrincipal;
	}

	public Long getReceiveInterest() {
		return receiveInterest;
	}

	public void setReceiveInterest(Long receiveInterest) {
		this.receiveInterest = receiveInterest;
	}

	public Long getReturnPrincipal() {
		return returnPrincipal;
	}

	public void setReturnPrincipal(Long returnPrincipal) {
		this.returnPrincipal = returnPrincipal;
	}

	public Long getReturnInterest() {
		return returnInterest;
	}

	public void setReturnInterest(Long returnInterest) {
		this.returnInterest = returnInterest;
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

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(Long freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public Long getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(Long availableAmount) {
		this.availableAmount = availableAmount;
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

}