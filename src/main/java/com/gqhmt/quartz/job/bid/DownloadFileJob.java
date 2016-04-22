package com.gqhmt.quartz.job.bid;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import com.gqhmt.quartz.service.FtpResultService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.bid.DownloadFileJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:09
 * Description:
 * <p>富友ftp回盘文件下载job</>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class DownloadFileJob extends SupperJob{


    @Resource
    private FtpDownloadFileService ftpDownloadFileService;

    @Resource
    private FtpResultService ftpResultService;

    private static boolean isRunning = false;
        @Scheduled(cron="0/27 * 7-23  * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
            if(isRunning) return;

            startLog("富友ftp批量处理 下载回盘结果及结果处理");

        isRunning = true;
        try{
            //下载文件
            //结果分析

            ftpDownloadFileService.downFile();
            ftpResultService.parseDownloadResult();
            ftpResultService.parseResult();
            ftpResultService.notReturnResult();

        }catch (Exception e){
            e.getMessage();
            LogUtil.error(this.getClass(),e.getMessage(),e);
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
