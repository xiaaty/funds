package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Filename:    com.gq.funds.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/6/2 15:43
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */

@Entity
@Table(name = "t_gq_fund_withraw_charge")
public class FundWithrawCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;                                                                    //bigint(20) NOT NULL
    @Column(name = "order_no",updatable = false)
    private String orderNo;                                                            //varchar(20) NULL订单号

    @Column(name="account_id",updatable = false)
    private Long accountId;                                                            //bigint(20) NULL账户id

    @Column(name="user_name",updatable = false)
    private String userName;                                                                  //varchar(30) NULL第三方账户名
    @Column(name="amount",updatable = false)
    private BigDecimal amount;                                                          //decimal(19,2) NULL提现额

    @Column(name="charge_amount",updatable = false)
    private BigDecimal  chargeAmount;                                                  //decimal(2,2) NULL手续费

    @Column(name="state")
    private int state;                                                                  //int(2) NULL状态，1，新增，2，提现操作已完成，3，已扣手续费

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
