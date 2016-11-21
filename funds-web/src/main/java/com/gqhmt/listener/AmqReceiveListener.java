package com.gqhmt.listener;

import com.gqhmt.core.util.SpringUtils;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.common.AbstractMultiThread;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
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

//    @Resource
//    private ConversionService conversionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println(sce);
//        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
//        ConversionService conversionService = wac.getBean(ConversionService.class);
        System.out.println("AmqReceiveListener.contextInitialized() 开始");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AmqSendAndReceive asr = new AmqReceiver("AMQ.TTT3");
                System.out.println("接收线程启动");
                while (AmqReceiveListener.flag == 1) {
                    try {
                        Message msg = asr.receiveMessage();
                        TextMessage tm = (TextMessage) msg;
                        System.out.println("接收道的报文："+tm.getText());
//                        MessageConvertDto dto= conversionService.ReceiveMqMsg(tm.getText());
                    } catch (AmqException e) {
                        e.printStackTrace();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
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
        AmqReceiveListener.flag=0;
        System.out.println("AmqReceiveListener.contextInitialized() 结束 ");
        System.out.println("daemons.size="+daemons.size());
        for (int i = daemons.size() - 1; i >= 0; i--) {
            Thread t = (Thread) daemons.get(i);
            try {
                t.join(2*1000);
                t.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
