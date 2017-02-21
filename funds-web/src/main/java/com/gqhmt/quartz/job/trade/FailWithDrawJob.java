package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.trade.BatchWithholdingJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:15
 * Description:
 * <p>线上提现失败job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class FailWithDrawJob extends SupperJob{

    @Resource
    private FssTradeProcessService tradeProcessService;


    private static boolean isRunning = false;
    @Scheduled(cron="36 0/5 * * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("线上提现失败JOB");
        isRunning = true;
        try {
            //获得提现失败数据
            List<TradeProcessEntity> list= tradeProcessService.getFailWithDrawProcess();
            if(CollectionUtils.isNotEmpty(list)){
                for (TradeProcessEntity entity:list) {
                    tradeProcessService.checkResult(entity);
                }
            }
		} catch (Exception e) {
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
