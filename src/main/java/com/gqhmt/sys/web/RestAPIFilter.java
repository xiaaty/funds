package com.gqhmt.sys.web;

import com.gqhmt.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestAPIFilter implements Filter {
	private Pattern pattern = null;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pattern = Pattern.compile("^/api/");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getServletPath();
		Matcher matcher = pattern.matcher(url);
        LogUtil.debug(this.getClass(),"url:"+url);
        LogUtil.debug(this.getClass(),"httpServletRequest.getRequestURI():"+httpServletRequest.getRequestURI());
		if (matcher.find()) {
			// 商户权限验证
			String busiCode = (String) httpServletRequest.getAttribute("busiCode");
			// 获得MD5密文(加密解密方式验证签名)
//			String signature = Encriptor.getMD5(busiCode);
//			String srcCode = Encriptor.encriptDES(keystr, srcCode);
//			String encriptCode = Encriptor.decriptDES(encriptCode, keystr);
			// 验证IP
			String ipAddress = getUserIpAddr(httpServletRequest);
			LogUtil.debug(this.getClass(), "请求客户端的IP地址:" + ipAddress);
			
			// 获取系统标识对应的拦截规则
			String authType = "";
//			SELECT a.`auth_type`,a.`busi_code`,a.`busi_name`
//			FROM t_gq_fund_ws_business a WHERE a.`busi_code` = '2'
//			SELECT b.`busi_code`,b.`ip_addr`,b.`ip_addr_section` 
//			FROM t_gq_fund_ws_ipconfig b WHERE b.`busi_code` = '2'

			if("0".equals(authType)) {// 不校验IP
			} else if("1".equals(authType)) { //IP全校验
				List<String> ipList = new ArrayList<String>();
				boolean flag = false;
				for (String ip : ipList) {
					if (ip.equals(ipAddress)) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
				}
			} else if("2".equals(authType)) { // IP段校验
				List<String> ipList = new ArrayList<String>();
				boolean flag = false;
				for (String ip : ipList) {
					if (ipAddress.indexOf(ip) > 0) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
				}
			}
			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**获取客户端ip地址**/
	private String getUserIpAddr(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		// 反向代理取不到客户端ip,取请求头里ip信息
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		return ip;
	}
	
	@Override
	public void destroy() {
	}

}
