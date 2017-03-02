package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsBatchTrade;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
 * <p>线上提现</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class RepaymentJob extends SupperJob{

    @Resource
    private FssTradeProcessService tradeProcessService;

    @Resource
    private IFundsBatchTrade fundsBatchTrade;

    private static boolean isRunning = false;
    @Scheduled(cron="39 0/1 * * * * ")
    public void execute() throws FssException {
        if(!isIp("upload")){
            return;
        }
       if(isRunning) return;
        startLog("借款人还款代扣");
        isRunning = true;
        try {
            List<TradeProcessEntity> list= tradeProcessService.processList("14010007");
            if(CollectionUtils.isNotEmpty(list)){
                for (TradeProcessEntity entity:list) {
                    //修改主交易状态，防止数据被下次定时任务重新扫到的情况出现
                    entity.setProcessState("10170004");
                    tradeProcessService.updateTradeProcessEntity(entity);
                    //查询子交易列表
                    List<TradeProcessEntity> replist= tradeProcessService.findByParentIdAndActionType("1401",entity.getId().toString());

                    for (TradeProcessEntity reEntity:replist) {
                        //得到充值子交易
                        if(StringUtils.equals("14010007",reEntity.getFundType())){
                            //启动线程池执行多线程任务
//                            executeprocess(reEntity);
                            ThreadExecutor.execute(runnableProcess(reEntity));
                        }
                    }
                }
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
     * @param entity
     * @return
     */
    public Runnable runnableProcess(final TradeProcessEntity entity) {
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = tradeProcessService.getCountByParentId(entity.getId());
                    int successCount = tradeProcessService.getSuccessCountByParentId(entity.getId());

                    if (count != 0 && count <= successCount) {
                        return;
                    }
                    List<TradeProcessEntity> moList = tradeProcessService.moneySplit(entity);
                    this.batch(moList);
                } catch (Exception e) {
                    LogUtil.error(getClass(), e);
                }
            }
//        }
//        return null;
//    }
//    public void executeprocess(TradeProcessEntity entity) {
//        try {
//            int count = tradeProcessService.getCountByParentId(entity.getId());
//            int successCount = tradeProcessService.getSuccessCountByParentId(entity.getId());
//
//            if (count != 0 && count <= successCount) {
//                return;
//            }
//            List<TradeProcessEntity> moList = tradeProcessService.moneySplit(entity);
//            this.batch(moList);
//        } catch (Exception e) {
//            LogUtil.error(getClass(), e);
//        }
//    }

            private void batch(List<TradeProcessEntity> moList){
                int flag = 0;  //是否中断
                String msg = "";
                for (TradeProcessEntity entity : moList) {
                    long startTime = Calendar.getInstance().getTimeInMillis();
                    try {
                        if(StringUtils.equals("10180003",entity.getWithHoldType())){
                            tradeProcessService.updateTradeProcessExecuteState(entity,2,"10170003");
                        }
                        if(flag == 0) {
                            fundsBatchTrade.batchTrade(entity);
                        }else{
                            tradeProcessService.updateTradeProcessExecuteState(entity,3,"10170003");//todo 增加失败原因ss
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
