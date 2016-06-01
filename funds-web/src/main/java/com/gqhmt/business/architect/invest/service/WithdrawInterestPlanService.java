package com.gqhmt.business.architect.invest.service;

import com.gqhmt.business.architect.invest.entity.WithdrawInterestPlan;
import com.gqhmt.business.architect.invest.mapper.read.WithdrawInterestPlanReadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 22:30
 * Description: 出借人收益计划用
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class WithdrawInterestPlanService  {

    @Resource
    private WithdrawInterestPlanReadMapper withdrawInterestPlanReadMapper;
    
    public WithdrawInterestPlan selectOne(WithdrawInterestPlan entity) {
		return withdrawInterestPlanReadMapper.selectOne(entity);
	}
	
	public List<WithdrawInterestPlan> select(WithdrawInterestPlan entity) {
		return withdrawInterestPlanReadMapper.select(entity);
	}

}
