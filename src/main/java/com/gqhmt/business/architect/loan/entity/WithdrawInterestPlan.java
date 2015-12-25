package com.gqhmt.business.architect.loan.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.loan.entity.WithdrawInterestPlan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 21:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_withdraw_interest_plan")
public class WithdrawInterestPlan {
    //主键
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //标的ID
    private Integer bidId;
    //投标记录表id
    private Integer tenderId;
    //客户id
    private Integer custId;
    //用户id
    private Integer userId;
    //线下导入时生成的出借合同id
    private Integer contractId;
    //线下出借合同编号
    private String offlineInvestNo;
    //出借人来源 1线上 2线下
    private Integer contractType;
    //期数
    private Integer period;

    //线下合同本金计算基数
    private BigDecimal contractPrincipalBase;

    //预计收益时间
    private Date bidPreDate;
    //预计收益本金
    private BigDecimal bidPrePrincipal;
    //预计收益利息
    private BigDecimal bidPreInterest;
    //预计本息
    private BigDecimal bidPreAmount;
    //实际收益时间
    private Date bidRealDate;
    //实际收益本金
    private BigDecimal bidRealPrincipal;
    //实际收益利息
    private BigDecimal bidRealInterest;
    //实际本息
    private BigDecimal bidRealAmount;
    //预计收益时间(线下)
    private Date contractPreDate;
    //预计收益本金(线下)
    private BigDecimal contractPrePrincipal;
    //预计收益利息(线下)
    private BigDecimal contractPreInterest;
    //实际收益时间(线下)
    private Date contractRealDate;
    //实际收益本金(线下)
    private BigDecimal contractRealPrincipal;
    //实际收益利息(线下)
    private BigDecimal contractRealInterest;
    //预计差额本金
    private BigDecimal preDiffPrincipal;
    //预计差额利息
    private BigDecimal preDiffInterest;
    //实际差额本金
    private BigDecimal realDiffPrincipal;
    //实际差额利息
    private BigDecimal realDiffInterest;
    //预计本息差额
    private BigDecimal preDiffAmount;
    //实际本息差额
    private BigDecimal realDiffAmount;
    //收益状态(0:未收益,1:已收益,2:已逾期)
    private Integer repaymentState;
    //预计本息
    private BigDecimal contractPreAmount;
    //实际本息
    private BigDecimal contractRealAmount;

    // 满标开始时间
    private Date bidStartDate;

    // 线下出借合同生效时间
    private Date investStartDate;

    //标的开始和合同生效相差天数
    private Integer daysDiff;

    // 创建时间
    private Date createTime;
    // 创建者
    private Integer createUserId;
    // 修改时间
    private Date modifyTime;
    // 修改者
    private Integer modifyUserId;
    //是否删除
    private Integer deleted;
    //债权转让id
    private Integer debtId;
    private Integer oldTenderId;
    private Integer debtStatus;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "bid_id")
    public Integer getBidId() {
        return this.bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    @Column(name = "tender_id")
    public Integer getTenderId() {
        return this.tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    @Column(name = "cust_id")
    public Integer getCustId() {
        return this.custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "CONTRACT_ID")
    public Integer getContractId() {
        return this.contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Column(name = "OFFLINE_INVEST_NO", length = 30)
    public String getOfflineInvestNo() {
        return this.offlineInvestNo;
    }

    public void setOfflineInvestNo(String offlineInvestNo) {
        this.offlineInvestNo = offlineInvestNo;
    }

    @Column(name = "CONTRACT_TYPE")
    public Integer getContractType() {
        return this.contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    @Column(name = "period")
    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Column(name = "contract_principal_base", precision = 18, scale = 2)
    public BigDecimal getContractPrincipalBase() {
        return this.contractPrincipalBase;
    }

    public void setContractPrincipalBase(BigDecimal contractPrincipalBase) {
        this.contractPrincipalBase = contractPrincipalBase;
    }

    @Column(name = "bid_pre_date", length = 10)
    public Date getBidPreDate() {
        return this.bidPreDate;
    }

    public void setBidPreDate(Date bidPreDate) {
        this.bidPreDate = bidPreDate;
    }

    @Column(name = "bid_pre_principal", precision = 18, scale = 2)
    public BigDecimal getBidPrePrincipal() {
        return this.bidPrePrincipal;
    }

    public void setBidPrePrincipal(BigDecimal bidPrePrincipal) {
        this.bidPrePrincipal = bidPrePrincipal;
    }

    @Column(name = "bid_pre_interest", precision = 18, scale = 2)
    public BigDecimal getBidPreInterest() {
        return this.bidPreInterest;
    }

    public void setBidPreInterest(BigDecimal bidPreInterest) {
        this.bidPreInterest = bidPreInterest;
    }

    @Column(name = "bid_pre_amount", precision = 18, scale = 2)
    public BigDecimal getBidPreAmount() {
        return this.bidPreAmount;
    }

    public void setBidPreAmount(BigDecimal bidPreAmount) {
        this.bidPreAmount = bidPreAmount;
    }

    @Column(name = "bid_real_date", length = 10)
    public Date getBidRealDate() {
        return this.bidRealDate;
    }

    public void setBidRealDate(Date bidRealDate) {
        this.bidRealDate = bidRealDate;
    }

    @Column(name = "bid_real_principal", precision = 18, scale = 2)
    public BigDecimal getBidRealPrincipal() {
        return this.bidRealPrincipal;
    }

    public void setBidRealPrincipal(BigDecimal bidRealPrincipal) {
        this.bidRealPrincipal = bidRealPrincipal;
    }

    @Column(name = "bid_real_interest", precision = 18, scale = 2)
    public BigDecimal getBidRealInterest() {
        return this.bidRealInterest;
    }

    public void setBidRealInterest(BigDecimal bidRealInterest) {
        this.bidRealInterest = bidRealInterest;
    }

    @Column(name = "bid_real_amount", precision = 18, scale = 2)
    public BigDecimal getBidRealAmount() {
        return this.bidRealAmount;
    }

    public void setBidRealAmount(BigDecimal bidRealAmount) {
        this.bidRealAmount = bidRealAmount;
    }

    @Column(name = "contract_pre_date", length = 10)
    public Date getContractPreDate() {
        return this.contractPreDate;
    }

    public void setContractPreDate(Date contractPreDate) {
        this.contractPreDate = contractPreDate;
    }

    @Column(name = "contract_pre_principal", precision = 18, scale = 2)
    public BigDecimal getContractPrePrincipal() {
        return this.contractPrePrincipal;
    }

    public void setContractPrePrincipal(BigDecimal contractPrePrincipal) {
        this.contractPrePrincipal = contractPrePrincipal;
    }

    @Column(name = "contract_pre_interest", precision = 18, scale = 2)
    public BigDecimal getContractPreInterest() {
        return this.contractPreInterest;
    }

    public void setContractPreInterest(BigDecimal contractPreInterest) {
        this.contractPreInterest = contractPreInterest;
    }

    @Column(name = "contract_real_date", length = 10)
    public Date getContractRealDate() {
        return this.contractRealDate;
    }

    public void setContractRealDate(Date contractRealDate) {
        this.contractRealDate = contractRealDate;
    }

    @Column(name = "contract_real_principal", precision = 18, scale = 2)
    public BigDecimal getContractRealPrincipal() {
        return this.contractRealPrincipal;
    }

    public void setContractRealPrincipal(BigDecimal contractRealPrincipal) {
        this.contractRealPrincipal = contractRealPrincipal;
    }

    @Column(name = "contract_real_interest", precision = 18, scale = 2)
    public BigDecimal getContractRealInterest() {
        return this.contractRealInterest;
    }

    public void setContractRealInterest(BigDecimal contractRealInterest) {
        this.contractRealInterest = contractRealInterest;
    }

    @Column(name = "pre_diff_principal", precision = 18, scale = 2)
    public BigDecimal getPreDiffPrincipal() {
        return this.preDiffPrincipal;
    }

    public void setPreDiffPrincipal(BigDecimal preDiffPrincipal) {
        this.preDiffPrincipal = preDiffPrincipal;
    }

    @Column(name = "pre_diff_interest", precision = 18, scale = 2)
    public BigDecimal getPreDiffInterest() {
        return this.preDiffInterest;
    }

    public void setPreDiffInterest(BigDecimal preDiffInterest) {
        this.preDiffInterest = preDiffInterest;
    }

    @Column(name = "real_diff_principal", precision = 18, scale = 2)
    public BigDecimal getRealDiffPrincipal() {
        return this.realDiffPrincipal;
    }

    public void setRealDiffPrincipal(BigDecimal realDiffPrincipal) {
        this.realDiffPrincipal = realDiffPrincipal;
    }

    @Column(name = "real_diff_interest", precision = 18, scale = 2)
    public BigDecimal getRealDiffInterest() {
        return this.realDiffInterest;
    }

    public void setRealDiffInterest(BigDecimal realDiffInterest) {
        this.realDiffInterest = realDiffInterest;
    }

    @Column(name = "pre_diff_amount", precision = 18, scale = 2)
    public BigDecimal getPreDiffAmount() {
        return this.preDiffAmount;
    }

    public void setPreDiffAmount(BigDecimal preDiffAmount) {
        this.preDiffAmount = preDiffAmount;
    }

    @Column(name = "real_diff_amount", precision = 18, scale = 2)
    public BigDecimal getRealDiffAmount() {
        return this.realDiffAmount;
    }

    public void setRealDiffAmount(BigDecimal realDiffAmount) {
        this.realDiffAmount = realDiffAmount;
    }

    @Column(name = "repayment_state")
    public Integer getRepaymentState() {
        return this.repaymentState;
    }

    public void setRepaymentState(Integer repaymentState) {
        this.repaymentState = repaymentState;
    }

    @Column(name = "contract_pre_amount", precision = 18, scale = 2)
    public BigDecimal getContractPreAmount() {
        return this.contractPreAmount;
    }

    public void setContractPreAmount(BigDecimal contractPreAmount) {
        this.contractPreAmount = contractPreAmount;
    }

    @Column(name = "contract_real_amount", precision = 18, scale = 2)
    public BigDecimal getContractRealAmount() {
        return this.contractRealAmount;
    }

    public void setContractRealAmount(BigDecimal contractRealAmount) {
        this.contractRealAmount = contractRealAmount;
    }


    @Column(name = "invest_start_date", length = 10)
    public Date getInvestStartDate() {
        return this.investStartDate;
    }

    public void setInvestStartDate(Date investStartDate) {
        this.investStartDate = investStartDate;
    }

    @Column(name = "bid_start_date", length = 10)
    public Date getBidStartDate() {
        return this.bidStartDate;
    }

    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }



    @Column(name = "days_diff", length = 11)
    public Integer getDaysDiff() {
        return this.daysDiff;
    }

    public void setDaysDiff(Integer daysDiff) {
        this.daysDiff = daysDiff;
    }

    @Column(name = "CREATE_TIME", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CREATE_USER_ID")
    public Integer getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Column(name = "MODIFY_TIME", length = 19)
    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "MODIFY_USER_ID")
    public Integer getModifyUserId() {
        return this.modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    @Column(name = "deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @Column(name = "debt_id")
    public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }
    @Column(name = "old_tender_id")
    public Integer getOldTenderId() {
        return oldTenderId;
    }

    public void setOldTenderId(Integer oldTenderId) {
        this.oldTenderId = oldTenderId;
    }

    @Column(name = "debt_status")
    public Integer getDebtStatus() {
        return debtStatus;
    }

    public void setDebtStatus(Integer debtStatus) {
        this.debtStatus = debtStatus;
    }
}
