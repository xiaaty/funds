package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class DbtrAcct {



	private String IdTp;
	private String IssrCd;
	private String Issr;
	private String Branch;
	private String Id;
	private String ShortCard;
	private String Ccy;
	private String Nm;
	private String Tp;

	public String getIdTp() {
		return IdTp;
	}

	public void setIdTp(String idTp) {
		IdTp = idTp;
	}

	public String getIssrCd() {
		return IssrCd;
	}

	public void setIssrCd(String issrCd) {
		IssrCd = issrCd;
	}

	public String getIssr() {
		return Issr;
	}

	public void setIssr(String issr) {
		Issr = issr;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String branch) {
		Branch = branch;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getShortCard() {
		return ShortCard;
	}

	public void setShortCard(String shortCard) {
		ShortCard = shortCard;
	}

	public String getCcy() {
		return Ccy;
	}

	public void setCcy(String ccy) {
		Ccy = ccy;
	}

	public String getNm() {
		return Nm;
	}

	public void setNm(String nm) {
		Nm = nm;
	}

	public String getTp() {
		return Tp;
	}

	public void setTp(String tp) {
		Tp = tp;
	}
}
