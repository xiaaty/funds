package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.fetchService.FetchDataService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.fss.architect.fuiouFtp.service.BidSupper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/7/14 11:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/7/14  于泳      1.0     1.0 Version
 */
public class BidSupper {

    @Resource
    private FetchDataService fetchDataService;

    protected Map<String,String> titleMap = new ConcurrentHashMap<>();

    protected Map<String,Bid> bidMap = new ConcurrentHashMap<>();

    protected Map<String,Tender> tenderMap = new ConcurrentHashMap<>();

    protected Map<String,List<Tender>> tenderListMap = new ConcurrentHashMap<>();

    protected Map<String,RepaymentBean> bidRepaymentMap = new ConcurrentHashMap<>();

    protected Map<String,List<RepaymentBean>> bidRepaymentListMap = new ConcurrentHashMap<>();

    protected void initTender(String  contractId,String  type) throws FssException {
        this.initBid(contractId,type);
        this.initTitle(contractId,type);
        this.initTenderList(contractId,type);
    }



    protected void initRepay(String  contractId,String  type,String bidRepaymentId) throws FssException {
        this.initBid(contractId,type);
        this.initTitle(contractId,type);
        this.initRepayList(bidRepaymentId);
    }

    private void initBid(String  contractId,String  type) throws FssException {
        if(getBid(contractId) != null){
            return;
        }
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",contractId);
        paramMap.put("type",type);
        Bid bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
        bidMap.put(contractId,bid);
    }

    private void initTitle(String  contractId,String  type) throws FssException {
        if(getTitle(contractId) != null){
            return;
        }
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",contractId);
        paramMap.put("type",type);
        String title =  UrlConnectUtil.sendDataReturnString("findProductName",paramMap);
        this.titleMap.put(contractId,title);

    }

    private void initTenderList(String  contractId,String  type) throws FssException {
        if(getTenderList( contractId )!= null){
            return;
        }
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",contractId);
        paramMap.put("type",type);
        List<Tender> list = fetchDataService.featchData(Tender.class,"tenderList",paramMap);
        tenderListMap.put(contractId,list);
        for(Tender tender:list){
            tenderMap.put(contractId+"_"+tender.getId(),tender);
        }
    }

    private void  initRepayList(String repayId) throws FssException {
        if(getBidRepayment(repayId) != null) return;
        Map<String,String > repParamMap = new HashMap<>();
        repParamMap.put("id",repayId);
        List<RepaymentBean> list  = fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
        bidRepaymentListMap.put(repayId,list);
        for(RepaymentBean repaymentBean:list){
            bidRepaymentMap.put(repayId+"_"+repaymentBean.getId(),repaymentBean);
        }
    }

    protected String  getTitle(String contractId){
        return titleMap.get(contractId);
    }

    protected Bid getBid(String contractId){
        return bidMap.get(contractId);
    }

    protected Tender getTender(String contractId,String id){
        return tenderMap.get(contractId+"_"+id);
    }

    protected List<Tender> getTenderList(String contractId){
        return tenderListMap.get(contractId);
    }


    protected List<RepaymentBean> getBidRepayment(String repaymentId){
        return bidRepaymentListMap.get(repaymentId);
    }

}
