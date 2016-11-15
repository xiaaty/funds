package com.gqhmt.pay.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.conversion.bean.request.CdtrAcct;
import com.gqhmt.conversion.bean.request.ConverBean;
import com.gqhmt.conversion.bean.response.PmtIdResponse;
import com.gqhmt.conversion.bean.response.ReqContentResponse;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Filename:    com.gqhmt.pay.service.TradeRecordService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/12
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/12  keyulai      1.0     1.0 Version
 */

@Service
public class TyzfTradeService {

    @Resource
    private ConversionService conversionService;
    @Resource
    private FssAccountBindService fssAccountBindService;
    @Resource
    private BidService bidService;

    /**
     * 统一支付进行开户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @throws FssException
     */
    public FssAccountBindEntity createTyzfAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,String mchn) throws FssException{
        FssAccountBindEntity entity=null;
        String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(tradeType);//设置账户类型
        //如果,线下出借,借款,保理,则业务编号不能为空
        if("10010002".equals(accType) || "10010003".equals(accType) || "10010004".equals(accType) || "10019002".equals(accType) || "10019001".equals(accType) || "10019003".equals(accType)) {
            if(busiNo == null || "".equals(busiNo)){
                throw new FssException("90002016");
            }
        }
        //11020001:wap开户
        //11020002:web开户
        //11020003:安卓开户
        //11020004:微信开户
        //11020005:ios开户
        //11020014:代开互联网账户
        //11020015:app开户
        //11020017:新版wap开户
        if("11020001".equals(tradeType) || "11020002".equals(tradeType) || "11020003".equals(tradeType)|| "11020004".equals(tradeType)|| "11020005".equals(tradeType) || "11020014".equals(tradeType) || "11020015".equals(tradeType) || "11020017".equals(tradeType)){
            entity=this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3,mchn);
        }
        //委托出借开户 2
        if("11020006".equals(tradeType)){
            entity=this.createInvstmentAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,2,mchn);
        }
        //11020007:借款人开户（冠e通）
        //11020008:代偿人开户
        //11020009:抵押权人开户
        //11020011:借款人（纯线下）开户
        //11020012:借款人开户（借款系统）
        //11020013:借款代还人开户
        if("11020007".equals(tradeType) || "11020008".equals(tradeType) || "11020009".equals(tradeType) || "11020011".equals(tradeType) || "11020012".equals(tradeType) || "11020013".equals(tradeType)){
            this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,1,mchn);
        }
        //标的开户
        if("11020019".equals(tradeType)){
            this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,90,mchn);
        }
        return entity;
    }

    /**
     * 开通互联网账户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @param busi_type
     */
    public FssAccountBindEntity createInternetAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type,String mchn) throws FssException{
        //开通互联网账户(线上账户)
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
          entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity,mchn);
          fssAccountBindService.updateBindAccount(entity);
        }
        return entity;
    }

    /**
     * 创建线下出借账户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @param busi_type
     * @throws FssException
     */
    public FssAccountBindEntity createInvstmentAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type,String mchn) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3,mchn);
        //开通线下出借账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通线下出借应付款账户
        FssAccountBindEntity entity2 = fssAccountBindService.getBindAccountByParam(custId,96);
        if (entity2 == null){//绑定
            entity2 = fssAccountBindService.createFssAccountMapping(custId,96,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,"96",entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        return entity;
    }

    /**
     * 创建借款人账户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @param busi_type
     */
    public FssAccountBindEntity createLoanAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type,String mchn) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3,mchn);
        //开通借款人信贷账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通标的账户
        this.createBidAcocunt(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,90,mchn);
        return entity;
    }

    /**
     * 创建标的账户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @param busi_type
     * @throws FssException
     */
    public FssAccountBindEntity createBidAcocunt(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type,String mchn) throws FssException{
        //开通标的账户
        if(bid_id==null) {//如果bid_id为空
            Bid bid = bidService.getBidByContractNo(busiNo);//根据借款合同号查询标的id
            if (bid != null) {
                bid_id = Long.valueOf(bid.getId());
            }
        }
        //判断借款人账户是否开通
        this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,1,mchn);
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(bid_id,90);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(bid_id,90,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,"90",entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        return entity;
    }
    public void createBusiAccount() throws FssException{
        //开通对公账户

    }

    /**
     * 统一支付开户
     * @param tradeType
     * @param bid_id
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param orderNo
     * @param seq_no
     * @param busi_type
     * @param fssAccountBindEntity
     * @return
     * @throws FssException
     */
    public FssAccountBindEntity createAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,String busi_type,FssAccountBindEntity fssAccountBindEntity,String mchn) throws FssException{
            ReqContentResponse transContentResponse=null;
            String chnlId=null;
            if("11020006".equals(tradeType) || "11020011".equals(tradeType) || "11020012".equals(tradeType)){
                chnlId="30040002";//线下
            }else {
                chnlId="30040001";
            }
            String CapTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            //发送报文调用统一支付开户
            ConverBean bean = new ConverBean();
            bean.setTxnTp(GlobalConstants.TYZF_ACCTYPE);//交易类型
            bean.setBizTp(tradeType);//业务类型
            bean.setOrderId(seq_no==null?"":seq_no);//业务订单号
            bean.setChnlID(chnlId);//线上线下类型(pc端、手机端属于线上) 30010001 线上；30040002 线下
            bean.setCdtr_Nm(custName);//客户姓名
            bean.setCdtr_PorO(GlobalConstants.TYZF_PERSONCUST);
            bean.setCdtrId("97010001");//通道编号
            bean.setCdtr_IDType(certType);//证件类型
            bean.setCdtr_IDNo(certNo);//证件号
            bean.setCdtr_PorO(GlobalConstants.TYZF_PERSON);//账户主体类型
            bean.setCdtr_ContractNo(busiNo == null ? "" : busiNo);//合同号
            bean.setCdtrAcct_IdTp(String.valueOf(busi_type));//账户类型
            bean.setCdtrAcct_Ccy("30080001");//人民币
            bean.setMerchID(mchn);//商户号
        try{
            transContentResponse = conversionService.sendAndReceiveMsg(bean, true);
            //统一支付开户成功返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            String busiId = null;
            String accountId = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
                if ("0000".equals(respCode)) {
                    List<CdtrAcct> cdtrAcctList = transContentResponse.getRequestMsg().getCdtrAcct();
                    if (cdtrAcctList.size() > 0) {
                        for (CdtrAcct cdtrAcct : cdtrAcctList) {
                            accountId = cdtrAcct.getId();//统一支付返回的账号
                        }
                    }
                    fssAccountBindEntity.setAccNo(accountId);
                    fssAccountBindEntity.setStatus("1");
                    fssAccountBindEntity.setOpenAccTime(String.valueOf(new Date()));
                }else {//失败
                    //修改映射账户信息
                    fssAccountBindEntity.setAccNo(null);
                    fssAccountBindEntity.setStatus("0");
                }
            }
        }catch (Exception e){
            //修改映射账户信息
            fssAccountBindEntity.setAccNo(null);
            fssAccountBindEntity.setStatus("0");
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90002044");
        }
        return fssAccountBindEntity;
    }

    /**
     * 充值
     * @param entity
     * @param amount
     * @param orderEntity
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfRecharge(FundAccountEntity entity,BigDecimal amount, FundOrderEntity orderEntity, String fundType, String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(),entity.getBusiType());
        if(bindEntity==null) throw new FssException("90004034");//账户未绑定
        ConverBean bean = new ConverBean();
        //参数传入
        String capTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bean.setTxnTp(GlobalConstants.TYZF_RECHARGE);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCapTm(capTm);
        bean.setCdtrId("90070001");//通道编号
        bean.setChnlID("1");//线上线下类型
        bean.setCdtrAcct_Tp(String.valueOf(bindEntity.getBusiType()));//充值账户类型
        bean.setCdtrAcct_Id(bindEntity.getAccNo());//充值账户号
        bean.setOprtrID(String.valueOf(entity.getCustId()));//操作人
        bean.setDbtrAcct_Ccy("30080001");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
            //返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
            }
            if(!"0000".equals(respCode)) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 提现
     * @param entity
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfWithDraw(FundAccountEntity entity,BigDecimal amount,Integer fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        if(bindEntity==null) throw new FssException("90004034");//账户未绑定
        ConverBean bean = new ConverBean();
        //参数传入
        String capTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bean.setTxnTp(GlobalConstants.TYZF_WITHDRAW);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCapTm(capTm);
        bean.setCdtrId("90070001");//通道编号
        bean.setDbtrAcct_Id(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcct_Ccy("RMB");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//个人账户记账借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
            //返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
            }
            if(!"0000".equals(respCode)) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    public void tyzfTransfer(Long fromCustId,Integer fromAccountType,Long toCustId,Integer toAccountType,BigDecimal amount,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity fromBindEntity = fssAccountBindService.getBindAccountByParam(fromCustId,fromAccountType);
        FssAccountBindEntity toBindEntity = fssAccountBindService.getBindAccountByParam(toCustId,toAccountType);
        this.tyzfTransfer(fromBindEntity,toBindEntity,amount,tradeType,seqNo);
    }

    /**
     * 转账重载
     * @param fromEntity
     * @param toEntity
     * @param amount
     * @param tradeType
     * @param seqNo
     * @throws FssException
     */
    public void tyzfTransfer(FssAccountBindEntity fromEntity,FssAccountBindEntity toEntity,BigDecimal amount,String tradeType,String seqNo) throws FssException {
        if(fromEntity==null || toEntity==null) throw new FssException("90004034");
        ConverBean bean = new ConverBean();
        //参数传入
        String capTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bean.setTxnTp(GlobalConstants.TYZF_TRANSFER);//交易类型
        bean.setCapTm(capTm);//交易日期
        bean.setOrderId(seqNo== null ? "" : seqNo);//业务订单号
        bean.setCdtrId("90070001");//通道编号
        bean.setDbtrAcct_Id(fromEntity.getAccNo());//转出账户
        bean.setCdtrAcct_Id(toEntity.getAccNo());//转入账户
        bean.setIntrBkSttlmCcy("30080001");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识 借：02020001 贷：02020002
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);
        try{
            ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
            //返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
            }
            if(!"0000".equals(respCode)) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 冻结
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfFroze(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        if(bindEntity==null) throw new FssException("90004034");
        ConverBean bean = new ConverBean();
        //参数传入
        String capTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bean.setTxnTp(GlobalConstants.TYZF_FRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCapTm(capTm);//交易日期
        bean.setCdtrId("90010007");//通道编号
        bean.setCdtrAcct_Id(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcct_Ccy("30080001");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
            //返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
            }
            if(!"0000".equals(respCode)) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 解冻
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfUnFroze(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType, String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        if(bindEntity==null) throw new FssException("90004034");
        ConverBean bean = new ConverBean();
        //参数传入
        String capTm= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bean.setTxnTp(GlobalConstants.TYZF_UNFRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCapTm(capTm);//交易日期
        bean.setCdtrId("90010007");//通道编号
        bean.setCdtrAcct_Id(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcct_Ccy("30080001");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
            //返回结果
            List<PmtIdResponse> PmtIdlist = transContentResponse.getRequestMsg().getPmtID();
            String respCode = null;
            if (PmtIdlist.size() > 0) {
                for (PmtIdResponse pmtIdResponse : PmtIdlist) {
                    respCode = pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                }
            }
            if(!"0000".equals(respCode)) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 投标
     * @param entity    //出账账户
     * @param amount    //实际出账金额
     * @param boundsAmount  //红包金额
     * @param fundType  //账户类型
     * @param tradeType //交易类型
     * @param bidId     //标的Id
     * @param seqNo     //流水号
     * @param summary     //摘要
     * @throws FssException
     */
    public void tender(FundAccountEntity entity,BigDecimal amount,BigDecimal boundsAmount,
                       Integer fundType, String tradeType,String bidId,String seqNo,
                       String orderNo,String summary) throws FssException {
    //// TODO: 2016/11/14
        //出借人账户
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        //标的账户
        FssAccountBindEntity bidEntity = fssAccountBindService.getBindAccountByParam(Long.valueOf(bidId),90);
//        this.tyzfTransfer(bindEntity.getAccNo(),bidEntity.getAccNo(),fundType,amount,orderNo,tradeType,"","",seqNo,summary);
        //红包账户
        if(BigDecimal.ZERO.compareTo(boundsAmount)>0){
            FssAccountBindEntity boundEntity = fssAccountBindService.getBindAccountByParam(Long.valueOf(bidId),90);
        }


    }

    /**
     * 满标
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param lendNo
     * @param toCustId
     * @param toLendNo
     * @param loanCustId
     * @param loanNo
     * @throws FssException
     */
    public void fullStandard(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType, String tradeType,String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
    //// TODO: 2016/11/14

    }

    /**
     * 回款
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param lendNo
     * @param toCustId
     * @param toLendNo
     * @param loanCustId
     * @param loanNo
     * @throws FssException
     */
    public void backSection(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType, String tradeType,String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
    //// TODO: 2016/11/14

    }
}
