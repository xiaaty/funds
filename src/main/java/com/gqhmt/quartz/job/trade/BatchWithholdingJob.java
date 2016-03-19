package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class BatchWithholdingJob implements Job {

    @Resource
    private FssTradeApplyService applyService;

    @Resource
    private FssTradeRecordService recordService;

    @Resource
    private FssAccountService accountService;

    @Resource
    private IFundsTrade fundsTrade;


    @Override

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //
        List<FssTradeRecordEntity>  recordEntities = this.recordService.findNotExecuteRecodes();

        for(FssTradeRecordEntity entity:recordEntities){
            String  accNo = entity.getAccNo();
            try {
                FundOrderEntity orderEntity = this.fundsTrade.withholdingApplyNew(accNo,entity.getApplyNo(),entity.getAmount(),entity.getId());
                entity.setOrderNo(orderEntity.getOrderNo());
                this.recordService.updateTradeRecordExecuteState(entity,1,null);
            } catch (FssException e) {
                LogUtil.error(this.getClass(),e);
                this.recordService.updateTradeRecordExecuteState(entity,2,e.getMessage());//todo 增加失败原因ss
            }
        }

    }
}
