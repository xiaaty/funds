package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.accounting.service.FssCheckDateService;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.quartz.job.accounting.CheckHistoryAccounting
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author wanggp
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:  2016/11/11 0011.
 * Description:
 * <p/>
 * Modification History:
 * Date      Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/11 0011. wanggp         1.0     1.0 Version
 */
@Component
public class CheckHistoryAccounting extends SupperJob {
    @Resource
    private FssCheckAccountingService fssCheckAccountingService;
    @Resource
    private FssCheckDateService fssCheckDateService;

    private static boolean isRunning = false;

    @Scheduled(cron="0 0/1 02-06 * * *")
    public void execute() throws FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;

        startLog("满标回款历史标的对账");

        FssCheckDate fssCheckDate = fssCheckDateService.queryDate(); //20150601之后的日期
        if (null == fssCheckDate) {
            return;
        }
        isRunning = true;
        try {
            LogUtil.info(this.getClass(),"满标回款历史对账，当前查询批次日期为：" + fssCheckDate.getOrderDate());
            if (StringUtils.isNotEmpty(fssCheckDate.getOrderDate())) {
                fssCheckAccountingService.checkHistoryAccount(fssCheckDate.getOrderDate());
            }
        } catch (FssException e) {
            throw new FssException("满标回款查询对账定时任务异常，当前查询批次日期为[]", e);
        } finally {
            fssCheckDateService.updateInputUserState(fssCheckDate);//更新对账日期为已对账
            LogUtil.info(this.getClass(),"满标回款历史对账更新为已对账，当前更新批次日期为：" + fssCheckDate.getOrderDate());
            isRunning = false;
        }

        endtLog();
    }
    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
