package com.gqhmt.fss.architect.order.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class ChangeCardServiceTest extends AbstractJUnit4SpringContextTests {

//    @Resource
//    private ChangeCardService changeCardService;
//
//    private ChangeCardEntity entity;
//    @Before
//    public void setUp() throws FssException {
//    	entity = new ChangeCardEntity();
//    	entity.setId(44l);
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//
//    	ChangeCardEntity entity1 = changeCardService.get(entity.getId());
//    	Assert.assertEquals("430726198608274316", entity1.getCertNo());
//
//    	entity1.setCertNo("430726198608274310");
//    	changeCardService.update(entity1);
//    	Assert.assertEquals("430726198608274310", entity1.getCertNo());
//
//    	entity1.setCertNo("430726198608274316");
//    	changeCardService.update(entity1);
//    	Assert.assertEquals("430726198608274316", entity1.getCertNo());
//    }
    
}