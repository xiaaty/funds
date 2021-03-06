package com.gqhmt.funds.architect.account.entity;

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

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actionType")
    private Integer actionType;

    @Column(name = "ACCOUNT_ID",updatable = false)
    private Long accountId;

    @Column(name = "fund_type")
    private Integer fundType;

    @Column(name = "amount",updatable = false)
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "CREATE_TIME",updatable = false)
    private Date createTime;

    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    @Column(name="thirdparty_type")
    private int thirdPartyType;

    @Column(name = "order_no",updatable = false)
    private String orderNo;

    @Column(name = "sumary")
    private String  sumary;

    @Column(name="O_ACCOUNT_ID",updatable = false)
    private Long oAccountId;

    @Column(name = "token")
    private String token;

    @Column(name = "s_order_no")
    private String sOrderNo;

    @Column(name="cust_id",updatable = false)
    private Long custId;       // bigint(20) DEFAULT NULL COMMENT '出账（转账）入账（其他交易）客户id',

    @Column(name="lend_no",updatable = false)
    private String  lendNo; //varchar(45) DEFAULT NULL COMMENT '出账（转账）入账（其他交易）出借编号，线上客户为空',

    @Column(name="to_cust_id",updatable = false)
    private Long  toCustId; //bigint(20) DEFAULT NULL COMMENT '入账（转账）客户id',

    @Column(name="to_lend_no",updatable = false)
    private String toLendNo; //varchar(45) DEFAULT NULL COMMENT '入账（转账）出借编号',

    @Column(name="loan_cust_id",updatable = false)
    private Long loanCustId; //bigint(20) DEFAULT NULL COMMENT '对应借款标的借款人客户id，非抵押权人，原始借款人客户id',

    @Column(name="loan_no",updatable = false)
    private String loanNo; //varchar(45) DEFAULT NULL COMMENT '投标，满标，回款，等等对应借款合同编号',

    @Column(name="new_fund_type",updatable = false)
    private String newFundType; //char(10) DEFAULT '' COMMENT '新资金类型',

    @Column(name="trade_type",updatable = false)
    private String tradeType; //char(10) DEFAULT '' COMMENT '交易类型',




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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





    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public Long getoAccountId() {
        return oAccountId;
    }

    public void setoAccountId(Long oAccountId) {
        this.oAccountId = oAccountId;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(int thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public String getsOrderNo() {
        return sOrderNo;
    }

    public void setsOrderNo(String sOrderNo) {
        this.sOrderNo = sOrderNo;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getLendNo() {
        return lendNo;
    }

    public void setLendNo(String lendNo) {
        this.lendNo = lendNo;
    }

    public Long getToCustId() {
        return toCustId;
    }

    public void setToCustId(Long toCustId) {
        this.toCustId = toCustId;
    }

    public String getToLendNo() {
        return toLendNo;
    }

    public void setToLendNo(String toLendNo) {
        this.toLendNo = toLendNo;
    }

    public Long getLoanCustId() {
        return loanCustId;
    }

    public void setLoanCustId(Long loanCustId) {
        this.loanCustId = loanCustId;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getNewFundType() {
        return newFundType;
    }

    public void setNewFundType(String newFundType) {
        this.newFundType = newFundType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
