package com.gqhmt.fss.logicService;

import com.gqhmt.fss.event.account.CreateAccountEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.logicService.CreateAccountTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/9 23:31
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/9  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class CreateAccountTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void createAccountTest() throws InterruptedException {
        System.err.println("执行开始");
        applicationContext.publishEvent(new CreateAccountEvent(""));

        Thread.sleep(1000*10);

        System.err.println("执行完成");
    }
}
