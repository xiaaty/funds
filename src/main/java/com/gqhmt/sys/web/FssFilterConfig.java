package com.gqhmt.sys.web;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.sys.web.FssFilterConfig
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/7 16:30
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/7  于泳      1.0     1.0 Version
 */
public class FssFilterConfig implements FilterConfig {


    private String filterName;

    private ServletContext servletContext;

    private Map<String,String> paraMap = new ConcurrentHashMap<>();

    public FssFilterConfig(String filterName,ServletContext servletContext){
        this.filterName = filterName;
        this.servletContext = servletContext;
    }

    @Override
    public String getFilterName() {
        return filterName;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getInitParameter(String name) {
        return paraMap.get(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {


        return new Enumeration<String>() {
            Iterator<String> iterable =  paraMap.keySet().iterator();

            @Override
            public boolean hasMoreElements() {
                return iterable.hasNext();
            }

            @Override
            public String nextElement() {
                return iterable.next();
            }
        };

    }

    public void setParm(String key,String value){
         paraMap.put(key,value);
    }
}
