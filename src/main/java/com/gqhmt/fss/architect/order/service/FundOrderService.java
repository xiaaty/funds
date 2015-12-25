package com.gqhmt.fss.architect.order.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.gqhmt.fss.architect.job.bean.FundOrder;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.architect.order.mapper.read.FundOrderReadMapper;
import com.gqhmt.fss.architect.order.mapper.write.FundOrderWriteMapper;
import com.gqhmt.util.GlobalConstants;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/20 20:44
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/20  于泳      1.0     1.0 Version
 */
@Service
public class FundOrderService  {

    @Resource
    private FundOrderReadMapper fundOrderReadMapper;
    @Resource
    private FundOrderWriteMapper fundOrderWriteMapper;

    public void update(FundOrderEntity entity) throws Exception{
        entity.setLastModifyTime(new Date());
        fundOrderWriteMapper.updateByPrimaryKeySelective(entity);
    }

    public FundOrderEntity findfundOrder(Long id){
    	return fundOrderReadMapper.selectByPrimaryKey(id);
    }

    public FundOrderEntity findfundOrder(String order) {
        return fundOrderReadMapper.getFundOrder(order);
    }

    public String getOrderNo(){
        Date date = new Date();
        String year  = String.format("%tY",date);
        String month = String.format("%tm",date);
        String dateString = String.format("%td",date);
        Double d = Math.random();
        d = d*10000000000L;
        d.longValue();
        return year+month+dateString+String.format("%010d",d.longValue());
    }

    public List<FundOrderEntity> queryFundOrder(int orderType,int orderSource,int orderFromId){
        return fundOrderReadMapper.queryFundOrder(orderType,orderSource,orderFromId);
    }

    public boolean checkWithdrawNumber(long accountId){
        int num = fundOrderReadMapper.getWithdrawNum(accountId);
        if(num > GlobalConstants.CHECK_WITHRAW_NUM)
            return true;
        return false;
    }
}
