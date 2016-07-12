package com.gqhmt.pay.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.account.impl.FundsAccountImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

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

public class CreateAccountTest extends TestService {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    FundsAccountImpl fundsAccount;

    @Resource
    CustomerInfoService customerInfoService;

}
