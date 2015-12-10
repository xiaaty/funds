package com.gqhmt.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class InitCommon extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		Map<String,Object> parameMap = new HashMap<String,Object>();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Application.getInstance().runALL(ctx, this.getServletContext(), parameMap);

	}
	
	/*
	 * key application里面的key
	 *method  
	 *update  仅仅做修改一个对象
	 *reload 重写加载application某key
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key=request.getParameter("key");
		String method=request.getParameter("method");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if(key!=null&&key.length()>0&&method!=null){
			if("update".equalsIgnoreCase(method)){
			String obj=request.getParameter("obj");
			Application.getInstance().update(this.getServletContext(), key, ctx,obj);
			}else if("reload".equalsIgnoreCase(method)){
				Application.getInstance().reload(this.getServletContext(), key, ctx);
			}
			
		}
	}
}
