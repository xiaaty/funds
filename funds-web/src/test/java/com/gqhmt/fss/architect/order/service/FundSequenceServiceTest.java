package com.gqhmt.fss.architect.order.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.util.ThirdPartyType;
import org.junit.Before;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

public class FundSequenceServiceTest extends TestService {

    @Resource
    private FundSequenceService fundSequenceService;

    private FundSequenceEntity entity;
    @Before
    public void setUp() throws FssException {
    	entity = new FundSequenceEntity();
    	entity.setCreateTime(new Timestamp(new Date().getTime()));
		entity.setAmount(new BigDecimal(0));
		entity.setAccountId(1l);
		entity.setCurrency("0001");
		entity.setFundType(1);
		entity.setActionType(1);
		entity.setThirdPartyType(ThirdPartyType.FUIOU.getKey());
	    entity.setOrderNo("12");
	    entity.setModifyTime(new Date());
	    entity.setoAccountId(2l);
    }
    

}