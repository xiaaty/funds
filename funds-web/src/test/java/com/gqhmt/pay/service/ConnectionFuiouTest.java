package com.gqhmt.pay.service;

import com.gqhmt.TestService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.pay.core.command.CommandResponse;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.ConnectionFuiouTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/9/7 11:58
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/9/7  于泳      1.0     1.0 Version
 */
public class ConnectionFuiouTest extends TestService{

    private static final Long custNid = 44862l;


    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Test
    public void testQueryUserInfo(){
        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custNid,0);
        try {
            CommandResponse response  = paySuperByFuiou.userInfoQuery(fundAccountEntity);
            System.out.println(response.getMap());
            assert response.getMap() != null;
        } catch (FssException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryCZTX(){
        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custNid,0);
        try {

            CommandResponse response  = paySuperByFuiou.tradeCZZTXQuery("PW11",fundAccountEntity,"2016-08-10","2016-09-08",1);
            System.out.println(response.getMap());
            assert response.getMap() != null;
        } catch (FssException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryTrade(){
        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custNid,0);
        try {



            CommandResponse response  = paySuperByFuiou.tradeQuery("PW13",fundAccountEntity,"","20160901","20160907",1);
            System.out.println(response.getMap());
            assert response.getMap() != null;
        } catch (FssException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testBalance(){
        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custNid,0);
        try {
            CommandResponse response = paySuperByFuiou.banlance(fundAccountEntity);
            System.out.println(response);

            assert response.getMap() != null;
        } catch (FssException e) {
            e.printStackTrace();
        }

    }

}
