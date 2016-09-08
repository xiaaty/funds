package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/7.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/7.  xdw         1.0     1.0 Version
 */
@Component
public class TradeInfo extends SupperJob {

    @Resource
    private FtpDownloadFileService ftpDownloadFileService;

    @Value("#{configProperties['tradeInfoPath']}")
    private String tradeInfoPath;

    @Value("#{configProperties['prefixFileName']}")
    private String prefixFileName;

    @Value("#{configProperties['createTime']}")
    private String createTime;

    private static boolean isRunning = false;
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Scheduled(cron = "0 0/10 18-22 * * *")
    public void execute() throws FssException, ParseException {
        if(isRunning) return;
        startLog("线下充值回盘记录");
        isRunning = true;
        String[] timeAll = createTime.split(",");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = new Date();
        String today = sdf.format(date);

        List<Date> fileCreateTime = new ArrayList<Date>();

        for(int i=0; i<timeAll.length; i++){
            String createTimeStr = today+ " " +timeAll[i];
            fileCreateTime.add(sdf2.parse(createTimeStr));
        }


        System.out.println(tradeInfoPath);
        System.out.println(prefixFileName);
        for(int i=0; i<fileCreateTime.size(); i++){
            ftpDownloadFileService.downloadTradeInfo(fileCreateTime.get(i),prefixFileName,tradeInfoPath);
        }
    }

}
