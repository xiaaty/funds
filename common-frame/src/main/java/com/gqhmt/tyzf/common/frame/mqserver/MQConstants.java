package com.gqhmt.tyzf.common.frame.mqserver;

/**
 * Created by zhou on 2016/10/24.
 * Description:mq常量
 */
public class MQConstants {

    /**
     * 构造函数
     */
    private MQConstants(){
        throw new IllegalAccessError("Utility class");
    }

    public static final String CLASSNAME = "CLASS_NAME";

    /**
     * AMQ相关参数.
     */
    public static final String AMQ_HOST="AMQ_Host";
    public static final String AMQ_RECEIVEQUEUENAME="receiveQueueName";
    public static final String AMQ_SENDQUEUENAME="sendQueueName";
    public static final String AMQ_CON_TRY_COUNT="AMQ_ConnectMQTryCount";
    public static final String AMQ_CON_TRY_INTV="ConnectTryInterval";
    public static final String AMQ_TIMEOUT="AMQ_Timeout";
    public static final String AMQ_THREAD_NUM="AMQ_THREAD_NUM";
    public static final String AMQ_RECEIVE_BUFFER="AMQ_ReceiveBuffer";
    public static final String AMQ_SEND_BUFFER="AMQ_SendBuffer";

}
