package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.buffer.IMsgBuffer;
import com.gqhmt.tyzf.common.frame.cache.ICacheManager;
import com.gqhmt.tyzf.common.frame.common.AbstractMultiThread;
import com.gqhmt.tyzf.common.frame.common.BasicStatus;
import com.gqhmt.tyzf.common.frame.common.Constants;
import com.gqhmt.tyzf.common.frame.common.IStatus;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exec.IEventAppsExec;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.IServiceObject;
import com.gqhmt.tyzf.common.frame.message.InterruptMessage;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.message.ServiceResponse;
import com.gqhmt.tyzf.common.frame.spring.BaseSpring;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/21.
 * Description:抽象基础处理线程
 */
public abstract class BaseHandler extends AbstractMultiThread implements IServiceHandler {
    //日志前缀
    protected String LOG_PREFIX = "BaseHandler";
    //配置管理对象
    protected ConfigManager cm = null;
    //数据库缓存对象
    protected ICacheManager cache = null;
    //系统编号
    protected String appSysID = null;
    // 应用系统名称
    protected String appSysName = null;
    //本地缓存，子类初始化
    protected IMsgBuffer receiveBuffer = null;

    /**
     * 构造函数
     * @throws FrameException
     */
    public BaseHandler() throws FrameException {
        cm = ConfigManager.getInstance();
        if (cm == null) {
            throw new FrameException(FrameConstans.CONFIGMGR_INIT_EERR);
        }
        // 设置应用系统编号
        appSysID = (String) cm.get("Adapter.SYSID");
        if (appSysID == null) {
            throw new FrameException(FrameConstans.CONFIGMGR_LACK_PARAMETER_EERR);
        }
        // 设置应用系统名称
        appSysName = (String) ConfigManager.getInstance().get("Adapter.NAME");
        if (appSysName == null) {
            throw new FrameException(FrameConstans.CONFIGMGR_LACK_PARAMETER_EERR);
        }
        // 调用缓存接口
        cache = (ICacheManager) ((BaseSpring) ConfigManager.getInstance().get(Constants.SPRING_INSTANCE))
                .getBean(Constants.CACHE_MANAGER_BEAN_ID);
        if (cache == null) {
            throw new FrameException(FrameConstans.CACHE_INIT_ERR);
        }
    }


    @Override
    public void run() {
        setStatus(AbstractMultiThread.RUN);
        while (this.getStatus() == AbstractMultiThread.RUN) {
            IServiceObject iServObj = null;
            try {
                iServObj = this.receiveBuffer.get();
                if(iServObj instanceof InterruptMessage) {
                    System.err.println("**********************************");
                    this.receiveBuffer.put((InterruptMessage)iServObj);
                    break;
                }

                if ((null == iServObj)) {
                    continue;
                }

            } catch (Exception e) {
                LogUtil.getInstance().error(e);
                continue;
            }
            try {
                ServiceRequest sRequest = (ServiceRequest) iServObj;
                requestMessageProc(sRequest);
                LogUtil.getInstance().info(LOG_PREFIX + "当前交易执行结束...........................");
            } catch (Exception ex) {
                LogUtil.getInstance().error("Handler Catched Exception:", ex);
            }
        }
    }

    /**
     * 请求消息处理
     * @param servRequest
     */
    public void requestMessageProc(ServiceRequest servRequest) {
        IEventAppsExec exec = null;
        try {
            // 转换服务请求为mo形式
            if (!BaseHandlerUtil.readXmlToMo(servRequest)) {
                return;
            }
            // 初始化本地应用程序类
            exec = BaseHandlerUtil.initEventAppsExec(servRequest);
            // 调用发送前的本地应用程序逻辑处理
            BaseHandlerUtil.executeBeforeSend(servRequest, exec);

        } catch (FrameException fex) {
            LogUtil.getInstance().error(fex, servRequest.getMo());
            return;
        }
    }

    @Override
    protected void stop() {
        // this.setStatus(AbstractMultiThread.SHOULD_STOP);
        if (getStatus() == STOPPED) {
            return;
        }
        release();
        setStatus(STOPPED);
    }

    @Override
    public void execute() {
        Thread thread = new Thread(this);
        this.setThread(thread);
        try {
            // 非守候线程
            isDaemon = false;
            thread.start();
        } catch (IllegalThreadStateException ie) {
            LogUtil.getInstance().error(ie);
            try {
                Thread.currentThread().join(100);
                if (thread != null) {
                    thread.start();
                }
            } catch (InterruptedException e) {
                LogUtil.getInstance().error(e);
            }
        }
    }

    @Override
    public void compensate() throws FrameException { }

    @Override
    public IStatus checkStatus() throws FrameException {
        LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus begin");
        try {
            LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
            if (getStatus() == AbstractMultiThread.RUN) {
                return new BasicStatus(true, HandlerConstants.CHECK_HANDLER_RUN_SUCCESS);
            } else if (getStatus() == AbstractMultiThread.SHOULD_STOP) {
                return new BasicStatus(false, HandlerConstants.CHECK_HANDLER_STOPPING_ERROR);
            } else {
                return new BasicStatus(false, HandlerConstants.CHECK_HANDLER_STOPPED_ERROR);
            }
        } catch (Exception e) {
            return new BasicStatus(false, e.getMessage());
        }
    }

    @Override
    public void refresh() throws FrameException {
    }

    @Override
    public abstract void release();

}
