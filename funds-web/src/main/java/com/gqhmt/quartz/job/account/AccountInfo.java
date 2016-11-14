package com.gqhmt.quartz.job.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoFileService;
import com.gqhmt.fss.architect.accounting.service.FssCheckDateService;
import com.gqhmt.quartz.job.SupperJob;
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
public class AccountInfo extends SupperJob {

    @Resource
    private FssCheckDateService fssCheckDateService;

    @Resource
    private FuiouAccountInfoFileService fuiouAccountInfoFileService;

    private static boolean isRunning = false;

    @Scheduled(cron = "0 15 18 * * *")
    public void execute() throws FssException {

        if(!isIp("upload")){
            return;
        }

        if (isRunning) return;

        startLog("金账户对账文件ftp批量处理 下载及导入文件");
        fssCheckDateService.insertDate();
        isRunning = true;

        Date date = new Date();

        FuiouAccountInfoFileEntity entityDJJD = fuiouAccountInfoFileService.getFileEntity(date, "DJJD");
        FuiouAccountInfoFileEntity entityZZ = fuiouAccountInfoFileService.getFileEntity(date, "ZZ");
        FuiouAccountInfoFileEntity entityHB = fuiouAccountInfoFileService.getFileEntity(date, "HB");
        FuiouAccountInfoFileEntity entityWTCZ = fuiouAccountInfoFileService.getFileEntity(date, "WTCZ");
        FuiouAccountInfoFileEntity entityWTTX = fuiouAccountInfoFileService.getFileEntity(date, "WTTX");
        FuiouAccountInfoFileEntity entityYSQ = fuiouAccountInfoFileService.getFileEntity(date, "YSQ");

        try {

            fuiouAccountInfoFileService.downFileAccountInfo(entityDJJD);
            fuiouAccountInfoFileService.downFileAccountInfo(entityZZ);
            fuiouAccountInfoFileService.downFileAccountInfo(entityHB);
            fuiouAccountInfoFileService.downFileAccountInfo(entityWTCZ);
            fuiouAccountInfoFileService.downFileAccountInfo(entityWTTX);
            fuiouAccountInfoFileService.downFileAccountInfo(entityYSQ);

        } catch (Exception e) {
            LogUtil.error(getClass(), e);
        } finally {
            isRunning = false;
        }
        endtLog();
    }

    //如果抓取失败。 20点到22点会自动抓取，间隔五分钟
    @Scheduled(cron = "0 0/5 20-22 * * *")
    public void inspect() throws FssException {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            map.put("createFileDate", dateStr);

            // 检测今天的抓取情况 如果有抓取失败的文件， 则重新抓取
            List<FuiouAccountInfoFileEntity> accountInfoFailList = fuiouAccountInfoFileService.queryFailAccInfoFileList(map);
            List<FuiouAccountInfoFileEntity> accountInfoList = fuiouAccountInfoFileService.querySucceedAccInfoFiltList(map);

            //判断 是否没有抓取失败记录 并且 抓取成功记录大于5
            if (accountInfoFailList.size() <= 0 && accountInfoList.size() > 5) {
                return;
            }

            execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }

}