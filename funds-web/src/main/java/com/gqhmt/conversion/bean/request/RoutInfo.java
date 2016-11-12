package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class RoutInfo {

	private String AssCdtrId;
	private String CdtrId;
	private String CdtrNm;
	private String KeyPath;
	private String IsChange;
	private String ExMerchId;

	public String getAssCdtrId() {
		return AssCdtrId;
	}

	public void setAssCdtrId(String assCdtrId) {
		AssCdtrId = assCdtrId;
	}

	public String getCdtrId() {
		return CdtrId;
	}

	public void setCdtrId(String cdtrId) {
		CdtrId = cdtrId;
	}

	public String getCdtrNm() {
		return CdtrNm;
	}

	public void setCdtrNm(String cdtrNm) {
		CdtrNm = cdtrNm;
	}

	public String getKeyPath() {
		return KeyPath;
	}

	public void setKeyPath(String keyPath) {
		KeyPath = keyPath;
	}

	public String getIsChange() {
		return IsChange;
	}

	public void setIsChange(String isChange) {
		IsChange = isChange;
	}

	public String getExMerchId() {
		return ExMerchId;
	}

	public void setExMerchId(String exMerchId) {
		ExMerchId = exMerchId;
	}
}
