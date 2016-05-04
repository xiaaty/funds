package com.gqhmt.dataMig;

import com.gqhmt.DataMigration.account.OnlineAccountDataMigration;
import com.gqhmt.TestService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.dataMig.DataMigTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/20 11:13
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/20  于泳      1.0     1.0 Version
 */
public class DataMigTest extends TestService {

    @Resource
    private OnlineAccountDataMigration onlineAccountDataMigration;

    /**
     * 线上客户数据迁移
     */
    public  void onlineAccountDataMigTest(){
        onlineAccountDataMigration.onlineAccountDataMig();
    }


    /**
     * 线上借款客户账户迁移
     */
    public void loanOnlineAccountDataMigTest(){
        onlineAccountDataMigration.loanOnlineAccountDataMig();
    }

    /**
     *线上出借客户账户迁移
     */
    public void lendOnLineAccountDataMigTest(){
        onlineAccountDataMigration.lendOnLineAccountDataMig();
    }


    /**
     * 线下借款历史数据迁移
     */
    @Test
    public void LoanOffLineAccountDataMig(){
        onlineAccountDataMigration.LoanOffLineAccountDataMig();
    }


}
