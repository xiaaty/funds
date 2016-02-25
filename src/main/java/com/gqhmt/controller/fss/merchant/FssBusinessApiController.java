package com.gqhmt.controller.fss.merchant;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.merchant.bean.BusinessApiBean;
import com.gqhmt.fss.architect.merchant.entity.ApiAddrEntity;
import com.gqhmt.fss.architect.merchant.entity.MerchantApiEntity;
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
public class FssBusinessApiController {

    @Resource
    private MerchantService merchantService;

   /**
    * 
    * author:jhz
    * time:2016年2月19日
    * function：API录入列表
    */
    @RequestMapping(value = "/fss/api/businessApiList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object businessApiList(HttpServletRequest request,ModelMap model, BusinessApiBean businessApiBean){
    	List<BusinessApiBean> apiList = merchantService.findBusinessApiList(businessApiBean);
		model.addAttribute("page", apiList);
		model.addAttribute("businessApiBean", businessApiBean);
		return "sys/busi/busiApiList";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID删除商户API授权
     */
    @RequestMapping(value = "/fss/api/deleteBusinessApi/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object deleteBusinessApi(HttpServletRequest request,ModelMap model, @PathVariable Long id){
    	merchantService.deleteBusinessApi(id);
    	return "redirect:/fss/api/businessApiList";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID查询商户API授权
     */
    @RequestMapping(value = "/fss/api/selectBusinessApi/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object selectBusinessApi(HttpServletRequest request,ModelMap model, @PathVariable Long id){
    	BusinessApiBean selectBusinessApi = merchantService.selectBusinessApi(id);
    	List<ApiAddrEntity> apiList= merchantService.findApiAddrList();
    	model.addAttribute("busiApi", selectBusinessApi);
    	model.addAttribute("apiList", apiList);
    	return "/sys/busi/businessApiUpdate";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：修改商户API授权
     * @throws ParseException 
     */
    @RequestMapping(value = "/fss/api/updateBusinessApi",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateBusinessApi(HttpServletRequest request,ModelMap model,MerchantApiEntity merchantApiEntity) throws ParseException{
    	String creatTime = request.getParameter("creatTime");
    	merchantApiEntity.setCreateTime(ToDateUtils.toDate(creatTime));
    	merchantApiEntity.setModifyTime(new Date());
    	merchantService.updateBusinessApi(merchantApiEntity);
    	Map<String, String> map = new HashMap<String, String>();
    	 map.put("code", "0000");
         map.put("message", "success");
 		return map;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：添加商户API授权
     * @throws ParseException 
     */
    @RequestMapping(value = "/fss/api/insertBusinessApi",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object toBusinessApiAdd(HttpServletRequest request,ModelMap model,MerchantApiEntity merchantApiEntity) {
    	merchantApiEntity.setCreateTime(new Date());
    	merchantService.insertBusinessApi(merchantApiEntity);
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("code", "0000");
    	map.put("message", "success");
    	return map;
    }
  
}
