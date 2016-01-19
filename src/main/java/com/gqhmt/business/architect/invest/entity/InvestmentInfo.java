package com.gqhmt.business.architect.invest.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.business.architect.invest.entity.InvestmentInfo
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/24 21:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/24  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_investment_info")
public class InvestmentInfo {
	@Id
 	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 客户编号
    private Integer custId;
    // 出借编号
    private String investId;
    // 合同编号线下
    private String contractNo;
    // 产品编号
    private Integer productId;
    // 预计出借金额
    private BigDecimal preInvestAmount;
    // 预计出借日期
    private Date preInvestDate;
    // 出借金额
    private BigDecimal investAmount;
    // 出借金额大写
    private String captilAmount;
    // 划扣银行编号(代扣)
    private Integer drawBankId;
    // 回款银行编号(代付)
    private Integer repayBankId;
    // 实际出借日期
    private Date investFactDate;
    // 出借类型 1-首投 2-续投
    private Integer investType;
    // 债权状态 2-通过 5-划扣成功 6-划扣失败
    private Integer status;
    // 合同开始日期
    private Date investStartDate;
    // 合同截止日期
    private Date investEndDate;
    // 合同状态 1-签订 2-执行 3-废止
    private Integer state;
    // 回访方式 1-短信 2-电话
    private Integer callbackWay;
    // 接收债权方式 1-邮件 2-快递
    private Integer acceptClaimWay;
    // 顾问编号
    private String userHrId;
    //成本中心代码
    private String costCenterCode;
    //公司简称
    private String companySortName;

    //所属大区
    private String regions;
    //所属机构
    private String organization;

    // 备注
    private String remark;
    // 剩余出借金额
    private BigDecimal surplusInvestAmount;
    // 期数
    private Integer thePeriod;
    // 最新还款时间
    private Date newRepaymentTime;

    // 创建时间
    private Date createTime;
    // 创建者
    private Integer createUserId;
    // 修改时间
    private Date modifyTime;
    // 修改者
    private Integer modifyUserId;

    private Integer isMatch; // 是否匹配债权

    private BigDecimal specialProductEachInterest;

    private Integer payChannel;
    //是否同步到出借系统（0-未 1-已经同步）
    private Integer isSynchronization;

    private Integer version;

    // Constructors

    /** default constructor */
    public InvestmentInfo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //	@Version
    @Column(name = "version", length = 10)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "cust_id", length = 20)
    public Integer getCustId() {
        return this.custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    @Column(name = "invest_id", length = 30)
    public String getInvestId() {
        return this.investId;
    }

    public void setInvestId(String investId) {
        this.investId = investId;
    }

    @Column(name = "contract_no", length = 20)
    public String getContractNo() {
        return this.contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Column(name = "product_id")
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "pre_invest_amount", precision = 18, scale = 2)
    public BigDecimal getPreInvestAmount() {
        return this.preInvestAmount;
    }

    public void setPreInvestAmount(BigDecimal preInvestAmount) {
        this.preInvestAmount = preInvestAmount;
    }

    @Column(name = "pre_invest_date", length = 10)
    public Date getPreInvestDate() {
        return this.preInvestDate;
    }

    public void setPreInvestDate(Date preInvestDate) {
        this.preInvestDate = preInvestDate;
    }

    @Column(name = "invest_amount", precision = 18, scale = 2)
    public BigDecimal getInvestAmount() {
        return this.investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    @Column(name = "captil_amount", length = 100)
    public String getCaptilAmount() {
        return this.captilAmount;
    }

    public void setCaptilAmount(String captilAmount) {
        this.captilAmount = captilAmount;
    }

    @Column(name = "draw_bank_id")
    public Integer getDrawBankId() {
        return this.drawBankId;
    }

    public void setDrawBankId(Integer drawBankId) {
        this.drawBankId = drawBankId;
    }

    @Column(name = "repay_bank_id")
    public Integer getRepayBankId() {
        return this.repayBankId;
    }

    public void setRepayBankId(Integer repayBankId) {
        this.repayBankId = repayBankId;
    }

    @Column(name = "invest_fact_date", length = 10)
    public Date getInvestFactDate() {
        return this.investFactDate;
    }

    public void setInvestFactDate(Date investFactDate) {
        this.investFactDate = investFactDate;
    }

    @Column(name = "invest_type")
    public Integer getInvestType() {
        return this.investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "invest_start_date", length = 10)
    public Date getInvestStartDate() {
        return this.investStartDate;
    }

    public void setInvestStartDate(Date investStartDate) {
        this.investStartDate = investStartDate;
    }

    @Column(name = "invest_end_date", length = 10)
    public Date getInvestEndDate() {
        return this.investEndDate;
    }

    public void setInvestEndDate(Date investEndDate) {
        this.investEndDate = investEndDate;
    }

    @Column(name = "state")
    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name = "callback_way")
    public Integer getCallbackWay() {
        return this.callbackWay;
    }

    public void setCallbackWay(Integer callbackWay) {
        this.callbackWay = callbackWay;
    }

    @Column(name = "accept_claim_way")
    public Integer getAcceptClaimWay() {
        return this.acceptClaimWay;
    }

    public void setAcceptClaimWay(Integer acceptClaimWay) {
        this.acceptClaimWay = acceptClaimWay;
    }

    @Column(name = "user_hr_id", length = 20)
    public String getUserHrId() {
        return this.userHrId;
    }

    public void setUserHrId(String userHrId) {
        this.userHrId = userHrId;
    }

    @Column(name = "COST_CENTER_CODE", length = 20)
    public String getCostCenterCode() {
        return this.costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    @Column(name = "company_sort_name", length = 100)
    public String getCompanySortName() {
        return this.companySortName;
    }

    public void setCompanySortName(String companySortName) {
        this.companySortName = companySortName;
    }

    @Column(name = "regions", length = 20)
    public String getRegions() {
        return this.regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    @Column(name = "organization", length = 20)
    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    @Column(name = "remark", length = 200)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "surplus_invest_amount", precision = 18, scale = 2)
    public BigDecimal getSurplusInvestAmount() {
        return surplusInvestAmount;
    }

    public void setSurplusInvestAmount(BigDecimal surplusInvestAmount) {
        this.surplusInvestAmount = surplusInvestAmount;
    }

    @Column(name = "the_period")
    public Integer getThePeriod() {
        return thePeriod;
    }

    public void setThePeriod(Integer thePeriod) {
        this.thePeriod = thePeriod;
    }

    @Column(name = "new_repayment_time", length = 19)
    public Date getNewRepaymentTime() {
        return newRepaymentTime;
    }

    public void setNewRepaymentTime(Date newRepaymentTime) {
        this.newRepaymentTime = newRepaymentTime;
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

    @Column(name = "is_match")
    public Integer getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(Integer isMatch) {
        this.isMatch = isMatch;
    }

    @Column(name = "special_product_each_interest", precision = 18, scale = 2)
    public BigDecimal getSpecialProductEachInterest() {
        return specialProductEachInterest;
    }

    public void setSpecialProductEachInterest(BigDecimal specialProductEachInterest) {
        this.specialProductEachInterest = specialProductEachInterest;
    }

    @Column(name = "pay_channel")
    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    @Column(name = "is_synchronization")
    public Integer getIsSynchronization() {
        return isSynchronization;
    }

    public void setIsSynchronization(Integer isSynchronization) {
        this.isSynchronization = isSynchronization;
    }
}
