package com.gqhmt.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Filename:    com.gq.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:27
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
public class ServiceLoader {

    private static ApplicationContext context;
    static {
        //spring/spring-components.xml,spring/springs-mvc.xml,spring/spring-webservice.xml
        String[] files = {"spring/spring-common.xml" };
        context =  ContextLoader.getCurrentWebApplicationContext();
        if(context == null){
            context = new ClassPathXmlApplicationContext(files);
        }
    }
     public static <T> T get(Class<T> clazz) {
        return context.getBean(clazz);
    }
     public static Object get(String beanName) {
        return context.getBean(beanName);
    }
}
