package com.gqhmt.pay.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.ReturnWithdrawTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/14 11:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/14  于泳      1.0     1.0 Version
 */
public class ReturnWithdrawTest extends TestService{

    @Resource
    private TradeRecordService tradeRecordService;

    @Test
    public void test(){
        try {
            tradeRecordService.returnWithdraw("201609056173407441");
            assert true;
        } catch (FssException e) {
            e.printStackTrace();
            assert false;
        }
    }
}
