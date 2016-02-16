package com.gqhmt.funds.account;

import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.funds.account.FundAccountServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/15 17:33
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/15  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class FundAccountServiceTest {

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Test
    public void createAccountTest()  {

        CustomerInfoEntity customerInfoEntity = customerInfoService.queryCustomerById(611636);

        try {
            fundAccountService.createAccount(customerInfoEntity,customerInfoEntity.getUserId());
        } catch (FssException e) {
            assert false;
        }

        assert true;

    }

}
