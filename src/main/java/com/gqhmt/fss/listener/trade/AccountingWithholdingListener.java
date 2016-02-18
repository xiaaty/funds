package com.gqhmt.fss.listener.trade;

import com.gqhmt.fss.event.trade.WithholdingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

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
@Component
public class AccountingWithholdingListener  implements SmartApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.err.println(this.getClass().toString());
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == WithholdingEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
