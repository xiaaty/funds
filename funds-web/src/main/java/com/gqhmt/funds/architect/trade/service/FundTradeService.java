package com.gqhmt.funds.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import com.gqhmt.funds.architect.trade.mapper.read.FundTradeReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FundTradeWriteMapper;
import com.gqhmt.core.util.StringUtils;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
     * @return
     */
    public List<FundTradeBean> queryFundTrade(Integer cust_no,String str_trade_time,String end_trade_time,String tradeFilters) throws FssException{
    	Map map=new HashMap();
    	if(null!=cust_no){
    		map.put("cust_no", cust_no);
    	}
    	if(str_trade_time!=null && !"".equals(str_trade_time)){
    			map.put("str_trade_time",str_trade_time);
    		
    	}
    	if(end_trade_time!=null && !"".equals(end_trade_time)){
    			map.put("end_trade_time",end_trade_time);
    	}
    	String tradeType=null; 
    	StringBuffer types = new StringBuffer();
    	if(tradeFilters!=null && !"".equals(tradeFilters)){
    			if(tradeFilters.indexOf("c")>=0){//充值
    				types.append("1001,1002,");		
    			}
    			if(tradeFilters.indexOf("w")>=0){//提现	
    				types.append("1003,1004,1012,2001,2002,2003,");
    			}
    			if(tradeFilters.indexOf("b")>=0){//出借
    				types.append("2004,3001,3002,3009,");
    			}
    			if(tradeFilters.indexOf("r")>=0){//回款
    				types.append("3005,3006,3012,");
				}
    			if(tradeFilters.indexOf("o")>=0){//其他
    				types.append("1005,1006,1007,1008,1009,1010,1011,1013,2005,2007,2008,2009,2010,3003,3004,3007,3008,3010,3011,4001,4002,4003,4004,4005,4006,4007,4010,4011,4012,4013,4014,4015,");
				}
    			types.deleteCharAt(types.length()-1);
    		}else{//当tradeFilters为空的默认查询所有状态
    			types.append("-1");//空的，不存在
    		}
    		tradeType=types.toString();
	    	List list=new ArrayList();
	 	   	if(StringUtils.isNotEmptyString(tradeType)){
	 		   String str[]=tradeType.split(",");
	 		   for (int i = 0; i < str.length; i++){
	 			   list.add(str[i]);
	 		   }
	 	   }
		    if(list!=null && list.size()>0){
	    		map.put("list", list);
	    	}
		    List<FundTradeBean> fundtradelist=this.fundTradeReadMapper.queryFundTradeList(map);
		    return fundtradelist;
    }
    
}
