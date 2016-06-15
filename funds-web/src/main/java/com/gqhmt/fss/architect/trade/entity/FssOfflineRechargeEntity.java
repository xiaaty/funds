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
 * Create at:   2016/06/08
 * Description:线下充值实体对象
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/06/08 柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_offline_recharge")
public class FssOfflineRechargeEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apply_no")
    private String applyNo;

    @Column(name = "apply_type")
    private Integer applyType;

    @Column(name = "cust_id")
    private Long custId;  
    
    @Column(name = "cust_name")
    private String custName;

	@Column(name = "cust_type")
	private String custType;

    @Column(name = "fy_acc_no")
    private String fyAccNo;

    @Column(name = "order_no")
    private String orderNo;

	@Column(name = "amt")
	private BigDecimal amt ;

	@Column(name = "apply_state")
	private String applyState  ;

    @Column(name = "trade_state")
    private String tradeState ;

	@Column(name = "create_time")
	private Date createTime ;

	@Column(name = "modify_time")
	private Date modifyTime ;

	@Column(name = "busi_no")
	private String busiNo ;

	@Column(name = "busi_type")
	private String busiType ;

	@Column(name = "seq_no")
	private String seqNo;

    @Column(name = "mchn")
    private String mchn ;

    @Column(name = "channel_no")
    private String channelNo;

    @Column(name = "cust_no")
    private String custNo;
	@Column(name = "chg_cd")
    private String chgCd;
    @Column(name = "chg_dt")
    private String chgDt;
    @Column(name = "fy_bank")
    private String fyBank;
    @Column(name = "fy_bank_branch")
    private String fyBankBranch;
    @Column(name = "desc_code")
    private String descCode;
	@Column(name = "fy_acc_nm")
    private String fyAccNm;
	@Column(name = "acc_no")
	private String accNo;
	@Column(name = "result_state")
	private String resultState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getMchn() {
		return mchn;
	}

	public void setMchn(String mchn) {
		this.mchn = mchn;
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getDescCode() {
		return descCode;
	}

	public void setDescCode(String descCode) {
		this.descCode = descCode;
	}

	public String getFyAccNo() {
		return fyAccNo;
	}

	public void setFyAccNo(String fyAccNo) {
		this.fyAccNo = fyAccNo;
	}

	public String getChgCd() {
		return chgCd;
	}

	public void setChgCd(String chgCd) {
		this.chgCd = chgCd;
	}

	public String getChgDt() {
		return chgDt;
	}

	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}

	public String getFyBank() {
		return fyBank;
	}

	public void setFyBank(String fyBank) {
		this.fyBank = fyBank;
	}

	public String getFyBankBranch() {
		return fyBankBranch;
	}

	public void setFyBankBranch(String fyBankBranch) {
		this.fyBankBranch = fyBankBranch;
	}
	public String getFyAccNm() {
		return fyAccNm;
	}

	public void setFyAccNm(String fyAccNm) {
		this.fyAccNm = fyAccNm;
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

	public String getResultState() {
		return resultState;
	}

	public void setResultState(String resultState) {
		this.resultState = resultState;
	}
}
