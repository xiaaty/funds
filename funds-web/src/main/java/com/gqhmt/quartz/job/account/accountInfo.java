package com.gqhmt.quartz.job.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import com.gqhmt.quartz.service.FtpUploadService;
import com.mysql.jdbc.log.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.shiro.crypto.hash.Hash;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/28.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/28.  xdw         1.0     1.0 Version
 */
@Component
public class accountInfo extends SupperJob {

    @Resource
    private FtpDownloadFileService ftpDownloadFileService;

    @Resource
    private FuiouAccountInfoService fuiouAccountInfoService;

    private static boolean isRunning = false;

    //@Scheduled(cron="0 15 10 ? * MON-FRI")
    @Scheduled(cron="0 15 18 ? * MON-FRI")
    public void execute() throws PayChannelNotSupports {
        /*if(!isIp("upload")){
            return;
        }*/

        //   if(isRunning) return;

        startLog("金账户对账文件ftp批量处理 下载及导入文件");


        isRunning = true;


        FuiouAccountInfoEntity fuiouAccountInfoEntityDJJD = new FuiouAccountInfoEntity();
        FuiouAccountInfoEntity fuiouAccountInfoEntityZZ = new FuiouAccountInfoEntity();
        FuiouAccountInfoEntity fuiouAccountInfoEntityHB = new FuiouAccountInfoEntity();
        FuiouAccountInfoEntity fuiouAccountInfoEntityWTCZ = new FuiouAccountInfoEntity();
        FuiouAccountInfoEntity fuiouAccountInfoEntityWTTX = new FuiouAccountInfoEntity();
        FuiouAccountInfoEntity fuiouAccountInfoEntityYSQ = new FuiouAccountInfoEntity();


        // 测试用的。
        // String dateStr = "20150701";
        // SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        boolean DJJD = false;
        boolean ZZ = false;
        boolean HB = false;
        boolean WTCZ = false;
        boolean WTTX = false;
        boolean YSQ = false;
        try{
            //       java.util.Date date=sdf.parse(dateStr);

            Date date = new Date();
            fuiouAccountInfoEntityDJJD.setTradingTime(date);
            fuiouAccountInfoEntityDJJD.setTradeType("DJJD");
            DJJD = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityDJJD);
            if(!DJJD){
                fuiouAccountInfoEntityDJJD.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityDJJD);
            }

            fuiouAccountInfoEntityZZ.setTradingTime(date);
            fuiouAccountInfoEntityZZ.setTradeType("ZZ");
            ZZ = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityZZ);
            if(!ZZ){
                fuiouAccountInfoEntityZZ.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityZZ);
            }

            fuiouAccountInfoEntityHB.setTradingTime(date);
            fuiouAccountInfoEntityHB.setTradeType("HB");
            HB = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityHB);
            if(!HB){
                fuiouAccountInfoEntityHB.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityHB);
            }

            fuiouAccountInfoEntityWTCZ.setTradingTime(date);
            fuiouAccountInfoEntityWTCZ.setTradeType("WTCZ");
            WTCZ = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityWTCZ);
            if(!WTCZ){
                fuiouAccountInfoEntityWTCZ.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityWTCZ);
            }

            fuiouAccountInfoEntityWTTX.setTradingTime(date);
            fuiouAccountInfoEntityWTTX.setTradeType("WTTX");
            WTTX = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityWTTX);
            if(!WTTX){
                fuiouAccountInfoEntityWTTX.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityWTTX);
            }

            fuiouAccountInfoEntityYSQ.setTradingTime(date);
            fuiouAccountInfoEntityYSQ.setTradeType("YSQ");
            YSQ = ftpDownloadFileService.downloadFuiouAccount(fuiouAccountInfoEntityYSQ);
            if(!YSQ){
                fuiouAccountInfoEntityYSQ.setBooleanType("-1");
                fuiouAccountInfoService.addFuiouAccountInfoEntity(fuiouAccountInfoEntityYSQ);
            }
        }catch (Exception e){
            LogUtil.error(getClass(),e);
        }finally {
            isRunning = false;
        }

        endtLog();
    }

    @Scheduled(cron="0 15 20 ? * MON-FRI")
    public void inspect() throws FssException {
        Map<String,String> map = new HashMap<String,String>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        map.put("tradingTime",dateStr);
        // 检测今天的抓取情况 如果有抓取失败的文件， 则重新抓取
        List<FuiouAccountInfoEntity> accountInfoFailList = fuiouAccountInfoService.queryAccountFailInfoList(map);
        if (accountInfoFailList.size()<0){
            return;
        }

        for(FuiouAccountInfoEntity accountInfoFail:accountInfoFailList){
            ftpDownloadFileService.downloadFuiouAccount(accountInfoFail);
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }

}