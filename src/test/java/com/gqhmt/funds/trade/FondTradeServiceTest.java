package com.gqhmt.funds.trade;

import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.sys.service.MenuServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:54
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class FondTradeServiceTest {

    @Resource
    private FundTradeService fundTradeService;

    @Test
    public void findFundTrade(){
    	FundTradeEntity fundTradeEntity = fundTradeService.findFundTradeById(71314);
    		assert fundTradeEntity.getId()==2;
    }
    @Test
    public void insertFundTrade() throws Exception{
    	
    }
    @Test
    public void updateFundTrade() throws Exception{
    	FundTradeEntity fundTradeEntity = new FundTradeEntity();
    	fundTradeEntity.setId(107744);
    	fundTradeEntity.setAccountId(8888888888888888L);
    	fundTradeEntity.setBidId(666666666);
    	fundTradeEntity.setTradeTime(new Date());
    	fundTradeService.updateFundTrade(fundTradeEntity);
    	
    }


}
