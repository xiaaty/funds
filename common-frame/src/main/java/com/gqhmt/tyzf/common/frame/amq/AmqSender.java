package com.gqhmt.tyzf.common.frame.amq;

import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.*;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq发送类
 */
public class AmqSender extends BaseAmqSendAndReceive {

	private String sendToQueue;
	private String receiveFromQueue;

	public AmqSender(String receiveFromQueue, String sendToQueue) {
		this.receiveFromQueue = receiveFromQueue;
		this.sendToQueue = sendToQueue;
	}

	/**
	 * 创建生产者对象
	 * @param session
	 * @return MessageProducer
     */
	private MessageProducer creteProducer(Session session) {
		Queue sendQueue = null;
		try {
			sendQueue = session.createQueue(this.sendToQueue);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		try {
			return session.createProducer(sendQueue);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		return null;
	}

	/**
	 * 创建生产者
	 * @param destination
	 * @param session
     * @return
     */
	private MessageProducer createProducer(Destination destination, Session session) {
		MessageProducer p = null;
		try {
			p = session.createProducer(destination);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		return p;
	}

	/**
	 * 创建生产者
	 * @param session
	 * @param filter
     * @return
     */
	private MessageConsumer createMessageConsumer(Session session, String filter) {
		Queue receiveQueue = null;
//		MessageConsumer consumer = null;
		try {
			receiveQueue = session.createQueue(this.receiveFromQueue);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		try {
			if ((filter == null) || (filter.trim().length() <= 0))
				return session.createConsumer(receiveQueue);
			else {
				return session.createConsumer(receiveQueue, filter);
			}
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		return null;
	}

	/*public void putMsgToMap(String uuid, Map<String, ArrayBlockingQueue<TextMessage>> globalMap, String message,
							String filter, boolean autoFilter, long timeout) throws AmqException {
		TextMessage tm = (TextMessage) sendSynchronizeMessage(message, filter, autoFilter, timeout);
		ArrayBlockingQueue abq = (ArrayBlockingQueue) globalMap.get(uuid);
		if (abq == null)
			return;
		abq.add(tm);
	}*/


	@Override
	public void sendMsg(String message) throws AmqException {
		Session session = this.context.getSession();

		MessageProducer p = creteProducer(session);
		try {
			Message msg = session.createTextMessage(message);
			p.send(msg);
		} catch (JMSException e) {
			throw new AmqException("发送消息出错",e);
		}
		sessionClose(session);
	}

	@Override
	public void sendMsg(Message message) throws AmqException {
		Session session = this.context.getSession();
		//目的对象（Queue）
		Destination queue = null;
		try {
			if (this.sendToQueue != null)
				queue = session.createQueue(this.sendToQueue);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		MessageProducer p = createProducer(queue, session);
		try {
			p.send(message);
		} catch (JMSException e) {
			throw new AmqException("发送消息出错",e);
		} finally {
			sessionClose(session);
		}
	}

	@Override
	public void sendMsg(Message message, String queueName) throws AmqException {
		Session session = this.context.getSession();
		Destination queue = null;
		try {
			queue = message.getJMSDestination();
			if (queue == null)
				queue = session.createQueue(queueName);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		MessageProducer p = createProducer(queue, session);
		try {
			p.send(message);
		} catch (JMSException e) {
			throw new AmqException("发送消息出错",e);
		} finally {
			sessionClose(session);
		}
	}

	@Override
	public void sendSimpleSynchronizedMsg(String message, String correlationId) throws AmqException {
		Session session = this.context.getSession();

		MessageProducer p = creteProducer(session);
		try {
			Message msg = session.createTextMessage(message);
			msg.setJMSCorrelationID(correlationId);
			p.send(msg);
		} catch (JMSException e) {
			throw new AmqException("发送消息出错",e);
		}
		sessionClose(session);
	}

	@Override
	public Message sendSynchronizeMessage(String message, String filter, boolean autoFilter, long timeout)
			throws AmqException {
		Session session = this.context.getSession();
		String msgId = null;
		Message receiveMessage = null;

		MessageProducer p = creteProducer(session);
		try {
			Message msg = session.createTextMessage(message);
			p.send(msg);
			msgId = msg.getJMSMessageID();
		} catch (JMSException e) {
			sessionClose(session);
			throw new AmqException("发送消息出错",e);
		}

		System.out.println("发送消息成功..");
		if (autoFilter) {
			filter = "JMSCorrelationID = '" + msgId + "'";
		}
		MessageConsumer consumer = createMessageConsumer(session, filter);
		try {
			receiveMessage = consumer.receive(timeout);
			if (receiveMessage == null) {
				System.out.println("连接超时");
			} else
				System.out.println("receive message success.");
		} catch (JMSException e) {
			throw new AmqException("同步等待出错.",e);
		} finally {
			sessionClose(session);
		}
		return receiveMessage;
	}


}
