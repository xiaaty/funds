package com.gqhmt.fss.architect.account.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.p2p.account.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 15:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fund_sequence")
public class FundSequenceEntity  implements java.io.Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer actionType;
    private Long accountId;
    private Integer fundType;
    private BigDecimal amount;
    private String currency;
    private Date createTime;
    private Date modifyTime;
//    private ThirdPartyType thirdPartyType;
    private String orderNo;

    private String  sumary;
//O_ACCOUNT_ID
    private Long oAccountId;

    private String token;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "actionType")
    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "ACCOUNT_ID")
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "fund_type")
    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "CREATE_TIME",updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundSequenceEntity that = (FundSequenceEntity) o;

        if (id != that.id) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (actionType != null ? !actionType.equals(that.actionType) : that.actionType != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (fundType != null ? !fundType.equals(that.fundType) : that.fundType != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        if(result == null){
            return 0;
        }
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (fundType != null ? fundType.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);

        return result.intValue();
    }

//    @Column(name = "thirdparty_type",updatable = false,nullable = false)
//    @Type(type = "com.gq.funds.type.ThirdPartyUserType")
//    public ThirdPartyType getThirdPartyType() {
//        return thirdPartyType;
//    }
//
//    public void setThirdPartyType(ThirdPartyType thirdPartyType) {
//        this.thirdPartyType = thirdPartyType;
//    }

    @Column(name = "order_no",updatable = false)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name="O_ACCOUNT_ID" ,updatable = false)
    public Long getoAccountId() {
        return oAccountId;
    }

    public void setoAccountId(Long oAccountId) {
        this.oAccountId = oAccountId;
    }

    @Column
    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    @Column
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
