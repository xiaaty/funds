package com.gqhmt.funds.order;

import com.gqhmt.funds.architect.order.bean.FundOrderBean;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class FondOrderServiceTest {

    @Resource
    private FundOrderService fundOrderService;

    @Test
    public void findFundOrder(){
    	Long id=(long) 1;
    	FundOrderEntity findfundOrder = fundOrderService.findfundOrder(id);
    	assert findfundOrder.getId().intValue() == 2;
    }
    @Test
    public void insertFundOrder() throws Exception{
    	FundOrderEntity fundOrder = new FundOrderEntity();
    	fundOrder.setAccountId(9999999999999L);
    	fundOrder.setOrderNo("88888888888888888888");
    	fundOrder.setOrderState(1);
    	fundOrder.setCreateTime(new Date());
    	fundOrderService.insert(fundOrder);
    }
    @Test
    public void updateFundOrder() throws Exception{
    	FundOrderEntity fundOrder = new FundOrderEntity();
    	fundOrder.setAccountId(8888888888L);
    	fundOrder.setOrderNo("666666666666");
    	fundOrder.setOrderState(2);
    	fundOrder.setCreateTime(new Date());
    	fundOrder.setId(407L);
    	fundOrderService.update(fundOrder);
    }


	@Test
	public void getFundOrderList(){
		List<FundOrderBean> list = fundOrderService.getFundOrderReWithList(44915);

		assert list.size() >0;
	}


}
