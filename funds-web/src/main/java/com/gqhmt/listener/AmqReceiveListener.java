package com.gqhmt.listener;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.common.AbstractMultiThread;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Vector;

/**
 * Created by zhou on 2016/11/17.
 * Description:
 */
public class AmqReceiveListener implements ServletContextListener {

    public static int flag = 1;

    Vector<Thread> daemons = new Vector<Thread>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("AmqReceiveListener.contextInitialized() 开始");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AmqSendAndReceive asr = new AmqReceiver("AMQ.TEST03");
                System.out.println("接收线程启动");
                while (1 == 1) {
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
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        daemons.add(t);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("AmqReceiveListener.contextInitialized() 结束 ");
        System.out.println("daemons.size="+daemons.size());
        for (int i = daemons.size() - 1; i >= 0; i--) {
            Thread t = (Thread) daemons.get(i);
                t.interrupt();
        }
    }
}
