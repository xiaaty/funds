package com.gqhmt.fss.controller.merchant;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.merchant.entity.ApiAddr;
import com.gqhmt.fss.architect.merchant.service.*;
import com.gqhmt.util.ToDateUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.sys.controller.MenuController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015年12月21日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/02/19  jhz      1.0     1.0 Version
 */
@Controller
public class FssApiController {

    @Resource
    private RestApiService restApiService;

   /**
    * 
    * author:jhz
    * time:2016年2月19日
    * function：API列表
    */
    @RequestMapping(value = "/fss/api/apiList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object apiList(HttpServletRequest request,ModelMap model){
    	List<ApiAddr> apiList = restApiService.findApiAddrList();
		model.addAttribute("page", apiList);
		return "sys/busi/api/apiAddrList";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID删除API
     */
    @RequestMapping(value = "/fss/api/deleteApi/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object deleteApi(HttpServletRequest request,ModelMap model, @PathVariable Long id){
    	restApiService.deleteApiAddr(id);
    	return "redirect:/fss/api/apiList";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID查询API
     */
    @RequestMapping(value = "/fss/api/selectApi/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object selectBusinessApi(HttpServletRequest request,ModelMap model, @PathVariable Long id){
    	ApiAddr addr = restApiService.findapiAddrById(id);
    	model.addAttribute("addr", addr);
    	return "/sys/busi/api/apiAddrUpdate";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：修改API
     */
    @RequestMapping(value = "/fss/api/updateApi",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateBusinessApi(HttpServletRequest request,ModelMap model,ApiAddr apiAddr) throws ParseException{
    	String creatTime = request.getParameter("creatTime");
    	apiAddr.setCreateTime(ToDateUtils.toDate(creatTime));
    	apiAddr.setModifyTime(new Date());
    	restApiService.updateApiAddr(apiAddr);
    	Map<String, String> map = new HashMap<String, String>();
    	 map.put("code", "0000");
         map.put("message", "success");
 		return map;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：条转到API添加
     */
    @RequestMapping(value = "/fss/api/toAddApi",method = {RequestMethod.GET,RequestMethod.POST})
    public Object toAddApiAddr(HttpServletRequest request,ModelMap model){
    	return "/sys/busi/api/apiAddrAdd";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：添加API
     * @throws ParseException 
     */
    @RequestMapping(value = "/fss/api/insertApi",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object toBusinessApiAdd(HttpServletRequest request,ModelMap model,ApiAddr apiAddr) {
    	apiAddr.setCreateTime(new Date());
    	restApiService.insertApiAddr(apiAddr);
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("code", "0000");
    	map.put("message", "success");
    	return map;
    }
  
}
