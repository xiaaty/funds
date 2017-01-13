package com.gqhmt.tyzf.common.frame.message;

/**
 * Created by zhou on 2016/10/21.
 * Description:消息接口
 */
public interface IServiceObject {

    /**
     * 获取服务类型
     * @return
     */
    int getMessageType();

    /**
     * 获取消息参数
     * @return
     */
    ServiceParameter getServPara();

    /**
     * 释放资源
     */
    void release();

}
