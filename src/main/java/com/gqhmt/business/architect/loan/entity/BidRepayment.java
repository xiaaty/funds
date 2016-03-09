package com.gqhmt.business.architect.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.loan.entity.BidRepayment
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 17:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_bid_repayment")
public class BidRepayment {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "bid_id")
    private Integer bidId;

    @Column(name = "period")
    private Integer period;

    @Column(name = "bid_pre_date")
    private Date bidPreDate;

    @Column(name = "bid_pre_principal")
    private BigDecimal bidPrePrincipal;

    @Column(name = "bid_pre_interest")
    private BigDecimal bidPreInterest;

    @Column(name = "bid_pre_amount")
    private BigDecimal bidPreAmount;

    @Column(name = "bid_real_date")
    private Date bidRealDate;

    @Column(name = "bid_real_principal")
    private BigDecimal bidRealPrincipal;

    @Column(name = "bid_real_interest")
    private BigDecimal bidRealInterest;

    @Column(name = "bid_real_amount")
    private BigDecimal bidRealAmount;

    @Column(name = "contract_pre_date")
    private Date contractPreDate;


    @Column(name = "contract_pre_principal")

    private BigDecimal contractPrePrincipal;

    @Column(name = "contract_pre_interest")
    private BigDecimal contractPreInterest;

    @Column(name = "contract_real_date")
    private BigDecimal contractPreAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private BigDecimal preDiffAmount;
    /*private Date contractRealDate;
    private BigDecimal contractRealPrincipal;
    private BigDecimal contractRealInterest;
    private BigDecimal contractRealAmount;
    private BigDecimal preDiffPrincipal;
    private BigDecimal preDiffInterest;
    private BigDecimal realDiffPrincipal;
    private BigDecimal realDiffInterest;
    private BigDecimal realDiffAmount;
    private Integer repaymentState;
    private BigDecimal prePenalyAmount;
    private BigDecimal realPenalyAmount;
    private BigDecimal preConsultingAmount;
    private BigDecimal realConsultingAmount;
    private Integer dueDay = 0;
    private Integer isCompensatory = 0;
    private Date createTime;
    private Integer createUserId;
    private Date modifyTime;
    private Integer modifyUserId;
    private Integer status = 0;
    private Integer payState = 0;*/

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getBidPreDate() {
		return bidPreDate;
	}

	public void setBidPreDate(Date bidPreDate) {
		this.bidPreDate = bidPreDate;
	}

	public BigDecimal getBidPrePrincipal() {
		return bidPrePrincipal;
	}

	public void setBidPrePrincipal(BigDecimal bidPrePrincipal) {
		this.bidPrePrincipal = bidPrePrincipal;
	}

	public BigDecimal getBidPreInterest() {
		return bidPreInterest;
	}

	public void setBidPreInterest(BigDecimal bidPreInterest) {
		this.bidPreInterest = bidPreInterest;
	}

	public BigDecimal getBidPreAmount() {
		return bidPreAmount;
	}

	public void setBidPreAmount(BigDecimal bidPreAmount) {
		this.bidPreAmount = bidPreAmount;
	}

	public Date getBidRealDate() {
		return bidRealDate;
	}

	public void setBidRealDate(Date bidRealDate) {
		this.bidRealDate = bidRealDate;
	}

	public BigDecimal getBidRealPrincipal() {
		return bidRealPrincipal;
	}

	public void setBidRealPrincipal(BigDecimal bidRealPrincipal) {
		this.bidRealPrincipal = bidRealPrincipal;
	}

	public BigDecimal getBidRealInterest() {
		return bidRealInterest;
	}

	public void setBidRealInterest(BigDecimal bidRealInterest) {
		this.bidRealInterest = bidRealInterest;
	}

	public BigDecimal getBidRealAmount() {
		return bidRealAmount;
	}

	public void setBidRealAmount(BigDecimal bidRealAmount) {
		this.bidRealAmount = bidRealAmount;
	}

	public Date getContractPreDate() {
		return contractPreDate;
	}

	public void setContractPreDate(Date contractPreDate) {
		this.contractPreDate = contractPreDate;
	}

	public BigDecimal getContractPrePrincipal() {
		return contractPrePrincipal;
	}

	public void setContractPrePrincipal(BigDecimal contractPrePrincipal) {
		this.contractPrePrincipal = contractPrePrincipal;
	}

	public BigDecimal getContractPreInterest() {
		return contractPreInterest;
	}

	public void setContractPreInterest(BigDecimal contractPreInterest) {
		this.contractPreInterest = contractPreInterest;
	}

	public BigDecimal getContractPreAmount() {
		return contractPreAmount;
	}

	public void setContractPreAmount(BigDecimal contractPreAmount) {
		this.contractPreAmount = contractPreAmount;
	}

	public BigDecimal getPreDiffAmount() {
		return preDiffAmount;
	}

	public void setPreDiffAmount(BigDecimal preDiffAmount) {
		this.preDiffAmount = preDiffAmount;
	}


}
