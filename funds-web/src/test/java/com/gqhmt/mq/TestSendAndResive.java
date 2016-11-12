package com.gqhmt.mq;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;
import org.junit.Test;

public class TestSendAndResive {

    @Test
    public void testResiverMessage() {
        AmqReceiver receiver = new AmqReceiver("createAccount.QC");
        try {
            receiver.receiveMessage(new MessageListenerImpl(new AmqSender(null, "createAccount.QD")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
