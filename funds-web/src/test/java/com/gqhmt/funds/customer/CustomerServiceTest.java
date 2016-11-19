package com.gqhmt.funds.customer;

import com.gqhmt.TestService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.junit.Test;

import javax.annotation.Resource;
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

public class CustomerServiceTest extends TestService {

    @Resource
    private CustomerInfoService customerInfoService;

    @Test
    public void findCustomerById(){
    	CustomerInfoEntity queryCustomeById = customerInfoService.queryCustomeById(1l);

    	assert queryCustomeById.getId().intValue() == 2;
    }

    @Test
    public void queryCustomerInfoBYDate(){
        List<CustomerInfoEntity> customerInfoEntities = customerInfoService.queryCustomerInfoByDate("20161107");

        assert customerInfoEntities.size()>0;
    }


}
