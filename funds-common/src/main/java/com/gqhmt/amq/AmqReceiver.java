package com.gqhmt.amq;

import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.*;


public class AmqReceiver extends BaseAmqSendAndReceive {

	private AmqContext context = AmqContext.getAmqContext();
	private String receiveFromQueue;

	public AmqReceiver(String receiveFromQueue) {
		this.receiveFromQueue = receiveFromQueue;
	}

	public void receiveMessage(MessageListener listener) {
		Session session = this.context.getSession();
		MessageConsumer consumer = null;
		consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			consumer.setMessageListener(listener);
		} catch (JMSException e) {
			sessionClose(session);
			LogUtil.getInstance().error(e);
		}
	}

	public void receiveMessage(MessageListener listener, Session session) {
		MessageConsumer consumer = null;
		consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			consumer.setMessageListener(listener);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
	}

	public Message synchronizedReceivedMessage(Session session, String filter, long timeout) {
		MessageConsumer consumer = null;
		Message msg = null;
		consumer = createMessageConsumer(session, filter, this.receiveFromQueue);
		try {
			msg = consumer.receive(timeout);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		} finally {
			sessionClose(session);
		}
		return msg;
	}

	public Message receiveMessage() throws AmqException {
		Session session = this.context.getSession();
		MessageConsumer consumer = null;
		Message msg = null;
		consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			msg = consumer.receive();
		} catch (Exception e) {
			throw new AmqException();
		} finally {
			sessionClose(session);
		}
		return msg;
	}
}
