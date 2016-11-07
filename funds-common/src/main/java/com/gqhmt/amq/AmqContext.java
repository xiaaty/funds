package com.gqhmt.amq;

import com.gqhmt.core.util.LogUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq上下文对象
 */
public class AmqContext {
	private static AmqContext amqContext;
	private ActiveMQConnectionFactory connectionFactory;
	/** mq连接对象 */
	private Connection connection;
	private String url;

	/**
	 * 无参构造
	 */
	private AmqContext() {
		init();
	}

	/**
	 * 初始化参数信息
	 */
	private void init() {
		InputStream stream = AmqContext.class.getClassLoader().getResourceAsStream("config/mq.properties");
		Properties p = new Properties();
		try {
			p.load(stream);
			this.url = p.getProperty("URL");
			this.connectionFactory = new ActiveMQConnectionFactory(this.url);
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
		} catch (IOException e) {
			LogUtil.error(getClass(),e);
		} catch (JMSException e) {
			if (this.connection != null) {
				closeConnection();
			}
			LogUtil.error(getClass(),e);
		}
	}

	public static AmqContext getAmqContext() {
		synchronized (AmqContext.class) {
			if (amqContext == null) {
				amqContext = new AmqContext();
			}
		}
		return amqContext;
	}

	public Session getSession() {
		Session session = null;
		try {
			if (this.connection != null)
				session = this.connection.createSession(false, 1);
		} catch (JMSException e) {
			LogUtil.error(getClass(),e);
		}
		return session;
	}

	public synchronized void closeConnection() {
		if (this.connection == null)
			return;
		try {
			this.connection.close();
		} catch (JMSException e) {
			LogUtil.error(getClass(),e);
		}
		this.connection = null;
	}

}
