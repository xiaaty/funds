package com.gqhmt.quartz.job.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.fuiouFtp.service.BidAbortService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.job.loan.AbortBidBeforeJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/21 09:29
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/21  于泳      1.0     1.0 Version
 */
@Component
public class AbortBidBeforeJob extends SupperJob {

    @Resource
    private FssLoanService fssLoanService;
    @Resource
    private BidAbortService abortService;

    private static boolean isRunning = false;
//    @Scheduled(cron="0 0/1 *  * * * ")
    public void execute() throws PayChannelNotSupports {

        if(!isIp("upload")){
            return;
        }


        if(isRunning) return;

        startLog("借款业务流标 执行流标前置");
        isRunning = true;

        List<FssLoanEntity> loanEntities = fssLoanService.findAbortBid();

        for (FssLoanEntity loanEntity:loanEntities){
            try {
                abortService.bidAbort(loanEntity);
            } catch (FssException e) {
                LogUtil.error(getClass(),e);
                continue;
            }
        }



        isRunning = false;


        endtLog();

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
