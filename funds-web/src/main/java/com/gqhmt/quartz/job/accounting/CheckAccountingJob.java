package com.gqhmt.quartz.job.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.accounting.service.FssCheckDateService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * jhz
 * 充值体现对账JOB
 */
@Component
public class CheckAccountingJob extends SupperJob {
	
	@Resource
	private FssCheckAccountingService fssCheckAccountingService;

    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private FssCheckDateService fssCheckDateService;

	 private static boolean isRunning = false;
    //每天2点到6点执行，每隔1分钟执行一次
	@Scheduled(cron="30 0/1 02-06 * * *")
//	@Scheduled(cron="25 0/1 * * * *")
    public void execute( )throws FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;

		startLog("充值体现转账对账跑批");
        FssCheckDate orderDate= fssCheckDateService.getOrderDate();
        if (orderDate==null){
            return;
        }
        LogUtil.info(this.getClass(),"核对"+orderDate.getOrderDate()+"的对账");
        isRunning = true;
        try {
            //查询所有充值体现转账新版满标新版回款数据
//            List<FundOrderEntity> orderEntities=fundOrderService.getOrders(orderDate.getOrderDate());
            //查询富友对账信息
            List<FssCheckAccountingEntity> checkAccountings=fssCheckAccountingService.getCheckAccounts(orderDate.getOrderDate());
            if(CollectionUtils.isEmpty(checkAccountings)){
                return;
            }
            int i=0;
            for (FssCheckAccountingEntity check:checkAccountings) {
                fssCheckAccountingService.checkFundOrder(check);
                i++;
            }
            LogUtil.info(this.getClass(),"本批次共核对"+i+"条对账信息");

        }catch (Exception e){
            LogUtil.error(this.getClass(),e);

        }finally {
            fssCheckDateService.updateOrderUserState(orderDate);
            isRunning = false;
        }

		endtLog();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
