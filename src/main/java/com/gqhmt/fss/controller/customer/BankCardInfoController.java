package com.gqhmt.fss.controller.customer;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Filename:    com.gqhmt.fss.controller.BankCardInfoController
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
public class BankCardInfoController {
	@Resource
    private BankCardInfoService bankCardInfoService;

    private BankCardInfoEntity entity;

	public BankCardInfoEntity getEntity() {
		return entity;
	}

	public void setBean(BankCardInfoEntity entity) {
		this.entity = entity;
	}
	
	 /**
     * 银行卡变更信息列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/customer/cardchangelist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object bankCardChangeList(HttpServletRequest request,ModelMap model,FssChangeCardEntity fssBankcard){
		List<FssChangeCardEntity> changecardList = bankCardInfoService.getBankCardChangeList(fssBankcard);
		model.addAttribute("page", changecardList);
		model.addAttribute("fssBankcard", fssBankcard);
		model.addAttribute("state",fssBankcard.getState());
		return "fss/customer/bankCardChangeList";
    }
    
    /**
     * 银行列表
     * @param request
     * @param model
     * @param fssBankcard
     * @return
     */
    @RequestMapping(value = "/fund/banklist",method = {RequestMethod.GET,RequestMethod.POST})
   	@AutoPage
       public Object bankList(HttpServletRequest request,ModelMap model,BankEntity bankinfo){
   		List<BankEntity> banklist = bankCardInfoService.getBankList(bankinfo);
   		model.addAttribute("page", banklist);
   		model.addAttribute("bankinfo", bankinfo);
   		return "fss/customer/bankList";
       }
    
	
    /**
     * 添加银行信息
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fund/banktoadd",method = {RequestMethod.GET,RequestMethod.POST})
    public Object bankAdd(HttpServletRequest request, ModelMap model) throws FssException {

    	return "fss/customer/bankAdd";
    }
    
    /**
     * 保存银行信息
     * @param request
     * @param bank
     * @return
     */
    @RequestMapping(value = "/fund/savebank",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object bankSave(HttpServletRequest request,@ModelAttribute(value="bank")BankEntity bank){
    	if(StringUtils.isNotEmptyString(bank.getBankName())){
    		bank.setBankName(bank.getBankName());
    	}else{
    		bank.setBankName(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getSortName())){
    		bank.setSortName(bank.getSortName());
    	}else{
    		bank.setSortName(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getBankIcon())){
    		bank.setBankIcon(bank.getBankIcon());
    	}else{
    		bank.setBankIcon(null);
    	}
    	if(StringUtils.isNotEmptyString(bank.getLimitPage())){
    		bank.setLimitPage(bank.getLimitPage());
    	}else{
    		bank.setLimitPage(null);
    	}
    	
    	Date date=new Date();
    	bank.setCreateTime(date);//创建日期
    	bank.setModifyTime(date);//修改日期
    	bank.setCreateUserId(1l);//创建人
    	bank.setModifyUserId(1l);//修改人
    	
    	if(StringUtils.isNotEmptyString(bank.getTmplatePage())){
    		bank.setTmplatePage(bank.getTmplatePage());
    	}else{
    		bank.setTmplatePage(null);
    	}
    	
    	if(bank.getIsSetLimitPage()!=null && !"".equals(bank.getIsSetLimitPage())){
    		bank.setIsSetLimitPage(bank.getIsSetLimitPage());
    	}else{
    		bank.setIsSetLimitPage(null);
    	}
    	
    	if(StringUtils.isNotEmptyString(bank.getBankCode())){
    		bank.setBankCode(bank.getBankCode());
    	}else{
    		bank.setBankCode(null);
    	}
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		bankCardInfoService.saveBank(bank);
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
     * 修改银行信息
     * @param request
     * @param model
     * @param id
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fund/banktoupdate/{id}",method = {RequestMethod.GET,RequestMethod.POST})
  	public Object banktoUpdate(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
      	BankEntity bank =bankCardInfoService.getBankById(id);
      	model.addAttribute("bank", bank);
  		return "fss/customer/bankUpdate";
  	}
    
    /**
     * 修改保存
     * @param request
     * @param bank
     * @return
     */
    @RequestMapping(value = "/fund/bankupdateandsave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object dictUpdate(HttpServletRequest request,@ModelAttribute(value="bank")BankEntity bank){
    	Date date=new Date();
    	bank.setModifyTime(date);//修改日期
    	bank.setModifyUserId(1l);//修改人
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		 bankCardInfoService.updateBank(bank);
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
