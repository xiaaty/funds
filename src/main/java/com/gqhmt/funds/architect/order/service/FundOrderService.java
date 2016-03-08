package com.gqhmt.funds.architect.order.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.bean.FundOrderBean;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.mapper.read.FundOrderReadMapper;
import com.gqhmt.funds.architect.order.mapper.write.FundOrderWriteMapper;
import com.gqhmt.pay.exception.CommandParmException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    public void insert(FundOrderEntity entity) throws FssException{
        fundOrderWriteMapper.insertSelective(entity);
    }
    
    public void update(FundOrderEntity entity) throws FssException{
        entity.setLastModifyTime(new Date());
        fundOrderWriteMapper.updateByPrimaryKeySelective(entity);
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
     * @param thirdPartyType
     * @return
     * @throws FssException
     */
    public FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity, BigDecimal amount, BigDecimal chargeAmount, int orderType, Long sourceID, Integer sourceType, String thirdPartyType) throws FssException {
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
        fundOrderEntity.setThirdPartyType(thirdPartyType);
        fundOrderEntity.setChargeAmount(chargeAmount);
        fundOrderEntity.setOrderState(GlobalConstants.ORDER_STATUS_SUBMIT);
        try {
        	this.insert(fundOrderEntity);
        	this.update(fundOrderEntity);
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
    
    /**
     * 修改订单
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws CommandParmException
     */
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, noRollbackFor = { CommandParmException.class }, readOnly = false)
	public final void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws FssException {
		fundOrderEntity.setOrderState(status);
		fundOrderEntity.setRetCode(code);
		fundOrderEntity.setRetMessage(msg);
		try {
			this.insert(fundOrderEntity);
			this.update(fundOrderEntity);
		} catch (Exception e) {
			throw new FssException(e.getMessage());
		}
	}
    
    public List<FundOrderBean> getFundOrderReWithList(Integer custId){
        return fundOrderReadMapper.getFundOrderRechargeAndWithdraw(custId);
    }
    
}
