package com.gqhmt.controller.fss.merchant;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.merchant.entity.ApiAddrEntity;
import com.gqhmt.fss.architect.merchant.entity.MerchantIpConfigEntity;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.service.*;

import org.apache.commons.codec.digest.Md5Crypt;
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
    private MerchantService merchantService;

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
    	List<MerchantEntity> merchantEntityList = merchantService.findBusinessList(param);
    	String returnId="0";
    	if(Integer.parseInt(parentId)>0){
    		MerchantEntity findMerchantEntityById = merchantService.findBusinessById(Long.parseLong(parentId));
			returnId = findMerchantEntityById.getParentId();
		}
		model.addAttribute("page", merchantEntityList);
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
    	 MerchantEntity findMerchantEntityById = merchantService.findBusinessById(parentId);
    	model.addAttribute("busi", findMerchantEntityById);
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
    public Object businessAddConfirm(HttpServletRequest request,@ModelAttribute(value="busi")MerchantEntity busi){
    	busi.setCreateTime(new Date());
    	String encryption = Md5Crypt.apr1Crypt(busi.getMchnKey());
    	busi.setMchnKey(encryption);
    	merchantService.insertBusiness(busi);
    	
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
    
    /**
     * 商户新增确认
     * @param request
     * @return
     */
    @RequestMapping(value = "/sys/busi/checkCode")
    @ResponseBody
    public Object busiCheckCode(HttpServletRequest request,@ModelAttribute(value="mchnNo")String mchnNo){
    	Map<String, Object> param =  new HashMap<String, Object>();
    	param.put("mchnNo", mchnNo.trim());
    	List<MerchantEntity> busiList = merchantService.findBusinessList(param);
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
    	List<MerchantEntity> busiList = merchantService.findBusinessList(param);
    	model.addAttribute("busi", busiList.get(0));
    	model.addAttribute("parentId", parentId);
    	if(parentId==0){
    		return "sys/busi/masterBusiUpdate";
    	}else{
    	List<MerchantEntity> findMerchantEntityList = merchantService.findBusinessList(null);
    	model.addAttribute("businessList", findMerchantEntityList);
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
    public Object businessUpdateConfirm(HttpServletRequest request,@ModelAttribute(value="busi")MerchantEntity busi){
    	busi.setModifyTime(new Date());
    	merchantService.updateBusiness(busi);
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
    	MerchantIpConfigEntity merchantIpConfigEntity =  new MerchantIpConfigEntity();
    	merchantIpConfigEntity.setMchnNo(mchnNo);
    	List<MerchantIpConfigEntity> apiIpList = merchantService.findApiIpList(merchantIpConfigEntity);
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
    public Object ipUpdateConfirm(HttpServletRequest request, @ModelAttribute(value="ipAddr")MerchantIpConfigEntity ipConfig, ModelMap model){
    	String[] ipAddrs = ipConfig.getIpAddress().split(",");
    	String[] mchnNos = ipConfig.getMchnNo().split(",");
    	// 删掉原有ip
    	MerchantIpConfigEntity ip = new MerchantIpConfigEntity();
		ip.setMchnNo(mchnNos[0]);
		merchantService.deleteApiIpConfig(ip);
    	Map<String, String> map = new HashMap<String, String>();
    	// 保存新录入ip
    	if(StringUtils.isNotBlank(ipConfig.getIpAddress())) {
			for (int i=0;i<ipAddrs.length;i++) {
					ip = new MerchantIpConfigEntity();
					ip.setMchnNo(mchnNos[i]);
					ip.setIpAddress(ipAddrs[i]);;
					ip.setModifyTime(new Date());
					merchantService.insertApiIpConfig(ip);
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
    	List<ApiAddrEntity> apiList= merchantService.findApiAddrList();
    	model.addAttribute("apiList", apiList);
    	model.addAttribute("mchnName", mchnName);
    	model.addAttribute("parentId", parentId);
    	return "/sys/busi/businessApiAdd";
    }
    
}
