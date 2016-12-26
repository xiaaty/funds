package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.service.trade.IFundsBatchTrade;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.trade.BatchWithholdingJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:15
 * Description:
 * <p>批量代扣job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class BatchWithholdingJob extends SupperJob{

    @Resource
    private ApplicationContext context;

    @Resource
    private FssTradeRecordService fssTradeRecordService;
    @Resource
    private FssTradeApplyService fssTradeApplyService;

    @Resource
    private IFundsBatchTrade fundsBatchTrade;

    private static boolean isRunning = false;
    @Scheduled(cron="7 0/1 * * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("代扣代付");
        isRunning = true;
        try {
            List<FssTradeApplyEntity> applyEntities= fssTradeApplyService.getTradeAppliesByApplyState("10100002");
            for (FssTradeApplyEntity apply:applyEntities) {
            	//放入线程池前修改数据状态，防止数据被下次定时任务重新扫到的情况出现
            	apply.setApplyState("10100004");
                fssTradeApplyService.updateTradeApply(apply);
            	//启动线程池执行多线程任务
                ThreadExecutor.execute(runnableProcess(apply));
            }

		} catch (Exception e) {
			  LogUtil.error(getClass(),e);
		}finally{
			isRunning = false;
		}
        endtLog();
    }


    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 创建线程
     * @param apply
     * @return
     */
    public Runnable runnableProcess(final FssTradeApplyEntity apply){
    	 Runnable thread = new Runnable() {
             @Override
             public void run() {
                 try {
                     int count= fssTradeRecordService.getCountByApplyNo(apply.getApplyNo());
                     if (count!=0 && apply.getCount()<=apply.getSuccessCount()) {
                    	 return;
                     }
                     List<FssTradeRecordEntity> recordEntities = fssTradeRecordService.moneySplit(apply);
                     this.batch(recordEntities,apply.getBusinessNo(),apply.getCustType(),apply.getSeqNo());
                 } catch (Exception e) {
                	 LogUtil.error(getClass(),e);
                 }
             }

             private void batch(List<FssTradeRecordEntity> recordEntities,String contractNo,int custType,String seqNo){
                 int flag = 0;  //是否中断
                 String msg = "";
                 for (FssTradeRecordEntity entity : recordEntities) {
                     long startTime = Calendar.getInstance().getTimeInMillis();
                     try {
                         if(flag == 0) {
                             fundsBatchTrade.batchTrade(entity,contractNo,custType,seqNo);
                         }else{
                             fssTradeRecordService.updateTradeRecordExecuteState(entity,3,msg);//todo 增加失败原因ss
                         }
                     } catch (FssException e) {
                         msg = e.getMessage();
                         String breakMsg = Application.getInstance().getDictOrderValue("breakMsg");
                         if(breakMsg != null && breakMsg.contains(msg)){
                             flag = 1; //如果存在余额不足等，中断代扣、代付操作。
                         }
                     }
                     long endTime = Calendar.getInstance().getTimeInMillis();
                     LogUtil.info(getClass(), "代扣执行完成,共耗时:" + (endTime - startTime));
                 }
             }

         };
         return thread;
    }

}
