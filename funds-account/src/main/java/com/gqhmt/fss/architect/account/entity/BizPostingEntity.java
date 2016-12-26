package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author kyl
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/8/22.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/8/22.  keyulai         1.0     1.0 Version
 */
public class BizPostingEntity {

    private long id;

    //业务单号
    private String orderId;
    //报文唯一标识
    private String msgId;
    //报文版本号
    private String fmtVersion;
    //通道交易单号
    private String txnId;
    //交易拆分标识1
    private String splitFlg1;

    //交易拆分标识2
    private String splitFlg2;

    //交易类型
    private String txnType;

    //交易子类型
    private String txnSubType;

    //支付类型
    private String pmtTp;

     //业务类型
    private String bizTp;

    //同异步处理标识
    private String procMode;

    //报文状态
    private String msgSts;

    //错误描叙
    private String errorDesp;

    //响应吗
    private String respCode;

    //响应描叙
    private String resMsg;

    //是否为客户原因
    private String reasonFlg;

    //第三方复核状态
    private String chkSts;

    //数字签名
    private String signInfo;

    //预约标识
    private String reserveFlg;

    //预约人
    private String reserveId;

    //预约时间
    private Date reserveTm;

    //客户号
    private String custId;

    //借贷分离标识
    private String cardTp;

    //业务系统批次号(来报批次号)
    private String batchIdIn;

    //通道批次号(往报批次号)
    private String batchIdOut;

    //业务端return url
    private String returnUrl;

    //业务nodify url
    private String nodifyUrl;

    //通道端return url
    private String exReturnUrl;

    //通道端nodify url
    private String exNodifyUrl;

    //接收时间
    private Date captureTime;

    //回盘时间
    private Date traceTime;

    //执行时间(发送时间)
    private Date exeTime;

    //通道清算日期
    private Date exSttlDt;

    //平台清算日期
    private Date nsSttlDt;

    //操作类型
    private String operateType;

    private String outTxnId;//发给第三方的点单号(通道发送第三方交易ID)

    private String postingSts;//入账状态

    private String orglOrderid;//原报文业务单号

    private String orglMid;//原报文唯一标识

    private String orglTxntp;//原报文交易类型

    private BigDecimal orglSttlAmt;//原交易金额

    private String orglSttlAmtCcy;//原报文交易币种

    private String chanId;//渠道编号(WEB交易，POS交易还是APP交易等)

    private String operId; //报文处理操作员ID

    private String appSysId;//应用系统ID

    private String product;//产品编号

    private String mercId;//商户编号

    private String bizProdId;//业务产品编号

    private String bizProdInfo;//业务产品描述

    private int bizProdQty;//业务产品数量

    private String cvvInfo; //银行卡cvv信息

    private String smsCode;//短信验证码

    private String valDate;//卡有效期

    private String tokenInfo;//token信息

    private String payFlg;//是否为首次支付标识符

    private String authId;//授权码

    private BigDecimal sttlAmt;//交易金额

    //交易币种
    private String sttlAmtCcy;

       //成功交易金额
    private BigDecimal actualSttlAmt;

   //成功交易币种
    private String actualSttlCcy;

       //被返回交易金额
    private BigDecimal rSttlAmt;

 //被返回加以币种
    private String rSttlCcy;

    //前端指定的贷方支付路由ID
    private String asgnCdtMop;

  //最终贷方支付路由ID
    private String cdtMop;

    //前端指定的贷方支付路由ID
    private String cdtMopName;

    //密钥文件保存的地址路径
    private String keyPath;

    //指定路由是否可变
    private String isChange;

    //借方姓名
    private String dbtrNm;

   //借方是公司客户还是个人客户
    private String dbtrPoFalg;

    //借方生日
    private Date dbtrPBirthDate;

    //借方所在省
    private String dbtrPBirthPrv;

    //借方所在市
    private String dbtrPBirthCity;

    //借方所在国家
    private String dbtrPBirthCtry;

    //借方证件类型(01-身份证，02-护照，03-退伍证等)
    private String dbtrIdTp;

    //借方证件号码
    private String dbtrIdNumber;

   //借方电话号码
    private String dbtrContactno;

     //出借合同号
    private String loadContractNo;

   //贷方姓名
    private String cdtrNm;

    //贷方是公司或个人客户
    private String cdtrPoFlag;

    //贷方个人生日
    private Date cdtr_p_birth_date;

   //贷方所在省
    private String cdtrPBirthPrv;

    //贷方所在市
    private String cdtrPBirthCity;

    //贷方所在国家
    private String cdtrPBirthCtry;

     //贷方是银行还是非银行
    private String cdtrTp;

    //贷方证件类型(01-身份证，02-护照，03-退伍证等)
    private String cdtrIdTp;

    //借方证件号码
    private String cdtrIdNumber;

    //贷方电话号码
    private String cdtrContactno;

   //借款合同号
    private String lenderContractNo;

    //借方账户号类型
    private String dbtrAcctIdtp;

    //借方账户行代码
    private String dbtrIssuerCd;

   //借方账户号发行方
    private String dbtrIssuerNm;

    //借方账户号
    private String dbtrAcctId;

    //借方短卡号
    private String dbtrShortCard;

    //借方账户币种
    private String dbtrAcctCcy;

    //借方账户名称
    private String dbtrAcctNm;

   //借方账户类型
    private String dbtrAcctTp;

    //贷方账户号类型
    private String cdtrAcctIdtp;

    //贷方账户行代码
    private String cdtrIssrCd;

   //贷方账户号发行方
    private String cdtrIssuerNm;

    //贷方开户网点
    private String cdtrBranch;

  //贷方账户号
    private String cdtrAcctId;

     //贷方账户币种
    private String cdtrAcctCcy;

   //贷方账户名称
    private String cdtrAcctNm;

    //贷方账户类型
    private String cdtrAcctTp;

    //贷方通道商户号
    private String exMerchId;

     //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;
    
   //冗余字段1
    private String remark1;
    
    //冗余字段2
    private String remark2;
    
    //冗余字段3
    private String remark3;
    
    //冗余字段4
    private String remark4;
    
    //冗余字段5
    private String remark5;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFmtVersion() {
        return fmtVersion;
    }

    public void setFmtVersion(String fmtVersion) {
        this.fmtVersion = fmtVersion;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getSplitFlg1() {
        return splitFlg1;
    }

    public void setSplitFlg1(String splitFlg1) {
        this.splitFlg1 = splitFlg1;
    }

    public String getSplitFlg2() {
        return splitFlg2;
    }

    public void setSplitFlg2(String splitFlg2) {
        this.splitFlg2 = splitFlg2;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getPmtTp() {
        return pmtTp;
    }

    public void setPmtTp(String pmtTp) {
        this.pmtTp = pmtTp;
    }

    public String getBizTp() {
        return bizTp;
    }

    public void setBizTp(String bizTp) {
        this.bizTp = bizTp;
    }

    public String getProcMode() {
        return procMode;
    }

    public void setProcMode(String procMode) {
        this.procMode = procMode;
    }

    public String getMsgSts() {
        return msgSts;
    }

    public void setMsgSts(String msgSts) {
        this.msgSts = msgSts;
    }

    public String getErrorDesp() {
        return errorDesp;
    }

    public void setErrorDesp(String errorDesp) {
        this.errorDesp = errorDesp;
    }

    public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getReasonFlg() {
        return reasonFlg;
    }

    public void setReasonFlg(String reasonFlg) {
        this.reasonFlg = reasonFlg;
    }

    public String getChkSts() {
        return chkSts;
    }

    public void setChkSts(String chkSts) {
        this.chkSts = chkSts;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public String getReserveFlg() {
        return reserveFlg;
    }

    public void setReserveFlg(String reserveFlg) {
        this.reserveFlg = reserveFlg;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public Date getReserveTm() {
        return reserveTm;
    }

    public void setReserveTm(Date reserveTm) {
        this.reserveTm = reserveTm;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCardTp() {
        return cardTp;
    }

    public void setCardTp(String cardTp) {
        this.cardTp = cardTp;
    }

    public String getBatchIdIn() {
        return batchIdIn;
    }

    public void setBatchIdIn(String batchIdIn) {
        this.batchIdIn = batchIdIn;
    }

    public String getBatchIdOut() {
        return batchIdOut;
    }

    public void setBatchIdOut(String batchIdOut) {
        this.batchIdOut = batchIdOut;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNodifyUrl() {
        return nodifyUrl;
    }

    public void setNodifyUrl(String nodifyUrl) {
        this.nodifyUrl = nodifyUrl;
    }

    public String getExReturnUrl() {
        return exReturnUrl;
    }

    public void setExReturnUrl(String exReturnUrl) {
        this.exReturnUrl = exReturnUrl;
    }

    public String getExNodifyUrl() {
        return exNodifyUrl;
    }

    public void setExNodifyUrl(String exNodifyUrl) {
        this.exNodifyUrl = exNodifyUrl;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public Date getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(Date traceTime) {
        this.traceTime = traceTime;
    }

    public Date getExeTime() {
        return exeTime;
    }

    public void setExeTime(Date exeTime) {
        this.exeTime = exeTime;
    }

    public Date getExSttlDt() {
        return exSttlDt;
    }

    public void setExSttlDt(Date exSttlDt) {
        this.exSttlDt = exSttlDt;
    }

    public Date getNsSttlDt() {
        return nsSttlDt;
    }

    public void setNsSttlDt(Date nsSttlDt) {
        this.nsSttlDt = nsSttlDt;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOutTxnId() {
        return outTxnId;
    }

    public void setOutTxnId(String outTxnId) {
        this.outTxnId = outTxnId;
    }

    public String getPostingSts() {
        return postingSts;
    }

    public void setPostingSts(String postingSts) {
        this.postingSts = postingSts;
    }

    public String getOrglOrderid() {
        return orglOrderid;
    }

    public void setOrglOrderid(String orglOrderid) {
        this.orglOrderid = orglOrderid;
    }

    public String getOrglMid() {
        return orglMid;
    }

    public void setOrglMid(String orglMid) {
        this.orglMid = orglMid;
    }

    public String getOrglTxntp() {
        return orglTxntp;
    }

    public void setOrglTxntp(String orglTxntp) {
        this.orglTxntp = orglTxntp;
    }

    public BigDecimal getOrglSttlAmt() {
        return orglSttlAmt;
    }

    public void setOrglSttlAmt(BigDecimal orglSttlAmt) {
        this.orglSttlAmt = orglSttlAmt;
    }

    public String getOrglSttlAmtCcy() {
        return orglSttlAmtCcy;
    }

    public void setOrglSttlAmtCcy(String orglSttlAmtCcy) {
        this.orglSttlAmtCcy = orglSttlAmtCcy;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getAppSysId() {
        return appSysId;
    }

    public void setAppSysId(String appSysId) {
        this.appSysId = appSysId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMercId() {
        return mercId;
    }

    public void setMercId(String mercId) {
        this.mercId = mercId;
    }

    public String getBizProdId() {
        return bizProdId;
    }

    public void setBizProdId(String bizProdId) {
        this.bizProdId = bizProdId;
    }

    public String getBizProdInfo() {
        return bizProdInfo;
    }

    public void setBizProdInfo(String bizProdInfo) {
        this.bizProdInfo = bizProdInfo;
    }

    public int getBizProdQty() {
        return bizProdQty;
    }

    public void setBizProdQty(int bizProdQty) {
        this.bizProdQty = bizProdQty;
    }

    public String getCvvInfo() {
        return cvvInfo;
    }

    public void setCvvInfo(String cvvInfo) {
        this.cvvInfo = cvvInfo;
    }

    public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public String getPayFlg() {
        return payFlg;
    }

    public void setPayFlg(String payFlg) {
        this.payFlg = payFlg;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public BigDecimal getSttlAmt() {
        return sttlAmt;
    }

    public void setSttlAmt(BigDecimal sttlAmt) {
        this.sttlAmt = sttlAmt;
    }

    public String getSttlAmtCcy() {
        return sttlAmtCcy;
    }

    public void setSttlAmtCcy(String sttlAmtCcy) {
        this.sttlAmtCcy = sttlAmtCcy;
    }

    public BigDecimal getActualSttlAmt() {
        return actualSttlAmt;
    }

    public void setActualSttlAmt(BigDecimal actualSttlAmt) {
        this.actualSttlAmt = actualSttlAmt;
    }

    public String getActualSttlCcy() {
		return actualSttlCcy;
	}

	public void setActualSttlCcy(String actualSttlCcy) {
		this.actualSttlCcy = actualSttlCcy;
	}

	public BigDecimal getrSttlAmt() {
        return rSttlAmt;
    }

    public void setrSttlAmt(BigDecimal rSttlAmt) {
        this.rSttlAmt = rSttlAmt;
    }

    public String getrSttlCcy() {
		return rSttlCcy;
	}

	public void setrSttlCcy(String rSttlCcy) {
		this.rSttlCcy = rSttlCcy;
	}

	public String getAsgnCdtMop() {
        return asgnCdtMop;
    }

    public void setAsgnCdtMop(String asgnCdtMop) {
        this.asgnCdtMop = asgnCdtMop;
    }

    public String getCdtMop() {
        return cdtMop;
    }

    public void setCdtMop(String cdtMop) {
        this.cdtMop = cdtMop;
    }

    public String getCdtMopName() {
        return cdtMopName;
    }

    public void setCdtMopName(String cdtMopName) {
        this.cdtMopName = cdtMopName;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public String getDbtrNm() {
        return dbtrNm;
    }

    public void setDbtrNm(String dbtrNm) {
        this.dbtrNm = dbtrNm;
    }

    public String getDbtrPoFalg() {
        return dbtrPoFalg;
    }

    public void setDbtrPoFalg(String dbtrPoFalg) {
        this.dbtrPoFalg = dbtrPoFalg;
    }

    public Date getDbtrPBirthDate() {
        return dbtrPBirthDate;
    }

    public void setDbtrPBirthDate(Date dbtrPBirthDate) {
        this.dbtrPBirthDate = dbtrPBirthDate;
    }

    public String getDbtrPBirthPrv() {
        return dbtrPBirthPrv;
    }

    public void setDbtrPBirthPrv(String dbtrPBirthPrv) {
        this.dbtrPBirthPrv = dbtrPBirthPrv;
    }

    public String getDbtrPBirthCity() {
        return dbtrPBirthCity;
    }

    public void setDbtrPBirthCity(String dbtrPBirthCity) {
        this.dbtrPBirthCity = dbtrPBirthCity;
    }

    public String getDbtrPBirthCtry() {
        return dbtrPBirthCtry;
    }

    public void setDbtrPBirthCtry(String dbtrPBirthCtry) {
        this.dbtrPBirthCtry = dbtrPBirthCtry;
    }

    public String getDbtrIdTp() {
        return dbtrIdTp;
    }

    public void setDbtrIdTp(String dbtrIdTp) {
        this.dbtrIdTp = dbtrIdTp;
    }

    public String getDbtrIdNumber() {
        return dbtrIdNumber;
    }

    public void setDbtrIdNumber(String dbtrIdNumber) {
        this.dbtrIdNumber = dbtrIdNumber;
    }

    public String getDbtrContactno() {
        return dbtrContactno;
    }

    public void setDbtrContactno(String dbtrContactno) {
        this.dbtrContactno = dbtrContactno;
    }

    public String getLoadContractNo() {
        return loadContractNo;
    }

    public void setLoadContractNo(String loadContractNo) {
        this.loadContractNo = loadContractNo;
    }

    public String getCdtrNm() {
        return cdtrNm;
    }

    public void setCdtrNm(String cdtrNm) {
        this.cdtrNm = cdtrNm;
    }

    public String getCdtrPoFlag() {
        return cdtrPoFlag;
    }

    public void setCdtrPoFlag(String cdtrPoFlag) {
        this.cdtrPoFlag = cdtrPoFlag;
    }

    public Date getCdtr_p_birth_date() {
        return cdtr_p_birth_date;
    }

    public void setCdtr_p_birth_date(Date cdtr_p_birth_date) {
        this.cdtr_p_birth_date = cdtr_p_birth_date;
    }

    public String getCdtrPBirthPrv() {
        return cdtrPBirthPrv;
    }

    public void setCdtrPBirthPrv(String cdtrPBirthPrv) {
        this.cdtrPBirthPrv = cdtrPBirthPrv;
    }

    public String getCdtrPBirthCity() {
        return cdtrPBirthCity;
    }

    public void setCdtrPBirthCity(String cdtrPBirthCity) {
        this.cdtrPBirthCity = cdtrPBirthCity;
    }

    public String getCdtrPBirthCtry() {
        return cdtrPBirthCtry;
    }

    public void setCdtrPBirthCtry(String cdtrPBirthCtry) {
        this.cdtrPBirthCtry = cdtrPBirthCtry;
    }

    public String getCdtrTp() {
        return cdtrTp;
    }

    public void setCdtrTp(String cdtrTp) {
        this.cdtrTp = cdtrTp;
    }

    public String getCdtrIdTp() {
        return cdtrIdTp;
    }

    public void setCdtrIdTp(String cdtrIdTp) {
        this.cdtrIdTp = cdtrIdTp;
    }

    public String getCdtrIdNumber() {
        return cdtrIdNumber;
    }

    public void setCdtrIdNumber(String cdtrIdNumber) {
        this.cdtrIdNumber = cdtrIdNumber;
    }

    public String getCdtrContactno() {
        return cdtrContactno;
    }

    public void setCdtrContactno(String cdtrContactno) {
        this.cdtrContactno = cdtrContactno;
    }

    public String getLenderContractNo() {
        return lenderContractNo;
    }

    public void setLenderContractNo(String lenderContractNo) {
        this.lenderContractNo = lenderContractNo;
    }

    public String getDbtrAcctIdtp() {
        return dbtrAcctIdtp;
    }

    public void setDbtrAcctIdtp(String dbtrAcctIdtp) {
        this.dbtrAcctIdtp = dbtrAcctIdtp;
    }

    public String getDbtrIssuerCd() {
        return dbtrIssuerCd;
    }

    public void setDbtrIssuerCd(String dbtrIssuerCd) {
        this.dbtrIssuerCd = dbtrIssuerCd;
    }

    public String getDbtrIssuerNm() {
        return dbtrIssuerNm;
    }

    public void setDbtrIssuerNm(String dbtrIssuerNm) {
        this.dbtrIssuerNm = dbtrIssuerNm;
    }

    public String getDbtrAcctId() {
        return dbtrAcctId;
    }

    public void setDbtrAcctId(String dbtrAcctId) {
        this.dbtrAcctId = dbtrAcctId;
    }

    public String getDbtrShortCard() {
        return dbtrShortCard;
    }

    public void setDbtrShortCard(String dbtrShortCard) {
        this.dbtrShortCard = dbtrShortCard;
    }

    public String getDbtrAcctCcy() {
        return dbtrAcctCcy;
    }

    public void setDbtrAcctCcy(String dbtrAcctCcy) {
        this.dbtrAcctCcy = dbtrAcctCcy;
    }

    public String getDbtrAcctNm() {
        return dbtrAcctNm;
    }

    public void setDbtrAcctNm(String dbtrAcctNm) {
        this.dbtrAcctNm = dbtrAcctNm;
    }

    public String getDbtrAcctTp() {
        return dbtrAcctTp;
    }

    public void setDbtrAcctTp(String dbtrAcctTp) {
        this.dbtrAcctTp = dbtrAcctTp;
    }

    public String getCdtrAcctIdtp() {
        return cdtrAcctIdtp;
    }

    public void setCdtrAcctIdtp(String cdtrAcctIdtp) {
        this.cdtrAcctIdtp = cdtrAcctIdtp;
    }

    public String getCdtrIssrCd() {
        return cdtrIssrCd;
    }

    public void setCdtrIssrCd(String cdtrIssrCd) {
        this.cdtrIssrCd = cdtrIssrCd;
    }

    public String getCdtrIssuerNm() {
        return cdtrIssuerNm;
    }

    public void setCdtrIssuerNm(String cdtrIssuerNm) {
        this.cdtrIssuerNm = cdtrIssuerNm;
    }

    public String getCdtrBranch() {
        return cdtrBranch;
    }

    public void setCdtrBranch(String cdtrBranch) {
        this.cdtrBranch = cdtrBranch;
    }

    public String getCdtrAcctId() {
        return cdtrAcctId;
    }

    public void setCdtrAcctId(String cdtrAcctId) {
        this.cdtrAcctId = cdtrAcctId;
    }

    public String getCdtrAcctCcy() {
        return cdtrAcctCcy;
    }

    public void setCdtrAcctCcy(String cdtrAcctCcy) {
        this.cdtrAcctCcy = cdtrAcctCcy;
    }

    public String getCdtrAcctNm() {
        return cdtrAcctNm;
    }

    public void setCdtrAcctNm(String cdtrAcctNm) {
        this.cdtrAcctNm = cdtrAcctNm;
    }

    public String getCdtrAcctTp() {
        return cdtrAcctTp;
    }

    public void setCdtrAcctTp(String cdtrAcctTp) {
        this.cdtrAcctTp = cdtrAcctTp;
    }

    public String getExMerchId() {
        return exMerchId;
    }

    public void setExMerchId(String exMerchId) {
        this.exMerchId = exMerchId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}
	
}
