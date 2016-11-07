package com.gqhmt.amq;


import com.gqhmt.amq.exception.AmqException;

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

	public abstract Message sendSynchronizeMessage(String paramString1, String paramString2, boolean paramBoolean, long paramLong) throws AmqException;

	public abstract void sendMsg(Message paramMessage) throws AmqException;

	public abstract void sendSimpleSynchronizedMsg(String paramString1, String paramString2) throws AmqException;

	public abstract void sendMsg(Message paramMessage, String paramString) throws AmqException;

	public abstract void receiveMessage(MessageListener paramMessageListener) throws AmqException;

	public abstract Message receiveMessage() throws AmqException;

}
