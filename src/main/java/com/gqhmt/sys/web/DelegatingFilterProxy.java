package com.gqhmt.sys.web;

import com.gqhmt.core.util.LogUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filename:    com.gqhmt.sys.web.DelegatingFilterProxy
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/14 14:50
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/14  于泳      1.0     1.0 Version
 */
public class DelegatingFilterProxy implements Filter {

    private String targetBean;
    private Filter[] proxy;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LogUtil.debug(this.getClass(),filterConfig.getClass().getName());
        this.targetBean = filterConfig.getInitParameter("target");
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        if(this.targetBean == null){
            return;
        }

        String[] targetProxy = this.targetBean.split(",");
        this.proxy = new Filter[targetProxy.length];

        for (int i = 0;i<targetProxy.length;i++){
            String tmp = targetProxy[i];
            if(tmp == null){
                continue;
            }
            Filter filter= (Filter) wac.getBean(tmp);
            filter.init(filterConfig);
            this.proxy[i] = filter;
        }








    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(proxy == null || proxy.length == 0){
            chain.doFilter(request,response);
        }

        for(Filter filter:proxy){
            filter.doFilter(request,response,chain);
        }
    }

    @Override
    public void destroy() {

    }
}
