package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 保付支付-代付交易拆封接口实体类
 * @author 柯禹来
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="req")
@XmlType(name = "", propOrder = {})
public class RequestMsg {

	@XmlElement(name = "PmtID")
	private List<PmtId> requestMsg;

	@XmlElement(name = "OrgnlMsgInfo")
	private List<OrgnlMsgInfo> OrgnlMsgInfo;

	@XmlElement(name = "Chnl")
	private List<Chnl> Chnl;

	@XmlElement(name = "TxnAmtInfo")
	private List<TxnAmtInfo> TxnAmtInfo;

	@XmlElement(name = "RoutInfo")
	private List<RoutInfo> RoutInfo;
//----------------
	@XmlElement(name = "Dbtr")
	private List<Dbtr> Dbtr;

	@XmlElement(name = "Cdtr")
	private List<Cdtr> Cdtr;

	@XmlElement(name = "DbtrAcct")
	private List<DbtrAcct> DbtrAcct;

	@XmlElement(name = "CdtrAcct")
	private List<CdtrAcct> CdtrAcct;

	public List<RoutInfo> getRoutInfo() {
		return RoutInfo;
	}

	public void setRoutInfo(List<RoutInfo> routInfo) {
		RoutInfo = routInfo;
	}

	public List<TxnAmtInfo> getTxnAmtInfo() {
		return TxnAmtInfo;
	}

	public void setTxnAmtInfo(List<TxnAmtInfo> txnAmtInfo) {
		TxnAmtInfo = txnAmtInfo;
	}

	public List<PmtId> getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(List<PmtId> requestMsg) {
		this.requestMsg = requestMsg;
	}

	public List<OrgnlMsgInfo> getOrgnlMsgInfo() {
		return OrgnlMsgInfo;
	}

	public void setOrgnlMsgInfo(List<OrgnlMsgInfo> orgnlMsgInfo) {
		OrgnlMsgInfo = orgnlMsgInfo;
	}

	public List<com.gqhmt.conversion.bean.request.Chnl> getChnl() {
		return Chnl;
	}

	public void setChnl(List<com.gqhmt.conversion.bean.request.Chnl> chnl) {
		Chnl = chnl;
	}

	public List<com.gqhmt.conversion.bean.request.Dbtr> getDbtr() {
		return Dbtr;
	}

	public void setDbtr(List<com.gqhmt.conversion.bean.request.Dbtr> dbtr) {
		Dbtr = dbtr;
	}

	public List<com.gqhmt.conversion.bean.request.Cdtr> getCdtr() {
		return Cdtr;
	}

	public void setCdtr(List<com.gqhmt.conversion.bean.request.Cdtr> cdtr) {
		Cdtr = cdtr;
	}

	public List<com.gqhmt.conversion.bean.request.DbtrAcct> getDbtrAcct() {
		return DbtrAcct;
	}

	public void setDbtrAcct(List<com.gqhmt.conversion.bean.request.DbtrAcct> dbtrAcct) {
		DbtrAcct = dbtrAcct;
	}

	public List<com.gqhmt.conversion.bean.request.CdtrAcct> getCdtrAcct() {
		return CdtrAcct;
	}

	public void setCdtrAcct(List<com.gqhmt.conversion.bean.request.CdtrAcct> cdtrAcct) {
		CdtrAcct = cdtrAcct;
	}
}
