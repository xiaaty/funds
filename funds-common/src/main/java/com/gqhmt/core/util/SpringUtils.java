package com.gqhmt.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Description Spring工具类
 * @author yangfei.eng@gmail.com
 * @createDate：2014年9月4日  下午6:48:13
 * @version 1.0 
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class SpringUtils {

	public static Object getBeanByName(String beanId){
		if(beanId == null){
			return null;
		}
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(beanId);
	}
	
	public static <T> T getBeanByType(Class clazz){
		if(clazz == null){
			return null;
		}
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return (T) wac.getBean(clazz);
	}
	
	public static <T> T getBean(Class clazz){
		if(clazz == null){
			return null;
		}
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-common.xml");
		return (T) context.getBean(clazz);
	}
	
	public static <T> T getBean(String beanId){
		if(beanId == null){
			return null;
		} 
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-common.xml");
		return (T) context.getBean(beanId);
	}
	
	/**
	 * 获取servlet上下文
	 * @return
	 */
	public static ServletContext getServletContext(){ 
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getServletContext();
	}
}
