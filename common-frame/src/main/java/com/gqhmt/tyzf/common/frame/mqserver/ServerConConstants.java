package com.gqhmt.tyzf.common.frame.mqserver;

/**
 * Created by zhou on 2016/10/25.
 * Description:
 */
public class ServerConConstants {

    private ServerConConstants(){
        throw new IllegalAccessError("Utility class");
    }


    public static final String TRANS_CLEAR = "Adapter.TransClear";//启动清空交易选项

    public static final String CHECK_MQ_ERROR = "MQ连接不可用，使用当前参数无法连接到MQ队列";
    public static final String CHECK_MQ_SUCCESS = "MQ连接有效！";
    public static final String CHECK_MQ_RECEIVE_RUN_SUCCESS = "组件(请求分派守护线程)运行良好";
    public static final String CHECK_MQ_RECEIVE_STOPPING_ERROR = "组件(请求分派守护线程)正在被停止";
    public static final String CHECK_MQ_RECEIVE_STOPPED_ERROR = "组件(请求分派守护线程)已经被停止";
    public static final String CHECK_MQPOOL_RECEIVE_RUN_SUCCESS = "(MQ服务端接收线程池组件)运行良好！";
    public static final String CHECK_MQPOOL_RECEIVE_RUN_FAIL = "(MQ服务端接收线程池组件)运行不正常！";
    public static final String CHECK_MQ_SEND_RUN_SUCCESS = "组件(发送守护线程)运行良好";
    public static final String CHECK_MQ_SEND_STOPPING_ERROR = "组件(发送守护线程)正在被停止";
    public static final String CHECK_MQ_SEND_STOPPED_ERROR = "组件(发送守护线程)已经被停止";
    public static final String CHECK_MQPOOL_SEND_RUN_SUCCESS = "(MQ服务端发送线程池组件)运行良好！";
    public static final String CHECK_MQPOOL_SEND_RUN_FAIL = "(MQ服务端发送线程池组件)运行不正常！";

}
