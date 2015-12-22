package com.gqhmt.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gqhmt.core.mybatis.GqPageInfo;
import com.gqhmt.sys.entity.ApiAddr;
import com.gqhmt.sys.entity.ApiIpConfig;
import com.gqhmt.sys.entity.Business;
import com.gqhmt.sys.service.RestApiService;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.RequestUtil;

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
    @RequestMapping(value = "/sys/busi/list",method = RequestMethod.GET)
    public Object businessList(HttpServletRequest request,ModelMap model){
		int pageNum = RequestUtil.getInt(request, "pageNum1", 0);
		pageNum = pageNum > 0 ? pageNum : GlobalConstants.PAGE_SIZE;
		int cpage = RequestUtil.getInt(request, "pageNum", 0);
		Page<Business> page = PageHelper.startPage(cpage, pageNum);
		List<Business> businessList = restApiService.findBusinessList(null);
		GqPageInfo pageInfo = new GqPageInfo(businessList);
		model.addAttribute("page", pageInfo);
		return "sys/busi/busi-list";
    }
    
    /**
     * 跳转至商户新增
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/add",method = RequestMethod.GET)
    public Object businessAdd(HttpServletRequest request,ModelMap model){
		return "sys/busi/busi-add";
    }
    
    /**
     * 商户新增确认
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/addConfirm")
    @ResponseBody
    public Object businessAddConfirm(HttpServletRequest request,@ModelAttribute(value="busi")Business busi){
    	restApiService.insertBusiness(busi);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    /**
     * 商户新增确认
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/checkCode")
    @ResponseBody
    public Object busiCheckCode(HttpServletRequest request,@ModelAttribute(value="busiCode")String busiCode){
    	Map<String, Object> param =  new HashMap<>();
    	param.put("busiCode", busiCode);
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
     * 跳转至商户修改
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/update/{busiCode}",method = RequestMethod.GET)
    public Object businessUpdate(HttpServletRequest request,ModelMap model, @PathVariable String busiCode){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("busiCode", busiCode);
    	List<Business> busiList = restApiService.findBusinessList(param);
		model.addAttribute("busi", busiList.get(0));
		return "sys/busi/busi-update";
    }
    /**
     * 商户修改确认
     * @param request
     * @param model
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
     * 跳转至商户ip修改
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/ipupdate/{busiCode}",method = RequestMethod.GET)
    public Object businessIpUpdate(HttpServletRequest request,ModelMap model, @PathVariable String busiCode){
    	ApiIpConfig apiIpConfig =  new ApiIpConfig();
    	apiIpConfig.setBusiCode(busiCode);
    	List<ApiIpConfig> apiIpList = restApiService.findApiIpList(apiIpConfig);
		model.addAttribute("apiIpList", apiIpList);
		model.addAttribute("busiCode", busiCode);
		return "sys/busi/busi-ipupdate";
    }
    /**
     * 商户修改确认
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/ipUpdateConfirm")
    @ResponseBody
    public Object ipUpdateConfirm(HttpServletRequest request,@ModelAttribute(value="ipAddr")ApiIpConfig ipConfig, ModelMap model){
    	// 删掉原有ip
    	ApiIpConfig ip = new ApiIpConfig();
		ip.setBusiCode(ipConfig.getBusiCode());
		restApiService.deleteApiIpConfig(ip);
    	Map<String, String> map = new HashMap<String, String>();
    	// 保存新录入ip
    	if(StringUtils.isNotBlank(ipConfig.getIpAddr())) {
	    	String[] ipAddrs = ipConfig.getIpAddr().split(",");
			for (String addr : ipAddrs) {
				ip = new ApiIpConfig();
				ip.setBusiCode(ipConfig.getBusiCode());
				ip.setIpAddr(addr);
				restApiService.insertApiIpConfig(ip);
			}
    	}
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    /**
     * 跳转至商户ip修改
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/apiupdate/{busiCode}",method = RequestMethod.GET)
    public Object businessApiUpdate(HttpServletRequest request,ModelMap model, @PathVariable String busiCode){
    	ApiAddr apiAddr =  new ApiAddr();
    	apiAddr.setBusiCode(busiCode);
    	List<ApiAddr> apiAddrList = restApiService.findApiAddrList(apiAddr);
		model.addAttribute("apiList", apiAddrList);
		model.addAttribute("busiCode", busiCode);
		return "sys/busi/busi-apiupdate";
    }
    /**
     * 商户修改确认
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/apiUpdateConfirm")
    @ResponseBody
    public Object apiUpdateConfirm(HttpServletRequest request,@ModelAttribute(value="ipAddr")ApiAddr apiAddr, ModelMap model){
    	// 删掉原有api地址
    	ApiAddr addr = new ApiAddr();
    	addr.setBusiCode(apiAddr.getBusiCode());
		restApiService.deleteApiAddr(addr);
    	Map<String, String> map = new HashMap<String, String>();
    	// 保存新录入api地址
    	if(StringUtils.isNotBlank(apiAddr.getApiAddr())) {
	    	String[] apiAddrs = apiAddr.getApiAddr().split(",");
			for (String api : apiAddrs) {
				addr = new ApiAddr();
				addr.setBusiCode(apiAddr.getBusiCode());
				addr.setApiAddr(api);
				restApiService.insertApiAddr(addr);
			}
    	}
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
}
