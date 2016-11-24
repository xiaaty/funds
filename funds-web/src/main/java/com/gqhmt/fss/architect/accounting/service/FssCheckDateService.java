package com.gqhmt.fss.architect.accounting.service;

import com.google.common.collect.Maps;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import com.gqhmt.fss.architect.accounting.mapper.read.FssCheckDateReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssCheckDateWriteMapper;
import org.apache.commons.lang3.StringUtils;
import com.gqhmt.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
public class FssCheckDateService {

    @Resource
    private FssCheckDateReadMapper fssCheckDateReadMapper;

    @Resource
    private FssCheckDateWriteMapper fssCheckDateWriteMapper;

    /**
     * wanggp
     * 更新已对账日期状态
     * @param checkDate
     * @return
     */
    public void updateInputUserState(FssCheckDate checkDate) throws FssException {
        fssCheckDateWriteMapper.updateInputUserState(checkDate);
    }
    /**
     * jhz
     * 添加
     * @param checkDate
     * @throws FssException
     */
    public void insert(FssCheckDate checkDate)throws FssException{
        fssCheckDateWriteMapper.insert(checkDate);
    }
    /**
     * jhz
     * 修改
     * @param checkDate
     * @throws FssException
     */
    public void update(FssCheckDate checkDate)throws FssException{
        fssCheckDateWriteMapper.updateByPrimaryKey(checkDate);
    }
    /**
     * jhz
     * 根据日期修改状态
     * @param checkDate
     * @throws FssException
     */
    public int updateOrderUserState(FssCheckDate checkDate)throws FssException{
        FssCheckDate fssCheckDate = new FssCheckDate();
        fssCheckDate.setOrderDate(checkDate.getOrderDate());
        return fssCheckDateWriteMapper.updateOrderUserState(checkDate);
    }
    /**
     * jhz
     * 获得订单表跑批日期
     * @return
     */
    public FssCheckDate getOrderDate(){
        return fssCheckDateReadMapper.getOrderDate();
    }
    /**
     * jhz
     * 获得订单表跑批日期
     * @return
     */
    public int selectOrderDate(String orderDate)throws FssException{
        return fssCheckDateReadMapper.selectOrderDate(orderDate);
    }
    /**
     * jhz
     * 添加日期
     * @return
     */
    public void insertDate()throws FssException{
        Date date=new Date();
        String orderDate=DateUtil.dateTostring(date);
        int result=this.selectOrderDate(orderDate);
        if(result==0){
            FssCheckDate checkDate= this.creatCheckDate(orderDate,"98010002");
            this.insert(checkDate);
        }
    }

    /**
     * jhz
     * @param orderDate
     * @param orderDateState
     * @return
     * @throws FssException
     */
    public FssCheckDate creatCheckDate(String orderDate,String orderDateState)throws FssException{
        FssCheckDate checkDate=new FssCheckDate();
        checkDate.setOrderDate(orderDate);
        checkDate.setOrderUserState(orderDateState);
        return checkDate;
    }

    /**
     * wanggp
     * 查询对账日期列表
     * @param map
     * @return
     */
    public List<FssCheckDate> getFssCheckDate(Map<String,String> map) {
        Map<String, String> map2= Maps.newHashMap();
        if (map != null) {
            String orderDate = map.get("orderDate");
            map2.put("orderDate", orderDate != null ? orderDate.replace("-", "") : null);
            map2.put("inputUserState",map.get("inputUserState"));
            map2.put("orderUserState",map.get("orderUserState"));
        }
        return fssCheckDateReadMapper.selectFssCheckDateList(map2);
    }

    public FssCheckDate queryDate() {
        return fssCheckDateReadMapper.queryOrderDate();
    }

    public FssCheckDate getFssCheckDate(String orderDate) {
        return fssCheckDateReadMapper.getFssCheckDate(orderDate);
    }
}
