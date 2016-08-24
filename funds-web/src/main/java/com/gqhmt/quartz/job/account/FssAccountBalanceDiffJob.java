package com.gqhmt.quartz.job.account;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.service.FssAccountBalanceDiffService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;


/**
 * 账户余额校验定时任务
 * @author zhaoenyue
 */
@Component
public class FssAccountBalanceDiffJob extends SupperJob {
	
	@Resource
	private FssAccountBalanceDiffService fssAccountBalanceDiffService;

    private static boolean isRunning = false;

    //每天00:10执行
    @Scheduled(cron="0 10 0 * * ?")
    public void executeJob() throws PayChannelNotSupports {
    	
    	LogUtil.info(getClass(), "init账户余额校验定时任务");
    	
    	if(!isIp("upload")){
            return;
        }
    	
    	if(isRunning) {
        	return;
        }
        
        startLog("账户余额校验");
        isRunning = true;

        try {
        	fssAccountBalanceDiffService.validateBalance();
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
