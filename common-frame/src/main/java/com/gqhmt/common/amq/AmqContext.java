package com.gqhmt.common.amq;

import com.gq.base.constant.BaseConstant;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.*;
import java.util.Properties;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq上下文对象
 */
public class AmqContext {
	/** 上下文对象 */
	private static AmqContext amqContext;

	/** 连接对象 */
	private Connection connection;
	
	private static Logger logger = LoggerFactory.getLogger(AmqContext.class);
	
	/**
	 * 系统环境变量中的key
	 */
	private final static String AMQ_SYS_PROPERTY_CONFIG_KEY = "fss.mq.url";
	
	/**
	 * 配置文件中的key
	 */
	private final static String AMQ_PROPERTIES_CONFIG_KEY = "URL";

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
		try {
			//连接地址
			//从环境变量中获取地址.
			String url = System.getProperty(AMQ_SYS_PROPERTY_CONFIG_KEY);
			if (StringUtils.isEmpty(url)) {
				logger.debug("cannot get config from system property , try to read from property file.");
				String appHome = System.getProperty(BaseConstant.APP_HOME);
				InputStream stream;
				Properties p = new Properties();
				try {
					stream = new FileInputStream(appHome + File.separator + "conf/mq.properties");
				} catch (FileNotFoundException e) {
					stream = AmqContext.class.getClassLoader().getResourceAsStream("conf/mq.properties");
				}
				if (null == stream) {
					throw new RuntimeException("AmqContext初始化失败");
				}
				p.load(stream);
				//从配置文件获取地址.
				url = p.getProperty(AMQ_PROPERTIES_CONFIG_KEY);
			}
			//连接工厂
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			this.connection = connectionFactory.createConnection();
			this.connection.start();
		} catch (IOException e) {
			LogUtil.getInstance().error(e);
		} catch (JMSException e) {
			if (this.connection != null) {
				closeConnection();
			}
			LogUtil.getInstance().error(e);
		}
	}

	/**
	 * 静态获取AmqContext对象
	 * @return AmqContext
     */
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
			LogUtil.getInstance().error(e);
		}
		return session;
	}

	public synchronized void closeConnection() {
		if (this.connection == null)
			return;
		try {
			this.connection.close();
		} catch (JMSException e) {
			LogUtil.getInstance().error(e);
		}
		this.connection = null;
	}

}
