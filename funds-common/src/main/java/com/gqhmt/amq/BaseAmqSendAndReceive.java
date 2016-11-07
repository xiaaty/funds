package com.gqhmt.amq;


import com.gqhmt.amq.exception.AmqException;
import com.gqhmt.core.util.LogUtil;
import javax.jms.*;


public class BaseAmqSendAndReceive implements AmqSendAndReceive {

	AmqContext context = AmqContext.getAmqContext();

	public void sendMsg(String message) throws AmqException {
	}

	public Message sendSynchronizeMessage(String message, String filter, boolean autoFilter, long timeout)
			throws AmqException {
		return null;
	}

	public void sendMsg(Message message) throws AmqException {
	}

	MessageConsumer createMessageConsumer(Session session, String filter, String receiveFromQueue) {
		Queue receiveQueue = null;
		MessageConsumer consumer = null;
		try {
			receiveQueue = session.createQueue(receiveFromQueue);
		} catch (JMSException e) {
			LogUtil.error(this.getClass(),e);
		}
		try {
			if ((filter == null) || (filter.trim().length() <= 0))
				consumer = session.createConsumer(receiveQueue);
			else {
				consumer = session.createConsumer(receiveQueue, filter);
			}
		} catch (JMSException e) {
			LogUtil.error(this.getClass(),e);
		}
		return consumer;
	}

	public void receiveMessage(MessageListener listener) throws AmqException {
	}

	public void sendMsg(Message message, String queueName) throws AmqException {
	}

	public Message receiveMessage() throws AmqException {
		return null;
	}

	public void sessionClose(Session session) {
		try {
			session.close();
		} catch (JMSException e) {
			LogUtil.error(this.getClass(),e);
		}
	}

	public TextMessage createMessage(String msg) throws AmqException {
		Session session = getDefaultSession();
		TextMessage tmg = null;
		try {
			tmg = session.createTextMessage(msg);
		} catch (JMSException e) {
			throw new AmqException("创建消息失败");
		}
		return tmg;
	}

	public TextMessage createMessage() throws AmqException {
		Session session = getDefaultSession();
		TextMessage tmg = null;
		try {
			tmg = session.createTextMessage();
		} catch (JMSException e) {
			throw new AmqException("创建消息失败");
		}
		return tmg;
	}

	public void sendSimpleSynchronizedMsg(String message, String correlationId) throws AmqException {
	}

	public Session getDefaultSession() {
		Session session = this.context.getSession();
		return session;
	}

	public void release() {
		this.context.closeConnection();
	}
}
