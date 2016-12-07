package com.gqhmt.core.thread;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.ConversionService;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Filename:    com.gqhmt.core.thread.AsyncThreadSendMq
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/1 14:54
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/1  于泳      1.0     1.0 Version
 */
public class AsyncThreadSendMq {
    private static final int POOL_SIZE = 50;
    private AsyncThreadSendMqBuffer<MessageConvertDto> buffer;

    private static final AsyncThreadSendMq instance = new AsyncThreadSendMq();
    private ConversionService conversionService;

    private final Thread threadDemo;

    private static int executeFlag = 1;


    private AsyncThreadSendMq(){
        conversionService = new ConversionService();
        buffer = new AsyncThreadSendMqBuffer();
        this.threadDemo = this.startDemo();
    }

    public static AsyncThreadSendMq getInstance(){
        return instance;
    }
    private ExecutorService executorService;

    public  void sendMqMsg(MessageConvertDto dto) throws InterruptedException, FssException {
        if(AsyncThreadSendMq.executeFlag !=1 ){
            throw new FssException("服务即将停止，请稍后再试。。。。。");
        }
        try {
            buffer.put(dto);
            LogUtil.info(AsyncThreadSendMq.class,"异步消息发送进入队列:当前队列数："+buffer.getNum());
        } catch (InterruptedException e) {
            LogUtil.info(AsyncThreadSendMq.class,"异步消息发送进入队列失败");
            throw e;
        }
    }

    private ExecutorService getExecutor() {
        if (executorService == null || executorService.isShutdown()) {
            synchronized (ThreadFactory.class) {
                if (executorService == null || executorService.isShutdown()) {
                    executorService = Executors.newFixedThreadPool(POOL_SIZE);
                }
            }
        }
        LogUtil.info(AsyncThreadSendMq.class,executorService.getClass().getName());
        return executorService;
    }


    private Thread startDemo(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogUtil.info(this.getClass(),"异步消息发送守护线程启动。。。。");
                while(AsyncThreadSendMq.executeFlag == 1){

                    if(buffer.getNum() == 0){
                        LogUtil.info(this.getClass(),"异步消息发送守护线程队列为空，休息一下。。。。");
                        this.sleep();
                    }
                    LogUtil.info(this.getClass(),"异步消息发送守护线程执行发送。。。。 当前队列数："+buffer.getNum());
                    if(threadPoolCheck()){
                        try {
                            MessageConvertDto dto = null;
                            if(buffer == null) continue;
                            dto = buffer.get();
                            if(dto == null) continue;
                            Runnable runnable1 = executor(dto);
                            if(runnable1 == null ) continue;
                            getExecutor().execute(runnable1);
                            LogUtil.info(AsyncThreadSendMq.class,"消息发送进入多线程池：当前队列数："+buffer.getNum());
                        } catch (InterruptedException e) {
                            LogUtil.error(AsyncThreadSendMq.class,e);
                        }

                        continue;
                    }
                    LogUtil.info(this.getClass(),"执行失败");
                    this.sleep();
                }
                LogUtil.info(this.getClass(),"异步消息发送守护线程停止。。。。");
            }

            /**
             * 线程池可用判断
             * @return
             */
            private boolean threadPoolCheck(){
                if(!(executorService instanceof ThreadPoolExecutor)){
                    LogUtil.info(this.getClass(),"非ThreadPoolExecutor继续执行");
                    return true;
                }
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;
                long count = threadPoolExecutor.getTaskCount();
                long complete = threadPoolExecutor.getCompletedTaskCount();
                LogUtil.info(this.getClass(),"ThreadPoolExecutor执行校验当前任务数:"+count+":"+complete);
                if(count - complete <= POOL_SIZE){
                    return true;
                }
                return false;
            }

            /**
             * 睡眠函数
             */
            private void sleep(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();

        return t;
    }

    private final Runnable executor(final MessageConvertDto dto){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogUtil.info(this.getClass(),"消息发送执行");
                try {
                    conversionService.sendAndReceiveMsg(dto);
                } catch (Exception e) {
                    LogUtil.error(this.getClass(),e.getMessage(),e);
                }
            }
        };

        return runnable;
    }


    public void drop(){

//        while (true){
//            if(buffer.isEmpty()){
//
//            }
//        }
        this.buffer = null;
        AsyncThreadSendMq.executeFlag = 0;


        try {

            threadDemo.join(2 * 1000);
            Thread.sleep(2*1000);
            threadDemo.interrupt();
//            break;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogUtil.info(this.getClass(),"异步消息发送守护线程结束");
    }

}
