package com.gqhmt.sys.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqhmt.core.util.AuthMenu;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.entity.MenuEntity;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
		SysUsers sysUsers = new SysUsers();  
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session = null;
		try{
			session = request.getSession();
		}catch (Exception e){
			chain.doFilter(request, response);
			return;
		}

		//在session中自定义一个参数，以它来校验是否完成过自动登陆
		Object user_login = session.getAttribute("AURORA_USER_LOGIN");
		if (user_login != null){
			//登陆过，就继续执行其他filter
			chain.doFilter(request, response);
			return;
		}
		 //通过CAS的API获得登陆账号
		 String loginName = null;
				 Assertion assertion = AssertionHolder.getAssertion();
				 if(assertion!=null){
					 loginName= assertion.getPrincipal().getName();
					 
				 }
		if(loginName!=null){
		 //执行本系统的登陆。跟平常同时校验用户名和密码不同，这里只有用户名。
		 try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			 String authUrl = ResourceUtil.getValue("config.appContext","authUrl");
			HttpPost httpPost = new HttpPost(authUrl);
			// 拼接参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("loginName", loginName));
			nvps.add(new BasicNameValuePair("systemName", "资金结算系统"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
			CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpPost);
			try {
				StatusLine statusLine =closeableHttpResponse.getStatusLine();
				HttpEntity entity = closeableHttpResponse.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(),statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				HashMap<String, Object> userInfo = new ObjectMapper().readValue(entity.getContent(), HashMap.class);
				// 获取用户信息,保存session或缓存
				System.out.println(userInfo.get("name"));
				 session.setAttribute("userName", userInfo.get("name"));
                /**..start--记录用户session信息**/
				sysUsers.setId((String) userInfo.get("id"));
                sysUsers.setUserName((String) userInfo.get("name"));
                sysUsers.setLoginName((String) userInfo.get("loginName"));
                sysUsers.setEmployeeNo((String) userInfo.get("no"));
                GlobalConstants.setSession(request, GlobalConstants.SESSION_EMP, sysUsers);
                /**..end--记录用户session信息**/

                /**..start--记录菜单session信息**/
				String url  =  request.getServletPath();
				String context = request.getContextPath();
                List<Map<String, String>> menuList = (List<Map<String, String>>) userInfo.get("menuList");
                List<MenuEntity> menuEntities=new ArrayList<>();
                MenuEntity menuEntity=null;
                if(null != menuList){
				for (int i = 0; i < menuList.size(); i++) {
					//菜单系统自行处理
					menuEntity=new MenuEntity();
					Map<String, String> map = menuList.get(i);
					menuEntity.setMenuName(map.get("name"));
					menuEntity.setMenuUrl(map.get("href"));
					menuEntity.setId(map.get("id"));
					menuEntity.setParentId(map.get("parentId"));
					menuEntity.setParma(map.get("isShow"));
					menuEntities.add(menuEntity);
					}

				/**..end--记录菜单session信息**/
                }

//				String menu = Application.getInstance().getMenu(context,url);//
				String menu = authMenu.getMenu(menuEntities,context,url);
				request.getSession().setAttribute("menu", menu);
				// 消耗掉response
				EntityUtils.consume(entity);
			} finally {
				closeableHttpResponse.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//登陆成功
		session.setAttribute("loginName",loginName);
		session.setAttribute("AURORA_USER_LOGIN", Boolean.TRUE);
//		
		//跳转到登陆成功后的页面（系统自定义）
		response.sendRedirect(request.getContextPath()+"/main");
		}else{
			chain.doFilter(servletRequest, servletResponse);
		}

	}
	
	@Override
	public void destroy() {
	}

}
