package com.gqhmt.fss.architect.trade.entity;

import com.gqhmt.annotations.AutoDate;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gqhmt.funds.architect.record.entity.TradeProcess
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/26 17:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/26  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_trade_process")
public class TradeProcessEntity implements java.io.Serializable{


    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id               ; //bigint(19)       主键

    @Column(name = "parnet_id",updatable = false)
    private Long parnetId        ; //bigint(19)       上级目标

    @Column(name = "mchn_no",updatable = false)
    private String mchnNo           ; //varchar(45)      与业务系统交互商户号

    @Column(name = "seq_no",updatable = false)
    private String seqNo           ; //varchar(45)      与业务系统交互流水号

    @Column(name = "order_no")
    private String orderNo         ; //varchar(45)      与支付系统交互流水号

    @Column(name = "from_cust_no",updatable = false)
    private String fromCustNo     ; //varchar(45)      出账账户客户编号

    @Column(name = "from_cust_type",updatable = false)
    private String fromCustType   ; //char(8)          出账账户客户类型，1，2，3

    @Column(name = "from_acc_no",updatable = false)
    private String fromAccNo      ; //varchar(45)      出账支付系统账户id

    @Column(name = "from_acc_id",updatable = false)
    private Long fromAccId      ; //long(19)         出账账户id

    @Column(name = "from_cust_mobile",updatable = false)
    private String fromCustMobile ; //varchar(11)      出账账户客户手机号

    @Column(name = "from_cust_name",updatable = false)
    private String fromCustName   ; //varchar(45)      出账账户客户姓名

    @Column(name = "to_cust_no",updatable = false)
    private String toCustNo       ; //varchar(45)      入账账户客户编号

    @Column(name = "to_cust_type",updatable = false)
    private String toCustType     ; //varchar(45)      入账账户客户类型，1，2，3

    @Column(name = "to_acc_no",updatable = false)
    private String toAccNo        ; //varchar(45)      入账支付系统账户id

    @Column(name = "to_acc_id",updatable = false)
    private Long toAccId      ; //long(19)         入账账户id

    @Column(name = "to_cust_mobile",updatable = false)
    private String toCustMobile   ; //varchar(11)      入账账户客户手机号

    @Column(name = "to_cust_name",updatable = false)
    private String toCustName     ; //varchar(45)      入账账户客户姓名

    @Column(name = "amt",updatable = false)
    private BigDecimal amt              ; //decimal(19,2)    交易金额

    @Column(name = "audit_amount",updatable = false)
    private BigDecimal audiAmount              ; //decimal(19,2)    审核金额

   @Column(name = "real_trade_amount")
    private BigDecimal realTradeAmount              ; //decimal(19,2)    实际交易成功金额

    @Column(name = "bid_id",updatable = false)
    private String bidId           ; //varchar(45)      标的id

    @Column(name = "tender_id",updatable = false)
    private String tenderId           ; //varchar(45)      投标id

    @Column(name = "new_tender_id",updatable = false)
    private String newTenderId    ; //varchar(45)      债转新投标id

    @Column(name = "loan_contract_no",updatable = false)
    private String loanContractNo ; //varchar(45)      借款合同编号

    @Column(name = "lend_contract_no",updatable = false)
    private String lendContractNo ; //varchar(45)      出借编号

    @Column(name = "product_name",updatable = false)
    private String productName     ; //varchar(45)      产品名称

    @Column(name = "trade_type_parent",updatable = false)
    private String tradeTypeParent; //char(4)          父交易类型

    @Column(name = "trade_type",updatable = false)
    private String tradeType       ; //char(8)          交易类型

    @Column(name = "action_type",updatable = false)
    private String actionType    ; //char(8)          业务类型，借款，出借，

    @Column(name = "fund_type",updatable = false)
    private String fundType     ; //char(8)          进程类型，开户，绑卡，线上充

    @Column(name = "status")
    private String status           ; //char(8)          交易状态

    @Column(name = "process_state")
    private String processState           ; //流程状态

    @Column(name = "memo")
    private String memo             ; //varchar(2000)    交易备注

    @Column(name = "sync")
    private String sync             ; //char(8)          同步交易、异步交易

    @Column(name = "callback")
    private String callback        ;//  char(8)          是否需要回调通知

    @AutoDate
    @Column(name = "create_time",updatable = false)
    private Date createTime      ; //timestamp        创建时间

    @AutoDate
    @Column(name = "modify_time")
    private Date modifyTime   ; //   timestamp        最后修改时间

    @Column(name = "settle_type")
    private String settleType;//提现时效

    @Column(name = "withhold_type")
    private String withHoldType;//代扣类型字段

    @Column(name = "serial_number")
    private String serialNumber;//序列号

    @Column(name = "resp_code")
    private String respCode;//统一支付返回码

    @Column(name = "resp_msg")
    private String respMsg;//统一支付返回消息

    @Transient
    private List<TradeProcessEntity> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParnetId() {
        return parnetId;
    }

    public void setParnetId(Long parnetId) {
        this.parnetId = parnetId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFromCustNo() {
        return fromCustNo;
    }

    public void setFromCustNo(String fromCustNo) {
        this.fromCustNo = fromCustNo;
    }

    public String getFromCustType() {
        return fromCustType;
    }

    public void setFromCustType(String fromCustType) {
        this.fromCustType = fromCustType;
    }

    public String getFromAccNo() {
        return fromAccNo;
    }

    public void setFromAccNo(String fromAccNo) {
        this.fromAccNo = fromAccNo;
    }

    public String getFromCustMobile() {
        return fromCustMobile;
    }

    public void setFromCustMobile(String fromCustMobile) {
        this.fromCustMobile = fromCustMobile;
    }

    public String getFromCustName() {
        return fromCustName;
    }

    public void setFromCustName(String fromCustName) {
        this.fromCustName = fromCustName;
    }

    public String getToCustNo() {
        return toCustNo;
    }

    public void setToCustNo(String toCustNo) {
        this.toCustNo = toCustNo;
    }

    public String getToCustType() {
        return toCustType;
    }

    public void setToCustType(String toCustType) {
        this.toCustType = toCustType;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public String getToCustMobile() {
        return toCustMobile;
    }

    public void setToCustMobile(String toCustMobile) {
        this.toCustMobile = toCustMobile;
    }

    public String getToCustName() {
        return toCustName;
    }

    public void setToCustName(String toCustName) {
        this.toCustName = toCustName;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }


    public String getNewTenderId() {
        return newTenderId;
    }

    public void setNewTenderId(String newTenderId) {
        this.newTenderId = newTenderId;
    }

    public String getLoanContractNo() {
        return loanContractNo;
    }

    public void setLoanContractNo(String loanContractNo) {
        this.loanContractNo = loanContractNo;
    }

    public String getLendContractNo() {
        return lendContractNo;
    }

    public void setLendContractNo(String lendContractNo) {
        this.lendContractNo = lendContractNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeTypeParent() {
        return tradeTypeParent;
    }

    public void setTradeTypeParent(String tradeTypeParent) {
        this.tradeTypeParent = tradeTypeParent;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
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

    public List<TradeProcessEntity> getList() {
        return list;
    }

    public void setList(List<TradeProcessEntity> list) {
        this.list = list;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getSettleType() {
        if(StringUtils.isEmpty(settleType)){
            return "0";
        }
        return settleType;
    }

    public void setSettleType(String settleType) {
        if(StringUtils.isEmpty(settleType)){
            settleType="0";
        }
        this.settleType = settleType;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public Long getFromAccId() {
        return fromAccId;
    }

    public void setFromAccId(Long fromAccId) {
        this.fromAccId = fromAccId;
    }

    public Long getToAccId() {
        return toAccId;
    }

    public void setToAccId(Long toAccId) {
        this.toAccId = toAccId;
    }

    public String getWithHoldType() {
        return withHoldType;
    }

    public void setWithHoldType(String withHoldType) {
        this.withHoldType = withHoldType;
    }

    public BigDecimal getAudiAmount() {
        return audiAmount;
    }

    public void setAudiAmount(BigDecimal audiAmount) {
        this.audiAmount = audiAmount;
    }

    public BigDecimal getRealTradeAmount() {
        return realTradeAmount;
    }

    public void setRealTradeAmount(BigDecimal realTradeAmount) {
        this.realTradeAmount = realTradeAmount;
    }

    public String getMchnNo() {
        return mchnNo;
    }

    public void setMchnNo(String mchnNo) {
        this.mchnNo = mchnNo;
    }
}
