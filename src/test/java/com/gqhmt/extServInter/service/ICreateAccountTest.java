package com.gqhmt.extServInter.service;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.CreateAccountByFuiouDto;
import com.gqhmt.extServInter.service.account.impl.CreateAccountImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.service.ICreateAccountTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:19
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class ICreateAccountTest {

    @Resource
    private CreateAccountImpl createAccount;

    @Test
    public void createAccountTest() throws APIExcuteErrorException {
        CreateAccountByFuiouDto dto = new CreateAccountByFuiouDto();
        try {
            Response response = createAccount.excute(dto);
            assert response.getResp_code().equals("90008101");
        }catch (Throwable t){
            t.printStackTrace();

            assert false;
        }

    }

}
