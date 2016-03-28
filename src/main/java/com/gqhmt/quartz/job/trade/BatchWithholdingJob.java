package com.gqhmt.quartz.job.trade;

import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.fss.event.trade.WithholdingEvent;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
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
 * <p>批量代扣job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class BatchWithholdingJob{

    @Resource
    private ApplicationContext context;

    @Resource
    private FssTradeRecordService recordService;


    @Scheduled(cron="0 0/1 *  * * * ")
    public void execute() throws JobExecutionException {
        //
        List<FssTradeRecordEntity>  recordEntities = this.recordService.findNotExecuteRecodes();

        for(FssTradeRecordEntity entity:recordEntities){
            context.publishEvent(new WithholdingEvent(entity));
        }

    }
}
