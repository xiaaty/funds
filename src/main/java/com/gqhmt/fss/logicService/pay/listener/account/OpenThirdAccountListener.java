package com.gqhmt.fss.logicService.pay.listener.account;

import com.gqhmt.fss.logicService.pay.event.account.CreateAccountEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.fss.logicService.pay.listener.account.OpenThirdAccount
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 17:02
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Component
public class OpenThirdAccountListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == CreateAccountEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.err.println("第三方开户");

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
