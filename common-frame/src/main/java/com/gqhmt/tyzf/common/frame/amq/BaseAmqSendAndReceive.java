package com.gqhmt.tyzf.common.frame.amq;


import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.*;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq接收和发送基础类
 */
public abstract class BaseAmqSendAndReceive implements AmqSendAndReceive {

	/** 上下文对象 */
	AmqContext context = AmqContext.getAmqContext();

	@Override
	public void sendMsg(String message) throws AmqException {
	}

	@Override
	public void sendMsg(Message message) throws AmqException {
	}

	@Override
	public void sendMsg(Message message, String queueName) throws AmqException {
	}

	@Override
	public void sendSimpleSynchronizedMsg(String message, String correlationId) throws AmqException {
	}

	@Override
	public Message sendSynchronizeMessage(String message, String filter, boolean autoFilter, long timeout)
			throws AmqException {
		return null;
	}

	@Override
	public Message receiveMessage() throws AmqException {
		return null;
	}

	public void receiveMessage(MessageListener listener) throws AmqException {
	}

	/**
	 * 创建消费者
	 * @param session Session
	 * @param filter 过滤条件
	 * @param receiveFromQueue 消息队列名称
     * @return MessageConsumer
     */
	MessageConsumer createMessageConsumer(Session session, String filter, String receiveFromQueue) {
		Queue receiveQueue = null;
		try {
			receiveQueue = session.createQueue(receiveFromQueue);
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

	/**
	 * 关闭Session
	 * @param session
     */
	public void sessionClose(Session session) {
		try {
			session.close();
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
	}

	/**
	 * 创建文本消息对象
	 * @param msg
	 * @return TextMessage
	 * @throws AmqException
     */
	public TextMessage createMessage(String msg) throws AmqException {
		Session session = getDefaultSession();
		TextMessage tmg = null;
		try {
			tmg = session.createTextMessage(msg);
		} catch (JMSException e) {
			throw new AmqException("创建消息失败",e);
		}
		return tmg;
	}

	/**
	 * 创建空文本消息对象
	 * @return TextMessage
	 * @throws AmqException
     */
	public TextMessage createMessage() throws AmqException {
		Session session = getDefaultSession();
		TextMessage tmg = null;
		try {
			tmg = session.createTextMessage();
		} catch (JMSException e) {
			throw new AmqException("创建消息失败",e);
		}
		return tmg;
	}

	/**
	 * 获取默认Session
	 * @return
     */
	public Session getDefaultSession() {
		Session session = this.context.getSession();
		return session;
	}

	/**
	 * 释放连接
	 */
	public void release() {
		this.context.closeConnection();
	}
}
