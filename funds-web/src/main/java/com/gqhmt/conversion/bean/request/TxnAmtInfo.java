package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class TxnAmtInfo {
	private String IntrBkSttlmAmt;
	private String IntrBkSttlmCcy;
	private String ActSttlmAmt;
	private String ActSttlmCcy;
	private String RtrdIntrBkSttlmAmt;
	private String RtrdIntrBkSttlmCcy;

	public String getIntrBkSttlmAmt() {
		return IntrBkSttlmAmt;
	}

	public void setIntrBkSttlmAmt(String intrBkSttlmAmt) {
		IntrBkSttlmAmt = intrBkSttlmAmt;
	}

	public String getIntrBkSttlmCcy() {
		return IntrBkSttlmCcy;
	}

	public void setIntrBkSttlmCcy(String intrBkSttlmCcy) {
		IntrBkSttlmCcy = intrBkSttlmCcy;
	}

	public String getActSttlmAmt() {
		return ActSttlmAmt;
	}

	public void setActSttlmAmt(String actSttlmAmt) {
		ActSttlmAmt = actSttlmAmt;
	}

	public String getActSttlmCcy() {
		return ActSttlmCcy;
	}

	public void setActSttlmCcy(String actSttlmCcy) {
		ActSttlmCcy = actSttlmCcy;
	}

	public String getRtrdIntrBkSttlmAmt() {
		return RtrdIntrBkSttlmAmt;
	}

	public void setRtrdIntrBkSttlmAmt(String rtrdIntrBkSttlmAmt) {
		RtrdIntrBkSttlmAmt = rtrdIntrBkSttlmAmt;
	}

	public String getRtrdIntrBkSttlmCcy() {
		return RtrdIntrBkSttlmCcy;
	}

	public void setRtrdIntrBkSttlmCcy(String rtrdIntrBkSttlmCcy) {
		RtrdIntrBkSttlmCcy = rtrdIntrBkSttlmCcy;
	}
}
