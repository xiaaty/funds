package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.bonus.entity.FssBonusEntity;
import com.gqhmt.fss.architect.bonus.service.FssBonusService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.trade.BatchWithholdingJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:15
 * Description:
 * <p>红包job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  jhz      1.0     1.0 Version
 */
@Component
public class BonusJob extends SupperJob{


    @Resource
    private FssBonusService fssBonusService;

    private static boolean isRunning = false;
    @Scheduled(cron="37 0/6 * * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("红包");
        isRunning = true;
        try {
            List<FssBonusEntity> bonusEntities= fssBonusService.queryBonusList();
            for (FssBonusEntity entity:bonusEntities) {
                entity.setTradeState("10100004");
                fssBonusService.update(entity);
                fssBonusService.updateBonusExecuteState(entity);
            }
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
        }finally{
            isRunning = false;
        }
        endtLog();

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


}
