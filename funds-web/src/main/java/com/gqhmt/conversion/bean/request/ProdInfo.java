package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class ProdInfo {
	private String MerchID;
	private String ProdID;
	private String PrdInfo;
	private String PrdQty;

	public String getMerchID() {
		return MerchID;
	}

	public void setMerchID(String merchID) {
		MerchID = merchID;
	}

	public String getProdID() {
		return ProdID;
	}

	public void setProdID(String prodID) {
		ProdID = prodID;
	}

	public String getPrdInfo() {
		return PrdInfo;
	}

	public void setPrdInfo(String prdInfo) {
		PrdInfo = prdInfo;
	}

	public String getPrdQty() {
		return PrdQty;
	}

	public void setPrdQty(String prdQty) {
		PrdQty = prdQty;
	}
}
