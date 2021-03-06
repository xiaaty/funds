package com.gqhmt.fss.architect.depos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月6日
 * Description:
 * <p>项目信息实体
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_depos_fuiou_project_info")
public class FssProjectInfoEntity implements Serializable {


    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "seq_no")
    private String seqNo;       //平台流水号

    @Column(name = "item_no")
    private String itemNo;      //项目编号

    @Column(name = "loan_type")
    private String loanType;    //借款类型(0.抵押标   1.担保标  2.信用标  3.净值标 4.流转标  5.秒标 6.其他)

    @Column(name = "loan_tittle")
    private String loanTittle;  //借款标题

    @Column(name = "organization")
    private String organization;//推荐机构

    @Column(name = "description")
    private String description; //借款用途

    @Column(name = "loan_amt")
    private Long loanAmt;       //借款金额

    @Column(name = "expected_return")
    private String expectedReturn;//预期收益

    @Column(name = "product_name")
    private String productName; //产品名称

    @Column(name = "repayment_type")
    private String repaymentType;    //还款方式(0.一次性还本付息 1.先息后本 2.等额本息/等额本金 3.其他)

    @Column(name = "loan_time")
    private String loanTime;    //借款期限  单位天

    @Column(name = "start_date")
    private String startDate;   //筹标起始日

    @Column(name = "each_bid_amount")
    private Long eachBidAmount; //每份投标金额

    @Column(name = "min_num")
    private int minNum;         //最低投标份数

    @Column(name = "max_amount")
    private Long maxAmount;     //最多投标金额

    @Column(name = "acc_no")
    private String accNo;       //借款人平台用户名

    @Column(name = "acc_gold_no")
    private String accGoldNo;   //借款人金账户用户名

    @Column(name = "loan_item_description")
    private String loanItemDescription;    //借款人项目概述

    @Column(name = "fee_type")
    private Long feeType;       //费用项

    @Column(name = "trade_status")
    private String tradeStatus; //筹集情况

    @Column(name = "period")
    private int period;         //还款期数

    @Column(name = "prepare_amount")
    private Long prepareAmount; //备用金额

    @Column(name = "bid_year_irr")
    private String bidYearIrr;  //发标年化利率

    @Column(name = "borrow_type")
    private String borrowType;  //借款人类型01-个人; 02-企业

    @Column(name = "license_no")
    private String licenseNo;   //借款企业营业执照编号(借款人类型为02必填),

    @Column(name = "cust_name")
    private String custName;    //借款人名称,

    @Column(name = "cert_type")
    private String certType;    //借款人证件类型

    @Column(name = "cert_no")
    private String certNo;      //借款人证件号码

    @Column(name = "status")
    private String status;      //状态

    @Column(name = "create_time")
    private Date createTime;	//创建时间

    @Column(name = "modify_time")
    private Date modifyTime;	//修改时间

    @Column(name = "order_no")
    private String orderNo;		//订单号

    @Column(name = "trade_type")
    private String tradeType;	//交易类型

    @Column(name = "mchn_no")
    private String mchnNo;		//来源商户号

    @Column(name = "file_path")
    private String filePath;	//附件的文件路径

    @Column(name = "attach_status")
    private String attachStatus;//附件状态  N未上传  Y已上传

    @Column(name = "busi_no")
    private String busiNo;		//标的编号

    @Column(name = "offer_count")
    private int offerCount;     //报盘次数

    @Column(name = "contract_no")
    private String contractNo;     //合同号

    @Column(name = "bid_interest")
    private Long bidInterest;     //利息

    @Column(name = "resp_code")
    private String respCode;     //富友返回码

    @Column(name = "resp_msg")
    private String respMsg;     //富友返回消息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanTittle() {
        return loanTittle;
    }

    public void setLoanTittle(String loanTittle) {
        this.loanTittle = loanTittle;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(Long loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(String expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Long getEachBidAmount() {
        return eachBidAmount;
    }

    public void setEachBidAmount(Long eachBidAmount) {
        this.eachBidAmount = eachBidAmount;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccGoldNo() {
        return accGoldNo;
    }

    public void setAccGoldNo(String accGoldNo) {
        this.accGoldNo = accGoldNo;
    }

    public String getLoanItemDescription() {
        return loanItemDescription;
    }

    public void setLoanItemDescription(String loanItemDescription) {
        this.loanItemDescription = loanItemDescription;
    }

    public Long getFeeType() {
        return feeType;
    }

    public void setFeeType(Long feeType) {
        this.feeType = feeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Long getPrepareAmount() {
        return prepareAmount;
    }

    public void setPrepareAmount(Long prepareAmount) {
        this.prepareAmount = prepareAmount;
    }

    public String getBidYearIrr() {
        return bidYearIrr;
    }

    public void setBidYearIrr(String bidYearIrr) {
        this.bidYearIrr = bidYearIrr;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMchnNo() {
        return mchnNo;
    }

    public void setMchnNo(String mchnNo) {
        this.mchnNo = mchnNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAttachStatus() {
        return attachStatus;
    }

    public void setAttachStatus(String attachStatus) {
        this.attachStatus = attachStatus;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Long getBidInterest() {
        return bidInterest;
    }

    public void setBidInterest(Long bidInterest) {
        this.bidInterest = bidInterest;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}