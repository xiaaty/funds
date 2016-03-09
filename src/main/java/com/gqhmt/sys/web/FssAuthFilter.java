package com.gqhmt.sys.web;

import com.gqhmt.core.util.ResourceUtil;
import org.jasig.cas.client.authentication.AuthenticationFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Filename:    com.gqhmt.sys.web.FssAuthFilter
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 17:11
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  于泳      1.0     1.0 Version
 */
public class FssAuthFilter extends AuthenticationFilter {

    @Override
    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        super.initInternal(filterConfig);
        super.setServerName( ResourceUtil.getValue("config.appContext","localServerName"));
        super.setCasServerLoginUrl(ResourceUtil.getValue("config.appContext","casLoginUrl"));
    }
}
