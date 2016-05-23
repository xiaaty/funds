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


	}
	
	/*
	 * key application里面的key
	 *method  
	 *update  仅仅做修改一个对象
	 *reload 重写加载application某key
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
