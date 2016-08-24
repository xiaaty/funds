package com.gqhmt.fss.architect.depos.bean;

import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/6
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/6  jhz     1.0     1.0 Version
 */
public class FssProjectInfoBean {

    private Long id;

    private Integer custId;

    private String tradeType;   //交易类型

    private String mchnNo;  //商户号

    private String orderNo; //订单号

    private String seqNo;        //平台流水号

    private String itemNo;   //项目编号

    private String loanType;        //借款类型(0.抵押标   1.担保标  2.信用标  3.净值标 4.流转标  5.秒标 6.其他)

    private String loanTittle;        //借款标题

    private String organization;        //推荐机构

    private String description;            //借款用途

    private Long loanAmt;        //借款金额

    private Long expectedReturn;        //预期收益

    private String productName;            //产品名称

    private String repaymentType;        //还款方式(0.一次性还本付息 1.先息后本 2.等额本息/等额本金 3.其他)

    private String loanTime;        //借款期限

    private String startDate;        //筹标起始日

    private Long eachBidAmount;            //每份投标金额

    private int minNum;            //最低投标份数

    private Long maxAmount;            //最多投标金额

    private String accNo;        //借款人平台用户名

    private String accGoldNo;        //借款人金账户用户名

    private String loanItemDescription;        //借款人项目概述

    private Long feeType;            //费用项

    private String tradeStatus;        //筹集情况

    private int period;            //还款期数

    private Long prepareAmount;        //备用金额

    private String bidYearIrr;            //发标年化利率

    private String borrowType;        //借款人类型01-个人; 02-企业

    private String licenseNo;        //借款企业营业执照编号(借款人类型为02必填)',

    private String custName;        //借款人名称',

    private String certType;        //借款人证件类型

    private String certNo;            //借款人证件号码

    private String status;            //状态

    private Date modifyTime;

    private Date createTime;

    private String  filePath;     //文件路径

    private String busiNo; //标的编号

    private String contractNo;  //合同编号

    private Long bidInterest;   //利息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
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

    public Long getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(Long expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public String getProductName() {
        return productName;
    }

    public void  setProductName(String productName) {
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public Long getBidInterest() {
        return bidInterest;
    }

    public void setBidInterest(Long bidInterest) {
        this.bidInterest = bidInterest;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
}
