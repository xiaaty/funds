package com.gqhmt.sys.web;

import com.gqhmt.util.*;
import com.gqhmt.sys.beans.SysUsers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {
	private Pattern pattern = null;
	private String exclude=null;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pattern = Pattern.compile(GlobalConstants.EXCLUDE_DIRECTORY_REGEX);
		exclude=Resources.getString(GlobalConstants.EXCLUDE_URL_INIT);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SysUsers sysUsers = null;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String contextPath = httpServletRequest.getContextPath();
		String url = httpServletRequest.getServletPath();
		Matcher matcher = pattern.matcher(url);
        LogUtil.debug(this.getClass(),"url:"+url);
        //httpServletRequest.getRequestURI()
        LogUtil.debug(this.getClass(),"httpServletRequest.getRequestURI():"+httpServletRequest.getRequestURI());

//        BillLogUtil.debug(this.getClass(),"httpServletRequest.getRequestURL():"+httpServletRequest.getRequestURL());
//        BillLogUtil.debug(this.getClass(),"httpServletRequest.getServletPath():"+httpServletRequest.getServletPath());

        request.setAttribute("fid",GlobalConstants.getMenuParentId(url));
		if (matcher.find() || "/lend/addLendAnnex".equals(url) 
				|| "/loan/addLoanAnnex".equals(url)) {
			chain.doFilter(request, response);
		} else {
			if (url.lastIndexOf(".") > 0) {
				url = url.substring(0, url.lastIndexOf("."));
			}
                LogUtil.debug(this.getClass(),"exclude:"+exclude);
			if(exclude.indexOf(url)<0){
				sysUsers = (SysUsers) httpServletRequest.getSession().getAttribute(GlobalConstants.SESSION_EMP);
                LogUtil.debug(this.getClass(),"user:"+sysUsers);
				if (sysUsers == null) {
					httpServletRequest.getRequestDispatcher(GlobalConstants.USER_HOME).forward(request, response);
                    LogUtil.debug(this.getClass(),"login:false");
					return;
				} else {

                    String urlType = request.getParameter("urlType");
                    if(urlType != null && !"".equals(urlType)){
                        url = url+"?urlType="+urlType;
                    }
                    LogUtil.debug(this.getClass(),"url:"+url);
                    boolean checkauth = Auth.checkAuth(httpServletRequest,url,sysUsers);
                    LogUtil.debug(this.getClass(),"auth:"+checkauth);
					if (!checkauth) {
						request.setAttribute("error_key","nopower");
						request.getRequestDispatcher(GlobalConstants.ERROR_PAGE).forward(request, response);
						return;
					}
					request.setAttribute("menu",GlobalConstants.getSession((HttpServletRequest)request,GlobalConstants.SESSION_MENU));
					request.setAttribute("user",GlobalConstants.getSession((HttpServletRequest)request,GlobalConstants.SESSION_EMP));
					request.setAttribute("funcs",GlobalConstants.funcMap);
					request.setAttribute("iconMap",GlobalConstants.iconMap);

                    LogUtil.debug(this.getClass(),GlobalConstants.getSession((HttpServletRequest)request,GlobalConstants.SESSION_MENU));

				}
			}
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
	}

}
