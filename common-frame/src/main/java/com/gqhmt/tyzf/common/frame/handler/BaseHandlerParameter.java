package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.common.StringUtils;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/25.
 * Description: 基础处理参数类
 */
public abstract class BaseHandlerParameter implements IHandlerParameter {

    // 线程池线程数量
    protected int handlerNumber;
    // 停止每个Handler的等待时间，单位秒
    protected int handlerStopWaitTime;
    // 执行线程名称
    protected String handlerClassName;

    /**
     * 根据配置文件初始配置,初始化handlerNumber、handlerStopWaitTime、handlerClassName
     */
    protected abstract void initCfgData()throws FrameException;

    /**
     * 构造函数
     * @throws FrameException
     */
    public BaseHandlerParameter() throws FrameException {
        this.initCfgData();
        if(handlerNumber<0||handlerStopWaitTime<0|| StringUtils.isEmpty(handlerClassName)){
            LogUtil.getInstance().error("Handler Pool 初始化参数是负值!handlerNumber="+handlerNumber+",handlerStopWaitTime="+handlerStopWaitTime+",handlerClassName="+handlerClassName);
            throw new FrameException(FrameConstans.HANDLER_POOL_INIT_ERR+"-Handler Pool 初始化参数是负值!handlerNumber="+handlerNumber+",handlerStopWaitTime="+handlerStopWaitTime+",handlerClassName="+handlerClassName);
        }
    }

    /**
     * 获取线程池线程数量
     * @return
     */
    public int getHandlerNumber(){
        return this.handlerNumber;
    }

    /**
     * 停止每个Handler的等待时间，单位秒
     * @return
     */
    public int getHandlerStopWaitTime(){
        return this.handlerStopWaitTime;
    }

    /**
     * 获取执行线程的名称
     * @return
     */
    public String getHandlerClassName(){
        return this.handlerClassName;
    }

}
