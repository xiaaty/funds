package com.gqhmt.listener;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhou on 2016/11/17.
 * Description:
 */
public class AmqReceiveListener implements ServletContextListener {

    public static int flag = 1;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("AmqReceiveListener.contextInitialized() 开始");
        Thread t = new Thread() {
            @Override
            public void run() {
                AmqSendAndReceive asr = new AmqReceiver("AMQ.TEST03");
                while (true) {
                    try {
                        Message msg = asr.receiveMessage();
                        TextMessage tm = (TextMessage) msg;
                        System.out.println(tm.getText());
                    } catch (AmqException e) {
                        e.printStackTrace();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("AmqReceiveListener.contextInitialized() 结束 ");
    }
}
