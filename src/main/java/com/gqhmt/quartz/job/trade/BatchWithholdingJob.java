package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.service.trade.IFundsBatchTrade;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
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
 * <p>批量代扣job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class BatchWithholdingJob extends SupperJob{

    @Resource
    private ApplicationContext context;

    @Resource
    private FssTradeRecordService recordService;

    @Resource
    private IFundsBatchTrade fundsBatchTrade;

    private static boolean isRunning = false;
    @Scheduled(cron="0 0/1 * * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("借款业务流标 执行流标前置 ");
        isRunning = true;
        List<FssTradeRecordEntity>  recordEntities = this.recordService.findNotExecuteRecodes();

        for(FssTradeRecordEntity entity:recordEntities){
            long startTime = Calendar.getInstance().getTimeInMillis();
            fundsBatchTrade.batchTrade(entity);
            long endTime = Calendar.getInstance().getTimeInMillis();
            LogUtil.info(getClass(),"代扣执行完成,共耗时:"+(endTime-startTime));
        }
        endtLog();
        isRunning = false;
    }


    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
