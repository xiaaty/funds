package com.gqhmt.sys.web;

import com.gqhmt.core.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.AuthMenu;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.sys.beans.SysUsers;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月4日
 * Description:	资金结算系统单点登录过滤器
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月4日  jhz      1.0     1.0 Version
 */
public class LoginFilter implements Filter {
	
	private Pattern pattern = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pattern = Pattern.compile(GlobalConstants.EXCLUDE_DIRECTORY_REGEX);
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		AuthMenu authMenu = AuthMenu.getInstance();
		//用户session 
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session = request.getSession();
        String url  =  request.getServletPath();
        String context = request.getContextPath();

		//在session中自定义一个参数，以它来校验是否完成过自动登陆
        Boolean isUserInfo = (Boolean) session.getAttribute("AURORA_USER_LOGIN");
        if (isUserInfo == null){
            isUserInfo = Boolean.FALSE;
        }
        if(isUserInfo){
            request.setAttribute("menu",authMenu.getMenu((String)session.getAttribute("loginName"),context,url));
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        //通过CAS的API获得登陆账号
        String loginName = null;
        Assertion assertion = AssertionHolder.getAssertion();
        if(assertion!=null){
            loginName= assertion.getPrincipal().getName();
        }
        if (loginName == null){
            chain.doFilter(servletRequest,servletResponse);
            return;
        }

        session.setAttribute("loginName",loginName);
        session.setAttribute("AURORA_USER_LOGIN", Boolean.TRUE);

        Map<String,String> param = new HashMap<>();
        param.put("loginName",loginName);

        
        
        param.put("systemName", "资金结算系统");
        try {
            Map<String, Object> userInfo = UrlConnectUtil.sendDataReturnSingleObjcet(HashMap.class,"authUrl",param);
            session.setAttribute("userName", userInfo.get("name"));
            SysUsers sysUsers = new SysUsers();
            sysUsers.setId((String) userInfo.get("id"));
            sysUsers.setUserName((String) userInfo.get("name"));
            sysUsers.setLoginName((String) userInfo.get("loginName"));
            sysUsers.setEmployeeNo((String) userInfo.get("no"));
            GlobalConstants.setSession(request, GlobalConstants.SESSION_EMP, sysUsers);
            List<Map<String, String>> menuList = (List<Map<String, String>>) userInfo.get("menuList");
            authMenu.addUserMenu(loginName,menuList);
            request.setAttribute("menu",authMenu.getMenu(loginName,context,url));
            chain.doFilter(servletRequest, servletResponse);
//            response.sendRedirect(request.getContextPath()+"/main");
            List<String> permissionList = new ArrayList<String>();
            permissionList.add((String) userInfo.get("permission"));
            session.setAttribute("permissionList", permissionList);
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            chain.doFilter(servletRequest, servletResponse);
            throw new ServletException("获取菜单失败",e);
        }


	}
	
	@Override
	public void destroy() {
	}

}
