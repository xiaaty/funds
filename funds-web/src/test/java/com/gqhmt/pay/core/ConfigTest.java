package com.gqhmt.pay.core;

import com.gqhmt.TestService;
import com.gqhmt.pay.core.factory.ThirdpartyFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.core.ConfigTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/28 15:16
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/28  于泳      1.0     1.0 Version
 */

public class ConfigTest extends TestService {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void congfig(){
        ThirdpartyFactory.command(PayCommondConstants.PAY_CHANNEL_FUIOU,"0100",null);
    }


}
