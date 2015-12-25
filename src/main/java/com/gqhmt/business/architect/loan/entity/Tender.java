package com.gqhmt.business.architect.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private Integer id;

    @Column(name="bid_id")
    private Integer bidId;

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name="fund_account_id")
    private Long fundAccountId;

    @Column(name = "investment_id")
    private Integer investmentId;

    @Column(name="real_amount")
    private Double realAmount;

    @Column(name="bonus_ids")
    private String bonusIds;

    @Column(name = "bonus_amount")
    private Double bonusAmount;

    @Column(name="guanqian_amount")
    private Double guanqianAmount;

    @Column(name="invest_amount")
    private Double investAmount;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
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

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public String getBonusIds() {
        return bonusIds;
    }

    public void setBonusIds(String bonusIds) {
        this.bonusIds = bonusIds;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Double getGuanqianAmount() {
        return guanqianAmount;
    }

    public void setGuanqianAmount(Double guanqianAmount) {
        this.guanqianAmount = guanqianAmount;
    }

    public Double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(Double investAmount) {
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
}
