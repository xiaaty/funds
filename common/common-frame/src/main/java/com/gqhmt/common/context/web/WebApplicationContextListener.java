package com.gqhmt.common.context.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Filename:    com.gqhmt.common.context.web.WebApplicationContextListener
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/13 14:47
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/13  于泳      1.0     1.0 Version
 */
public class WebApplicationContextListener extends ContextLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
