package com.gqhmt.controller.quartz;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.quartz.entity.FssQuartzJobEntity;
import com.gqhmt.quartz.service.FssQuzrtzService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月14日
 * Description: 定时器控制类
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月14日  jhz      1.0     1.0 Version
 */
@Controller
public class FssQuartzController {
	
	@Resource
	private FssQuzrtzService fssQuzrtzService;
   
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：定时器列表
	 */
	@RequestMapping(value = "/quartz/quartzList",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String quartzList(HttpServletRequest request,ModelMap model) throws Exception {
		 List<FssQuartzJobEntity> quartzList = fssQuzrtzService.findAll();
		model.addAttribute("page", quartzList);
		return "sys/quarzt/quarztList";
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：跳转到添加定时器页面
	 */
	@RequestMapping(value = "/quartz/toQuartzAdd",method = {RequestMethod.GET,RequestMethod.POST})
	public String toQuartzAdd(HttpServletRequest request,ModelMap model) throws Exception {
		return "sys/quarzt/quarztAdd";
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：添加定时器
	 */
	@RequestMapping(value = "/quartz/quartzAdd",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object quartzAdd(HttpServletRequest request,ModelMap model,FssQuartzJobEntity fssQuartzJobEntity) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			fssQuzrtzService.insert(fssQuartzJobEntity);
			 map.put("code", "0000");
		     map.put("message", "success");
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
		
		return map;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：跳转到修稿定时器页面
	 */
	@RequestMapping(value = "/quartz/toQuartzUpdate/{id}",method = {RequestMethod.GET,RequestMethod.POST})
	public String toQuartzUpdate(HttpServletRequest request,ModelMap model,@PathVariable Long id) throws Exception {
		FssQuartzJobEntity selectByPrimaryKey = fssQuzrtzService.selectByPrimaryKey(id);
		model.addAttribute("quarzt", selectByPrimaryKey);
		return "sys/quarzt/quarztUpdate";
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：修改定时器
	 */
	@RequestMapping(value = "/quartz/quartzUpdate",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object quarztUpdate(HttpServletRequest request,ModelMap model,FssQuartzJobEntity fssQuartzJobEntity) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			fssQuzrtzService.update(fssQuartzJobEntity);
			map.put("code", "0000");
			map.put("message", "success");
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
			map.put("message", "error");
		}
		
		return map;
	}
	
}
