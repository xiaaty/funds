package com.gqhmt.fss.architect.trade.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gqhmt.core.json.BigDecimalSerialize;
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
	private String fyAccNm;//账户名
	private String fyAccNo;//账号
	private String fyBnakBranch;//支行名称

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

	@JsonSerialize(using = BigDecimalSerialize.class)
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

	public String getFyAccNm() {
		return fyAccNm;
	}

	public void setFyAccNm(String fyAccNm) {
		this.fyAccNm = fyAccNm;
	}

	public String getFyAccNo() {
		return fyAccNo;
	}

	public void setFyAccNo(String fyAccNo) {
		this.fyAccNo = fyAccNo;
	}

	public String getFyBnakBranch() {
		return fyBnakBranch;
	}

	public void setFyBnakBranch(String fyBnakBranch) {
		this.fyBnakBranch = fyBnakBranch;
	}
}
