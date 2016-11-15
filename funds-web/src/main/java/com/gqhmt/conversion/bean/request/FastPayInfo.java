package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class FastPayInfo {
	private String Cvv;
	private String SmsCd;
	private String ValidDt;
	private String TokenCd;
	private String PayFlg;
	private String AuthId;

	public String getCvv() {
		return Cvv;
	}

	public void setCvv(String cvv) {
		Cvv = cvv;
	}

	public String getSmsCd() {
		return SmsCd;
	}

	public void setSmsCd(String smsCd) {
		SmsCd = smsCd;
	}

	public String getValidDt() {
		return ValidDt;
	}

	public void setValidDt(String validDt) {
		ValidDt = validDt;
	}

	public String getTokenCd() {
		return TokenCd;
	}

	public void setTokenCd(String tokenCd) {
		TokenCd = tokenCd;
	}

	public String getPayFlg() {
		return PayFlg;
	}

	public void setPayFlg(String payFlg) {
		PayFlg = payFlg;
	}

	public String getAuthId() {
		return AuthId;
	}

	public void setAuthId(String authId) {
		AuthId = authId;
	}
}
