package com.gqhmt.funds.account;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.funds.account.FundAccountServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/15 17:33
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/15  于泳      1.0     1.0 Version
 */

public class FundAccountServiceTest extends TestService {

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Test
    public void createAccountTest()  {

        CustomerInfoEntity customerInfoEntity = customerInfoService.queryCustomerById(611636);

        try {
            fundAccountService.createAccount(customerInfoEntity,customerInfoEntity.getUserId());
        } catch (FssException e) {
            assert e.getMessage().equals("账户已存在");
        }

        assert true;

    }



    @Test
    public void findFundAccountByDateTest(){
        List<FundAccountCustomerBean> list = fundAccountService.findFundAccountByDate("20161107");
        LogUtil.info(this.getClass(),list.toString());
        assert list.size()>0;
    }

}
