package com.gqhmt.fss.architect.order.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.entity.FundWithrawCharge;
import com.gqhmt.fss.architect.account.service.FundWithrawChargeService;

/**
 * Filename:    com.gqhmt.fss.architect.order.service.OrderServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/10/27 15:47
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class FundWithrawChargeServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private FundWithrawChargeService fundWithrawChargeService;

    private FundWithrawCharge entity;
    @Before
    public void setUp() throws FssException {
    	entity = new FundWithrawCharge();
        entity.setOrderNo("201509228125129619");
    }
    
    @Test
    public void testInsert() throws Exception {
    	
    	FundWithrawCharge entity1 = fundWithrawChargeService.getFundWithrawCharge(entity.getOrderNo());
    	Assert.assertEquals("18600705315", entity1.getUserName());
    	FundAccountEntity fundAccountEntity = new FundAccountEntity();
    	fundAccountEntity.setId(12l);
    	fundAccountEntity.setUserName("12");
    	fundWithrawChargeService.add("18600705315", fundAccountEntity, new BigDecimal(11), new BigDecimal(22));
    	
    	FundWithrawCharge entity2 = fundWithrawChargeService.getFundWithrawCharge("18600705315");
    	Assert.assertEquals("18600705315", entity2.getOrderNo());
    	
    }
}