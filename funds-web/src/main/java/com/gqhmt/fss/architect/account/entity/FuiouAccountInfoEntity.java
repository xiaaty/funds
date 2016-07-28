package com.gqhmt.fss.architect.account.entity;

import com.gqhmt.annotations.AutoDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/27.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/27.  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_fuiou_account_info")
public class FuiouAccountInfoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trade_type")
    private String tradeType;   //交易类型：  1.冻结/解冻 - DJJD . 2 .转账 - zz . 3.划拨 - HB . 4.委托充值 - WTCZ . 5.委托提现 - WTTX . 6.预授权交易 - YSQ

    @Column(name = "business_code")
    private String businessCode;    //业务代码

    @Column(name = "trade_sources")
    private String tradeSources; //交易来源： 1:接口;2:页面单笔;3:批量上传;4:定时任务;5:app;

    @Column(name = "contract_num")
    private String contractNum;    //合同号： 交易类型为'预授权交易'时才有

    @Column(name = "batch")
    private String batch;           //交易流水号/充值流水号

    @Column(name = "batch_foiu_finance")
    private String batchFoiuFinance;  //富有财务流水号： 当交易类型为'委托充值'和'委托提现'时才有

    @Column(name = "trading_time")
    private Date tradingTime;      //交易时间/充值时间/提现时间

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;   //交易金额/充值金额/提现金额

    @Column(name = "user_account")
    private String userAccount;         //用户账户/出账账户

    @Column(name = "user_name")
    private String userName;            //用户名/出账用户名

    @Column(name = "in_account")
    private String inAccount;           //入账用户账户

    @Column(name = "in_user_name")
    private String inUserName;          //入账用户名

    @Column(name = "total_money")
    private BigDecimal totalMoney;      //总金额: 当交类型为'预授权交易'时才有

    @Column(name = "balance")
    private BigDecimal balance;         //余额： 当交类型为'预授权交易'时才有

    @Column(name = "remark")
    private String remark;              //备注

    @Column(name = "return_num")
    private String returnNum;           //返回码: 0000 为成功 ，  其他为失败

    @Column(name = "state")
    private String state;               //状态

    @Column(name = "account_state")
    private String accountState;        //状态 ，仅当交易类型为'预授权交易'时才有， 0: 冻结失败；1：已冻结；2：已解冻；3：已完成；

    public String getBooleanType() {
        return booleanType;
    }

    public void setBooleanType(String booleanType) {
        this.booleanType = booleanType;
    }

    @Column(name = "boolean_type")
    private String booleanType;         //抓取状态 ， -1 为失败 ， 其他为成功

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getTradeSources() {
        return tradeSources;
    }

    public void setTradeSources(String tradeSources) {
        this.tradeSources = tradeSources;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchFoiuFinance() {
        return batchFoiuFinance;
    }

    public void setBatchFoiuFinance(String batchFoiuFinance) {
        this.batchFoiuFinance = batchFoiuFinance;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    public String getInUserName() {
        return inUserName;
    }

    public void setInUserName(String inUserName) {
        this.inUserName = inUserName;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }
}
