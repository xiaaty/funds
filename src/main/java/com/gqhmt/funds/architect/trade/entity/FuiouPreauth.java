package com.gqhmt.funds.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Filename:    com.gq.funds.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/4/3 19:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/4/3  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_fuiou_preauth")
public class FuiouPreauth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;                            // bigint(20) NOT NULL主键

    @Column(name="account_id" ,updatable = false)
    private Long  accountId;                    // int(11) NULL账户id

    @Column(name="contract_no",updatable = false)
    private String contractNo;                 // bigint(20) NULL预授权合同号

    @Column(name="source_id",updatable =  false)
    private Integer sourceId;                        //big int(20) NULL对应标的

    @Column(name="amount",updatable = false)
    private BigDecimal amount;                  // decimal(11,2) NULL预授权金额
    @Column(name="state")
    private int state;                         // int(11) NULL是否有效
    @Column(name="use_amount")
    private BigDecimal useAmount;             // decimal(11,2) NULL已使用金额

    @Column(name="toUserName",updatable = false)
    private String toUserName;        //入账账户；需要添加，标记

    @Column(name="userName",updatable = false)
    private String userName;

    @Column(name="type",updatable = false)
    private int type;

    @Column(name="bid",updatable = false)
    private Integer bid;

    @Column(name="tenderid",updatable = false)
    private Integer tenderid;

    @Column(name="orderNo",updatable = false)
    private String orderNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }


    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getTenderid() {
        return tenderid;
    }

    public void setTenderid(Integer tenderid) {
        this.tenderid = tenderid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
