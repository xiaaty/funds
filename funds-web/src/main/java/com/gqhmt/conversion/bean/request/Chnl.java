package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class Chnl {

	private String ChnlID;
	private String OprtrID;
	private String APPId;

	@XmlElement(name = "ProdInfo")
	private List<ProdInfo> prodInfo;

	@XmlElement(name = "fastPayInfo")
	private List<FastPayInfo> fastPayInfo;

	public String getChnlID() {
		return ChnlID;
	}

	public void setChnlID(String chnlID) {
		ChnlID = chnlID;
	}

	public String getOprtrID() {
		return OprtrID;
	}

	public void setOprtrID(String oprtrID) {
		OprtrID = oprtrID;
	}

	public String getAPPId() {
		return APPId;
	}

	public void setAPPId(String APPId) {
		this.APPId = APPId;
	}

	public List<ProdInfo> getProdInfo() {
		return prodInfo;
	}

	public void setProdInfo(List<ProdInfo> prodInfo) {
		this.prodInfo = prodInfo;
	}

	public List<FastPayInfo> getFastPayInfo() {
		return fastPayInfo;
	}

	public void setFastPayInfo(List<FastPayInfo> fastPayInfo) {
		this.fastPayInfo = fastPayInfo;
	}
}