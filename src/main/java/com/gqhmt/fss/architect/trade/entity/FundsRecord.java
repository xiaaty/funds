package com.gqhmt.fss.architect.trade.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chris on 2015/5/2.
 */
@Entity
@Table(name="t_gq_fund_record")
public class FundsRecord implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //bigint(20) NOT NULL主键

    @Column(updatable = false)
    private Long bid;                                   //bigint(20) NULL标的id

    @Column(updatable = false)
    private Long oId;                                   //bigint(20) NULL投标ID，还款ID，债权id

    @Column(updatable = false)
    private Integer type;                              //int(11) NULL1，投标，2还款，3债权转让，4一般转账

    @Column(name="order_no",updatable = false)
    private String orderNo;                             //ovarchar(50) NULL订单号

    @Column(name="from_account_id",updatable = false)
    private Long fromAccountId;                       //bigint(20) NULL出账账户

    @Column(name="from_user_name",updatable = false)
    private String fromUserName;                      //varchar(50) NULL出账账户户名

    @Column(name="from_user_name_cn",updatable = false)
    private String fromUserNameCn;                  //varchar(50) NULL出账账户中文用户姓名

    @Column(name="to_account_id",updatable = false)
    private Long toAccountId;                         //bigint(20) NULL入账账户

    @Column(name="to_user_name",updatable = false)
    private String toUserName;                        //varchar(50) NULL入账账户户名

    @Column(name="to_user_name_cn",updatable = false)
    private String toUserNameCn;                     //varchar(50) NULL入账账户中文用户姓名

    @Column(updatable = false)
    private BigDecimal amount;                          //decimal(50,0) NULL金额

    @Column
    private int status;                                 //int(11) NULL状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserNameCn() {
        return fromUserNameCn;
    }

    public void setFromUserNameCn(String fromUserNameCn) {
        this.fromUserNameCn = fromUserNameCn;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserNameCn() {
        return toUserNameCn;
    }

    public void setToUserNameCn(String toUserNameCn) {
        this.toUserNameCn = toUserNameCn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
