package com.gqhmt.mqtt;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TestNodeB {

    public static void main(String[] args) {
        AmqReceiver receiver = new AmqReceiver("ACC.QB");
        try {
            receiver.receiveMessage(new MessageListenerImpl(new AmqSender(null, "ACC.QC")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
