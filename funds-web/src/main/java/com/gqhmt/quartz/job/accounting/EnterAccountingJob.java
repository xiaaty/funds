package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;
import com.gqhmt.quartz.job.SupperJob;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.accounting.EnterAccountingJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:04
 * Description:
 * <p>入账job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class EnterAccountingJob extends SupperJob {
	
	@Resource
	private FssEnterAccountService fssEnterAccountService;
	
	 private static boolean isRunning = false;
	@Scheduled(cron="3 0/7 *  * * * ")
    public void execute( ) throws JobExecutionException, FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;

		startLog("入账");

        isRunning = true;
        try {
			List<FssEnterAccountParentEntity> enterAccountParentByState = fssEnterAccountService.getEnterAccountParentByState();
			if(enterAccountParentByState.size()>0) {
				for (FssEnterAccountParentEntity fssEnterAccountParentEntity : enterAccountParentByState) {
					fssEnterAccountService.enterAccounting(fssEnterAccountParentEntity);
				}
			}

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
