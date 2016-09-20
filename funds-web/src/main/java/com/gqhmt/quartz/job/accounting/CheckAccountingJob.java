package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.quartz.job.SupperJob;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * jhz
 * 充值体现对账JOB
 */
@Component
public class CheckAccountingJob extends SupperJob {
	
	@Resource
	private FssCheckAccountingService fssCheckAccountingService;
	
	 private static boolean isRunning = false;
    //每天00:05执行一次
	@Scheduled(cron="0 5 0 * * ?")
    public void execute( ) throws JobExecutionException, FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;

		startLog("充值体现对账");

        isRunning = true;
        try {
			fssCheckAccountingService.checkAccounting();

        }catch (Exception e){
            LogUtil.error(this.getClass(),e);

        }finally {
            isRunning = false;
        }

		endtLog();
    }

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
