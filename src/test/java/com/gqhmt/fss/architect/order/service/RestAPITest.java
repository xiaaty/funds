package com.gqhmt.fss.architect.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqhmt.fss.architect.merchant.service.RestApiService;
import com.gqhmt.util.Encriptor;
import com.gqhmt.util.LogUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filename:    com.gqhmt.fss.architect.order.service.OrderServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/10/27 15:47
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class RestAPITest extends AbstractJUnit4SpringContextTests {
	@Resource
	private RestApiService restApiService;

	private MockHttpServletRequest request;  
	private MockHttpServletResponse response;
	
	@Before
    public void setUp() {
		request = new MockHttpServletRequest();  
        request.setMethod("POST");
        // 构建json数据
        Map<String, String> dataMap = new HashMap<String, String>();
        String str = "junlongli";
        dataMap.put("busiCode", "2");
        dataMap.put("data", str);
        String signature = Encriptor.getMD5(str);
        dataMap.put("signature", signature);
        // 转换为json数据
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
        	json = mapper.writeValueAsString(dataMap);
			System.out.println("Java2Json: "+json);
			request.setAttribute("apiJson", json);  
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        request.setRemoteAddr("100.101.10.222");
        request.setRequestURI("/api/FundService");
        request.setServletPath("/api/FundService");
	}
	
    @Test
	public void testSave() throws Exception {
//        LoginController loginController = (LoginController) this.applicationContext.getBean("loginController");
//        loginController.login("loginName", "loginPwd", "verifyCode", request, null);
        
    	String url = request.getServletPath();
        Pattern pattern = Pattern.compile("^/api/");
		Matcher matcher = pattern.matcher(url);
        LogUtil.debug(this.getClass(),"ServletPath:"+url);
        LogUtil.debug(this.getClass(),"httpServletRequest.getRequestURI():"+request.getRequestURI());
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
        	}
			// 根据商户认证规则，验证IP
        	String ipAddress = getUserIpAddr(request);
        	// 获取service
    		Map<String, Object> busiInfo = restApiService.getAuthTypeByCode(busiCode);
    		if(null == busiInfo || busiInfo.isEmpty()) {// 未设置返回错误标识
    			// 返回无权访问403
    		}
    		String authIpType = String.valueOf(busiInfo.get("auth_ip_type"));
        	boolean isWhiteList = checkIpAuth(restApiService, authIpType, ipAddress, busiCode);
			if(!isWhiteList) {
				// 返回无权访问403
			}
			// 根据商户API访问规则，校验是否有权限
			String authApiType = String.valueOf(busiInfo.get("auth_api_type"));
			boolean isLegalURI = checkApiAuth(restApiService, authApiType, url, busiCode);
			if(!isLegalURI) {
				// 返回无权访问403
			}
			assert true;
        }
    }
    /**
	 * 校验商户白名单
	 * @param service
	 * @param ipAddress
	 * @param busiCode
	 * @return 是否在白名单
	 */
	private boolean checkIpAuth(RestApiService service, String authIpType, String ipAddress, String busiCode) {
		LogUtil.debug(this.getClass(), "请求客户端的IP地址:" + ipAddress);
		if("0".equals(authIpType)) {// 不校验IP
			return true;
		} else if("1".equals(authIpType)) { //IP全校验
			List<Map<String, String>> ipList = service.getIpAddrListByCode(busiCode);
			boolean flag = false;
			for (Map<String, String> ipMap : ipList) {
				if (ipAddress.indexOf(ipMap.get("ip_addr")) > 0) {
					flag = true;
					break;
				}
			}
			return flag;
		} else if("2".equals(authIpType)) { // IP段校验
			List<Map<String, String>> ipList = service.getIpAddrListByCode(busiCode);
			boolean flag = false;
			for (Map<String, String> ipMap : ipList) {
				if (ipAddress.indexOf(ipMap.get("ip_addr_section")) > 0) {
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
	private boolean checkApiAuth(RestApiService service, String authApiType, String url, String busiCode) {
		// 获取service
		if("0".equals(authApiType)) {// 不校验API
			return true;
		} else if("1".equals(authApiType)) { //校验API
			List<Map<String, String>> ipList = service.getApiListByCode(busiCode);
			boolean flag = false;
			for (Map<String, String> ipMap : ipList) {
				if (url.indexOf(ipMap.get("api_addr")) > 0) {
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
}