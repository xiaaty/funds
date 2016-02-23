package com.gqhmt.controller.fss.merchant;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.merchant.entity.ApiAddr;
import com.gqhmt.fss.architect.merchant.entity.ApiIpConfig;
import com.gqhmt.fss.architect.merchant.entity.Business;
import com.gqhmt.fss.architect.merchant.service.*;
import com.gqhmt.util.MD5Utils;

import org.apache.commons.lang3.StringUtils;
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
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015年12月21日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/21  李俊龙      1.0     1.0 Version
 */
@Controller
public class BusinessController {

    @Resource
    private RestApiService restApiService;

    /**
     * 商户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/list/{parentId}",method = RequestMethod.GET)
	@AutoPage
    public Object businessList(HttpServletRequest request,ModelMap model,@PathVariable String parentId){
    	Map<String, Object> param =  new HashMap<String, Object>();
    	param.put("parentId", parentId);
    	List<Business> businessList = restApiService.findBusinessList(param);
    	String returnId="0";
    	if(Integer.parseInt(parentId)>0){
    		Business findBusinessById = restApiService.findBusinessById(Long.parseLong(parentId));
			returnId = findBusinessById.getParentId();
		}
		model.addAttribute("page", businessList);
		model.addAttribute("returnId", returnId);
		return "sys/busi/busiList";
    }
    
   /**
    * 
    * author:jhz
    * time:2016年2月23日
    * function：添加子账户
    */
    @RequestMapping(value = "/sys/busi/add/{parentId}",method = RequestMethod.GET)
    public Object businessAdd(HttpServletRequest request,ModelMap model, @PathVariable Integer parentId){
    	Map<String, Object> param =  new HashMap<String, Object>();
    	param.put("parentId", parentId);
    	if(parentId==0){
    		return "sys/busi/masterBusiAdd";
    	}else{
    	 Business findBusinessById = restApiService.findBusinessById(parentId);
    	model.addAttribute("busi", findBusinessById);
		return "sys/busi/childBusiAdd";
    	}
    }
    
    /**
     * 商户新增确认
     * @param request
     * @param busi
     * @return
     */
    @RequestMapping(value = "/sys/busi/addConfirm")
    @ResponseBody
    public Object businessAddConfirm(HttpServletRequest request,@ModelAttribute(value="busi")Business busi){
    	busi.setCreateTime(new Date());
    	String encryption = MD5Utils.encryption(busi.getMchnKey());
    	busi.setMchnKey(encryption);
    	restApiService.insertBusiness(busi);
    	
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    /**
     * 商户新增确认
     * @param request
     * @param busiCode
     * @return
     */
    @RequestMapping(value = "/sys/busi/checkCode")
    @ResponseBody
    public Object busiCheckCode(HttpServletRequest request,@ModelAttribute(value="mchnNo")String mchnNo){
    	Map<String, Object> param =  new HashMap<String, Object>();
    	param.put("mchnNo", mchnNo.trim());
    	List<Business> busiList = restApiService.findBusinessList(param);
    	Map<String, String> map = new HashMap<String, String>();
    	if(null != busiList && !busiList.isEmpty()) {
    		map.put("code", "0000");
    	} else {
    		map.put("code", "0001");
    	}
		return map;
    }
    
	 /**
	  * 
	  * author:jhz
	  * time:2016年2月23日
	  * function：跳转到商户修改
	  */
    @RequestMapping(value = "/sys/busi/update/{mchnNo}",method = RequestMethod.GET)
    public Object businessUpdate(HttpServletRequest request,ModelMap model, @PathVariable String mchnNo,Integer parentId){
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("mchnNo", mchnNo);
    	List<Business> busiList = restApiService.findBusinessList(param);
    	model.addAttribute("busi", busiList.get(0));
    	model.addAttribute("parentId", parentId);
    	if(parentId==0){
    		return "sys/busi/masterBusiUpdate";
    	}else{
    	List<Business> findBusinessList = restApiService.findBusinessList(null);
    	model.addAttribute("businessList", findBusinessList);
		return "sys/busi/childBusiUpdate";
    	}
    }
    /**
     * 商户修改确认
     * @param request
     * @param busi
     * @return
     */
    @RequestMapping(value = "/sys/busi/updateConfirm")
    @ResponseBody
    public Object businessUpdateConfirm(HttpServletRequest request,@ModelAttribute(value="busi")Business busi){
    	busi.setModifyTime(new Date());
    	restApiService.updateBusiness(busi);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    /**
     * 跳转至商户IP修改
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/ipupdate/{mchnNo}",method = RequestMethod.GET)
    public Object businessIpUpdate(HttpServletRequest request,ModelMap model, @PathVariable String mchnNo){
    	ApiIpConfig apiIpConfig =  new ApiIpConfig();
    	apiIpConfig.setMchnNo(mchnNo);
    	List<ApiIpConfig> apiIpList = restApiService.findApiIpList(apiIpConfig);
		model.addAttribute("apiIpList", apiIpList);
		model.addAttribute("mchnNo", mchnNo);
		return "sys/busi/busiIpUpdate";
    }
    /**
     * 商户IP修改确认
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/ipUpdateConfirm")
    @ResponseBody
    public Object ipUpdateConfirm(HttpServletRequest request,@ModelAttribute(value="ipAddr")ApiIpConfig ipConfig, ModelMap model){
    	String[] ipAddrs = ipConfig.getIpAddress().split(",");
    	String[] mchnNos = ipConfig.getMchnNo().split(",");
    	// 删掉原有ip
    	ApiIpConfig ip = new ApiIpConfig();
		ip.setMchnNo(mchnNos[0]);
		restApiService.deleteApiIpConfig(ip);
    	Map<String, String> map = new HashMap<String, String>();
    	// 保存新录入ip
    	if(StringUtils.isNotBlank(ipConfig.getIpAddress())) {
			for (int i=0;i<ipAddrs.length;i++) {
					ip = new ApiIpConfig();
					ip.setMchnNo(mchnNos[i]);
					ip.setIpAddress(ipAddrs[i]);;
					ip.setModifyTime(new Date());
					restApiService.insertApiIpConfig(ip);
			}
    	}
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：跳转到商户API授权页面
     * @throws ParseException 
     */
    @RequestMapping(value = "/sys/busi/toBusinessApiAdd/{mchnNo}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object toBusinessApiAdd(HttpServletRequest request,ModelMap model,@PathVariable String mchnNo,String mchnName,String parentId ) throws ParseException{
    	List<ApiAddr> apiList=restApiService.findApiAddrList();
    	model.addAttribute("apiList", apiList);
    	model.addAttribute("mchnName", mchnName);
    	model.addAttribute("parentId", parentId);
    	return "/sys/busi/businessApiAdd";
    }
    
}
