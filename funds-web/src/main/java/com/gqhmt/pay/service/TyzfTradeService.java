package com.gqhmt.pay.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private FssMappingService fssMappingService;

    /**
     *统一支付进行开户
     * @param tradeType 交易类型
     * @param custId 客户编号
     * @param custName 客户名称
     * @param custType 客户类型
     * @param certNo 证件号
     * @param certType 证件类型
     * @param busiNo 业务编号
     * @param seq_no 业务流水号
     * @param mchn 商户号
     * @return
     * @throws FssException
     */
    public FssAccountBindEntity createTyzfAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mchn,String mobile) throws FssException{
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
        //11029100:数据迁移
        if("11020001".equals(tradeType) || "11020002".equals(tradeType) || "11020003".equals(tradeType)|| "11020004".equals(tradeType)|| "11020005".equals(tradeType) || "11020014".equals(tradeType) || "11020015".equals(tradeType) || "11020017".equals(tradeType) || "11029100".equals(tradeType)){
            entity=this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,3,mchn,mobile);
        }
        //委托出借开户 2
        if("11020006".equals(tradeType)){
            entity=this.createInvstmentAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,2,mchn,mobile);
        }
        //11020007:借款人开户（冠e通）
        //11020008:代偿人开户
        //11020009:抵押权人开户
        //11020011:借款人（纯线下）开户
        //11020012:借款人开户（借款系统）
        //11020013:借款代还人开户
        if("11020007".equals(tradeType) || "11020008".equals(tradeType) || "11020009".equals(tradeType) || "11020011".equals(tradeType) || "11020012".equals(tradeType) || "11020013".equals(tradeType)){
            entity=this.createLoanAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,1,mchn,mobile);
        }
        //创建对公账户 收费账户：11020021、其他开户：11020022
        if("11020020".equals(tradeType) || "11020021".equals(tradeType) || "11020022".equals(tradeType)){
            entity=this.createBusiAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,mchn,mobile);
        }
        return entity;
    }

    /**
     * 开通互联网账户
     * @param tradeType 交易类型
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     */
    public FssAccountBindEntity createInternetAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String mchn,String mobile) throws FssException{
        //开通互联网账户(线上账户)
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户账户类型：互联网账户：30010001
          entity = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,"30010001",entity,mchn);
          fssAccountBindService.updateBindAccount(entity);
        }
        return entity;
    }

    /**
     * 创建线下出借账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     * @throws FssException
     */
    public FssAccountBindEntity createInvstmentAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String mchn,String mobile) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,3,mchn,mobile);
        //开通线下出借账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户类型， 线下出借账户：30010013
            entity = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,"30010013",entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通线下出借应付款账户
        FssAccountBindEntity entity2 = fssAccountBindService.getBindAccountByParam(custId,96);
        if (entity2 == null){//绑定
            entity2 = fssAccountBindService.createFssAccountMapping(custId,96,tradeType,seq_no,busiNo,custName,mobile);
        }
        if("0".equals(entity2.getStatus())){//未开通统一支付账户 出借应付款账户:
            entity2 = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,"30010016",entity2,mchn);
            fssAccountBindService.updateBindAccount(entity2);
        }
        return entity2;
    }

    /**
     * 创建借款人账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     */
    public FssAccountBindEntity createLoanAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String mchn,String mobile) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,3,mchn,mobile);
        //开通借款人信贷账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户 统一支付账户类型，借款账户：30010003
            entity = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,"30010003",entity,mchn);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通标的账户
        this.createBidAcocunt(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,90,mchn,mobile);
        return entity;
    }

    /**
     * 创建标的账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @throws FssException
     */
    public FssAccountBindEntity createBidAcocunt(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mobile) throws FssException{
//        //开通标的账户
//        if(busiNo==null || "".equals(busiNo)) throw new FssException("90002016");
//
//        Long bid_id = bidService.getBidByContractNo(busiNo);//根据借款合同号查询标的id
//
//        //标的账户是否开通
//        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(bid_id,90);
//        if (entity == null){//账户绑定
//            entity = fssAccountBindService.createFssAccountMapping(bid_id,90,tradeType,seq_no,busiNo,custName,mobile);
//        }
//        if("0".equals(entity.getStatus())){//统一支付账户开标的账户
//            entity = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,"30010015",entity,mchn);
//            fssAccountBindService.updateBindAccount(entity);
//        }

        return this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,90,mobile,"30040002","30010015");
    }

    /**
     * 创建对公账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param mchn
     * @throws FssException
     */
    public FssAccountBindEntity createBusiAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mchn,String mobile) throws FssException{
        //开通对公账户
        Integer busi_type=null;
        String accType=null;//统一支付8位账户类型
        if("11020020".equals(tradeType)){//运营账户开户：11020020
            busi_type=70;
            accType="30010009";
        }
        if("11020021".equals(tradeType)){//收费账户开户：11020021
            busi_type=60;
            accType="30010006";
        }
        if("11020022".equals(tradeType)){//其他开户：11020022
            busi_type=50;
            accType="30010017";
        }
        FssAccountBindEntity entity= this.createOnThePublicAcocunt(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,busi_type,mchn,mobile,accType);
        return entity;
    }

    /**
     * 创建对公账户
     * @param tradeType 运营账户开户：11020020，收费账户开户：11020021，//其他开户：11020022
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     * @param mchn
     * @return
     * @throws FssException
     */
     public FssAccountBindEntity createOnThePublicAcocunt(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String mchn,String mobile,String accType) throws FssException{
//         FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
//         if (entity == null){
//             entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
//         }
//         if("0".equals(entity.getStatus())){//未开通统一支付账户
//             entity = this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,accType,entity,mchn);
//             fssAccountBindService.updateBindAccount(entity);
//         }
        return this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no,busi_type,mobile,"30040002",accType);
     }

      public  FssAccountBindEntity createAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String  mobile,String chnlId,String accType) throws FssException{
           FssAccountBindEntity entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
           if("1".equals(entity.getStatus())) return entity;
           String accNO = this.createAccount(tradeType,custName,certNo,certType,busiNo,seq_no,accType,chnlId);
           fssAccountBindService.updateBindAccount(Long.valueOf(entity.getId()),"1",accNO,seq_no);
           return entity;
      }

    /**
     * 统一支付开户
     * @param tradeType
     * @param custName
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     * @return
     * @throws FssException
     */
    public String createAccount(String tradeType,String custName,String certNo,String certType,String busiNo,String seq_no,String busi_type,String chnlId) throws FssException{
        MessageConvertDto bean = new MessageConvertDto();
//        String chnlId=null;
//        if("11020006".equals(tradeType) || "11020011".equals(tradeType) || "11020012".equals(tradeType)){
//            chnlId="30040002";//线下
//        }else {
//            chnlId="30040001";
//        }
        //发送报文调用统一支付开户
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量开户
        bean.setTxnType(GlobalConstants.TYZF_ACCTYPE);//交易类型
        bean.setBizTp(tradeType);//业务类型
        bean.setOrderId(seq_no==null?"":seq_no);//业务订单号
        bean.setChanId(chnlId);//渠道编号(WEB交易，POS交易还是APP交易等)
        bean.setCdtMopName(custName);//客户姓名
        bean.setCdtrPoFlag(GlobalConstants.TYZF_PERSONCUST);
        bean.setCdtMop("97010001");//通道编号
        bean.setCdtrIdTp(certType);//证件类型
        bean.setCdtrIdNumber(certNo);//证件号
        bean.setCdtrPoFlag(GlobalConstants.TYZF_PERSON);//账户主体类型
        bean.setLoanContractNo(busiNo == null ? "" : busiNo);//借款合同号
        bean.setCdtrAcctIdtp(busi_type);//账户类型
        bean.setCdtrAcctCcy("30080001");//货币类型
//        bean.setMerchId(mchn);//商户号
        try {
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            //统一支付开户成功返回结果
            if("0000".equals(bm.getRespCode())){//开户成功
                //统一支付返回的账号
                return       bm.getCdtrAcctId()   ;



            }else{
                throw new FssException("90002044");
            }
        } catch (Exception e) {
            throw new FssException("90002044");
        }
    }

    /**
     * 充值
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfRecharge(Long custId,Integer busiType,BigDecimal amount,String fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = this.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        String chnlId=null;
        if("11030006".equals(tradeType) || "11030015".equals(tradeType) || "11030017".equals(tradeType)){
            chnlId="30040002";//线下
        }else {
            chnlId="30040001";
        }
        //参数传入
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量开户
        bean.setServiceId("0001");
        bean.setTxnType(GlobalConstants.TYZF_RECHARGE);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setChanId(chnlId);//渠道编号(WEB交易，POS交易还是APP交易等)
        bean.setCdtrAcctTp("30010001");//充值账户类型
        bean.setCdtrAcctId(bindEntity.getAccNo());//充值账户号
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            if("!0000".equals(bm.getRespCode())) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 提现
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfWithDraw(Long custId,Integer busiType,BigDecimal amount,Integer fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = this.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        //参数传入
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setServiceId("0001");
        bean.setTxnType(GlobalConstants.TYZF_WITHDRAW);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setDbtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//个人账户记账借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            if("!0000".equals(bm.getRespCode())) throw new FssException("90004035");
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
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setServiceId("0001");
        bean.setTxnType(GlobalConstants.TYZF_TRANSFER);//交易类型
        bean.setOrderId(seqNo== null ? "" : seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setDbtrAcctId(fromEntity.getAccNo());//转出账户
        bean.setCdtrAcctId(toEntity.getAccNo());//转入账户
        bean.setSttlAmtCcy("30080001");//货币类型
        bean.setRSttlAmt(amount);
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识 借：02020001 贷：02020002
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);
        try{
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            if("!0000".equals(bm.getRespCode())) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 冻结
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfFroze(Long custId,Integer busiType,BigDecimal amount,String fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = this.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setServiceId("0001");
        bean.setTxnType(GlobalConstants.TYZF_FRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setCdtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            if("!0000".equals(bm.getRespCode())) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 解冻
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfUnFroze(Long custId,Integer busiType,BigDecimal amount,String fundType, String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = this.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setServiceId("0001");
        bean.setTxnType(GlobalConstants.TYZF_UNFRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setCdtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        try{
            MessageConvertDto bm=conversionService.sendAndReceiveMsg(bean);
            if("!0000".equals(bm.getRespCode())) throw new FssException("90004035");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            throw new FssException("90004035");
        }
    }

    /**
     * 检查账户是否已经开户
     * @param busiId
     * @param busiType
     */
    public FssAccountBindEntity checkBindAccount(Long busiId,Integer busiType) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(busiId, busiType);
        if (bindEntity == null) throw new FssException("90004034");//账户未绑定
        if (bindEntity != null) {
            if (!"1".equals(bindEntity.getStatus())) throw new FssException("90004034");
        }
        return bindEntity;
    }

     /** 投标
     * @param busiId 业务id（冠E通客户的id，标的账户标的表的id）
     * @param busiType 账户类型
     * @param amount    //实际出账金额
     * @param boundsAmount  //红包金额
     * @param tradeType //交易类型
     * @param bidId     //标的Id
     * @param seqNo     //流水号
     * @throws FssException
     */
    public void tender(Long busiId,Integer busiType,BigDecimal amount,BigDecimal boundsAmount,String tradeType,String bidId,String seqNo) throws FssException {
        this.tyzfTransfer(busiId,busiType,Long.valueOf(bidId),90,amount,tradeType,seqNo);
        //红包账户
        if (boundsAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity fromEntity=null;
            //获取所有运营商的红包账户，（通过custId关联红包账户表查询）
            List<FssMappingBean> mappinglist=fssMappingService.getMappingListByType("10010006");
            if(mappinglist.size()>0){
                for(FssMappingBean  entity:mappinglist){
                    if (entity.getAmount().compareTo(boundsAmount)>=0){//账户余额大于红包金额，则从该账户扣除红包金额
                        fromEntity=fundAccountService.getFundAccountById(entity.getAccountId());
                        break;
                    }
                }
            }
            this.tyzfTransfer(fromEntity.getCustId(),70,Long.valueOf(bidId),90,boundsAmount,tradeType,seqNo);
        }
    }

    /**
     * jhz
     * 满标
     * @param toAccEntity
     * @param amount
     * @param tradeType
     * @param bidId
     * @param seqNo
     * @throws FssException
     */
    public void fullStandard(String bidId,FundAccountEntity toAccEntity,BigDecimal amount,
                             String tradeType,String seqNo) throws FssException {
        //// TODO: 2016/11/14
        this.tyzfTransfer(Long.valueOf(bidId),90,toAccEntity.getCustId(),toAccEntity.getBusiType(),amount,tradeType,seqNo);
    }

    /**
     * jhz
     * 回款
     * @param fromCustId
     * @param fromAccountType
     * @param toCustId
     * @param toAccountType
     * @param amount
     * @param tradeType
     * @param seqNo
     * @throws FssException
     */
    public void backSection(Long fromCustId,Integer fromAccountType,Long toCustId,Integer toAccountType,BigDecimal amount,String tradeType,String seqNo) throws FssException {
    //// TODO: 2016/11/14

    }

}
