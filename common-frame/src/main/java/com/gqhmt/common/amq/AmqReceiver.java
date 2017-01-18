package com.gqhmt.common.amq;

import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.*;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq接收类
 */
public class AmqReceiver extends BaseAmqSendAndReceive {

	private String receiveFromQueue;

	public AmqReceiver(String receiveFromQueue) {
		this.receiveFromQueue = receiveFromQueue;
	}

	@Override
	public Message receiveMessage() throws AmqException {
		Session session = this.context.getSession();
		MessageConsumer consumer = null;
		Message msg = null;
		consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			msg = consumer.receive();
		} catch (Exception e) {
			throw new AmqException(e);
		} finally {
			sessionClose(session);
		}
		return msg;
	}

	@Override
	public void receiveMessage(MessageListener listener) {
		Session session = this.context.getSession();
		MessageConsumer consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			consumer.setMessageListener(listener);
		} catch (JMSException e) {
			sessionClose(session);
			LogUtil.getInstance().error(e);
		}
	}

	/**
	 * 创建消费者并设置监听对象
	 * @param listener
	 * @param session
     */
	/*public void receiveMessage(MessageListener listener, Session session) {
		MessageConsumer consumer = null;
		consumer = createMessageConsumer(session, null, this.receiveFromQueue);
		try {
			consumer.setMessageListener(listener);
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
	}*/

	/**
	 * 同步接收消息
	 * @param session
	 * @param filter
	 * @param timeout
     * @return Message
     */
	/*public Message synchronizedReceivedMessage(Session session, String filter, long timeout) {
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
	}*/


}
