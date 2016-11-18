package com.gqhmt.fss.architect.account.service;

import com.gqhmt.conversion.bean.request.*;
import com.gqhmt.conversion.bean.response.ReqContentResponse;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import com.gqhmt.util.ConvertReportEnum;
import com.gqhmt.util.ConvertUtils;
import com.gqhmt.util.XmlUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.org.mozilla.javascript.internal.regexp.SubString;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/11/10
 * Description:对象参数转换为统一报文
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/11/10  keyulai      1.0     1.0 Version
 */
@Service
public class ConversionService {

    /**
     * 转换成xml统一报文
     * @param bean
     * @return
     * @throws Exception
     */
    public String ObjConversionXml(ConverBean bean) throws Exception{
        //转换为统一报文格式
        Req req = new Req();
        List<RequestHeader> transHeadList = this.createRequestHeader(bean);
        List<RequestMsg> requestMsgList = new ArrayList<RequestMsg>();
        RequestMsg requestMsg = new RequestMsg();
        List<PmtId> pmtIdList=this.createPmtId(bean);
        requestMsg.setRequestMsg(pmtIdList);
        List<OrgnlMsgInfo> orgnlMsgInfoList=this.createOrgnlMsgInfo(bean);
        requestMsg.setOrgnlMsgInfo(orgnlMsgInfoList);
        List<Chnl> chnlList =this.createChnl(bean);
        requestMsg.setChnl(chnlList);
        List<TxnAmtInfo> TxnAmtInfoList=this.createTxnAmtInfo(bean);
        requestMsg.setTxnAmtInfo(TxnAmtInfoList);
        List<RoutInfo> RoutInfoList=this.createRoutInfo(bean);
        requestMsg.setRoutInfo(RoutInfoList);
        List<Dbtr> dbtrlist=this.createDbtr(bean);
        requestMsg.setDbtr(dbtrlist);
        List<Cdtr> cdtrlist=this.createCdtr(bean);
        requestMsg.setCdtr(cdtrlist);
        List<DbtrAcct> dbtrAcctlist=this.createDbtrAcct(bean);
        requestMsg.setDbtrAcct(dbtrAcctlist);
        List<CdtrAcct> cdtrAcctlist=this.createCdtrAcct(bean);
        requestMsg.setCdtrAcct(cdtrAcctlist);
        requestMsgList.add(requestMsg);
        req.setRequestMsg(requestMsgList);

        //统一报文转换
        String bean2XmlString = ConvertUtils.convertToUniteReport(ConvertReportEnum.OBJTOXML, transHeadList,requestMsgList);
        System.out.println("请求报文：" + bean2XmlString);
        return bean2XmlString;
    }

    /**
     * 创建RequestHeader实体对象
     * @param bean
     * @return
     */
    public static List<RequestHeader> createRequestHeader(ConverBean bean){
        List<RequestHeader> transHeadList = new ArrayList<RequestHeader>();
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setSys_node_flag(bean.getSys_node_flag());
        requestHeader.setIs_actual(bean.getIs_actual());
        requestHeader.setReq_sys_id(bean.getReq_sys_id());
        requestHeader.setServ_sys_id(bean.getServ_sys_id());
        requestHeader.setService_id(bean.getService_id());
        requestHeader.setReceive_time(bean.getReceive_time());
        transHeadList.add(requestHeader);
        return transHeadList;
    }

    /**
     * 创建PmtId实体对象
     * @param bean
     * @return
     */
    public List<PmtId> createPmtId(ConverBean bean){
        List<PmtId> pmtIdList = new ArrayList<PmtId>();
        PmtId pmtId = new PmtId();
        pmtId.setOrderId(bean.getOrderId());
        pmtId.setMsgID(bean.getMsgID());
        pmtId.setFrmtVrsn(bean.getFrmtVrsn());
        pmtId.setExTxnId(bean.getExTxnId());
        pmtId.setSplitFlg1(bean.getSplitFlg1());
        pmtId.setSplitFlg2(bean.getSplitFlg2());
        pmtId.setTxnTp(bean.getTxnTp());
        pmtId.setTxnSubTp(bean.getTxnSubTp());
        pmtId.setPmtTp(bean.getPmtTp());
        pmtId.setBizTp(bean.getBizTp());
        pmtId.setBizAttr(bean.getBizAttr());
        pmtId.setProcMd(bean.getProcMd());
        pmtId.setMsgSts(bean.getMsgSts());
        pmtId.setErrDesp(bean.getErrDesp());
        pmtId.setRespCode(bean.getRespCode());
        pmtId.setRespCdDesp(bean.getRespCdDesp());
        pmtId.setReasonFlg(bean.getReasonFlg());
        pmtId.setChkSts(bean.getChkSts());
        pmtId.setSignInfo(bean.getSignInfo());
        pmtId.setRevFlg(bean.getRevFlg());
        pmtId.setRevId(bean.getRevId());
        pmtId.setRevDt(bean.getRevDt());
        pmtId.setCustID(bean.getCustID());
        pmtId.setCardTp(bean.getCardTp());
        pmtId.setBatchIdIn(bean.getBatchIdIn());
        pmtId.setBatchIdOut(bean.getBatchIdOut());
        pmtId.setChnReUrl(bean.getChnReUrl());
        pmtId.setChnNotiUrl(bean.getChnNotiUrl());
        pmtId.setExReUrl(bean.getExReUrl());
        pmtId.setExNotiUrl(bean.getExNotiUrl());
        pmtId.setCapTm(bean.getCapTm());
        pmtId.setTraceTm(bean.getTraceTm());
        pmtId.setExeTm(bean.getExeTm());
        pmtId.setExSttlDt(bean.getExSttlDt());
        pmtId.setNsSttlDt(bean.getNsSttlDt());
        pmtId.setOperateType(bean.getOperateType());
        pmtId.setOutTxnId(bean.getOutTxnId());
        pmtId.setPstgStatus(bean.getPstgStatus());
        pmtIdList.add(pmtId);
        return pmtIdList;
    }

    /**
     * 创建OrgnlMsgInfo实体对象
     * @param bean
     * @return
     */
    public List<OrgnlMsgInfo> createOrgnlMsgInfo(ConverBean bean){
        List<OrgnlMsgInfo> orgnlMsgInfoList = new ArrayList<OrgnlMsgInfo>();
        OrgnlMsgInfo orgnlMsgInfo = new OrgnlMsgInfo();
        orgnlMsgInfo.setOrgnlOrderId(bean.getOrgnlOrderId());
        orgnlMsgInfo.setOrgnlMsgId(bean.getOrgnlMsgId());
        orgnlMsgInfo.setOrgnlTxnTp(bean.getOrgnlTxnTp());
        orgnlMsgInfo.setOrgnlSttlmAmt(bean.getOrgnlSttlmAmt());
        orgnlMsgInfo.setOrgnlSttlmCcy(bean.getOrgnlSttlmCcy());
        orgnlMsgInfoList.add(orgnlMsgInfo);
        return orgnlMsgInfoList;
    }

    /**
     * 创建Chnl实体对象
     * @param bean
     * @return
     */
    public List<Chnl> createChnl(ConverBean bean){
        List<Chnl> chnlList = new ArrayList<Chnl>();
        Chnl chnl = new Chnl();
        chnl.setChnlID(bean.getChnlID());
        chnl.setOprtrID(bean.getOprtrID());
        chnl.setAPPId(bean.getAPPId());
        List<ProdInfo> prodInfolist=new ArrayList<>();
        ProdInfo prodInfo=new ProdInfo();
        prodInfo.setMerchID(bean.getMerchID());
        prodInfo.setProdID(bean.getProdID());
        prodInfo.setPrdInfo(bean.getPrdInfo());
        prodInfo.setPrdQty(bean.getPrdQty());
        prodInfolist.add(prodInfo);
        chnl.setProdInfo(prodInfolist);
        List<FastPayInfo> FastPayInfolist=new ArrayList<>();
        FastPayInfo fastPayInfo=new FastPayInfo();
        fastPayInfo.setCvv(bean.getCvv());
        fastPayInfo.setSmsCd(bean.getSmsCd());
        fastPayInfo.setValidDt(bean.getValidDt());
        fastPayInfo.setTokenCd(bean.getTokenCd());
        fastPayInfo.setPayFlg(bean.getPayFlg());
        fastPayInfo.setAuthId(bean.getAuthId());
        FastPayInfolist.add(fastPayInfo);
        chnl.setFastPayInfo(FastPayInfolist);
        chnlList.add(chnl);
        return chnlList;
    }

    /**
     * 创建TxnAmtInfo实体对象
     * @param bean
     * @return
     */
    public List<TxnAmtInfo> createTxnAmtInfo(ConverBean bean){
        List<TxnAmtInfo> TxnAmtInfoList = new ArrayList<TxnAmtInfo>();
        TxnAmtInfo txnAmtInfo = new TxnAmtInfo();
        txnAmtInfo.setIntrBkSttlmAmt(bean.getIntrBkSttlmAmt());
        txnAmtInfo.setIntrBkSttlmCcy(bean.getIntrBkSttlmCcy());
        txnAmtInfo.setActSttlmAmt(bean.getActSttlmAmt());
        txnAmtInfo.setActSttlmCcy(bean.getActSttlmCcy());
        txnAmtInfo.setRtrdIntrBkSttlmAmt(bean.getRtrdIntrBkSttlmAmt());
        txnAmtInfo.setRtrdIntrBkSttlmCcy(bean.getRtrdIntrBkSttlmCcy());
        TxnAmtInfoList.add(txnAmtInfo);
        return TxnAmtInfoList;
    }

    /**
     * 创建RoutInfo实体对象
     * @param bean
     * @return
     */
    public List<RoutInfo> createRoutInfo(ConverBean bean){
        List<RoutInfo> routInfoList = new ArrayList<RoutInfo>();
        RoutInfo routInfo = new RoutInfo();
        routInfo.setAssCdtrId(bean.getAssCdtrId());
        routInfo.setCdtrId(bean.getCdtrId());
        routInfo.setCdtrNm(bean.getCdtrNm());
        routInfo.setKeyPath(bean.getKeyPath());
        routInfo.setIsChange(bean.getIsChange());
        routInfo.setExMerchId(bean.getExMerchId());
        routInfoList.add(routInfo);
        return routInfoList;
    }

    /**
     * 创建Dbtr实体对象
     * @param bean
     * @return
     */
    public List<Dbtr> createDbtr(ConverBean bean){
        List<Dbtr> dbtrlist = new ArrayList<Dbtr>();
        Dbtr dbtr = new Dbtr();
        dbtr.setNm(bean.getDbtr_Nm());
        dbtr.setPorO(bean.getDbtr_PorO());
        dbtr.setBirthDt(bean.getDbtr_BirthDt());
        dbtr.setPrvcOfBirth(bean.getDbtr_PrvcOfBirth());
        dbtr.setCityOfBirth(bean.getDbtr_CityOfBirth());
        dbtr.setCtryOfBirth(bean.getDbtr_CtryOfBirth());
        dbtr.setIDType(bean.getDbtr_IDType());
        dbtr.setIDNo(bean.getDbtr_IDNo());
        dbtr.setContactNo(bean.getDbtr_ContactNo());
        dbtr.setContractNo(bean.getDbtr_ContractNo());
        dbtrlist.add(dbtr);
        return dbtrlist;
    }

    /**
     * 创建Cdtr实体对象
     * @param bean
     * @return
     */
    public List<Cdtr> createCdtr(ConverBean bean){
        List<Cdtr> cdtrlist = new ArrayList<Cdtr>();
        Cdtr cdtr = new Cdtr();
        cdtr.setNm(bean.getCdtr_Nm());
        cdtr.setPorO(bean.getCdtr_PorO());
        cdtr.setBirthDt(bean.getCdtr_BirthDt());
        cdtr.setPrvcOfBirth(bean.getCdtr_PrvcOfBirth());
        cdtr.setCityOfBirth(bean.getCdtr_CityOfBirth());
        cdtr.setCtryOfBirth(bean.getCdtr_CtryOfBirth());
        cdtr.setCrdtrTp(bean.getCdtr_CrdtrTp());
        cdtr.setIDType(bean.getCdtr_IDType());
        cdtr.setIDNo(bean.getCdtr_IDNo());
        cdtr.setContactNo(bean.getCdtr_ContactNo());
        cdtr.setContractNo(bean.getCdtr_ContractNo());
        cdtrlist.add(cdtr);
        return cdtrlist;
    }

    /**
     * 创建DbtrAcct对象
     * @param bean
     * @return
     */
    public List<DbtrAcct> createDbtrAcct(ConverBean bean){
        List<DbtrAcct> dbtrAcctlist = new ArrayList<DbtrAcct>();
        DbtrAcct dbtrAcct = new DbtrAcct();
        dbtrAcct.setIdTp(bean.getDbtrAcct_IdTp());
        dbtrAcct.setIssrCd(bean.getDbtrAcct_IssrCd());
        dbtrAcct.setIssr(bean.getDbtrAcct_Issr());
        dbtrAcct.setBranch(bean.getDbtrAcct_Branch());
        dbtrAcct.setId(bean.getDbtrAcct_Id());
        dbtrAcct.setShortCard(bean.getDbtrAcct_ShortCard());
        dbtrAcct.setCcy(bean.getDbtrAcct_Ccy());
        dbtrAcct.setNm(bean.getDbtrAcct_Nm());
        dbtrAcct.setTp(bean.getDbtrAcct_Tp());
        dbtrAcctlist.add(dbtrAcct);
        return dbtrAcctlist;
    }

    /**
     * 创建CdtrAcct对象
     * @param bean
     * @return
     */
    public List<CdtrAcct> createCdtrAcct(ConverBean bean){
        List<CdtrAcct> cdtrAcctlist = new ArrayList<CdtrAcct>();
        CdtrAcct cdtrAcct = new CdtrAcct();
        cdtrAcct.setIdTp(bean.getCdtrAcct_IdTp());
        cdtrAcct.setIssrCd(bean.getCdtrAcct_IssrCd());
        cdtrAcct.setIssr(bean.getCdtrAcct_Issr());
        cdtrAcct.setBranch(bean.getCdtrAcct_Branch());
        cdtrAcct.setId(bean.getCdtrAcct_Id());
        cdtrAcct.setCcy(bean.getCdtrAcct_Ccy());
        cdtrAcct.setNm(bean.getCdtrAcct_Nm());
        cdtrAcct.setTp(bean.getCdtrAcct_Tp());
        cdtrAcctlist.add(cdtrAcct);
        return cdtrAcctlist;
    }

    /**
     * 统一报文发送到MQ
     * @param bean
     * @return
     */
    public MessageConvertDto sendAndReceiveMsg(MessageConvertDto bean) throws FssException {
        MessageConvertDto bm=null;
        try {
            AmqSendAndReceive asr = new AmqSender(null, "tradeCheck");//发送
            AmqSendAndReceive receiver = new AmqReceiver("AMQ.TTT3");//解析
            String sendMessage = "";
            //将bean转换成xml统一报文
            MsgObject mo = new MsgObject(MsgObject.initSR);
            bean = mo.getBean4Message(bean);
            sendMessage=mo.toString();
            System.out.println(mo.toString());
            //发送报文
            asr.sendMsg(sendMessage);
            TextMessage resiveMsg=(TextMessage)receiver.receiveMessage();//监听队列中的报文
            //接收报文
            String msg=resiveMsg.getText();
            System.out.print("返回报文："+msg);

            MsgObject mo2 = new MsgObject(msg);
            bm = mo2.getMessge4Bean(MessageConvertDto.class);
        } catch (JMSException e){
            throw new FssException(e.getMessage());
        } catch (FrameException e){
            throw new FssException("");
        } catch (Exception e) {
            throw new FssException(e.getMessage());
        }
        return bm;
    }




}
