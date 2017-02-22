package com.gqhmt.common.context;

import com.gqhmt.common.base.*;
import com.gqhmt.common.exception.FrameException;
import com.gqhmt.common.log.Logger;
import com.gqhmt.common.server.IMQReceiveServer;

import java.util.*;

/**
 * Filename:    com.gqhmt.common.context.XmlConfigureCloseContext
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/22 09:58
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/22  于泳      1.0     1.0 Version
 */
public class XmlConfigureCloseContext implements ContextConstants{

    private XMLConfigureInitContext xmlConfigureInitContext;

    public XmlConfigureCloseContext(XMLConfigureInitContext initContext){
        this.xmlConfigureInitContext = initContext;
        checkStatus();
    }

    /**
     * 检查状态
     * @return
     */
    private void checkStatus() {
        Properties instances =  xmlConfigureInitContext.getInstances();
        for (Iterator<Object> components = instances.keySet().iterator(); components.hasNext();) {
            String element = (String) components.next();
            if (element != null) {
                Object component = instances.get(element);
                if (component instanceof IConfigurable) {
                    IConfigurable iconfig = (IConfigurable) component;
                    String componentName = element.substring(0,
                            element.indexOf(Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE));
                    String infoMessage = null;
                    IStatus istatus = null;
                    Logger.info(getClass(),componentName + " checkStatus Begin");
                    try {
                        istatus = iconfig.checkStatus();
                    } catch (FrameException e) {
                        // e.printStackTrace();
                        istatus = new BasicStatus(false, componentName, "", "Status unknown");
                    }
                    if (istatus == null) {
                        istatus = new BasicStatus(false, componentName, "", "Status unknown");
                    }
                    if (istatus.getStatus()) {
                        infoMessage = componentName + " : " + istatus.getDetail();
                    } else {
                        infoMessage = componentName + " : " + istatus.getDetail();
                    }
                    Logger.info(getClass(),infoMessage);
                    Logger.info(getClass(),componentName + " checkStatus End");
                }
            }
        }
    }


    /**
     * 停止方法
     * @return
     */
    public void stop() throws FrameException {
        if(xmlConfigureInitContext == null){
            throw new FrameException("");
        }

        List<String> sequence = xmlConfigureInitContext.sequence();

        Properties instances = xmlConfigureInitContext.getInstances();
        /**1.停掉mq接收线程*/
        for(int i = sequence.size(); i > 0; i--){
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof IMQReceiveServer)) {
                stopOperation(instance,name);
            }
        }

        /**2.处理接收Buffer*/
        Set<String> receiveBufferNameSet = new HashSet<String>(); //所有接收buffer名.
        Set<String> sendBufferNameSet = new HashSet<String>();//所有的发送buffer名.
        //2.1 get所有的接收buffer名和发送buffer名称
        for(int i = sequence.size(); i > 0; i--){
            String name = sequence.get(i - 1);
            if(name.trim().split("\\.").length == 2) {
                String receiveVal = xmlConfigureInitContext.getValueProperty(name + ".ReceiveBuffer");
                String sendVal = xmlConfigureInitContext.getValueProperty(name + ".SendBuffer");
                if(receiveVal != null && receiveVal.trim().length() > 0) {
                    receiveBufferNameSet.add(receiveVal.trim());
                }
                if(sendVal != null && sendVal.trim().length() > 0) {
                    sendBufferNameSet.add(sendVal.trim());
                }
            }
        }
//        //2.2 get所有的接收buffer和所有的发送buffer对象
//        Set<ESBMsgBuffer> receiveBufferSet = new HashSet<ESBMsgBuffer>();
//        Set<ESBMsgBuffer> sendBufferSet = new HashSet<ESBMsgBuffer>();
//        for (int i = sequence.size(); i > 0; i--) {
//            String name = sequence.get(i - 1);
//            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
//            Object instance = instances.get(instanceName);
//            if ((instance != null) && (instance instanceof ESBMsgBuffer)) {
//                if(receiveBufferNameSet.contains(name.trim())) {
//                    receiveBufferSet.add((ESBMsgBuffer)instance);
//                }
//                if(sendBufferNameSet.contains(name.trim())) {
//                    sendBufferSet.add((ESBMsgBuffer)instance);
//                }
//            }
//        }
//        System.out.println("2.receiveBufferSet="+receiveBufferSet.toString());
//        System.out.println("2.sendBufferSet="+receiveBufferSet.toString());
//        //2.3 等待所有的接收buffer都为0
//        for(ESBMsgBuffer receiveBuffer : receiveBufferSet) {
//            boolean flag = true;
//            try {
//                while(flag) {
//                    int num = receiveBuffer.getMsgBuffer().getNum();
//                    System.out.println("3.receiveBuffer.getMsgBuffer().getNum()="+receiveBuffer.getMsgBuffer().getNum());
//                    if (num == 0) {
//                        flag = false;
//                    }
//                }
//            }catch(FrameException e) {
//                LogUtil.getInstance().error(e);
//            }
//        }
//        //2.4 在空的接收Buffer里发送一个中断消息
//        for(ESBMsgBuffer receiveBuffer : receiveBufferSet) {
//            try {
//                IServiceObject im = new InterruptMessage(); //中断消息.
//                receiveBuffer.getMsgBuffer().put(im);
//                System.out.println("4.receiveBuffer.getMsgBuffer()="+receiveBuffer.getMsgBuffer());
//            } catch(FrameException e) {
//                LogUtil.getInstance().error(e);
//            }
//        }
//        System.out.println("4.receiveBufferSet="+receiveBufferSet.toString());
//
//        /**3 业务处理线程池*/
//        //3.1 线程池资源的释放
//        for (int i = sequence.size(); i > 0; i--) {
//            String name = sequence.get(i - 1);
//            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
//            Object instance = instances.get(instanceName);
//            if ((instance != null) && (instance instanceof BaseHandlerPool)) {
//                ((BaseHandlerPool)instance).release();
//            }
//        }
//        //5、等待发送buffer size 为0.
//        for(ESBMsgBuffer sendBuffer : sendBufferSet) {
//            boolean flag = true;
//            try {
//                while(flag) {
//                    int num = sendBuffer.getMsgBuffer().getNum();
//                    if (num == 0) {
//                        flag = false;
//                    }
//                }
//            }catch(FrameException e) {
//                LogUtil.getInstance().error(e);
//            }
//        }
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            LogUtil.getInstance().error(e);
//        }
//        //6、停掉发送线程
//        for(int i = sequence.size(); i > 0; i--){
//            String name = sequence.get(i - 1);
//            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
//            Object instance = instances.get(instanceName);
//            if ((instance != null) && (instance instanceof BaseMQMsgSend)) {
//                stopOperation(instance,result,name);
//            }
//        }
//
//        //7、停掉基础线程
//        for (int i = sequence.size(); i > 0; i--){
//            String name = sequence.get(i - 1);
//            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
//            Object instance = instances.get(instanceName);
//            if ((instance != null) && (instance instanceof BaseThreadHandler)) {
//                ((BaseThreadHandler)instance).release();
//            }
//        }
//
//        //7、释放所有组件资源
//		/* Release all component resources */
//        for (int i = sequence.size(); i > 0; i--) {
//            String name = sequence.get(i - 1);
//            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
//            Object instance = instances.get(instanceName);
//            if ((instance != null) && (instance instanceof IConfigurable)) {
//                if(instance instanceof BaseHandlerPool || instance instanceof BaseMQMsgReceive || instance instanceof BaseMQMsgSend)
//                    continue;
//                ((IConfigurable) instance).release();
//                String infoMessage = MSG_COMPONENT_RELEASE + name;
//                result.add(infoMessage);
//                if (LogUtil.getInstance() != null) {
//                    LogUtil.getInstance().info(infoMessage);
//                }
//            }
//        }
//
//		/* Release resources of ConfigManager */
//        cache.clear();
//        instances.clear();
//        parser = null;
//        document = null;
//        root = null;
//        configManager = null;
//		/* Step 2 : Register the shutdown hook */
//        Runtime.getRuntime().removeShutdownHook(hook);
//        hook = null;
//
//		/* Set running false */
//        running = false;

		/* Log and exit */
        Logger.info(getClass(),MSG_STOP);
        Logger.info(getClass(),MSG_STOP);
    }


    /**
     * 停止接收/发送线程
     * @param instance
     * @param name
     */
    private void stopOperation(Object instance, String name) {
        AbstractMultiThread thread = (AbstractMultiThread) instance;
        if (AbstractMultiThread.RUN == thread.getStatus()) {
            thread.askStop();
        }
        if (AbstractMultiThread.STOPPED != thread.getStatus()) {
            try {
                Thread.currentThread().join(50);
            } catch (Exception e) {
            }
            if (AbstractMultiThread.STOPPED != thread.getStatus()) {
                // interrupt线程(停止接收/发送线程，当阻塞时将抛异常，不用处理，线程依旧会被停掉)
                thread.interruptThread();
                if (AbstractMultiThread.STOPPED == thread.getStatus()) {
                    printStopDaemonInfo(name, true);
                } else {
                    try {
                        Thread.currentThread().join(50);
                    } catch (Exception e) {
                    }
                    if (AbstractMultiThread.STOPPED == thread.getStatus()) {
                        printStopDaemonInfo(name, true);
                    } else {
                        printStopDaemonInfo(name,  false);
                    }
                }
            } else {
                printStopDaemonInfo(name,  true);
            }
        } else {
            printStopDaemonInfo(name, true);
        }
    }

    /**
     * 打印守护线程停止信息
     * @param name
     * @param flag
     */
    private void printStopDaemonInfo(String name, boolean flag) {
        String infoMessage = null;
        if (flag) {
            infoMessage = MSG_DAEMON_STOP + name;
        } else {
            infoMessage = MSG_DAEMON_STOP_EXCEPTION + name;
        }
        System.out.println(infoMessage);
           Logger.info(getClass(),infoMessage);
    }

}
