package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/5/31 17:29
 * Description:
 * <p>记录费用收取交易信息
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/5/31  柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_charge_record")
public class FssChargeRecordEntity implements Serializable {
	@Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "seq_no",updatable = false)
    private String seqNo;
    @Column(name = "from_cust_no",updatable = false)
    private String fromCustNo;
    @Column(name = "from_cust_name",updatable = false)
    private String fromCustName;
    @Column(name = "from_acc_type",updatable = false)
    private String fromAccType;
    @Column(name = "from_acc_no",updatable = false)
    private String fromAccNo;
    @Column(name = "to_cust_no",updatable = false)
    private String toCustNo;
    @Column(name = "to_cust_name",updatable = false)
    private String toCustName;
	@Column(name = "to_acc_type",updatable = false)
	private String toAccType;
    @Column(name = "to_acc_no",updatable = false)
    private String toAccNo;
    @Column(name = "charge_type",updatable = false)
    private String chargeType;
	@Column(name = "amt",updatable = false)
	private BigDecimal amt;
    @Column(name = "busi_no",updatable = false)
    private String busiNo;
    @Column(name = "busi_type",updatable = false)
    private String busiType;
    @Column(name = "trade_type",updatable = false)
    private String tradeType;
    @Column(name = "trade_result")
    private String tradeResult;
    @Column(name = "trade_time",updatable = false)
    private Date tradeTime;
    @Column(name = "sumary",updatable = false)
    private String sumary;
    @Column(name = "platform",updatable = false)
    private String platform;
    @Column(name = "resp_code")
    private String respCode;
    @Column(name = "resp_msg")
    private String respMsg;
    @Column(name = "order_no")
    private String orderNo;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "modify_time")
	private Date modifyTime;

	public String getFromAccType() {
		return fromAccType;
	}

	public void setFromAccType(String fromAccType) {
		this.fromAccType = fromAccType;
	}

	public String getToAccType() {
		return toAccType;
	}

	public void setToAccType(String toAccType) {
		this.toAccType = toAccType;
	}
	public Long getId() {
		return id;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromCustNo() {
		return fromCustNo;
	}
	public void setFromCustNo(String fromCustNo) {
		this.fromCustNo = fromCustNo;
	}
	public String getFromCustName() {
		return fromCustName;
	}
	public void setFromCustName(String fromCustName) {
		this.fromCustName = fromCustName;
	}
	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public String getToCustNo() {
		return toCustNo;
	}
	public void setToCustNo(String toCustNo) {
		this.toCustNo = toCustNo;
	}
	public String getToCustName() {
		return toCustName;
	}
	public void setToCustName(String toCustName) {
		this.toCustName = toCustName;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeResult() {
		return tradeResult;
	}
	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getSumary() {
		return sumary;
	}
	public void setSumary(String sumary) {
		this.sumary = sumary;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
}
