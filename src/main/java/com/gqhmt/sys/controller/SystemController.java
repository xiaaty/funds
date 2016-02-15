package com.gqhmt.sys.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.merchant.entity.Business;
import com.gqhmt.fss.architect.merchant.service.RestApiService;
import com.gqhmt.sys.entity.DictMain;
import com.gqhmt.sys.entity.Settings;
import com.gqhmt.sys.service.SystemService;
import com.gqhmt.util.StringUtils;

@Controller
public class SystemController{

    @Resource
    private SystemService sysService;
    @Resource
    private RestApiService restApiService;
    /**
     * 查询字典表
     * @param request
     * @param model
     * @param dictmain
     * @return
     */
    @RequestMapping(value = "/sys/workassist/dictionary/{parent_id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object DictList(HttpServletRequest request,ModelMap model,DictMain dictmain,@PathVariable Long parent_id){
    	if(null!=parent_id && !"".equals(parent_id)){
    		dictmain.setParentId(parent_id);
    	}
        List<DictMain> dictList =sysService.queryDictmain(dictmain);
        model.addAttribute("page",dictList);
        model.addAttribute("dictmain",dictmain);
        return "sys/menu/dictList";
    }
    
    /**
     * 跳转到字典编辑页面
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictAdd/{parent_id}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object DictmainAdd(HttpServletRequest request, ModelMap model,@PathVariable Long parent_id,DictMain dict) throws FssException {
    	dict.setParentId(parent_id);
    	model.addAttribute("dict", dict);
		return "sys/menu/dictAdd";
	}
    
    /**
     * 保存新增字典信息
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object DictmainSave(HttpServletRequest request,@ModelAttribute(value="dict")DictMain dict){
    	if(StringUtils.isNotEmptyString(dict.getDictId())){
    		dict.setDictId(dict.getDictId());
    	}
    	if(StringUtils.isNotEmptyString(dict.getDictName())){
    		dict.setDictName(dict.getDictName());
    	}
    	if(null!=dict.getParentId()){
    		dict.setParentId(dict.getParentId());
    	}
    	dict.setCareateUserId(1l);
    	Date date=new Date();
    	dict.setCreateTime(date);
    	dict.setModifyUserId(1l);//以后从session中获取
    	dict.setModifyTime(date);
    	dict.setSort("0");
    	dict.setIsValid(dict.getIsValid());
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			sysService.insertDictmain(dict);
			 map.put("code", "0000");
		     map.put("message", "success");
		     map.put("parentid",dict.getParentId().toString());
		} catch (Exception e) {//保存失败
			e.printStackTrace();
			map.put("code", "0001");
		    map.put("message", "error");
		}
			return map;
    }
    
    /**
     * 修改
     * @param request
     * @param model
     * @param dictId
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/sys/workassist/dictToUpdate/{dictId}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object DictmaintoUpdate(HttpServletRequest request, ModelMap model,@PathVariable String dictId,@ModelAttribute(value="dict")DictMain dict) throws FssException {
    	List<DictMain> dictlist =sysService.getDictmainById(dictId);
    	model.addAttribute("dict", dictlist.get(0));
		return "sys/menu/dictUpdate";
	}
    
    /**
     * 修改保存
     * @param request
     * @param dict
     * @return
     */
    @RequestMapping(value = "/sys/workassist/updateAndSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictUpdate(HttpServletRequest request,@ModelAttribute(value="dict")DictMain dict){
    	dict.setModifyUserId(2l);
    	dict.setModifyTime(new Date());
    	sysService.updateDict(dict);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    
    /**
     * 删除
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/workassist/deletedeict",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictDelete(HttpServletRequest request,ModelMap model){
    	String dictId=	request.getParameter("dictId");
    	sysService.delteDict(dictId);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
        return map;
//		return "redirect:/sys/workassist/dictionary/0";
    }
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：商户列表
     */
    @RequestMapping(value = "/sys/workassist/getBusiness/{setingType}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object settingList(HttpServletRequest request,ModelMap model,@PathVariable String setingType,Business business){
    	List<Business> businesses=new ArrayList<Business>();
    	if(setingType.equals("9902")){
    		//主商户列表
    	 businesses = restApiService.getParentBusiness(business);
    	}else if(setingType.equals("9903")){
    		//子商户列表
    		businesses=restApiService.getChildBusiness(business);
    	}
    	model.addAttribute("page",businesses);
    	model.addAttribute("setingType",setingType);
    	model.addAttribute("business", business);
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
    	List<Settings> settingList = sysService.settingList(setting);
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
    	
    	
    	int insertSetting = sysService.insertSetting(set);
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
    	Settings findSettingById = sysService.findSettingById(id);
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
    	int updateSetting = sysService.updateSetting(setting);
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
    	int deleteSetting = sysService.deleteSetting(id);
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
