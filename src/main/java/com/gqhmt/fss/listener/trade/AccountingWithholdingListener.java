package com.gqhmt.fss.listener.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.event.trade.WithholdingEvent;
import com.gqhmt.pay.service.trade.IFundsBatchTrade;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Filename:    com.gqhmt.fss.listener.trade.AccountingWithholdingListener
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/31 17:10
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/31  于泳      1.0     1.0 Version
 */
//@Component
public class AccountingWithholdingListener  implements ApplicationListener<WithholdingEvent> {

    @Resource
    private IFundsBatchTrade fundsBatchTrade;

    @Override
    public void onApplicationEvent(WithholdingEvent withholdingEvent) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        FssTradeRecordEntity entity = (FssTradeRecordEntity) withholdingEvent.getSource();
        fundsBatchTrade.batchTrade(entity);
        long endTime = Calendar.getInstance().getTimeInMillis();
        LogUtil.info(getClass(),"代扣执行完成,共耗时:"+(endTime-startTime));

    }
}
