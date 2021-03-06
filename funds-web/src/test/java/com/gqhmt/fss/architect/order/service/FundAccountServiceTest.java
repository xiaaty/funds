package com.gqhmt.fss.architect.order.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import org.junit.Before;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

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

public class FundAccountServiceTest extends TestService {

    @Resource
    private FundAccountService fundAccountService;

    private FundAccountEntity entity;
    @Before
    public void setUp() throws FssException {
    	entity = new FundAccountEntity();
        entity.setCustId(1l);
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
    }
    

}