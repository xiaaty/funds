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
 * Description:出借资产详情实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_accounting_lend_asset_detail")
public class FssLendAssetDetailEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // bigint
   
    @Column(name="parent_id")
    private Long parentId;                        // bigint

    @Column(name="cust_no")
    private String custNo;                  // '客户编号',

    @Column(name="cust_name")
    private String custName;                  // varchar(45) NOT NULL COMMENT '客户姓名,


    @Column(name="acc_no")
    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',

    @Column(name="accounting_no")
    private String accountingNo;           // varchar(200) DEFAULT NULL COMMENT '账务编号，唯一',

    @Column(name="receive_balance")
    private Long receiveBalance;            // bigint '应收余额',
    
    @Column(name="receive_funds")
    private Long receiveFunds;            // bigint '应收资金',

    @Column(name="return_funds")
    private Long returnFunds;     // '实收资金,
    
    @Column(name="trade_time")
    private String tradeTime;     // 交易时间
    
    @Column(name="trade_date")
    private String tradeDate;     // 交易日期
    
    @Column(name="capital_type")
    private String capitalType;     // '资金类型,
    
    @Column(name="lend_no")
    private String lendNo;     // '出借编号,
    
    @Column(name="loan_no")
    private String loanNo;     // '借款编号,
    
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getReceiveFunds() {
		return receiveFunds;
	}

	public void setReceiveFunds(Long receiveFunds) {
		this.receiveFunds = receiveFunds;
	}

	public Long getReturnFunds() {
		return returnFunds;
	}

	public void setReturnFunds(Long returnFunds) {
		this.returnFunds = returnFunds;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}


	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

}