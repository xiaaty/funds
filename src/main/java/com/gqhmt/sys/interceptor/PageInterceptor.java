package com.gqhmt.sys.interceptor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.mybatis.GqPageInfo;
import com.gqhmt.core.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.interceptor.PageInterceptor
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/27 14:57
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/27  于泳      1.0     1.0 Version
 */
public class PageInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            if (null != method.getAnnotation(AutoPage.class)){
                String pageNumStr = request.getParameter("cpage");
                String pageSizeStr = request.getParameter("pageNum");

                if (StringUtils.isBlank(pageNumStr)) {
                    String sessionPageNum = (String) request.getSession(true).getAttribute("pageNum");
                    if (StringUtils.isBlank(sessionPageNum)) {
                        pageNumStr = "1";
                    } else {
                        pageNumStr = sessionPageNum;
                    }
                }
                if (StringUtils.isBlank(pageSizeStr))
                    pageSizeStr = "10";

                Integer pageNum, pageSize;
                if (StringUtils.isNotBlank(pageNumStr) && StringUtils.isNotBlank(pageSizeStr)) {
                    try {
                        pageNum = Integer.parseInt(pageNumStr);
                        pageSize = Integer.parseInt(pageSizeStr);
                    } catch (NumberFormatException e) {
                        LogUtil.debug(this.getClass(),"分页信息转换异常", e);
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return false;
                    }
                    PageHelper.startPage(pageNum, pageSize);
                    return true;
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && modelAndView.getModel().containsKey("page") && modelAndView.getModel().get("page") instanceof List){
            try {
                modelAndView.getModel().put("page",new GqPageInfo((List) modelAndView.getModel().get("page")));
            }catch (Exception e){
                LogUtil.error(this.getClass(),"转换pageData失败！", e);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
