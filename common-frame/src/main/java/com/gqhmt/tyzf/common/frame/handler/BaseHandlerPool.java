package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.common.BasicStatus;
import com.gqhmt.tyzf.common.frame.common.IConfigurable;
import com.gqhmt.tyzf.common.frame.common.IStatus;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhou on 2016/10/21.
 * Description:Handler线程池
 */
public abstract class BaseHandlerPool implements IConfigurable {

    // 日志前缀
    protected String LOG_PREFIX = "BaseHandlerPool";
   // 线程池数组
    private Vector<IServiceHandler> pool = new Vector<IServiceHandler>();
    //类名
    protected String prefix = null;
    protected ExecutorService executor;

    /**
     * 构造函数
     * @throws FrameException
     */
    public BaseHandlerPool() throws FrameException {
        IHandlerParameter para = this.initCfgData();
        for (int i = 0; i < para.getHandlerNumber(); i++) {
            LogUtil.getInstance().debug(LOG_PREFIX+" handler classname: "+para.getHandlerClassName());
            Object instance = null;
            try {
                instance = Class.forName(para.getHandlerClassName().trim()).newInstance();
            } catch (Exception e) {
                LogUtil.getInstance().error(e);
                throw new FrameException(FrameConstans.HANDLER_INIT_ERR,e);
            }
            if(instance==null){
                throw new FrameException(FrameConstans.HANDLER_INIT_ERR);
            }
            IServiceHandler handler = (IServiceHandler)instance;
            pool.add(handler);
        }
    }

    /**
     * 初始化配置参数（由子类实现）
     */
    protected abstract IHandlerParameter initCfgData()throws FrameException;

    @Override
    public void refresh() throws FrameException {

    }

    //释放线程池资源.
    public void release() {
        for (int i = 0; i < pool.size(); i++) {
            IServiceHandler thread = (IServiceHandler) pool.get(i);
            System.out.println("thread="+thread);
            thread.askStop();
        }
        pool.clear();
        executor.shutdown();
        boolean flag = false;
        while(!flag) {
            try {
                flag = executor.awaitTermination(1, TimeUnit.SECONDS);
                if(!flag)
                    System.out.println("等待处理线程处理完毕");
            } catch (InterruptedException e) {
                LogUtil.getInstance().error(e);
            }
        }
        LogUtil.getInstance().debug(LOG_PREFIX + " thread pool has released!");
    }

    /**
     * 检查状态
     */
    public IStatus checkStatus() throws FrameException {
        return new BasicStatus(true, LOG_PREFIX + HandlerConstants.CHECK_HANDLERPOOL_RUN_SUCCESS + " ，本地服务池 "+LOG_PREFIX+" 中共有[ "
                + pool.size() + " ]个线程对象");
    }

    /**
     * 将所有的BizHandler都运行起来 它们会因为得不到请求消息而阻塞
     * @throws FrameException
     */
    public void compensate() throws FrameException {
        for (int i = 0; i < pool.size(); i++) {
            BaseHandler handler=(BaseHandler) pool.get(i);
            executor.execute(handler);
        }
    }

}
