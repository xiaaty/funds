package com.gqhmt.sys.web;

import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {
	private Pattern pattern = null;
	private String exclude=null;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pattern = Pattern.compile(GlobalConstants.EXCLUDE_DIRECTORY_REGEX);
//		exclude=Resources.getString(GlobalConstants.EXCLUDE_URL_INIT);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Application application = Application.getInstance();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url  =  httpServletRequest.getServletPath();
		String context = httpServletRequest.getContextPath();
		request.setAttribute("menu", application.getMenu(context,url));
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}

}
