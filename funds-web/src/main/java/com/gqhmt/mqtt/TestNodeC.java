package com.gqhmt.mqtt;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TestNodeC {
    public static void main(String[] args) {
        AmqSendAndReceive receiver = new AmqReceiver("ACC.QC");
        try {

            receiver.receiveMessage(new MessageListenerImpl(new AmqSender(null, "AMQ.TTT3")));
        } catch (AmqException e) {
            e.printStackTrace();
        }
    }
}
