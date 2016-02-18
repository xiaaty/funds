package com.gqhmt.fss.listener.trade;

import com.gqhmt.fss.event.trade.WithdrawEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.pay.service.accounting.listener.WithdrawListener
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/31 16:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/31  于泳      1.0     1.0 Version
 */
@Component
public class AccountingWithdrawListener implements ApplicationListener<WithdrawEvent> {

    @Async
    @Override
    public void onApplicationEvent(WithdrawEvent withdrawEvent) {
        System.err.println("提现");
    }
}
