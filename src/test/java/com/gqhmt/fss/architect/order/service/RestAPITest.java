package com.gqhmt.fss.architect.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqhmt.fss.architect.order.mapper.write.OrderWriteDaoInter;
import com.gqhmt.fss.architect.order.entity.Order;
import com.gqhmt.sys.controller.LoginController;
import com.gqhmt.sys.service.RestApiService;
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

import junit.framework.Assert;

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
        if (matcher.find()) {
			// 商户权限验证
			String apiJson = (String) request.getAttribute("apiJson");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> dataMap = mapper.readValue(apiJson, Map.class);
			String data = dataMap.get("data");
			// 获得MD5密文(加密解密方式验证签名)
			String signature = dataMap.get("signature");
			String serviceSign = Encriptor.getMD5(data);
			if(!signature.equals(serviceSign)) {
				return;
			}
			// 验证IP
			String ipAddress = request.getRemoteAddr();
			LogUtil.debug(this.getClass(), "请求客户端的IP地址:" + ipAddress);
			
			// 获取系统标识对应的拦截规则
			String busiCode = dataMap.get("busiCode");
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
				Assert.assertEquals(true, flag);
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
				Assert.assertEquals(true, flag);
			}
		} else {
			
		}
        assert true;
    }
}