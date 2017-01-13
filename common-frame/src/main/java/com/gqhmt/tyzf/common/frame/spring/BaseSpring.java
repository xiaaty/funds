package com.gqhmt.tyzf.common.frame.spring;


import com.gq.base.constant.BaseConstant;
import com.gqhmt.tyzf.common.frame.common.BasicStatus;
import com.gqhmt.tyzf.common.frame.common.IConfigurable;
import com.gqhmt.tyzf.common.frame.common.IStatus;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring对象
 */
public class BaseSpring implements IConfigurable {
	// Spring 配置文件
	private static String PATH = "/conf/spring/application-config.xml";
	//日志前缀
	private static final String LOG_PREFIX = "BaseSpring";
	//远程配置中心配置key
	private static String APP_HOME = System.getProperty(BaseConstant.APP_HOME);
	private static String configLocation = "file:" + APP_HOME + PATH;
	//上下文对象
	private static ApplicationContext context;
	//日志对象
	private static Logger logger = LoggerFactory.getLogger(BaseSpring.class);

	/**
	 * 构造函数
	 * @throws FrameException
	 */
	public BaseSpring() throws FrameException {
		compensate();
	}

	/**
	 * 获取bean对象
	 * @param beanId
	 * @return
	 */
	public Object getBean(String beanId) {

		if (context == null) {
			String baseSpringSwitch = null;
			try {
				baseSpringSwitch = (String) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_SWITCH);
			} catch (FrameException e) {
				LogUtil.getInstance().error(LOG_PREFIX, e);
			}
			if (baseSpringSwitch != null && baseSpringSwitch.equalsIgnoreCase(BaseSpringConstants.BASE_SPRING_SWITCH_ON)) {
				context = initContext();
			} else
				return null;
		}
		return context.getBean(beanId);
	}

	/**
	 * 重新获取上下文对象
	 * @throws FrameException
	 */
	@Override
	public void refresh() throws FrameException {
		context = null;
		String baseSpringSwitch = (String) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_SWITCH);
		if (baseSpringSwitch.equalsIgnoreCase(BaseSpringConstants.BASE_SPRING_SWITCH_ON)) {
			context = initContext();
		}
	}

	/**
	 * 上下文为null的时候重新获取context对象
	 * @throws FrameException
	 */
	@Override
	public void compensate() throws FrameException {
		String baseSpringSwitch = (String) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_SWITCH);
		if (context == null && baseSpringSwitch.equalsIgnoreCase(BaseSpringConstants.BASE_SPRING_SWITCH_ON)) {
			context = initContext();
		}
	}

	/**
	 * 释放上下文对象
	 */
	@Override
	public void release() {
		context = null;
	}

	/**
	 * 检查状态
	 * @return
	 * @throws FrameException
	 */
	@Override
	public IStatus checkStatus() throws FrameException {
		String baseSpringSwitch = (String) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_SWITCH);
		if (context == null && baseSpringSwitch.equalsIgnoreCase(BaseSpringConstants.BASE_SPRING_SWITCH_ON))
			return new BasicStatus(false, LOG_PREFIX + BaseSpringConstants.CHECK_RUN_FALSE);
		else
			return new BasicStatus(true, LOG_PREFIX + BaseSpringConstants.CHECK_RUN_SUCCESS);
	}

	/**
	 * 初始化上下文对象
	 * @return
	 */
	public ApplicationContext initContext() {
		context = org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
		if(context == null) {
			logger.warn("从web容器初始化spring失败，尝试从文件初始化.");
			context = new FileSystemXmlApplicationContext(configLocation);
		}
		if (null == context) {
			logger.warn("从FileSystem初始化spring容器失败，尝试从classpath初始化.");
			context = new ClassPathXmlApplicationContext(PATH);
		}
		if (null == context) {
			logger.error("BaseSpring 初始化失败.");
			throw new RuntimeException("BaseSpring 初始化失败");
		}
		return context;
	}
}