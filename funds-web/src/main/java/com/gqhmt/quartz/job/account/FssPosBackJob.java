package com.gqhmt.quartz.job.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.card.entiry.FssPosBackEntity;
import com.gqhmt.fss.architect.card.service.FssPosBackService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * jhz
 * pos签约回调JOB
 */
@Component
public class FssPosBackJob extends SupperJob {
	
	@Resource
	private FssPosBackService fssPosBackService;
    @Resource
    private CustomerInfoService customerInfoService;


	 private static boolean isRunning = false;
    //每隔2分钟执行一次
	@Scheduled(cron="50 0/1 * * * *")
    public void execute( )throws FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;

		startLog("pos签约回调JOB跑批");
//        获得今天之前3天所有pos签约回调
        List<FssPosBackEntity> lists=fssPosBackService.selectPosBacks();

        if(CollectionUtils.isEmpty(lists)){
            return;
        }
        isRunning = true;
        try {
            for (FssPosBackEntity posBack:lists) {
                //修改客户表签约状态
                customerInfoService.updateCustomerState(posBack.getMobileNo(),posBack.getContractSt(),posBack.getBankNo());
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
        return false;
    }
}
