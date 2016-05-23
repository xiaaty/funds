package com.gqhmt.fss.architect.order.entity;

import com.gqhmt.annotations.AutoDate;
import com.gqhmt.annotations.AutoDateType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.order.entity.FssSeqOrder
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 15:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_seq_order")
public class FssSeqOrderEntity implements Serializable {

    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "seq_no",updatable = false)
    private String seqNo;                              // varchar(45) NOT NULL COMMENT '交易流水号',


    @Column(name = "cust_no",updatable = false)
    private String custNo;                             // varchar(45) DEFAULT NULL COMMENT '客户编号',


    @Column(name = "user_no",updatable = false)
    private String userNo;                             // varchar(45) DEFAULT NULL COMMENT '用户编号',


    @Column(name = "acc_no",updatable = false)
    private String accNo;                              // varchar(45) DEFAULT NULL COMMENT '账务账户编号',


    @Column(name = "trade_type",updatable = false)
    private String tradeType;                          // varchar(45) NOT NULL COMMENT '交易类型，1账户，2充值，3，提现，4标的，5收费，6转账，7，暂未定义 ，8资产查询，9其他',


    @Column(name = "trade_type_child",updatable = false)
    private String tradeTypeChild;                    // varchar(45) DEFAULT NULL COMMENT '见，kvcode表',


    @Column(name = "amount",updatable = false)
    private BigDecimal amount;                          // decimal(17,2) DEFAULT NULL COMMENT '操作资金金额',


    @Column(name = "charge_amount",updatable = false)
    private BigDecimal chargeAmount;                   // decimal(17,2) DEFAULT NULL COMMENT '本次交易手续费',


    @Column(name = "api_no",updatable = false)
    private String apiNo;                              // varchar(45) NOT NULL COMMENT '接口编号',


    @Column(name = "mchn_parent",updatable = false)
    private String mchnParent;                         // varchar(45) NOT NULL COMMENT '大商户编号',


    @Column(name = "mchn_child",updatable = false)
    private String mchnChild;                          // varchar(45) NOT NULL COMMENT '子商户号',


    @AutoDate(type = AutoDateType.DATE_CHAR_8)
    @Column(name = "trade_date",updatable = false)
    private String tradeDate;                          // char(8) DEFAULT NULL COMMENT '交易日期',


    @AutoDate(type = AutoDateType.TIME_CHAR_6)
    @Column(name = "trade_time",updatable = false)
    private String tradeTime;                          // char(6) DEFAULT NULL COMMENT '交易时间',


    @Column(name = "state")
    private Integer state;                              // int(11) NOT NULL DEFAULT '1' COMMENT '交易状态，1，交易接收，2交易成功，3交易失败，1000交易关闭',


    @Column(name = "return_state")
    private Integer returnState;                       //  int(11) NOT NULL DEFAULT '0' COMMENT '0，不需回调通知，1待回调通知，2已回调通知—具体参数，见kvcode表。',

    @AutoDate
    @Column(name = "create_time",updatable = false)
    private Date createTime;                           // datetime DEFAULT NULL COMMENT '创建时间',


    @AutoDate
    @Column(name = "modify_time")
    private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',


    @Column(name = "memo")
    private String  memo;                               //varchar(45) DEFAULT NULL,

    @Column(name = "trade_param",updatable = false)
    private String tradeParam;                         // blob COMMENT '交易参数存储字段',

    @Column(name = "resp_code")
    private String respCode;

    @Column(name="resp_msg")
    private String respMsg;

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

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeTypeChild() {
        return tradeTypeChild;
    }

    public void setTradeTypeChild(String tradeTypeChild) {
        this.tradeTypeChild = tradeTypeChild;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getApiNo() {
        return apiNo;
    }

    public void setApiNo(String apiNo) {
        this.apiNo = apiNo;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTradeParam() {
        return tradeParam;
    }

    public void setTradeParam(String tradeParam) {
        this.tradeParam = tradeParam;
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
