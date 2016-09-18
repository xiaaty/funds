package com.gqhmt.fss.architect.accounting.service;

import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssCheckAccountingReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssCheckAccountingWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/1
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/1  jhz     1.0     1.0 Version
 */
@Service
public class FssCheckAccountingService {

    @Resource
    private FssCheckAccountingReadMapper fssCheckAccountingReadMapper;

    @Resource
    private FssCheckAccountingWriteMapper fssCheckAccountingWriteMapper;

    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    /**
     * jhz
     * 添加
     * @param checkAccountingEntity
     */
    public void insert(FssCheckAccountingEntity checkAccountingEntity)throws FssException{
        fssCheckAccountingWriteMapper.insertSelective(checkAccountingEntity);
    }

    /***
     * jhz
     * 批量添加
     * @param list
     */
    public void insertList(List<FssCheckAccountingEntity> list)throws FssException{
        fssCheckAccountingWriteMapper.insertList(list);
    }
    /**
     * jhz
     * 修改
     * @param checkAccountingEntity
     */
    public void update(FssCheckAccountingEntity checkAccountingEntity)throws FssException{
        checkAccountingEntity.setModifyTime(new Date());
        fssCheckAccountingWriteMapper.updateByPrimaryKey(checkAccountingEntity);
    }
    /**
     * jhz
     * 列表
     * @param map
     */
    public List<FssCheckAccountingEntity> list(Map<String,String> map){
        Map<String, String> map2= Maps.newHashMap();
        if (map != null) {
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");
            map2.put("orderNo",map.get("orderNo"));
            map2.put("type",map.get("type"));
            map2.put("abnormalState",map.get("abnormalState"));
            map2.put("userName",map.get("userName"));
            map2.put("accName",map.get("accName"));
            map2.put("contractNo", map.get("contractNo"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
       return fssCheckAccountingReadMapper.queryList(map2);
    }

    public FssCheckAccountingEntity createChecking(String orderNo, String tradeTime, String accountingNo,
                                                   String accountingTime, String amount,String custId,String accNo,
                                                   String accName,String userName,String remark,String status,String tradeType,
                                                   String accountingResult,String accountingState)throws FssException{
        FssCheckAccountingEntity entity=new FssCheckAccountingEntity();
        entity.setOrderNo(orderNo);
        entity.setTradeTime(tradeTime);
        entity.setAccountingNo(accountingNo);
        entity.setAccountingTime(accountingTime);
        entity.setAmount(amount);
        entity.setCustId(custId);
        entity.setAccNo(accNo);
        entity.setAccName(accName);
        entity.setUserName(userName);
        entity.setRemark(remark);
        entity.setStatus(status);
        entity.setTradeType(tradeType);
        entity.setAccountingResult(accountingResult);
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setAccountingStatus(accountingState);//98010002未对帐，98010001已对账
        return  entity;
    }

    /**
     * jhz
     * @param entity
     * @return
     * @throws FssException
     */
    public FssCheckAccountingEntity createChecking(FssCheckAccountingEntity entity,String type)throws FssException{
        entity.setTradeType(type);
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setAccountingStatus("98010002");//98010002未对帐，98010001已对账
        return  entity;
    }
    /**
     * jhz
     * 查询充值体现列表
     */
    public List<FssCheckAccountingEntity> queryWithList(){

        return fssCheckAccountingReadMapper.queryWithList();
    }

    public void checkAccounting() throws  FssException{
        List<FssCheckAccountingEntity> list=this.queryWithList();
        for (FssCheckAccountingEntity entity:list) {
            List<FundSequenceEntity> sequences= fundSequenceService.queryByOrderNo(entity.getOrderNo());
            if(CollectionUtils.isNotEmpty(sequences)){
                if(sequences.size()>1){
                    this.updateNormalOrder(entity,"98010001","98080002");
                }else{
                    if(new BigDecimal(entity.getAmount()).compareTo(sequences.get(0).getAmount())==0){
                        this.updateNormalOrder(entity,"98010001","98080001");
                    }else {
                        this.updateNormalOrder(entity,"98010001","98080002");
                    }
                }
            }else{
                this.updateNormalOrder(entity,"98010001","98080002");
            }
        }
    }
    /**
     * jhz
     * 添加状态并修改对账表
     * @param entity
     * @param accountingStatus 98010001已对账，98010002未对帐
     * @param abnormalstate 异常状态,'98080001'正常,'98080002'异常
     * @throws FssException
     */
    public void updateNormalOrder(FssCheckAccountingEntity entity,String accountingStatus,String abnormalstate)throws FssException{
        entity.setAccountingStatus(accountingStatus);
        entity.setAbnormalState(abnormalstate);
        this.update(entity);
    }

    public List<Map<String,String>> getfuiouTradeCz(Long custid,String startTime,String endTime) throws  FssException{
        FundAccountEntity entity= fundAccountService.getFundAccount(custid, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        return this.getFuiouTradeCz(entity,startTime,endTime);
    }

    public List<Map<String,String>> getfuiouTradeCz(String  userName,String startTime,String endTime) throws FssException{
        FundAccountEntity entity= fundAccountService.getFundAccount(userName, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        return this.getFuiouTradeCz(entity,startTime,endTime);
    }

    public List<Map<String,String>> getFuiouTradeCz(FundAccountEntity entity,String startTime,String endTime)throws  FssException{
        List<Map<String,String>> list = null;
        CommandResponse response=null;
        try{
            response =paySuperByFuiou.tradeCZZTXQuery("PW11",entity,startTime,endTime,1);
            Map<String, Object> map=response.getMap();
            if(map == null || map.get("results") == null || map.get("total_number") == null ||  Integer.parseInt(map.get("total_number").toString())==0){
                return null;
            }

            if(Integer.parseInt(map.get("total_number").toString())==1) {
                Map<String, Object> resultsMap = (Map<String, Object>) map.get("results");
                Map<String, String> resultMap = (Map<String, String>) resultsMap.get(("result"));
                list = new ArrayList<>();
                list.add(resultMap);
                return list;
            }

            list  = (List<Map<String,String>>)map.get("results");
            return list;
        }catch (FssException e){
            LogUtil.error(this.getClass(),e.getMessage());

        }

        return null;
    }
}
