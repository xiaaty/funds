package com.gqhmt.quartz.job.bid;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.AbortBidService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.bid.AbortBid
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:13
 * Description:
 * <p>流标</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class AbortBidJob extends SupperJob{

    @Resource
    private AbortBidService abortBidService;

    private static boolean isRunning = false;
    //    @Scheduled(cron="0  5 18 * * * ")
    @Scheduled(cron="0 0/1 *  * * * ")
    public void execute() throws FssException {
        if(!isIp("upload")){
            return;
        }

        if(isRunning) return;


        startLog("流标退款");

        isRunning = true;
        //执行流标操作
        try {
            abortBidService.abortBid();
        }catch (Exception e){
            LogUtil.error(getClass(),e);
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
