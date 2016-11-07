package com.gqhmt.amq;
import com.gqhmt.amq.exception.AmqException;
import com.gqhmt.core.util.LogUtil;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageListenerImpl implements MessageListener {
	private AmqSender amqSender;

	public MessageListenerImpl(AmqSender amqSender) {
		this.amqSender = amqSender;
	}

	public void onMessage(Message message) {
		if (message == null) {
			return;
		}

		try {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println("接收到的msg = " + tm.getText());
			} catch (JMSException e) {
				LogUtil.error(this.getClass(),e);
			}
			try {
				System.out.println("msgId = " + message.getJMSMessageID());
				System.out.println("correlationId = " + message.getJMSCorrelationID());
				if (message.getJMSCorrelationID() == null)
					message.setJMSCorrelationID(message.getJMSMessageID());
			} catch (JMSException e) {
				LogUtil.error(this.getClass(),e);
			}
			this.amqSender.sendMsg(message);
		} catch (Exception e) {
			LogUtil.error(this.getClass(),e);
		}
	}
}