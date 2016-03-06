package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.CreateAccountTest
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

    @Resource
    FundsAccountImpl fundsAccount;

    @Resource
    CustomerInfoService customerInfoService;

    @Test
    public void createAccountTest() throws InterruptedException {
        /*System.err.println("执行开始");
        applicationContext.publishEvent(new CreateAccountEvent(""));

        Thread.sleep(1000*10);

        System.err.println("执行完成");*/

        try {

            CustomerInfoEntity customerInfoEntity = customerInfoService.queryCustomerById(611636);
            customerInfoEntity.setBankNo("232342");

            fundsAccount.createAccount(customerInfoEntity,"","");
        } catch (FssException e) {
            e.printStackTrace();
            assert e.getMessage().equals("账户已存在");
        }
        assert true;
    }
}
