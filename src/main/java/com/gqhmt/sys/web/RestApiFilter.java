package com.gqhmt.sys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqhmt.sys.service.RestApiService;
import com.gqhmt.util.Encriptor;
import com.gqhmt.util.LogUtil;
import com.gqhmt.util.SpringUtils;

public class RestApiFilter implements Filter {
	
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
        LogUtil.debug(this.getClass(), "url:" + url);
        LogUtil.debug(this.getClass(), "httpServletRequest.getRequestURI():" + httpServletRequest.getRequestURI());
        Map<String, String> dataMap = null;
        String busiCode = null;
        if (matcher.find()) {
        	try {
				// 商户权限验证
				String apiJson = (String) request.getAttribute("apiJson");
				ObjectMapper mapper = new ObjectMapper();
				dataMap = mapper.readValue(apiJson, Map.class);
				String data = dataMap.get("data");
				// 获得MD5密文(加密解密方式验证签名)
				String signature = dataMap.get("signature");
				String serviceSign = Encriptor.getMD5(data);
				if(!signature.equals(serviceSign)) {
					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
					return;
				}
				// 获取系统标识对应的拦截规则
				busiCode = dataMap.get("busiCode");
        	} catch(Exception e) {
        		 LogUtil.error(this.getClass(), "商户请求参数有误:" + e.getMessage());
        		 e.printStackTrace();
        		 chain.doFilter(request, response);
        	}
			// 根据商户认证规则，验证IP
			String ipAddress = getUserIpAddr(httpServletRequest);
			LogUtil.debug(this.getClass(), "请求客户端的IP地址:" + ipAddress);
			// 获取service
			RestApiService restApiService = SpringUtils.getBean(RestApiService.class);
			Map<String, Object> busiInfo = restApiService.getAuthTypeByCode(busiCode);
			String authType = String.valueOf(busiInfo.get("auth_type"));
			if("0".equals(authType)) {// 不校验IP
			} else if("1".equals(authType)) { //IP全校验
				List<Map<String, String>> ipList = restApiService.getIpAddrListByCode(busiCode);
				boolean flag = false;
				for (Map<String, String> ipMap : ipList) {
					if (ipAddress.indexOf(ipMap.get("ip_addr")) > 0) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
				}
				chain.doFilter(request, response);
			} else if("2".equals(authType)) { // IP段校验
				List<Map<String, String>> ipList = restApiService.getIpAddrListByCode(busiCode);
				boolean flag = false;
				for (Map<String, String> ipMap : ipList) {
					if (ipAddress.indexOf(ipMap.get("ip_addr_section")) > 0) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
				}
				chain.doFilter(request, response);
			}
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
