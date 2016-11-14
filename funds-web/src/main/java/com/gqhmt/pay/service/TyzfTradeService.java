package com.gqhmt.pay.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.conversion.bean.request.CdtrAcct;
import com.gqhmt.conversion.bean.request.ConverBean;
import com.gqhmt.conversion.bean.response.PmtIdResponse;
import com.gqhmt.conversion.bean.response.ReqContentResponse;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
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
    public void createTyzfAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no) throws FssException{
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
        //11020014:开互联网账户
        //11020015:app开户
        //11020017:新版wap开户
        if("11020001".equals(tradeType) || "11020002".equals(tradeType) || "11020003".equals(tradeType)|| "11020004".equals(tradeType)|| "11020005".equals(tradeType) || "11020014".equals(tradeType) || "11020015".equals(tradeType) || "11020017".equals(tradeType)){
            this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3);
        }
        //委托出借开户 2
        if("11020006".equals(tradeType)){
            this.createInvstmentAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,2);
        }
        //11020007:借款人开户（冠e通）
        //11020008:代偿人开户
        //11020009:抵押权人开户
        //11020011:借款人（纯线下）开户
        //11020012:借款人开户（借款系统）
        //11020013:借款代还人开户
        if("11020007".equals(tradeType) || "11020008".equals(tradeType) || "11020009".equals(tradeType) || "11020011".equals(tradeType) || "11020012".equals(tradeType) || "11020013".equals(tradeType)){
            this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,1);
        }
        //标的开户
        if("11020019".equals(tradeType)){
            this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,90);
        }
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
    public void createInternetAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type) throws FssException{
        //开通互联网账户(线上账户)
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
          entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity);
          fssAccountBindService.updateBindAccount(entity);
        }
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
    public void createInvstmentAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3);
        //开通线下出借账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通线下出借应付款账户
        FssAccountBindEntity entity2 = fssAccountBindService.getBindAccountByParam(custId,96);
        if (entity2 == null){//绑定
            entity2 = fssAccountBindService.createFssAccountMapping(custId,96,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,"96",entity);
            fssAccountBindService.updateBindAccount(entity);
        }
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
    public void createLoanAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,3);
        //开通借款人信贷账户
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(custId,busi_type);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,String.valueOf(busi_type),entity);
            fssAccountBindService.updateBindAccount(entity);
        }
        //开通标的账户
        this.createBidAcocunt(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,90);
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
    public void createBidAcocunt(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,Integer busi_type) throws FssException{
        //开通标的账户
        if(bid_id==null) {//如果bid_id为空
            Bid bid = bidService.getBidByContractNo(busiNo);//根据借款合同号查询标的id
            if (bid != null) {
                bid_id = Long.valueOf(bid.getId());
            }
        }
        //判断借款人账户是否开通
        this.createLoanAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,1);
        FssAccountBindEntity entity = fssAccountBindService.getBindAccountByParam(bid_id,90);
        if (entity == null){//绑定
            entity = fssAccountBindService.createFssAccountMapping(bid_id,90,tradeType,seq_no,busiNo);
        }
        if("0".equals(entity.getStatus())){//未开通统一支付账户
            entity = this.createAccount(tradeType,bid_id,custId,custName,custType,certNo,certType,busiNo,orderNo,seq_no,"90",entity);
            fssAccountBindService.updateBindAccount(entity);
        }
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
    public FssAccountBindEntity createAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no,String busi_type,FssAccountBindEntity fssAccountBindEntity) throws FssException{
            ReqContentResponse transContentResponse=null;
            //发送报文调用统一支付开户
            ConverBean bean = new ConverBean();
            bean.setTxnTp(tradeType);//交易类型
            bean.setOrderId(orderNo == null ? "" : orderNo);//业务订单号
            bean.setChnlID("");//线上线下类型
            bean.setCdtr_Nm(custName);//客户姓名
            bean.setCdtr_PorO("10140001");//客户类型
            bean.setCdtrAcct_IdTp(busi_type == null ? "" : String.valueOf(busi_type));//账户类型
            bean.setCdtr_ContractNo(busiNo == null ? "" : busiNo);//合同号
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
        return fssAccountBindEntity;
    }

    /**
     * 充值
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfRecharge(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType,String tradeType,
                             String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(),entity.getBusiType());
        ConverBean bean = new ConverBean();
        //参数传入
        bean.setTxnTp(tradeType);//交易类型
        bean.setBizTp(fundType);//业务类型
        bean.setOrderId(fundOrderEntity.getOrderNo()==null ? "" : fundOrderEntity.getOrderNo());//业务订单号
        bean.setCdtrId("");//通道编号
        bean.setExMerchId("");//通道商户号
        bean.setChnlID("1");//线上线下类型
        bean.setCdtrAcct_Tp(String.valueOf(bindEntity.getBusiType()));//充值账户类型
        bean.setCdtrAcct_Id(bindEntity.getAccNo());//充值账户号
        bean.setDbtrAcct_Tp("");//商户账户类型
        bean.setDbtrAcct_Id("");//商户账户号
        bean.setOprtrID(String.valueOf(entity.getCustId()));//操作人
        bean.setDbtrAcct_Ccy("RMB");//货币类型
        bean.setIntrBkSttlmAmt(String.valueOf(amount));//交易金额
        bean.setCardTp("j");//借贷标识
        bean.setOperateType("");//记账类型
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 提现
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
    public void tyzfWithDraw(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,Integer fundType,String tradeType,
                             String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        ConverBean bean = new ConverBean();
        //参数传入
        bean.setTxnTp(tradeType);//交易类型
        bean.setBizTp(fundType.toString());//业务类型
        bean.setCapTm(String.valueOf(fundOrderEntity.getCreateTime()));//交易日期
        bean.setOrderId(fundOrderEntity.getOrderNo());
        bean.setCdtrId("");
        bean.setExMerchId("");
        bean.setOprtrID(bindEntity.getBusiId().toString());
        bean.setChnlID("1");
        bean.setCdtrAcct_Id("");
        bean.setDbtrAcct_Id("");
        bean.setDbtrAcct_Ccy("");
        bean.setIntrBkSttlmAmt("");
        bean.setCardTp("");
        bean.setOperateType("");
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 转账
     * @param fromEntity
     * @param toEntity
     * @param fundType
     * @param accountType
     * @param amount
     * @param orderNo
     * @param tradeType
     * @throws FssException
     */
    public void tyzfTransfer(FundAccountEntity fromEntity,FundAccountEntity toEntity,Integer fundType,Integer accountType,BigDecimal amount,String orderNo,String tradeType,
            String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FssAccountBindEntity fromAccEntity = fssAccountBindService.getBindAccountByParam(fromEntity.getCustId(), fromEntity.getBusiType());
        FssAccountBindEntity toAccEntity = fssAccountBindService.getBindAccountByParam(toEntity.getCustId(), toEntity.getBusiType());
        ConverBean bean = new ConverBean();
        //参数传入
        bean.setService_id("00003");//充值服务号
        bean.setTxnTp(tradeType);//交易类型
        bean.setBizTp(fundType.toString());//业务类型
        bean.setOrderId(orderNo== null ? "" : orderNo);//业务订单号

        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
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
    public void tyzfFroze(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType,String tradeType,
                          String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        ConverBean bean = new ConverBean();
        //参数传入
        bean.setTxnTp("");
        bean.setBizTp("");
        bean.setOrderId(bindEntity.getAccNo());
        bean.setCapTm("");
        bean.setCdtrId("");
        bean.setExMerchId("");
        bean.setOprtrID("");
        bean.setChnlID("");
        bean.setCdtrAcct_Id("");
        bean.setDbtrAcct_Id("");
        bean.setDbtrAcct_Ccy("");
        bean.setIntrBkSttlmAmt("");
        bean.setCardTp("");
        bean.setOperateType("");
        bean.setProdID(bindEntity.getBusiType().toString());
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
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
    public void tyzfUnFroze(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType, String tradeType,String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId(), entity.getBusiType());
        ConverBean bean = new ConverBean();
        //参数传入
        bean.setTxnTp("");
        bean.setBizTp("");
        bean.setOrderId(bindEntity.getAccNo());
        bean.setCapTm("");
        bean.setCdtrId("");
        bean.setExMerchId("");
        bean.setOprtrID("");
        bean.setChnlID("");
        bean.setCdtrAcct_Id("");
        bean.setDbtrAcct_Id("");
        bean.setDbtrAcct_Ccy("");
        bean.setIntrBkSttlmAmt("");
        bean.setCardTp("");
        bean.setOperateType("");
        bean.setProdID(bindEntity.getBusiType().toString());
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 投标
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
    public void tender(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,String fundType, String tradeType,String lendNo,Long toCustId, String toLendNo,Long loanCustId,String loanNo) throws FssException {
    //// TODO: 2016/11/14

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
