package com.gqhmt.common.amq;


import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by zhou on 2016/10/20.
 * Description:定义发送和接收接口
 */
public interface AmqSendAndReceive {

	/**
	 * 发送文本消息方法
	 * @param paramString
	 * @throws AmqException
     */
	public abstract void sendMsg(String paramString) throws AmqException;

	/**
	 * 发送JMSMessage对象
	 * @param paramMessage
	 * @throws AmqException
	 */
	public abstract void sendMsg(Message paramMessage) throws AmqException;

	/**
	 * 向指定Queue发送消息对象
	 * @param paramMessage 消息对象
	 * @param paramString Queue名称
	 * @throws AmqException
	 */
	public abstract void sendMsg(Message paramMessage, String paramString) throws AmqException;

	/**
	 * 发送含有correlationId的文本消息
	 * @param paramString1 文本消息内容
	 * @param paramString1 correlationId
	 * @throws AmqException
	 */
	public abstract void sendSimpleSynchronizedMsg(String paramString1, String paramString2) throws AmqException;

	/**
	 * 同步发送/接收消息
	 * @param paramString1 消息文本
	 * @param paramString2 接收时的筛选条件
	 * @param paramBoolean 是否过滤
	 * @param paramLong 超时时间
	 * @return Message
     * @throws AmqException
     */
	public abstract Message sendSynchronizeMessage(String paramString1, String paramString2, boolean paramBoolean, long paramLong) throws AmqException;

	/**
	 * 接收消息方法
	 * @return Message
	 * @throws AmqException
	 */
	public abstract Message receiveMessage() throws AmqException;

	/**
	 * 接收消息并设置监听对象
	 * @param paramMessageListener
	 * @throws AmqException
     */
	public abstract void receiveMessage(MessageListener paramMessageListener) throws AmqException;

}
