package com.gqhmt.fss.architect.accounting.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/28
 * Description:客户资金流水总表，记录客户所有资金流水
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/28  jhz     1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_account_capital_flow")
public class FssAccountCapitalFlow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_no")
    private String  accountNo;          //记账编号，与交易日期唯一

    @Column(name = "ori_account_no")
    private String  oriAccountNo;       //冲正等其他记账记账编号

    @Column(name = "cust_no")
    private String  custNo;         //客户编号

    @Column(name = "acc_no")
    private String  accNo;          //账户编号

    @Column(name = "to_cust_no")
    private String  toCustNo;       //转账交易 对方客户编号

    @Column(name = "to_acc_no")
    private String  toAccNo;        //转账交易，对方资金账号

    @Column(name = "funds_type")
    private String  fundsType;      //账务科目

    @Column(name = "secend_funds_type")
    private String  secendFundsType;        //二级账务科目

    @Column(name = "amt")
    private BigDecimal amt;         //交易金额，正入账，负值出账

    @Column(name = "lend_contract_no")
    private String lendContractNo;        //出借合同号，线下出借投标时，满标，回款会关联此编号，线下债权转让，接标人出借编号

    @Column(name = "load_contact_no")
    private String loadContactNo;           //借款编号，投标、满标、回款、收费等，都会关联此业务编号

    @Column(name = "ori_lend_contract_no")
    private String oriLendContractNo;       //债权转让—原出借编号，线下债权转让时，会记录转让人出借编号

    @Column(name = "ori_cust_no")
    private String oriCustNo;           //债权转让—原客户编号

    @Column(name = "ori_acc_no")
    private String oriAccNo;            //债权转让—原账户编号

    @Column(name = "order_no")
    private String orderNo;     //与第三方交易订单号

    @Column(name = "trade_date")
    private String tradeDate;       //交易日期

    @Column(name = "trade_time")
    private String tradeTime;       //交易时间

    @Column(name = "settle_date")
    private String settleDate;       //清算日期

    @Column(name = "pay_channel")
    private String payChannel;

    @Column(name = "mchn_parent")
    private String mchnParent;

    @Column(name = "mchn_child")
    private String mchnChild;       //商户号

    @Column(name = "create_time")
    private Date createTime;        //创建时间

    @Column(name = "modify_time")
    private Date modifyTime;        //修改时间

    @Column(name = "token")
    private String token;       //数据唯一校验值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOriAccountNo() {
        return oriAccountNo;
    }

    public void setOriAccountNo(String oriAccountNo) {
        this.oriAccountNo = oriAccountNo;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getToCustNo() {
        return toCustNo;
    }

    public void setToCustNo(String toCustNo) {
        this.toCustNo = toCustNo;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public String getFundsType() {
        return fundsType;
    }

    public void setFundsType(String fundsType) {
        this.fundsType = fundsType;
    }

    public String getSecendFundsType() {
        return secendFundsType;
    }

    public void setSecendFundsType(String secendFundsType) {
        this.secendFundsType = secendFundsType;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getLendContractNo() {
        return lendContractNo;
    }

    public void setLendContractNo(String lendContractNo) {
        this.lendContractNo = lendContractNo;
    }

    public String getLoadContactNo() {
        return loadContactNo;
    }

    public void setLoadContactNo(String loadContactNo) {
        this.loadContactNo = loadContactNo;
    }

    public String getOriLendContractNo() {
        return oriLendContractNo;
    }

    public void setOriLendContractNo(String oriLendContractNo) {
        this.oriLendContractNo = oriLendContractNo;
    }

    public String getOriCustNo() {
        return oriCustNo;
    }

    public void setOriCustNo(String oriCustNo) {
        this.oriCustNo = oriCustNo;
    }

    public String getOriAccNo() {
        return oriAccNo;
    }

    public void setOriAccNo(String oriAccNo) {
        this.oriAccNo = oriAccNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getMchnParent() {
        return mchnParent;
    }

    public void setMchnParent(String mchnParent) {
        this.mchnParent = mchnParent;
    }

    public String getMchnChild() {
        return mchnChild;
    }

    public void setMchnChild(String mchnChild) {
        this.mchnChild = mchnChild;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
}
