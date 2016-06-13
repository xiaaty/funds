package com.gqhmt.sys.controller;


import java.util.ArrayList;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.sys.service.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import com.gqhmt.sys.entity.Settings;

@Controller
public class SettingController{

    @Resource
    private SettingService setService;
    @Resource
    private MerchantService merchantService;
   
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：商户列表
     */
    @RequestMapping(value = "/sys/workassist/getBusiness/{setingType}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object settingList(HttpServletRequest request,ModelMap model,@PathVariable String setingType,MerchantEntity merchantEntity){
    	List<MerchantEntity> merchantEntities =new ArrayList<MerchantEntity>();
    	if(setingType.equals("9902")){
    		//主商户列表
    	 merchantEntities = merchantService.getParentBusiness(merchantEntity);
    	}else if(setingType.equals("9903")){
    		//子商户列表
    		merchantEntities = merchantService.getChildBusiness(merchantEntity);
    	}
    	model.addAttribute("page", merchantEntities);
    	model.addAttribute("setingType",setingType);
    	model.addAttribute("business", merchantEntity);
    	return "sys/workAssist/setting/businessList";
    }

    
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：商户的系统配置
     */
    @RequestMapping(value = "/sys/workassist/settingList/{mchnNo}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
	public Object settingList(HttpServletRequest request,ModelMap model,@PathVariable String mchnNo,Settings setting,String setingType)  {
    	setting.setMchnNo(mchnNo);
    	List<Settings> settingList = setService.settingList(setting);
    	for (Settings settings : settingList) {
			settings.setSetingType(setingType);
		}
    	model.addAttribute("page", settingList);
    	model.addAttribute("setting", setting);
		return "sys/workAssist/setting/settingList";
	}
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：添加商户的系统配置
     */
    @RequestMapping(value = "/sys/workassist/settingAdd/{mchnNo}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object settingAdd(HttpServletRequest request,ModelMap model,@PathVariable String mchnNo,Settings setting,String setingType)  {
    	setting.setMchnNo(mchnNo);
    	setting.setSetingType(setingType);
    	model.addAttribute("setting", setting);
    	return "sys/workAssist/setting/settingAdd";
    }
	/**
	 * 
	 * author:jhz
	 * time:2016年2月14日
	 * function：新增系统配置
	 */
    @RequestMapping(value = "/sys/workassist/settingSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object SettingsSave(HttpServletRequest request,Settings set){
    	
    	
    	int insertSetting = setService.insertSetting(set);
    	Map<String, String> map = new HashMap<String, String>();
    	if(insertSetting>0){
    		
    		map.put("code", "0000");
    		map.put("message", "success");
    	}else{
    		
    		map.put("code", "0001");
    		map.put("message", "error");
    	}
    	return map;
    }
    
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：修改系统配置
     */
    @RequestMapping(value = "/sys/workassist/settingToUpdate/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object settingToUpdate(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
    	Settings findSettingById = setService.findSettingById(id);
    	model.addAttribute("setting", findSettingById);
    	return "sys/workAssist/setting/settingUpdate";
    }
    
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：保存修改的系统配置
     */
    @RequestMapping(value = "/sys/workassist/settingSaveUpdate",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object settingUpdate(HttpServletRequest request,@ModelAttribute(value="setting")Settings setting){
    	int updateSetting = setService.updateSetting(setting);
    	Map<String, String> map = new HashMap<String, String>();
    	if(updateSetting>0){
    		
	    	map.put("code", "0000");
	    	map.put("message", "success");
    	}else{
    		map.put("code", "0001");
    		map.put("message", "error");
    	}
    	return map;
    }
    
    
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：删除系统配置
     */
    @RequestMapping(value = "/sys/workassist/deleteSetting",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object settingDelete(HttpServletRequest request,Long id){
    	int deleteSetting = setService.deleteSetting(id);
    	Map<String, String> map = new HashMap<String, String>();
    	if(deleteSetting>0){
    	
    	map.put("code", "0000");
    	map.put("message", "success");
    	}else{
    		
    		map.put("code", "0001");
    		map.put("message", "error");
    	}
    	return map;
    }

    
}
