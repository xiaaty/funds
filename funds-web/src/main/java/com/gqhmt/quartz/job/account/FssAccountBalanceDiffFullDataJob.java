package com.gqhmt.quartz.job.account;

import javax.annotation.Resource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.service.FssAccountBalanceDiffFullDataService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;


/**
 * 账户余额校验全量数据定时任务
 * @author zhaoenyue
 */
@Component
@PropertySource("classpath:config/appContext.properties")
public class FssAccountBalanceDiffFullDataJob extends SupperJob {
	
	@Resource
	private FssAccountBalanceDiffFullDataService fssAccountBalanceDiffFullDataService;

    private static boolean isRunning = false;

    //每天凌晨1-6点  每10分钟执行一次
    @Scheduled(cron="${jobs.FssAccountBalanceDiffFullDataJob.time}")
    public void executeJob() throws PayChannelNotSupports {
    	
    	LogUtil.info(getClass(), "init账户余额校验全量数据定时任务");
    	
    	if(!isIp("upload")){
            return;
        }
    	
    	if(isRunning) {
        	return;
        }
        
        startLog("账户余额校验全量数据");
        isRunning = true;

        try {
        	//初始化待余额校验的全量账户数据表
        	fssAccountBalanceDiffFullDataService.initFullData();
        	//执行校验
        	fssAccountBalanceDiffFullDataService.validateBalanceFullData();
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
