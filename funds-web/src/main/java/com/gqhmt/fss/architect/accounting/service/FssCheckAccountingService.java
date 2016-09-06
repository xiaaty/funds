package com.gqhmt.fss.architect.accounting.service;

import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssCheckAccountingReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssCheckAccountingWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                   String accountingResult)throws FssException{
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
        entity.setAccountingStatus("98010002");//98010002未对帐，98010001已对账
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

}
