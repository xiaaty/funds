package com.gqhmt.quartz.job.bid;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.BidTransferService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.bid.UploadFileJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:07
 * Description:
 * <p>异步处理转账job,满标,回款,其他ftp转账流程</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class UploadFileNewJob extends SupperJob{

    @Resource
    private BidTransferService bidTransferService;

    private static boolean isRunning = false;

    @Scheduled(cron="7 0/2 *  * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }

        if(isRunning) return;

        startLog("富友ftp批量处理 生成及上传交易文件");


        isRunning = true;

        try{
            bidTransferService.batchTransfer();
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
