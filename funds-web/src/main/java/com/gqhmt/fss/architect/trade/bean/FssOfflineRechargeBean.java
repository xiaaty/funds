package com.gqhmt.fss.architect.trade.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gqhmt.core.util.Application;

import java.math.BigDecimal;

public class FssOfflineRechargeBean{

	private Long id;
	private String seqNo;//流水号
	private String chgCd;//充值码
	private String chgDt;//充值码时间
	@JsonIgnore
	private String payStateType;//支付状态
	private String payState;//支付状态
	private BigDecimal amt;//充值金额

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

	public String getPayState() {
		return Application.getInstance().getDictName(this.payStateType);
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getPayStateType() {
		return payStateType;
	}

	public void setPayStateType(String payStateType) {
		this.payStateType = payStateType;
	}
}
