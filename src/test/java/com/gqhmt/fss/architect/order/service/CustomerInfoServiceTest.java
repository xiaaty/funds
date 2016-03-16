package com.gqhmt.fss.architect.order.service;

import com.gqhmt.TestService;
import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

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
public class CustomerInfoServiceTest extends TestService {

    @Resource
    private CustomerInfoService customerInfoService;

    private CustomerInfoEntity entity;
    @Before
    public void setUp() throws FssException {
    	entity = new CustomerInfoEntity();
    	entity.setId(44861);
    }
    
    @Test
    public void testUpdate() throws Exception {
    	
    	CustomerInfoEntity entity1 = customerInfoService.queryCustomerById(entity.getId());
    	Assert.assertEquals("赵先", entity1.getCustomerName());
    	
    	entity1.setCustomerName("赵先01");
    	customerInfoService.update(entity1);
    	Assert.assertEquals("赵先01", entity1.getCustomerName());
    	
    	entity1.setCustomerName("赵先");
    	customerInfoService.update(entity1);
    	Assert.assertEquals("赵先", entity1.getCustomerName());
    	
    }
    
}