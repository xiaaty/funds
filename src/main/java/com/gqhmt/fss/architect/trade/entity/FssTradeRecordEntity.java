package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_record")
public class FssTradeRecordEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                //int(11)        (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references

    @Column(name = "cust_no")
    private String custNo;                         //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "user_no")
    private String userNo ;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "acc_no")
    private String accNo;                          //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "trade_type")
    private Integer tradeType;                      //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  交易类型，充值，提现

    @Column(name = "amount")
    private BigDecimal amount  ;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  交易金额

    @Column(name = "charge")
    private BigDecimal charge  ;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  手续费

    @Column(name = "thirdparyt_charge")
    private BigDecimal thirdparytCharge;               //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  第三方收费

    @Column(name = "trade_date")
    private String tradeDate ;                     // char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "trade_time")
    private String tradeTime ;                     // char(6)        utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "apply_no")
    private String applyNo  ;                      //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  申请编号

    @Column(name = "settle_type")
    private Integer settleType ;                    //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  提现时效。0：T+0，1：T+1

    @Column(name = "trade_state")
    private Integer tradeState  ;                   // int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  交易状态，未交易，已交易，已退回

    @Column(name = "trade_result")
    private Integer tradeResult ;                   //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  交易结果，未交易，交易成功，交易失败，交易退票

    @Column(name = "sumary")
    private String sumary      ;                    // varchar(3000)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  交易描述

    @Column(name = "create_time")
    private Date createTime  ;                   //datetime       (NULL)           YES             (NULL)                   select,insert,update,references

    @Column(name = "modify_time")
    private Date modifyTime  ;                   //datetime       (NULL)           YES             (NULL)                   select,insert,update,references

    @Column(name = "mchn_parent")
    private String mchnParent  ;                   //qvarchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "mchn_child")
    private String mchnChild   ;                   //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "order_no")
    private String orderNo    ;                    // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

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

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getThirdparytCharge() {
		return thirdparytCharge;
	}

	public void setThirdparytCharge(BigDecimal thirdparytCharge) {
		this.thirdparytCharge = thirdparytCharge;
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

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}

	public Integer getTradeState() {
		return tradeState;
	}

	public void setTradeState(Integer tradeState) {
		this.tradeState = tradeState;
	}

	public Integer getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(Integer tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
}
