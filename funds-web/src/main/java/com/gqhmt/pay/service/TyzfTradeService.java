package com.gqhmt.pay.service;

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
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssTransRecordReadMapper;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.util.ThirdPartyType;
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

    /**
     * 调用统一支付进行开户
     * @param tradeType 交易类型
     * @param bid_id 标的id(标的开户)
     * @param custId (冠E通客户Id)
     * @param custName 客户名称
     * @param custType 客户类型（个人:10140001，企业:10140002)
     * @param certNo 证件号
     * @param certType 证件类型（01-身份证，02-护照，03-军官证等）
     * @param busiNo 业务编号（标的账户存储借款合同号）
     * @param orderNo 订单号
     * @param seq_no 交易流水号
     * @throws FssException
     */
    public void createTyzfAccount(String tradeType,Long bid_id,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String orderNo,String seq_no) throws FssException{
        String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(tradeType);//设置账户类型
        Long busi_Id=null;//个人开户传（冠E通客户id），标的开户（标的id）
        //如果,线下出借,借款,保理,则业务编号不能为空
        if("10010002".equals(accType) || "10010003".equals(accType) || "10010004".equals(accType) || "10019002".equals(accType) || "10019001".equals(accType)) {
            if(busiNo == null || "".equals(busiNo)){
                throw new FssException("90002016");
            }
        }
        ReqContentResponse transContentResponse=null;
        List list=new ArrayList();
        //判断开户类型（冠E通线上开通出借账户）
        if("10010001".equals(accType)){//线上出借，开通线上出借账户（3）
            list.add(3);
            busi_Id=custId;
            custType="10140001";
        }
        if("10010002".equals(accType)){//线下出借，开通线下出借账户（2）、应付款账户（96）
            list.add(2);
            list.add(96);
            busi_Id=custId;
            custType="10140001";
        }
        if("10010003".equals(accType) || "10019002".equals(accType)){//借款，开通借款账户（1）和标的账户（90）
            list.add(1);
            list.add(90);
            busi_Id=custId;
            custType="10140001";
        }
        if("10019003".equals(accType)){//标的开户90
            list.add(90);
            busi_Id=bid_id;
            custType="10140001";
        }
        for(Object busi_type:list){
            //创建映射账户
            FssAccountBindEntity fssAccountBindEntity = fssAccountBindService.createFssAccountMapping(busi_Id,Integer.valueOf(busi_type.toString()),tradeType,seq_no,busiNo);
            try {
                ConverBean bean = new ConverBean();
                bean.setService_id("0001");//服务号
                bean.setTxnTp(tradeType);//交易类型
                bean.setOrderId(orderNo == null ? "" : orderNo);//业务订单号
                bean.setCdtrId("fuiou_1");//通道编号
                bean.setExMerchId("88721657SUKQ");//通道商户号
                bean.setChnlID("1");//线上线下类型
                bean.setCdtr_Nm("zhangsan");//客户姓名
                bean.setCdtr_PorO(custType == null ? "":custType);//客户类型
                bean.setCdtrAcct_IssrCd("10001");//存管银行编号
                bean.setCdtrAcct_IdTp(busi_type==null ? "":String.valueOf(busi_type));//账户类型
                bean.setCdtr_ContractNo(busiNo == null ? "" : busiNo);//合同号
                bean.setCdtrAcct_Ccy("RMB");//货币类型
                bean.setCdtrAcct_Branch("12345");//机构号
                bean.setMerchID("88721657SUKQ");//商户号
                transContentResponse = conversionService.sendAndReceiveMsg(bean,true);
                //统一支付开户成功返回结果
                List<PmtIdResponse> PmtIdlist= transContentResponse.getRequestMsg().getPmtID();
                String respCode=null;
                String busiId=null;
                String accountId=null;
                if(PmtIdlist.size()>0){
                    for(PmtIdResponse pmtIdResponse:PmtIdlist){
                        respCode=pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
                    }
                    if("0000".equals(respCode)){
                        List<CdtrAcct> cdtrAcctList= transContentResponse.getRequestMsg().getCdtrAcct();
                        if(cdtrAcctList.size()>0){
                            for(CdtrAcct cdtrAcct:cdtrAcctList){
                                accountId= cdtrAcct.getId();//统一支付返回的账号
                            }
                        }
                    }
                    //修改映射账户信息
                    fssAccountBindEntity.setAccNo(accountId);
                    fssAccountBindEntity.setStatus("1");
                    fssAccountBindEntity.setOpenAccTime(String.valueOf(new Date()));
                    fssAccountBindService.updateBindAccount(fssAccountBindEntity);
                }
            }catch (Exception e){
                throw new FssException(e.getMessage());
            }
        }
    }

    /**
     *充值
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param flag
     * @throws FssException
     */
    public void asynchronousCallRecharge(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,int fundType,String tradeType,String flag) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("充值".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 提现
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param flag
     * @throws FssException
     */
    public void asynchronousCallWithHold(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, String tradeType, String flag) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("提现".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 异步调用统一支付记账处理
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param flag 用来区分是充值、提现、转账、投标、满标、回款 交易
     * @throws FssException
     */
    public void asynchronousCallTyzf(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, String tradeType, String flag) throws FssException {
        //TODO: 2016/11/11 需要考虑记账账户类型
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("充值".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        if ("提现".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("转账".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("投标".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("满标".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("回款".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }


}





