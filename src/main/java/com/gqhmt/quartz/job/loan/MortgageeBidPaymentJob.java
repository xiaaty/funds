package com.gqhmt.quartz.job.loan;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Filename:    com.gqhmt.quartz.job.loan.MortgageeBidPaymentJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 15:52
 * Description:
 * <p>抵押标放款job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
public class MortgageeBidPaymentJob implements Job {
    private static boolean running = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取抵押标转账列表

        //判断抵押权人账户是否有钱


        //资金转入借款人账户

        //收取费用


        //调用回调方法

        //回调成功,流程完结


    }
}
