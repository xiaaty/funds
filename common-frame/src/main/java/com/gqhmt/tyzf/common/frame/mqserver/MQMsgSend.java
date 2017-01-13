package com.gqhmt.tyzf.common.frame.mqserver;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

import java.util.Hashtable;

/**
 * Created by zhou on 2016/10/25.
 * Description:
 */
public class MQMsgSend extends BaseMQMsgSend {


    public MQMsgSend() throws FrameException {
    }

    public MQMsgSend(String prefix) throws FrameException {
        super(prefix);
    }

    @Override
    protected Hashtable<String, String> initCfgData() throws FrameException {
        Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(MQConstants.CLASSNAME, prefix + ".ClassName");
        props.put(MQConstants.AMQ_CON_TRY_COUNT,prefix +".ConnectMQTryCount");
        props.put(MQConstants.AMQ_CON_TRY_INTV,prefix +".ConnectTryInterval");
        props.put(MQConstants.AMQ_TIMEOUT,prefix +".timeout");
        props.put(MQConstants.AMQ_SENDQUEUENAME,prefix +".sendQueueName");
        props.put(MQConstants.AMQ_SEND_BUFFER,prefix +".SendBuffer");
//        props.put(MQConstants.AMQ_THREAD_NUM,prefix +".HandlerNum");
        return props;
    }
}
