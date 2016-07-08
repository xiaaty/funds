package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/06/29
 * Description:债权转让实体类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/06/29 柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_bond_transfer")
public class FssBondTransferEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cust_id")
    private String custId;
    @Column(name = "cust_no")
    private String custNo;
	@Column(name = "cust_name")
	private String custName;
	@Column(name = "acc_no")
	private String accNo;
	@Column(name = "acc_type")
	private String accType;
	@Column(name = "bid_id")
	private String bidId;
	@Column(name = "busi_bid_no")
	private String busiBidNo;
	@Column(name = "tender_no")
	private String tenderNo;
	@Column(name = "o_tender_no")
	private String oTenderNo;
	@Column(name = "o_cust_no")
	private String oCustNo;
	@Column(name = "busi_no")
	private String busiNo ;
	@Column(name = "busi_type")
	private String busiType ;
	@Column(name = "o_busi_no")
	private String oBusiNo ;
	@Column(name = "trade_type")
	private String tradeType;
	@Column(name = "principal")
	private BigDecimal principal;
	@Column(name = "interest")
	private BigDecimal interest;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "trade_state")
	private String tradeState ;
	@Column(name = "create_time")
	private Date createTime ;
	@Column(name = "modify_time")
	private Date modifyTime ;
	@Column(name = "mchn")
	private String mchn ;
	@Column(name = "seq_no")
	private String seqNo;
	@Column(name = "order_no")
	private String orderNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getBusiBidNo() {
		return busiBidNo;
	}

	public void setBusiBidNo(String busiBidNo) {
		this.busiBidNo = busiBidNo;
	}

	public String getTenderNo() {
		return tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public String getoTenderNo() {
		return oTenderNo;
	}

	public void setoTenderNo(String oTenderNo) {
		this.oTenderNo = oTenderNo;
	}

	public String getoCustNo() {
		return oCustNo;
	}

	public void setoCustNo(String oCustNo) {
		this.oCustNo = oCustNo;
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

	public String getoBusiNo() {
		return oBusiNo;
	}

	public void setoBusiNo(String oBusiNo) {
		this.oBusiNo = oBusiNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
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

	public String getMchn() {
		return mchn;
	}

	public void setMchn(String mchn) {
		this.mchn = mchn;
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
}
