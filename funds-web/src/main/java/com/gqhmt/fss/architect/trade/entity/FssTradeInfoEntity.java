package com.gqhmt.fss.architect.trade.entity;

import com.gqhmt.annotations.*;

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
 * Create at:   2016/9/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/6.  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_info")
public class FssTradeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "data_source")            //数据来源
    private String dataSource;

    @Column(name = "sys_code")              //系统代码
    private String sysCode;

    @Column(name = "orgl_seq_no")           //原交易流水号
    private String orglSeqNo;

    @Column(name = "chg_cd")                //充值码
    private String chgCd;

    @Column(name = "to_acc_time")           //入账时间
    @DateType(value = DateTypeEnum.DATE_TIME_FOURTEEN, memo = "入账时间")
    private Date toAccTime;

    @Column(name = "trade_time")            //交易时间
    @DateType(value = DateTypeEnum.DATE_TIME_FOURTEEN, memo = "交易时间")
    private Date tradeTime;

    @Column(name = "to_acc_nm")             //入账户名
    private String toAccNm;

    @Column(name = "to_acc_no")             //入账卡号
    private String toAccNo;

    @Column(name = "amount")                //金额
    @NumberType(value = NumberTypeEnum.DECIMAL , memo = "金额")
    private BigDecimal amount;

    @Column(name = "trade_sts")             //交易状态
    private String tradeSts;

    @Column(name = "card_verify")           //卡号校验 0 .未校验 1. 以校验
    private String cardVerify;

    @Column(name = "file_id")               //file文件id;
    private String fileId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getOrglSeqNo() {
        return orglSeqNo;
    }

    public void setOrglSeqNo(String orglSeqNo) {
        this.orglSeqNo = orglSeqNo;
    }

    public String getChgCd() {
        return chgCd;
    }

    public void setChgCd(String chgCd) {
        this.chgCd = chgCd;
    }

    public Date getToAccTime() {
        return toAccTime;
    }

    public void setToAccTime(Date toAccTime) {
        this.toAccTime = toAccTime;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getToAccNm() {
        return toAccNm;
    }

    public void setToAccNm(String toAccNm) {
        this.toAccNm = toAccNm;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTradeSts() {
        return tradeSts;
    }

    public void setTradeSts(String tradeSts) {
        this.tradeSts = tradeSts;
    }

    public String getCardVerify() {
        return cardVerify;
    }

    public void setCardVerify(String cardVerify) {
        this.cardVerify = cardVerify;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
