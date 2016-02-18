package com.gqhmt.fss.listener.account;

import com.gqhmt.fss.event.account.CreateAccountEvent;
import com.gqhmt.sys.session.SessionFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.fss.listener.account.CreateLocalAccount
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 17:03
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Component
public class CreateLocalAccountListener implements SmartApplicationListener {
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

      /*  try {
            Thread.sleep(1000*10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.err.println("创建本地账户:"+ SessionFactory.getCode());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
