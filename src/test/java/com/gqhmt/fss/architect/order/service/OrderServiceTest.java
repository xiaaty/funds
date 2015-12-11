package com.gqhmt.fss.architect.order.service;

import com.gqhmt.fss.architect.order.mapper.write.OrderWriteDaoInter;
import com.gqhmt.fss.architect.order.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class OrderServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderWriteDaoInter orderWriteDaoInter;

//    @Resource
//    private OrderReadDaoInter orderReadDaoInter;

    @Test
    public void testSave() throws Exception {
        Order order = new Order();
        order.setOrderNo("12324564325");
        orderService.save(order);

//        orderWriteDaoInter.insert(order);
        assert  true;
    }
}