package com.gqhmt.conversion.bean.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="OrgnlMsgInfo")
@XmlType(name = "", propOrder = {})
public class OrgnlMsgInfoResponse {
	private String OrgnlOrderId;
	private String OrgnlMsgId;
	private String OrgnlTxnTp;
	private String OrgnlSttlmAmt;
	private String OrgnlSttlmCcy;

	public String getOrgnlOrderId() {
		return OrgnlOrderId;
	}

	public void setOrgnlOrderId(String orgnlOrderId) {
		OrgnlOrderId = orgnlOrderId;
	}

	public String getOrgnlMsgId() {
		return OrgnlMsgId;
	}

	public void setOrgnlMsgId(String orgnlMsgId) {
		OrgnlMsgId = orgnlMsgId;
	}

	public String getOrgnlTxnTp() {
		return OrgnlTxnTp;
	}

	public void setOrgnlTxnTp(String orgnlTxnTp) {
		OrgnlTxnTp = orgnlTxnTp;
	}

	public String getOrgnlSttlmAmt() {
		return OrgnlSttlmAmt;
	}

	public void setOrgnlSttlmAmt(String orgnlSttlmAmt) {
		OrgnlSttlmAmt = orgnlSttlmAmt;
	}

	public String getOrgnlSttlmCcy() {
		return OrgnlSttlmCcy;
	}

	public void setOrgnlSttlmCcy(String orgnlSttlmCcy) {
		OrgnlSttlmCcy = orgnlSttlmCcy;
	}
}
