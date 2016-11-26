package com.gqhmt.quartz.job.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.dataMigration.account.AccountData;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Filename:    com.gqhmt.quartz.job.account.DataAccount
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/22 15:19
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/22  于泳      1.0     1.0 Version
 */
@Component
public class DataAccount extends SupperJob {

    @Resource
    AccountData accountData;

    @Override
    public boolean isRunning() {
        return false;
    }

    private boolean isRunning = false;

//    @Scheduled(cron="0 0/1 * * * ?")
    public void executeJob() throws PayChannelNotSupports {

        LogUtil.info(getClass(), "账户数据迁移job");

        if (!isIp("upload")) {
            return;
        }

        if (isRunning) {
            return;
        }

        startLog("账户数据迁移job");
        isRunning = true;

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = new GregorianCalendar(2015,01,01);
        Calendar now = Calendar.getInstance();
        int nowTime = Integer.parseInt(df.format(now.getTime()));
        int startTime = Integer.parseInt(df.format(calendar.getTime()));
        while(startTime<=nowTime){
            try {
                accountData.accountData(String.valueOf(startTime));
                calendar.add(Calendar.DATE,1);
                startTime = Integer.parseInt(df.format(calendar.getTime()));
            } catch (FssException e) {
                LogUtil.error(this.getClass(),e);
                continue;
            }
        }


    }

}
