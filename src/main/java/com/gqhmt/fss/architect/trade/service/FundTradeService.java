package com.gqhmt.fss.architect.trade.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import com.gqhmt.fss.architect.trade.mapper.read.FundTradeReadMapper;
import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.trade.entity.FundTradeEntity;
import com.gqhmt.fss.architect.trade.mapper.write.FundTradeWriteMapper;
import com.gqhmt.util.GlobalConstants;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class FundTradeService {

    @Resource
    private FundTradeReadMapper fundTradeReadMapper;
    @Resource
    private FundTradeWriteMapper fundTradeWriteMapper;

    public void save(FundTradeEntity entity){
        this.fundTradeWriteMapper.insertSelective(entity);
    }

    /**
     * 添加交易记录
     * @param userId 用户ID
     * @param accountId 账户ID
     * @param income 收入金额
     * @param spending 支出金额
     * @param tradeType 交易类型
     * @param remarks 备注
     */
    public void addFundTrade(Integer userId,Long accountId,BigDecimal income,BigDecimal spending, Integer tradeType,String remarks,int cusID)throws  Exception{
        this.addFundTrade(userId,accountId,income,spending,tradeType,remarks,cusID,0L);
    }

    /**
     * 添加交易记录
     @param userId 用户ID
      * @param accountId 账户ID
     * @param income 收入金额
     * @param spending 支出金额
     * @param tradeType 交易类型
     * @param remarks 备注
     * @param cusID  客户id
     * @param bonusAmount  红包金额
     * @throws Exception
     */
    public void addFundTrade(Integer userId,Long accountId,BigDecimal income,BigDecimal spending, Integer tradeType,String remarks,int cusID,Long bonusAmount)throws  Exception{
        FundTradeEntity fundTrade = new FundTradeEntity();
        fundTrade.setTradeNo("GQ_" + System.currentTimeMillis());
        fundTrade.setTradeType(tradeType);
        fundTrade.setUserId(userId);
        fundTrade.setAccountId(accountId);
        fundTrade.setIncome(income);
        fundTrade.setSpending(spending);
        fundTrade.setRemarks(remarks);
        fundTrade.setTradeTime(new Date(System.currentTimeMillis()));
        if(cusID > GlobalConstants.RESERVED_CUSTOMERID_LIMIT){
            BigDecimal usableSum = this.fundTradeReadMapper.getSumBigDecimal(userId);
            fundTrade.setUsableSum(usableSum);
        }else{
            BigDecimal usableSum = this.fundTradeReadMapper.getSumBigDecimalByCus(cusID);
            fundTrade.setUsableSum(usableSum);
        }
        fundTrade.setBonusAmount(bonusAmount);
        this.save(fundTrade);
    }
}
