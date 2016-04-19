package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;
import com.gqhmt.quartz.job.SupperJob;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobExecutionException;

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
public class EnterAccountingJob extends SupperJob {
	
	@Resource
	private FssEnterAccountService fssEnterAccountService;
	
    public void execute( ) throws JobExecutionException, FssException {
    	System.out.println("入账 跑批");
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        super.isRunning = true;
    	List<FssEnterAccountParentEntity> enterAccountParentByState = fssEnterAccountService.getEnterAccountParentByState();
    	for (FssEnterAccountParentEntity fssEnterAccountParentEntity : enterAccountParentByState) {
    		fssEnterAccountService.enterAccounting(fssEnterAccountParentEntity);
		}
    }
}
