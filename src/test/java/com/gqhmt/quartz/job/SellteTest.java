package com.gqhmt.quartz.job;

import com.gqhmt.TestService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.loan.SettleBeforeJob;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.job.SellteTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/14 15:24
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/14  于泳      1.0     1.0 Version
 */
public class SellteTest extends TestService{

    @Resource
    SettleBeforeJob settleBeforeJob;

    @Test
    public  void test(){
        try {
            settleBeforeJob.execute();
            assert  true;
        } catch (PayChannelNotSupports payChannelNotSupports) {
            assert false;

        }
    }
}
