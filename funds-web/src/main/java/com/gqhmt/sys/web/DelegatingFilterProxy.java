package com.gqhmt.sys.web;

import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.util.Resources;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    /**
     * The URL to the CAS Server login.
     */
    private String casServerLoginUrl;

    private String casServerUrlPrefix;

    private String serverName;



    private String targetBean;
    private Filter proxy;

    private Pattern pattern = null;
    private String exclude=null;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pattern = Pattern.compile(GlobalConstants.EXCLUDE_DIRECTORY_REGEX);
        exclude= Resources.getString(GlobalConstants.EXCLUDE_URL_INIT);

        this.casServerLoginUrl = ResourceUtil.getValue("config.appContext","casLoginUrl");
        this.casServerUrlPrefix = ResourceUtil.getValue("config.appContext","casUrl");
        this.serverName = ResourceUtil.getValue("config.appContext","localServerName");

        LogUtil.debug(this.getClass(),filterConfig.getClass().getName());
        this.targetBean = filterConfig.getInitParameter("target");
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        if(this.targetBean == null){
            return;
        }
        Filter filter= (Filter) wac.getBean(this.targetBean);
        if (filter instanceof AuthenticationFilter){
            FssFilterConfig fssFilterConfig = new FssFilterConfig(filterConfig.getFilterName(),filterConfig.getServletContext());
            fssFilterConfig.setParm("serverName",this.serverName);
            fssFilterConfig.setParm("casServerLoginUrl",this.casServerLoginUrl);
            filter.init(fssFilterConfig);
//                AuthenticationFilter authenticationFilter = (AuthenticationFilter)filter;
//                authenticationFilter.setServerName(this.serverName);
//                authenticationFilter.setCasServerLoginUrl(casServerLoginUrl);
        }else if(filter instanceof org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter){
            FssFilterConfig fssFilterConfig = new FssFilterConfig(filterConfig.getFilterName(),filterConfig.getServletContext());
            fssFilterConfig.setParm("serverName",this.serverName);
            fssFilterConfig.setParm("casServerUrlPrefix",this.casServerUrlPrefix);
            fssFilterConfig.setParm("redirectAfterValidation","false");
            filter.init(fssFilterConfig);

        }else if(filter != null){
            filter.init(filterConfig);
        }else{
            filter = this;
        }

        proxy = filter;

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getServletPath();
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            chain.doFilter(request, response);
            LogUtil.debug(this.getClass(),url+":tg");
            return;

        }
        if(proxy == null){
            chain.doFilter(request,response);
            return;
        }

        this.proxy.doFilter(request,response,chain);
    }

    @Override
    public void destroy() {

    }
}



