package com.gqhmt.funds.architect.trade.service;

import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import com.gqhmt.funds.architect.trade.mapper.read.FundTradeReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FundTradeWriteMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.fuiou.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 13:46
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
@Service
public class FundTradeService {
    @Resource
    private FundTradeReadMapper fundTradeReadMapper;
    
    @Resource
    private FundTradeWriteMapper fundTradeWriteMapper ;



    /**
     * 添加交易记录
     * @param income 收入金额
     * @param spending 支出金额
     * @param tradeType 交易类型
     * @param remarks 备注
     */
    public void addFundTrade(FundAccountEntity entity, BigDecimal income, BigDecimal spending, Integer tradeType, String remarks)throws FssException{
        this.addFundTrade(entity,income,spending,tradeType,remarks,BigDecimal.ZERO);
    }

    /**
     * 添加交易记录
     * @param income 收入金额
     * @param spending 支出金额
     * @param tradeType 交易类型
     * @param remarks 备注
     * @param bonusAmount  红包金额
     * @throws Exception
     */
    public void addFundTrade(FundAccountEntity entity,BigDecimal income,BigDecimal spending, Integer tradeType,String remarks,BigDecimal bonusAmount)throws  FssException{
        FundTradeEntity fundTrade = this.getFundTradeEntity(entity,income,spending,tradeType,remarks,bonusAmount);
        this.fundTradeWriteMapper.insertSelective(fundTrade);
    }


    public FundTradeEntity getFundTradeEntity(FundAccountEntity entity,BigDecimal income,BigDecimal spending, Integer tradeType,String remarks,BigDecimal bonusAmount){
        FundTradeEntity fundTrade = new FundTradeEntity();
        fundTrade.setTradeNo("GQ_" + System.currentTimeMillis());
        fundTrade.setTradeType(tradeType);
        fundTrade.setUserId(entity.getUserId());
        fundTrade.setAccountId(entity.getId());
        fundTrade.setIncome(income);
        fundTrade.setSpending(spending);
        fundTrade.setRemarks(remarks);
        fundTrade.setTradeTime(new Date(System.currentTimeMillis()));
        BigDecimal usableSum = entity.getAmount().add(income).subtract(spending);

        entity.setAmount(usableSum);
//        if(cusID > GlobalConstants.RESERVED_CUSTOMERID_LIMIT){
//            BigDecimal usableSum = this.fundTradeDao.getSumBigDecimal(userId);
//            fundTrade.setUsableSum(usableSum);
//        }else{
//            BigDecimal usableSum = this.fundTradeDao.getSumBigDecimalByCus(cusID);
//            fundTrade.setUsableSum(usableSum);
//        }
        fundTrade.setUsableSum(usableSum);
        fundTrade.setBonusAmount(bonusAmount);

        return  fundTrade;
    }


    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：查询fundTradeEntity集合信息
     */
    public List<FundTradeEntity> findFundTradeList(){
    	return fundTradeReadMapper.selectAll();
    }

    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：通过ID查询FundTradeEntity
     */
    public FundTradeEntity findFundTradeById(Integer id){
    	 return fundTradeReadMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 查询交易记录
     * @param cust_no
     * @param user_no
     * @param busi_no
     * @return
     */
    public List<FundTradeEntity> searchTradeRecord(Integer cust_no,Integer user_no,Integer busi_no,String trade_type){
    	Map map=new HashMap();
    	if(null!=cust_no){
    		map.put("cust_no", cust_no);
    	}
    	if(null!=user_no){
    		map.put("user_no", user_no);
    	}
    	if(null!=trade_type){
    		map.put("trade_type", Integer.parseInt(trade_type));
    	}
    	return this.fundTradeReadMapper.queryFundTradeList(map);
    }
    
    
    
    
    
}
