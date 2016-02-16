package com.gqhmt.funds.trade;

import com.gqhmt.funds.architect.trade.entity.WithdrawApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithdrawApplyService;

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
public class WithdrawApplyServiceTest {

    @Resource
    private WithdrawApplyService withdrawApplyService;

    @Test
    public void findWithdrawApply() throws Exception{
    	  WithdrawApplyEntity withDrawInfo = withdrawApplyService.getWithDrawInfo(821L);
    		assert withDrawInfo.getId()==821L;
    }
    @Test
    public void insertWithdrawApply() throws Exception{
    	WithdrawApplyEntity withDrawInfo = new WithdrawApplyEntity();
    	    	withDrawInfo.setApplyStatus(1);
    	    	withDrawInfo.setApplyTime(new Date());
    	    	withDrawInfo.setBankId(6666666);
    	    	withDrawInfo.setBussinessCompany("辅导费好");
    	    	withdrawApplyService.insert(withDrawInfo);
    }
    @Test
    public void updateWithdrawApply() throws Exception{
    	WithdrawApplyEntity withDrawInfo = new WithdrawApplyEntity();
    		withDrawInfo.setId(0);
    	    	withDrawInfo.setApplyStatus(2);
    	    	withDrawInfo.setApplyTime(new Date());
    	    	withDrawInfo.setBankId(8888);
    	    	withDrawInfo.setBussinessCompany("辅导费好9999");
    	    	withdrawApplyService.update(withDrawInfo);
    }


}
