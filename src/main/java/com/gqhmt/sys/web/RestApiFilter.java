package com.gqhmt.sys.web;

import com.gqhmt.fss.architect.merchant.service.MerchantService;
import com.gqhmt.util.LogUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Filename:    com.gqhmt.sys.web.RestApiFilter
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/14
 * Description:
 */
public class RestApiFilter implements Filter {
	
	private Pattern pattern = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pattern = Pattern.compile("^/api/");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		String url = httpServletRequest.getServletPath();
//		Matcher matcher = pattern.matcher(url);
//        LogUtil.debug(this.getClass(), "url:" + url);
//        LogUtil.debug(this.getClass(), "httpServletRequest.getRequestURI():" + httpServletRequest.getRequestURI());
//        Map<String, String> dataMap = null;
//        String busiCode = null;
//        if (matcher.find()) {
//        	try {
//				// 商户权限验证
//				String apiJson = (String) request.getAttribute("apiJson");
//				ObjectMapper mapper = new ObjectMapper();
//				dataMap = mapper.readValue(apiJson, Map.class);
//				String data = dataMap.get("data");
//				// 获得MD5密文(加密解密方式验证签名)
//				String signature = dataMap.get("signature");
//				String serviceSign = MD5Util.encryption(data);
//				if(!signature.equals(serviceSign)) {
//					//throw new Exception(new IllegalAccessException("IP address " + ipAddress + " is stint"));
//					return;
//				}
//				// 获取系统标识对应的拦截规则
//				busiCode = dataMap.get("busiCode");
//        	} catch(Exception e) {
//        		 LogUtil.error(this.getClass(), "商户请求参数有误:" + e.getMessage());
//        		 e.printStackTrace();
//        		 chain.doFilter(request, response);
//        	}
//			// 根据商户认证规则，验证IP
//        	String ipAddress = getUserIpAddr(httpServletRequest);
//        	RestApiService restApiService = SpringUtils.getBean(RestApiService.class);
//        	// 获取service
//    		Map<String, Object> busiInfo = restApiService.getAuthTypeByCode(busiCode);
//    		if(null == busiInfo || busiInfo.isEmpty()) {// 未设置返回错误标识
//    			// 返回无权访问403
//    		}
//    		String authIpType = String.valueOf(busiInfo.get("auth_ip_type"));
//        	boolean isWhiteList = checkIpAuth(restApiService, authIpType, ipAddress, busiCode);
//			if(!isWhiteList) {
//				// 返回无权访问403
//			}
//			// 根据商户API访问规则，校验是否有权限
//			String authApiType = String.valueOf(busiInfo.get("auth_api_type"));
//			boolean isLegalURI = checkApiAuth(restApiService, authApiType, url, busiCode);
//			if(!isLegalURI) {
//				// 返回无权访问403
//			}
//			chain.doFilter(request, response);
//		} else {
			chain.doFilter(request, response);
//		}
	}

	/**
	 * 校验商户白名单
	 * @param service
	 * @param ipAddress
	 * @param busiCode
	 * @return 是否在白名单
	 */
	private boolean checkIpAuth(MerchantService service, String authIpType, String ipAddress, String busiCode) {
		LogUtil.debug(this.getClass(), "请求客户端的IP地址:" + ipAddress);
		if("0".equals(authIpType)) {// 不校验IP
			return true;
		} else if("1".equals(authIpType)) { //IP段校验
			List<Map<String, String>> ipList = service.getIpAddrListByCode(busiCode);
			boolean flag = false;
			for (Map<String, String> ipMap : ipList) {
				if (ipAddress.indexOf(ipMap.get("ip_addr")) > 0) {
					flag = true;
					break;
				}
			}
			return flag;
		}
		return false;
	}
	/**
	 * 
	 * @param service
	 * @param url
	 * @param busiCode
	 * @return
	 */
	private boolean checkApiAuth(MerchantService service, String authApiType, String url, String busiCode) {
		// 获取service
		if("0".equals(authApiType)) {// 不校验API
			return true;
		} else if("1".equals(authApiType)) { //校验API
			List<Map<String, String>> apiList = service.getApiListByCode(busiCode);
			boolean flag = false;
			for (Map<String, String> apiMap : apiList) {
				if (url.indexOf(apiMap.get("api_addr")) > 0) {
					flag = true;
					break;
				}
			}
			return flag;
		}
		return false;
		
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
