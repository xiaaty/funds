package com.gqhmt.tyzf.common.frame.mqserver;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

import java.util.Hashtable;

/**
 * Created by zhou on 2016/10/24.
 * Description:
 */
public class MQMsgReceive extends BaseMQMsgReceive {

    public MQMsgReceive() throws FrameException {
        super();
    }

    public MQMsgReceive(String prefix) throws FrameException {
        super(prefix);
    }

    protected Hashtable<String,String> initCfgData() throws FrameException{
        Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(MQConstants.CLASSNAME, prefix +".ClassName");
        props.put(MQConstants.AMQ_RECEIVEQUEUENAME, prefix +".receiveQueueName");
        props.put(MQConstants.AMQ_TIMEOUT,prefix +".Timeout");
        props.put(MQConstants.AMQ_RECEIVE_BUFFER,prefix +".ReceiveBuffer");
        return props;
    }

}
