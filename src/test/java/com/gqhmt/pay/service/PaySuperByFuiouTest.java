package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.PaySuperByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/17 11:50
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/17  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class PaySuperByFuiouTest {

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Test
    public void createAccount(){
        try {
            paySuperByFuiou.createAccountByPersonal(null,"","");
        } catch (FssException e) {
            assert false;
        }

        assert true;

    }


}
