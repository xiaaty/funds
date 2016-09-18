package com.gqhmt.funds.architect.order.service;

import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.bean.FundOrderBean;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.mapper.read.FundOrderReadMapper;
import com.gqhmt.funds.architect.order.mapper.write.FundOrderWriteMapper;
import com.gqhmt.pay.exception.CommandParmException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private FundSequenceService fundSequenceService;

    public void insert(FundOrderEntity entity) throws FssException{
        fundOrderWriteMapper.insertSelective(entity);
    }
    
    public void update(FundOrderEntity entity) throws FssException{
        entity.setLastModifyTime(new Date());
        fundOrderWriteMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     *
     * @param id
     * @return
     * @throws FssException
     */
    public FundOrderEntity select(Long id) throws FssException{
      return  fundOrderReadMapper.selectByPrimaryKey(id);
    }

    /**
     * 创建交易订单
     * @param primaryAccount
     * @param toAccountEntity
     * @param amount
     * @param chargeAmount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @return
     * @throws FssException
     */
    public FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity, BigDecimal amount, BigDecimal chargeAmount, int orderType, Long sourceID, Integer sourceType) throws FssException {

        return this.createOrder(primaryAccount,toAccountEntity,amount,chargeAmount,orderType,sourceID,sourceType,null,null,null,null,null,null);
    }

    public FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity, BigDecimal amount, BigDecimal chargeAmount, int orderType, Long sourceID, Integer sourceType,String newOrderType,String tradeType,String lendNo,String toLendNo,Long loanCustId,String loanNo) throws FssException {
        FundOrderEntity fundOrderEntity = new FundOrderEntity();
        fundOrderEntity.setAccountId(primaryAccount.getId());
        if (toAccountEntity != null) {
            fundOrderEntity.setToAccountId(toAccountEntity.getId());
        }
        fundOrderEntity.setOrderNo(this.getOrderNo());
        fundOrderEntity.setCreateTime(new Date());
        fundOrderEntity.setOrderAmount(amount);
        fundOrderEntity.setOrderSource(sourceType);
        fundOrderEntity.setOrderFrormId(sourceID);
        // 订单类型(1-充值 2-提现 3-代偿 4-投标 5-转账 6-还款 7-流标)
        fundOrderEntity.setOrderType(orderType);
        fundOrderEntity.setThirdPartyType("2");
        fundOrderEntity.setChargeAmount(chargeAmount);
        fundOrderEntity.setOrderState(GlobalConstants.ORDER_STATUS_SUBMIT);
        fundOrderEntity.setNewOrderType(newOrderType);
        fundOrderEntity.setTradeType(tradeType);
        fundOrderEntity.setCustId(primaryAccount.getCustId());
        fundOrderEntity.setLendNo(lendNo);
        fundOrderEntity.setToCustId(toAccountEntity == null ? 0:toAccountEntity.getCustId());
        fundOrderEntity.setToLendNo(toLendNo);
        fundOrderEntity.setLoanCustId(loanCustId);
        fundOrderEntity.setLoanNo(loanNo);
        try {
            this.insert(fundOrderEntity);
//        	this.update(fundOrderEntity);
        } catch (Exception e) {
            throw new FssException(e.getMessage());
        }
        return fundOrderEntity;
    }


    public FundOrderEntity findfundOrder(Long id){
    	return fundOrderReadMapper.selectByPrimaryKey(id);
    }

    public FundOrderEntity findfundOrder(String order) {
        return fundOrderReadMapper.getFundOrder(order);
    }

    /**
     * jhz
     * 根据出账帐号查询所有充值代扣交易订单
     * @param map
     * @return
     */
    public List<FundOrderEntity> findfundOrdesrs(Map<String,String> map,List<Long> accNos) {
        Map<String, String> map2= Maps.newHashMap();
        String startTime = map.get("startTime");
        String endTime = map.get("endTime");
        map2.put("orderNo",map.get("orderNo"));
        map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
        map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        return fundOrderReadMapper.findfundOrdesrs(map2,accNos);
    }

    /**
     * 得到订单号
     * @return
     */
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
    	Map<Object,Object> map=new HashMap<>();
    	map.put("orderType", orderType);
    	map.put("orderSource", orderSource);
    	map.put("orderFromId", orderFromId);
        return fundOrderReadMapper.queryFundOrder(map);
    }

    public boolean checkWithdrawNumber(Long accountId){
//        int num = fundOrderReadMapper.getWithdrawNum(accountId);
//        if(num > GlobalConstants.CHECK_WITHRAW_NUM)
            return true;
//        return false;
    }
    
    /**
     * 修改订单
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws FssException
     */
	public  void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws FssException {
		fundOrderEntity.setOrderState(status);
		fundOrderEntity.setRetCode(code);
		fundOrderEntity.setRetMessage(msg);
        fundOrderEntity.setLastModifyTime(new Date());
//			this.insert(fundOrderEntity);
         this.update(fundOrderEntity);
	}
    
    public List<FundOrderBean> getFundOrderReWithList(Integer custId,Integer type,String strTime,String endTime){

        return fundOrderReadMapper.getFundOrderRechargeAndWithdraw(custId,type == null?0:type==1003?1:2,strTime,endTime);
    }



    public FundOrderEntity getOrderNoByAccountId(Long accountId){
        return fundOrderReadMapper.getFundOrderByAccountId(accountId);
    }
}
