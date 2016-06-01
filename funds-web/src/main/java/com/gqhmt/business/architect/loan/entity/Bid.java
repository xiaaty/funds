package com.gqhmt.business.architect.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.loan.entity.Bid
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 14:50
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_bid")
public class Bid {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;                 //客户id

    @Column(name="bid_title")                   //标的标题
    private String bidTitle;


    @Column(name="contract_no")
    private String contractNo;                  //合同号

    @Column(name = "bid_no")
    private String bidNo;                       //标编号
    // 描述

    @Column(name = "loan_type")
    private Integer loanType;                   //贷款类型 1:信用借款,2:抵押借款,3:质押借款,4:混合借款

    @Column(name = "loan_mode")                 //借款方式，1:新客户，2：循环贷
    private Integer loanMode;
    // 标的金额
    @Column(name = "bid_amount")
    private BigDecimal bidAmount;               //标的金额
    // 合同金额

    @Column(name = "contarct_amount")
    private BigDecimal contarctAmount;          //合同金额

    @Column(name = "contarct_year_irr")
    private BigDecimal contarctYearIrr;         //合同年利率

    @Column(name = "bid_year_irr")
    private BigDecimal bidYearIrr;              //标的年利率

    @Column(name="period")
    private Integer period;                     //期限

    @Column(name = "bid_repayment_type")
    private Integer bidRepaymentType;           //标的还款方式 等额本息-1 先息后本 -2，还本付息 -7

    @Column(name = "contract_repayment_type")
    private Integer contractRepaymentType;      //合同还款方式 先息后本 -2，等额等息 -3 自定义还款1-常比例 -4 自定义还款2-定比2% -5 自定义还款3-定比5% -6 自定义还款4-低比例 -7 自定义还款5-中比例 -8 自定义还款6-高比例 -9

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "modify_user_id")
    private Integer modifyUserId;

    @Column(name = "is_save_hypothecarius")
    private Integer isHypothecarius;

    @Column(name = "hypothecarius")
    private Integer hypothecarius;
    // 竞拍天数
//    private Integer assigmentAuctionDays;
//    private Integer assigmentStatus;
//    // 原标的id
//    private Integer parentId;
//    private BigDecimal assigmentManageFee;
//    // 原投标id
//    private Integer parentTenderId;
//    private Integer assigmentInvestId;
//    // 转让类型 (1：线下转让；2：线上转让)
//    private Integer assigmentType;
//    private String assigmentAcctId;
//    private Integer assigmentNodeStatus;
//    private Integer assigmentOrderId;
//    private Integer bidType;
//    private Date bidEndDate;
//    private Integer topId;
//    private BigDecimal assigmentDiscountCoefficient;
//    private Integer periodDay;
//    private Integer bidPeriodType;
//    private BigDecimal originalTenderAmount;
//
//    private Integer tenderCustomNum;
//    private BigDecimal alreadyTenderScale = new BigDecimal(0.00);

    @Column(name = "pay_state")
    private Integer payState;
    
    @Column(name = "bid_state")
    private Integer bidState;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBidTitle() {
        return bidTitle;
    }

    public void setBidTitle(String bidTitle) {
        this.bidTitle = bidTitle;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBidNo() {
        return bidNo;
    }

    public void setBidNo(String bidNo) {
        this.bidNo = bidNo;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanMode() {
        return loanMode;
    }

    public void setLoanMode(Integer loanMode) {
        this.loanMode = loanMode;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public BigDecimal getContarctAmount() {
        return contarctAmount;
    }

    public void setContarctAmount(BigDecimal contarctAmount) {
        this.contarctAmount = contarctAmount;
    }

    public BigDecimal getContarctYearIrr() {
        return contarctYearIrr;
    }

    public void setContarctYearIrr(BigDecimal contarctYearIrr) {
        this.contarctYearIrr = contarctYearIrr;
    }

    public BigDecimal getBidYearIrr() {
        return bidYearIrr;
    }

    public void setBidYearIrr(BigDecimal bidYearIrr) {
        this.bidYearIrr = bidYearIrr;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getBidRepaymentType() {
        return bidRepaymentType;
    }

    public void setBidRepaymentType(Integer bidRepaymentType) {
        this.bidRepaymentType = bidRepaymentType;
    }

    public Integer getContractRepaymentType() {
        return contractRepaymentType;
    }

    public void setContractRepaymentType(Integer contractRepaymentType) {
        this.contractRepaymentType = contractRepaymentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Integer getIsHypothecarius() {
        return isHypothecarius;
    }

    public void setIsHypothecarius(Integer isHypothecarius) {
        this.isHypothecarius = isHypothecarius;
    }

    public Integer getHypothecarius() {
        return hypothecarius;
    }

    public void setHypothecarius(Integer hypothecarius) {
        this.hypothecarius = hypothecarius;
    }

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Integer getBidState() {
		return bidState;
	}

	public void setBidState(Integer bidState) {
		this.bidState = bidState;
	}
    
}
