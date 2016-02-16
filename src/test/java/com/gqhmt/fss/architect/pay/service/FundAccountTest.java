package com.gqhmt.fss.architect.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.logicService.pay.IFundsAccount;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.util.CommonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class FundAccountTest extends AbstractJUnit4SpringContextTests {

    @Autowired(required=false)
    private IFundsAccount iFundsAccount;

    private CustomerInfoEntity entity;
    @Before
    public void setUp() throws FssException {
    	entity = new CustomerInfoEntity();
    	entity.setUserId(111);
    	//客户类型 1：借款用户 2:借款共借人 3：线下出借用户 4：线上出借注册用户 9：A0公司内用用户
    	entity.setCustomerType(2);
    	//移动电话
    	entity.setNationality("1");
    	//证件类型 1：身份证 2：护照 3：驾照 4：军人证
    	entity.setCertType(1);
    	entity.setCertNo("001");
		entity.setCustomerName("002");
		entity.setMobilePhone("003");
		// 签发日期
		entity.setCertIssueDate(CommonUtil.stringToDate("2012-01-01"));
		// 失效日期
		entity.setCertFailDate(CommonUtil.stringToDate("2012-01-01"));
		// 出生日期
		entity.setBirthdate(CommonUtil.stringToDate("2012-01-01"));

		// 地址
		String addr = "addr";

		entity.setAddress(addr);

		entity.setCreateTime((new Timestamp(new Date().getTime())));
		entity.setCreateUserId(123);

		// 是否实名认证 0：未认证 1:已认证
		entity.setNameIdentification(0);
		// 是否电话认证 0：未认证 1:已认证
		entity.setPhoneIdentification(0);

		// 是否email认证 0：未认证 1:已认证
		entity.setEmailIdentification(0);

		entity.setHasThirdAgreement(1);
    }
    
    @Test
    public void testInsert() throws Exception {
    	iFundsAccount.createAccount("2", entity, "123", "222");
    }
}