package com.gqhmt.tyzf.common.frame.common;

import java.util.ArrayList;

/**
 * Created by zhou on 2016/10/17.
 * Description: 抽象线程类
 */
public abstract class AbstractMultiThread  implements Runnable{

    /** 常量，停止状态 */
    public static final int STOPPED = 0;
    /** 常量，应该停止状态 */
    public static final int SHOULD_STOP = 1;
    /** 常量，运行状态 */
    public static final int RUN = 2;
    /** 当前状态 */
    private int status = STOPPED;

    //同步对象(保证对status更改是同步的)
    protected Object synObj = new Object();

    /** 停止原因，用于log */
    protected String stopReason = null;

    /** 被主线程控制的所有的处理对象，用于主控对象的实现 */
//    protected ArrayList<Object> handlers;

    /** 对主控制对象的引用，用于处理对象的实现 */
//    protected AbstractMultiThread mtc;

    protected boolean isDaemon = false;

    //执行类的线程对象
    private Thread thread = null;

    public boolean isDaemon()
    {
        return isDaemon;
    }

    public void askStop(){
        if(getStatus() == AbstractMultiThread.RUN){
            setStatus(SHOULD_STOP);
            stop();
        }
    }

    public void askStop(String reason)
    {
        this.stopReason = reason;
        this.askStop();
    }

    /**
     * 获取线程状态
     * @return
     */
    public int getStatus(){
        int s;
        synchronized(synObj)
        {
            s = this.status;
        }
        return s;
    }

    /**
     * 设置本线程的状态
     */
    protected void setStatus(int s)
    {
        synchronized(synObj)
        {
            this.status = s;
        }
    }

    /**
     * 抽象方法，由具体的类来完成如何停止，释放资源等等。
     */

    abstract protected void stop();

    /**
     * 抽象方法，由具体的类来完成如何启动。
     */
    abstract public void run();

//    protected void addHandler(Object h){
//        synchronized(handlers)
//        {
//            if(!handlers.contains(h))
//                handlers.add(h);
//        }
//    }
//
//    public void removeHandler(Object h){
//        synchronized(handlers)
//        {
//            if(handlers.contains(h))
//                handlers.remove(h);
//        }
//    }

    /**
     * 为当前抽象类线程对象设置值
     * @param t
     */
    public void setThread(Thread t){
        this.thread = t;
    }

    /**
     * 停止当前运行的线程
     */
    public void interruptThread(){
        if(null!=this.thread)
            this.thread.interrupt();
    }

}
