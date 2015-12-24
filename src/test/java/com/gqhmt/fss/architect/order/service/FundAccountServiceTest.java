package com.gqhmt.fss.architect.order.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.bean.FundAccountEntity;
import com.gqhmt.fss.architect.account.service.FundAccountService;

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
public class FundAccountServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private FundAccountService fundAccountService;

    private FundAccountEntity entity;
    @Before
    public void setUp() throws FssException {
    	entity = new FundAccountEntity();
        entity.setCustId(1);
        entity.setUserName("name");
        entity.setAmount(BigDecimal.ZERO);
        entity.setFreezeAmount(BigDecimal.ZERO);
        entity.setAccountType(0);
        entity.setBusiType(1);
        entity.setUserId(1);
        entity.setAccountNo("123");
        entity.setBankNo("456");
        entity.setCityId("001");
        entity.setParentBankId("002");
        entity.setCustName("custName1");
        entity.setCreateTime(new Date());
    	fundAccountService.insert(entity);
    }
    
    @Test
    public void testInsert() throws Exception {
    	
    	FundAccountEntity entity1 = fundAccountService.getFundAccountInfo(entity.getId());
    	Assert.assertEquals("name", entity1.getUserName());
    	
    	entity1.setUserName("updateName");
    	fundAccountService.update(entity1);
    	Assert.assertEquals("updateName", entity1.getUserName());
    	
    	fundAccountService.delete(entity1.getId());
    	FundAccountEntity entity2 = fundAccountService.getFundAccountInfo(entity.getId());
    	Assert.assertEquals(null, entity2);
    	
    }
}