package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.quartz.job.SupperJob;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.job.accounting.CheckHistoryAccounting
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author wanggp
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:  2016/11/11 0011.
 * Description:
 * <p/>
 * Modification History:
 * Date      Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/11 0011. wanggp         1.0     1.0 Version
 */
@Component
public class CheckHistoryAccounting extends SupperJob {
    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    private static boolean isRunning = false;

    @Scheduled(cron="0 0/5 02-06 * * *")
    public void execute() throws FssException {
        /*if(!isIp("upload")){
            return;
        }*/
        LogUtil.info(this.getClass(),"start--------------------历史标的对账开始");
        if(isRunning) return;

        startLog("历史标的对账开始");

        isRunning = true;
        try {
            fssCheckAccountingService.checkHistoryAccounting();
        }catch (Exception e){
            LogUtil.error(this.getClass(),e);
        }finally {
            isRunning = false;
        }
        endtLog();
    }
    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
