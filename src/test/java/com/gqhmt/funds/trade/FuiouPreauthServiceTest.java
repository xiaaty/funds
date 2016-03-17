package com.gqhmt.funds.trade;

import com.gqhmt.TestService;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import org.junit.Test;

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

public class FuiouPreauthServiceTest extends TestService {

    @Resource
    private FuiouPreauthService fuiouPreauthService;

    @Test
    public void findFuiouPreauth(){
    	 FuiouPreauth findFuiouPreauthById = fuiouPreauthService.findFuiouPreauthById(54137L);
    		assert findFuiouPreauthById.getId()==2L;
    		
    		
    }
    @Test
    public void insertFuiouPreauth() throws Exception{
    	FuiouPreauth fuiouPreauth = new FuiouPreauth();
    	fuiouPreauth.setAccountId(222222L);
    	fuiouPreauth.setOrderNo("66666666");
    	fuiouPreauthService.insert(fuiouPreauth);
    }
    @Test
    public void updateFuiouPreauth() throws Exception{
    	FuiouPreauth fuiouPreauth = new FuiouPreauth();
    	fuiouPreauth.setState(1);
    	fuiouPreauth.setId(54149L);
    	fuiouPreauthService.update(fuiouPreauth);
    }


}
