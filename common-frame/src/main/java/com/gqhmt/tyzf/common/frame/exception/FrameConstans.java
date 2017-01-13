package com.gqhmt.tyzf.common.frame.exception;

/**
 * Created by zhou on 2016/10/21.
 * Description: 框架异常消息
 */
public interface FrameConstans {

    public static final String HANDLER_INIT_ERR = "业务线程类初始化失败";
    public static final String SPRING_ERR = "Spring错误";

    public static final String LOG_INIT_ERR = "日志初始化失败";

    public static final String MO_INIT_ERR = "MO初始化失败";


    public static final String GET_MESSAGE_ERROR="读取队列消息错误";//
    public static final String PUT_MESSAGE_ERROR="存储队列消息错误";//从队列放消息错误

    public static final String MQ_MSG_RECEIVE_INSTANCE_INIT_ERR = "MQ消息接收器初始化失败";
    public static final String MQ_MSG_SEND_INSTANCE_INIT_ERR = "MQ消息发送器初始化失败";
    public static final String MQ_MSG_RECEIVE_INSTANCE_REFRESH_ERR = "MQ消息接收器刷新失败";

    public static final String MSG_BUFFER_INIT_ERR = "消息缓冲类初始化失败";
    public static final String MSG_BUFFER_ERR = "消息缓冲类错误";

    public static final String CONFIGMGR_INIT_EERR = "配置管理类初始化失败";
    public static final String CONFIGMGR_LACK_PARAMETER_EERR = "无此配置参数||Fatal";

    public static final String CACHE_INIT_ERR="数据库缓存组件初始化失败";

    public static final String SPRING_INIT_ERR="Spring初始化失败";

    public static final String HANDLER_POOL_INIT_ERR = "业务线程池类初始化失败";

}
