package com.gqhmt.mq;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class TestResive {

    @Test
    public void testResiverMessage() {
        AmqReceiver receiver = new AmqReceiver("createAccount.QB");
        try {
            receiver.receiveMessage(new MessageListenerImpl(new AmqSender(null, "createAccount.QC")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
