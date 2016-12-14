package com.gqhmt.business.inverment;

import com.gqhmt.TestService;
import com.gqhmt.business.architect.invest.service.InvestmentService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.business.inverment.InvesmentServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/12 16:57
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/12  于泳      1.0     1.0 Version
 */
public class InvesmentServiceTest extends TestService {

    @Resource
    private InvestmentService investmentService;

    @Test
    public void queryByCustIdTest(){
        int res = investmentService.queryByCustId(612121);

        assert res>0;
    }
}
