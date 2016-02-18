package com.gqhmt.business.architect.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.loan.entity.Tender
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 17:00
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_tender")
public class Tender {

    @Column(name = "id")
    private Long id;

    @Column(name="bid_id")
    private Long bidId;

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name="fund_account_id")
    private Long fundAccountId;

    @Column(name = "investment_id")
    private Integer investmentId;

    @Column(name="real_amount")
    private BigDecimal realAmount;

    @Column(name="bonus_ids")
    private String bonusIds;

    @Column(name = "bonus_amount")
    private BigDecimal bonusAmount;

    @Column(name="guanqian_amount")
    private BigDecimal guanqianAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getFundAccountId() {
        return fundAccountId;
    }

    public void setFundAccountId(Long fundAccountId) {
        this.fundAccountId = fundAccountId;
    }

    public Integer getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(Integer investmentId) {
        this.investmentId = investmentId;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public String getBonusIds() {
        return bonusIds;
    }

    public void setBonusIds(String bonusIds) {
        this.bonusIds = bonusIds;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getGuanqianAmount() {
        return guanqianAmount;
    }

    public void setGuanqianAmount(BigDecimal guanqianAmount) {
        this.guanqianAmount = guanqianAmount;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public Date getInvestTime() {
        return investTime;
    }

    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name="invest_amount")

    private BigDecimal investAmount;

    @Column(name="invest_time")
    private Date investTime;

    @Column(name="invest_type")
    private Integer investType;

    @Column(name="state")
    private Integer state;
//    private Date createTime;
//    private String createUserId;
//    private Date modifyTime;
//    private String modifyUserId;
//    private Integer offlineTenderPeriod;
//    private BigDecimal tenderScale;
//    private String remark;

//    private Integer debtStatus;
//    private Integer orderId;
//    private Integer debtId;
//    private Integer userOnlineTenderFrequency;
//    private Integer oldTenderId;
//    private Integer newBidId;
//    private Integer topBidId;
//    private Integer node;
//    private Integer productSalesPackId;
    //是否已经选择
//    private Integer isCreateNewBid;




}
