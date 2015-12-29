package com.gqhmt.fss.architect.account.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.entity.FundWithrawCharge;
import com.gqhmt.fss.architect.account.mapper.read.FundWithrawChargeReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FundWithrawChargeWriteMapper;

/**
 * Filename:    com.gq.funds.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/6/2 15:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
@Service
public class FundWithrawChargeService {

	@Resource
    private FundWithrawChargeReadMapper fundWithrawChargeReadMapper;
    @Resource
    private FundWithrawChargeWriteMapper fundWithrawChargeWriteMapper;

    public FundWithrawCharge add(String orderNo,FundAccountEntity fundAccountEntity,BigDecimal amount,BigDecimal chargeAmount){
        FundWithrawCharge fundWithrawCharge = new FundWithrawCharge();
        fundWithrawCharge.setState(1);
        fundWithrawCharge.setUserName(fundAccountEntity.getUserName());
        fundWithrawCharge.setOrderNo(orderNo);
        fundWithrawCharge.setAccountId(fundAccountEntity.getId());
        fundWithrawCharge.setAmount(amount);
        fundWithrawCharge.setChargeAmount(chargeAmount);
        fundWithrawChargeWriteMapper.insertSelective(fundWithrawCharge);
        return fundWithrawCharge;
    }


    public FundWithrawCharge getFundWithrawCharge(String orderNo){
        return this.fundWithrawChargeReadMapper.getFundWithrawCharge(orderNo);
    }


    public void updateSrate(String orderNo,int state){
        FundWithrawCharge fundWithrawCharge = this.getFundWithrawCharge(orderNo);
        fundWithrawCharge.setState(state);
        fundWithrawChargeWriteMapper.updateByPrimaryKeySelective(fundWithrawCharge);
    }

    public List<FundWithrawCharge> list(){
        return this.fundWithrawChargeReadMapper.list();
    }

}
