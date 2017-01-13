package com.gqhmt.tyzf.common.frame.amq;

import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageListenerImpl implements MessageListener {
	private AmqSender amqSender;

	public MessageListenerImpl(AmqSender amqSender) {
		this.amqSender = amqSender;
	}

	@Override
	public void onMessage(Message message) {
		if (message == null) {
			return;
		}
		try {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println("接收到的msg = " + tm.getText());
			} catch (JMSException e) {
				LogUtil.getInstance().error(e);
			}
			try {
				System.out.println("msgId = " + message.getJMSMessageID());
				System.out.println("correlationId = " + message.getJMSCorrelationID());
				if (message.getJMSCorrelationID() == null)
					message.setJMSCorrelationID(message.getJMSMessageID());
			} catch (JMSException e) {
				LogUtil.getInstance().error(e);
			}
			this.amqSender.sendMsg(message);
		} catch (AmqException e) {
			LogUtil.getInstance().error(e);
		}
	}
}