package com.gqhmt.conversion.bean.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 保付支付-代付交易拆封接口实体类
 * @author 柯禹来
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="PmtID")
@XmlType(name = "", propOrder = {})
public class PmtIdResponse {

	private String OrderId;
	private String MsgID;
	private String FrmtVrsn;
	private String ExTxnId;
	private String SplitFlg1;
	private String SplitFlg2;
	private String TxnTp;
	private String TxnSubTp;
	private String PmtTp;
	private String BizTp;
	private String BizAttr;
	private String ProcMd;
	private String MsgSts;
	private String ErrDesp;
	private String RespCode;
	private String RespCdDesp;
	private String ReasonFlg;
	private String ChkSts;
	private String SignInfo;
	private String RevFlg;
	private String RevId;
	private String RevDt;
	private String CustID;
	private String CardTp;
	private String BatchIdIn;
	private String BatchIdOut;
	private String ChnReUrl;
	private String ChnNotiUrl;
	private String ExReUrl;

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

	public String getFrmtVrsn() {
		return FrmtVrsn;
	}

	public void setFrmtVrsn(String frmtVrsn) {
		FrmtVrsn = frmtVrsn;
	}

	public String getExTxnId() {
		return ExTxnId;
	}

	public void setExTxnId(String exTxnId) {
		ExTxnId = exTxnId;
	}

	public String getSplitFlg1() {
		return SplitFlg1;
	}

	public void setSplitFlg1(String splitFlg1) {
		SplitFlg1 = splitFlg1;
	}

	public String getSplitFlg2() {
		return SplitFlg2;
	}

	public void setSplitFlg2(String splitFlg2) {
		SplitFlg2 = splitFlg2;
	}

	public String getTxnTp() {
		return TxnTp;
	}

	public void setTxnTp(String txnTp) {
		TxnTp = txnTp;
	}

	public String getTxnSubTp() {
		return TxnSubTp;
	}

	public void setTxnSubTp(String txnSubTp) {
		TxnSubTp = txnSubTp;
	}

	public String getPmtTp() {
		return PmtTp;
	}

	public void setPmtTp(String pmtTp) {
		PmtTp = pmtTp;
	}

	public String getBizTp() {
		return BizTp;
	}

	public void setBizTp(String bizTp) {
		BizTp = bizTp;
	}

	public String getBizAttr() {
		return BizAttr;
	}

	public void setBizAttr(String bizAttr) {
		BizAttr = bizAttr;
	}

	public String getProcMd() {
		return ProcMd;
	}

	public void setProcMd(String procMd) {
		ProcMd = procMd;
	}

	public String getMsgSts() {
		return MsgSts;
	}

	public void setMsgSts(String msgSts) {
		MsgSts = msgSts;
	}

	public String getErrDesp() {
		return ErrDesp;
	}

	public void setErrDesp(String errDesp) {
		ErrDesp = errDesp;
	}

	public String getRespCode() {
		return RespCode;
	}

	public void setRespCode(String respCode) {
		RespCode = respCode;
	}

	public String getRespCdDesp() {
		return RespCdDesp;
	}

	public void setRespCdDesp(String respCdDesp) {
		RespCdDesp = respCdDesp;
	}

	public String getReasonFlg() {
		return ReasonFlg;
	}

	public void setReasonFlg(String reasonFlg) {
		ReasonFlg = reasonFlg;
	}

	public String getChkSts() {
		return ChkSts;
	}

	public void setChkSts(String chkSts) {
		ChkSts = chkSts;
	}

	public String getSignInfo() {
		return SignInfo;
	}

	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}

	public String getRevFlg() {
		return RevFlg;
	}

	public void setRevFlg(String revFlg) {
		RevFlg = revFlg;
	}

	public String getRevId() {
		return RevId;
	}

	public void setRevId(String revId) {
		RevId = revId;
	}

	public String getRevDt() {
		return RevDt;
	}

	public void setRevDt(String revDt) {
		RevDt = revDt;
	}

	public String getCustID() {
		return CustID;
	}

	public void setCustID(String custID) {
		CustID = custID;
	}

	public String getCardTp() {
		return CardTp;
	}

	public void setCardTp(String cardTp) {
		CardTp = cardTp;
	}

	public String getBatchIdIn() {
		return BatchIdIn;
	}

	public void setBatchIdIn(String batchIdIn) {
		BatchIdIn = batchIdIn;
	}

	public String getBatchIdOut() {
		return BatchIdOut;
	}

	public void setBatchIdOut(String batchIdOut) {
		BatchIdOut = batchIdOut;
	}

	public String getChnReUrl() {
		return ChnReUrl;
	}

	public void setChnReUrl(String chnReUrl) {
		ChnReUrl = chnReUrl;
	}

	public String getChnNotiUrl() {
		return ChnNotiUrl;
	}

	public void setChnNotiUrl(String chnNotiUrl) {
		ChnNotiUrl = chnNotiUrl;
	}

	public String getExReUrl() {
		return ExReUrl;
	}

	public void setExReUrl(String exReUrl) {
		ExReUrl = exReUrl;
	}

	public String getExNotiUrl() {
		return ExNotiUrl;
	}

	public void setExNotiUrl(String exNotiUrl) {
		ExNotiUrl = exNotiUrl;
	}

	public String getCapTm() {
		return CapTm;
	}

	public void setCapTm(String capTm) {
		CapTm = capTm;
	}

	public String getTraceTm() {
		return TraceTm;
	}

	public void setTraceTm(String traceTm) {
		TraceTm = traceTm;
	}

	public String getExeTm() {
		return ExeTm;
	}

	public void setExeTm(String exeTm) {
		ExeTm = exeTm;
	}

	public String getExSttlDt() {
		return ExSttlDt;
	}

	public void setExSttlDt(String exSttlDt) {
		ExSttlDt = exSttlDt;
	}

	public String getNsSttlDt() {
		return NsSttlDt;
	}

	public void setNsSttlDt(String nsSttlDt) {
		NsSttlDt = nsSttlDt;
	}

	public String getOperateType() {
		return OperateType;
	}

	public void setOperateType(String operateType) {
		OperateType = operateType;
	}

	public String getOutTxnId() {
		return OutTxnId;
	}

	public void setOutTxnId(String outTxnId) {
		OutTxnId = outTxnId;
	}

	public String getPstgStatus() {
		return PstgStatus;
	}

	public void setPstgStatus(String pstgStatus) {
		PstgStatus = pstgStatus;
	}

	private String ExNotiUrl;
	private String CapTm;
	private String TraceTm;
	private String ExeTm;
	private String ExSttlDt;
	private String NsSttlDt;
	private String OperateType;
	private String OutTxnId;
	private String PstgStatus;


}
