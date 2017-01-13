package com.gqhmt.tyzf.common.frame.handler;

/**
 * Created by zhou on 2016/10/21.
 * Description:线程池参数
 */
public interface IHandlerParameter {
    /**
     * 获取线程数
     * @return
     */
    public int getHandlerNumber();

    /**
     * 获取停止时间
     * @return
     */
    public int getHandlerStopWaitTime();

    /**
     * 获取类名
     * @return
     */
    public String getHandlerClassName();

}
