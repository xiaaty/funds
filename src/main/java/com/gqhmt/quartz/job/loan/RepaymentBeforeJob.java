package com.gqhmt.quartz.job.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.fuiouFtp.service.BidRepaymentService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.job.loan.RepaymentBeforeJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/5 19:42
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/5  于泳      1.0     1.0 Version
 */
public class RepaymentBeforeJob extends SupperJob {

    @Resource
    private FssLoanService fssLoanService;
    @Resource
    private BidRepaymentService repaymentService;

    @Scheduled(cron="0 0/1 *  * * * ")
    public void execute() throws PayChannelNotSupports {
        System.out.println("借款业务满回款 执行回款转账 ftp 上传记录 跑批");
        if(!isIp("upload")){
            return;
        }

        if(isRunning) return;
        super.isRunning = true;

        List<FssLoanEntity> loanEntities = fssLoanService.findLoanRepayment();

        for (FssLoanEntity loanEntity:loanEntities){
            try {
                repaymentService.BidRepayment(loanEntity);
            } catch (FssException e) {
                LogUtil.error(getClass(),e);
                continue;
            }
        }

        super.isRunning = false;


    }
}
