package com.gqhmt.conversion.bean.request;

/**
 * 统一支付参数封装实体类
 * @author keyulai
 */
public class ConverBean {
//-----------RequestHeader----------------
	private String sys_node_flag="";
	private String is_actual="";
	private String req_sys_id="";
	private String serv_sys_id="";
	private String service_id="";
	private String receive_time="";
//---------------PmtID-----------------------
	private String OrderId="";
	private String MsgID="";
	private String FrmtVrsn="";
	private String ExTxnId="";
	private String SplitFlg1="";
	private String SplitFlg2="";
	private String TxnTp="";
	private String TxnSubTp="";
	private String PmtTp="";
	private String BizTp="";
	private String BizAttr="";
	private String ProcMd="";
	private String MsgSts="";
	private String ErrDesp="";
	private String RespCode="";
	private String RespCdDesp="";
	private String ReasonFlg="";
	private String ChkSts="";
	private String SignInfo="";
	private String RevFlg="";
	private String RevId="";
	private String RevDt="";
	private String CustID="";
	private String CardTp="";
	private String BatchIdIn="";
	private String BatchIdOut="";
	private String ChnReUrl="";
	private String ChnNotiUrl="";
	private String ExReUrl="";
	private String ExNotiUrl="";
	private String CapTm="";
	private String TraceTm="";
	private String ExeTm="";
	private String ExSttlDt="";
	private String NsSttlDt="";
	private String OperateType="";
	private String OutTxnId="";
	private String PstgStatus="";
//---------------OrgnlMsgInfo-----------------------
	private String OrgnlOrderId="";
	private String OrgnlMsgId="";
	private String OrgnlTxnTp="";
	private String OrgnlSttlmAmt="";
	private String OrgnlSttlmCcy="";
//---------------Chnl-----------------------
	private String ChnlID="";
	private String OprtrID="";
	private String APPId="";
//--------------ProdInfo------------------------
	private String MerchID="";
	private String ProdID="";
	private String PrdInfo="";
	private String PrdQty="";
//----------------FastPayInfo----------------------
	private String Cvv="";
	private String SmsCd="";
	private String ValidDt="";
	private String TokenCd="";
	private String PayFlg="";
	private String AuthId="";
//-----------TxnAmtInfo---------------------------
	private String IntrBkSttlmAmt="";
	private String IntrBkSttlmCcy="";
	private String ActSttlmAmt="";
	private String ActSttlmCcy="";
	private String RtrdIntrBkSttlmAmt="";
	private String RtrdIntrBkSttlmCcy="";

//--------------RoutInfo------------------------
	private String AssCdtrId="";
	private String CdtrId="";
	private String CdtrNm="";
	private String KeyPath="";
	private String IsChange="";
	private String ExMerchId="";
//------------Dbtr--------------------------
	private String dbtr_Nm="";
	private String dbtr_PorO="";
	private String dbtr_BirthDt="";
	private String dbtr_PrvcOfBirth="";
	private String dbtr_CityOfBirth="";
	private String dbtr_CtryOfBirth="";
	private String dbtr_IDType="";
	private String dbtr_IDNo="";
	private String dbtr_ContactNo="";
	private String dbtr_ContractNo="";
//--------------Cdtr------------------------
	private String cdtr_Nm="";
	private String cdtr_PorO="";
	private String cdtr_BirthDt="";
	private String cdtr_PrvcOfBirth="";
	private String cdtr_CityOfBirth="";
	private String cdtr_CtryOfBirth="";
	private String cdtr_CrdtrTp="";
	private String cdtr_IDType="";
	private String cdtr_IDNo="";
	private String cdtr_ContactNo="";
	private String cdtr_ContractNo="";
//--------------DbtrAcct------------------------
	private String dbtrAcct_IdTp="";
	private String dbtrAcct_IssrCd="";
	private String dbtrAcct_Issr="";
	private String dbtrAcct_Branch="";
	private String dbtrAcct_Id="";
	private String dbtrAcct_ShortCard="";
	private String dbtrAcct_Ccy="";
	private String dbtrAcct_Nm="";
	private String dbtrAcct_Tp="";
//--------CdtrAcct------------------------------
	private String cdtrAcct_IdTp="";
	private String cdtrAcct_IssrCd="";
	private String cdtrAcct_Issr="";
	private String cdtrAcct_Branch="";
	private String cdtrAcct_Id="";
	private String cdtrAcct_Ccy="";
	private String cdtrAcct_Nm="";
	private String cdtrAcct_Tp="";


	public String getSys_node_flag() {
		return sys_node_flag;
	}

	public void setSys_node_flag(String sys_node_flag) {
		this.sys_node_flag = sys_node_flag;
	}

	public String getIs_actual() {
		return is_actual;
	}

	public void setIs_actual(String is_actual) {
		this.is_actual = is_actual;
	}

	public String getReq_sys_id() {
		return req_sys_id;
	}

	public void setReq_sys_id(String req_sys_id) {
		this.req_sys_id = req_sys_id;
	}

	public String getServ_sys_id() {
		return serv_sys_id;
	}

	public void setServ_sys_id(String serv_sys_id) {
		this.serv_sys_id = serv_sys_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

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

	public String getDbtr_Nm() {
		return dbtr_Nm;
	}

	public void setDbtr_Nm(String dbtr_Nm) {
		this.dbtr_Nm = dbtr_Nm;
	}

	public String getDbtr_PorO() {
		return dbtr_PorO;
	}

	public void setDbtr_PorO(String dbtr_PorO) {
		this.dbtr_PorO = dbtr_PorO;
	}

	public String getDbtr_BirthDt() {
		return dbtr_BirthDt;
	}

	public void setDbtr_BirthDt(String dbtr_BirthDt) {
		this.dbtr_BirthDt = dbtr_BirthDt;
	}

	public String getDbtr_PrvcOfBirth() {
		return dbtr_PrvcOfBirth;
	}

	public void setDbtr_PrvcOfBirth(String dbtr_PrvcOfBirth) {
		this.dbtr_PrvcOfBirth = dbtr_PrvcOfBirth;
	}

	public String getDbtr_CityOfBirth() {
		return dbtr_CityOfBirth;
	}

	public void setDbtr_CityOfBirth(String dbtr_CityOfBirth) {
		this.dbtr_CityOfBirth = dbtr_CityOfBirth;
	}

	public String getDbtr_CtryOfBirth() {
		return dbtr_CtryOfBirth;
	}

	public void setDbtr_CtryOfBirth(String dbtr_CtryOfBirth) {
		this.dbtr_CtryOfBirth = dbtr_CtryOfBirth;
	}

	public String getDbtr_IDType() {
		return dbtr_IDType;
	}

	public void setDbtr_IDType(String dbtr_IDType) {
		this.dbtr_IDType = dbtr_IDType;
	}

	public String getDbtr_IDNo() {
		return dbtr_IDNo;
	}

	public void setDbtr_IDNo(String dbtr_IDNo) {
		this.dbtr_IDNo = dbtr_IDNo;
	}

	public String getDbtr_ContactNo() {
		return dbtr_ContactNo;
	}

	public void setDbtr_ContactNo(String dbtr_ContactNo) {
		this.dbtr_ContactNo = dbtr_ContactNo;
	}

	public String getDbtr_ContractNo() {
		return dbtr_ContractNo;
	}

	public void setDbtr_ContractNo(String dbtr_ContractNo) {
		this.dbtr_ContractNo = dbtr_ContractNo;
	}

	public String getCdtr_Nm() {
		return cdtr_Nm;
	}

	public void setCdtr_Nm(String cdtr_Nm) {
		this.cdtr_Nm = cdtr_Nm;
	}

	public String getCdtr_PorO() {
		return cdtr_PorO;
	}

	public void setCdtr_PorO(String cdtr_PorO) {
		this.cdtr_PorO = cdtr_PorO;
	}

	public String getCdtr_BirthDt() {
		return cdtr_BirthDt;
	}

	public void setCdtr_BirthDt(String cdtr_BirthDt) {
		this.cdtr_BirthDt = cdtr_BirthDt;
	}

	public String getCdtr_PrvcOfBirth() {
		return cdtr_PrvcOfBirth;
	}

	public void setCdtr_PrvcOfBirth(String cdtr_PrvcOfBirth) {
		this.cdtr_PrvcOfBirth = cdtr_PrvcOfBirth;
	}

	public String getCdtr_CityOfBirth() {
		return cdtr_CityOfBirth;
	}

	public void setCdtr_CityOfBirth(String cdtr_CityOfBirth) {
		this.cdtr_CityOfBirth = cdtr_CityOfBirth;
	}

	public String getCdtr_CtryOfBirth() {
		return cdtr_CtryOfBirth;
	}

	public void setCdtr_CtryOfBirth(String cdtr_CtryOfBirth) {
		this.cdtr_CtryOfBirth = cdtr_CtryOfBirth;
	}

	public String getCdtr_CrdtrTp() {
		return cdtr_CrdtrTp;
	}

	public void setCdtr_CrdtrTp(String cdtr_CrdtrTp) {
		this.cdtr_CrdtrTp = cdtr_CrdtrTp;
	}

	public String getCdtr_IDType() {
		return cdtr_IDType;
	}

	public void setCdtr_IDType(String cdtr_IDType) {
		this.cdtr_IDType = cdtr_IDType;
	}

	public String getCdtr_IDNo() {
		return cdtr_IDNo;
	}

	public void setCdtr_IDNo(String cdtr_IDNo) {
		this.cdtr_IDNo = cdtr_IDNo;
	}

	public String getCdtr_ContactNo() {
		return cdtr_ContactNo;
	}

	public void setCdtr_ContactNo(String cdtr_ContactNo) {
		this.cdtr_ContactNo = cdtr_ContactNo;
	}

	public String getCdtr_ContractNo() {
		return cdtr_ContractNo;
	}

	public void setCdtr_ContractNo(String cdtr_ContractNo) {
		this.cdtr_ContractNo = cdtr_ContractNo;
	}

	public String getDbtrAcct_IdTp() {
		return dbtrAcct_IdTp;
	}

	public void setDbtrAcct_IdTp(String dbtrAcct_IdTp) {
		this.dbtrAcct_IdTp = dbtrAcct_IdTp;
	}

	public String getDbtrAcct_IssrCd() {
		return dbtrAcct_IssrCd;
	}

	public void setDbtrAcct_IssrCd(String dbtrAcct_IssrCd) {
		this.dbtrAcct_IssrCd = dbtrAcct_IssrCd;
	}

	public String getDbtrAcct_Issr() {
		return dbtrAcct_Issr;
	}

	public void setDbtrAcct_Issr(String dbtrAcct_Issr) {
		this.dbtrAcct_Issr = dbtrAcct_Issr;
	}

	public String getDbtrAcct_Branch() {
		return dbtrAcct_Branch;
	}

	public void setDbtrAcct_Branch(String dbtrAcct_Branch) {
		this.dbtrAcct_Branch = dbtrAcct_Branch;
	}

	public String getDbtrAcct_Id() {
		return dbtrAcct_Id;
	}

	public void setDbtrAcct_Id(String dbtrAcct_Id) {
		this.dbtrAcct_Id = dbtrAcct_Id;
	}

	public String getDbtrAcct_ShortCard() {
		return dbtrAcct_ShortCard;
	}

	public void setDbtrAcct_ShortCard(String dbtrAcct_ShortCard) {
		this.dbtrAcct_ShortCard = dbtrAcct_ShortCard;
	}

	public String getDbtrAcct_Ccy() {
		return dbtrAcct_Ccy;
	}

	public void setDbtrAcct_Ccy(String dbtrAcct_Ccy) {
		this.dbtrAcct_Ccy = dbtrAcct_Ccy;
	}

	public String getDbtrAcct_Nm() {
		return dbtrAcct_Nm;
	}

	public void setDbtrAcct_Nm(String dbtrAcct_Nm) {
		this.dbtrAcct_Nm = dbtrAcct_Nm;
	}

	public String getDbtrAcct_Tp() {
		return dbtrAcct_Tp;
	}

	public void setDbtrAcct_Tp(String dbtrAcct_Tp) {
		this.dbtrAcct_Tp = dbtrAcct_Tp;
	}

	public String getCdtrAcct_IdTp() {
		return cdtrAcct_IdTp;
	}

	public void setCdtrAcct_IdTp(String cdtrAcct_IdTp) {
		this.cdtrAcct_IdTp = cdtrAcct_IdTp;
	}

	public String getCdtrAcct_IssrCd() {
		return cdtrAcct_IssrCd;
	}

	public void setCdtrAcct_IssrCd(String cdtrAcct_IssrCd) {
		this.cdtrAcct_IssrCd = cdtrAcct_IssrCd;
	}

	public String getCdtrAcct_Issr() {
		return cdtrAcct_Issr;
	}

	public void setCdtrAcct_Issr(String cdtrAcct_Issr) {
		this.cdtrAcct_Issr = cdtrAcct_Issr;
	}

	public String getCdtrAcct_Branch() {
		return cdtrAcct_Branch;
	}

	public void setCdtrAcct_Branch(String cdtrAcct_Branch) {
		this.cdtrAcct_Branch = cdtrAcct_Branch;
	}

	public String getCdtrAcct_Id() {
		return cdtrAcct_Id;
	}

	public void setCdtrAcct_Id(String cdtrAcct_Id) {
		this.cdtrAcct_Id = cdtrAcct_Id;
	}

	public String getCdtrAcct_Ccy() {
		return cdtrAcct_Ccy;
	}

	public void setCdtrAcct_Ccy(String cdtrAcct_Ccy) {
		this.cdtrAcct_Ccy = cdtrAcct_Ccy;
	}

	public String getCdtrAcct_Nm() {
		return cdtrAcct_Nm;
	}

	public void setCdtrAcct_Nm(String cdtrAcct_Nm) {
		this.cdtrAcct_Nm = cdtrAcct_Nm;
	}

	public String getCdtrAcct_Tp() {
		return cdtrAcct_Tp;
	}

	public void setCdtrAcct_Tp(String cdtrAcct_Tp) {
		this.cdtrAcct_Tp = cdtrAcct_Tp;
	}

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








}
