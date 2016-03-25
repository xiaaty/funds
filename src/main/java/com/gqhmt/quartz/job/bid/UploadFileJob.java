package com.gqhmt.quartz.job.bid;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.FtpUploadService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
public class UploadFileJob extends SupperJob implements Job{

    @Resource
    private FtpUploadService ftpUploadService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(isRunning) return;
        super.isRunning = true;

        try{
            ftpUploadService.upload();
            ftpUploadService.uploadFileToFtp();
        }catch (Exception e){
            LogUtil.error(getClass(),e);
        }finally {
            isRunning = false;
        }
        //生成数据文件

        //上传数据文件

    }
}
