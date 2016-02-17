package com.gqhmt.fss.controller.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssWaterEntity;
import com.gqhmt.fss.architect.account.service.FssWaterService;
import com.gqhmt.fss.architect.customer.bean.CustomerAndUser;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class FssAccountController {
	@Resource
    private FundAccountService fundAccountService;
	@Resource
	private FssWaterService fssWaterService;

    /**
     * 旧版账户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/account/oldlist",method = RequestMethod.GET)
	@AutoPage
    public Object accountOldList(HttpServletRequest request,ModelMap model){
//		List<FundAccountEntity> accountList = fundAccountService.queryFundsAccountList(entity);
//		model.addAttribute("page", accountList);
		return "fss/account/accountList";
    }
    /**
     * 
     * author:jhz
     * time:2016年1月26日
     * function：查看流水详情
     */
    @RequestMapping(value = "/fss/account/waterDetail/{id}",method = {RequestMethod.GET,RequestMethod.POST})
 	@AutoPage
     public Object waterDetail(HttpServletRequest request,ModelMap model,@PathVariable Long id,String startDate,String endDate){
    	List<FssWaterEntity> waterDetails = fssWaterService.queryWaterDetail(id,startDate,endDate);
 		model.addAttribute("startDate", startDate);
 		model.addAttribute("endDate", endDate);
 		model.addAttribute("page", waterDetails);
 		model.addAttribute("id",id);
 		return "fss/account/WaterDetail";
     }
    
    /**
     * 互联网账户列表、出借账户列表，根据账户标识符(t_gq_fss_account.busi_type)判断， 互联网账户（：10000001），出借账户（：10000002），
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/account/interaccountlist/{busiNo}",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object intenetAccountList(HttpServletRequest request,ModelMap model,@PathVariable String busiNo,String accNo,String custNo,String bussinessname,String bussinesscertno){
    	Map map=new HashMap();
    	if(StringUtils.isNotEmptyString(busiNo)){//业务编号
    		map.put("busiNo",busiNo);
    	}
    	if(StringUtils.isNotEmptyString(accNo)){
    		map.put("accNo",accNo);
    	}
    	if(StringUtils.isNotEmptyString(custNo)){
    		map.put("custNo",custNo);
    	}
    	if(StringUtils.isNotEmptyString(bussinessname)){
    		map.put("bussinessname",bussinessname);
    	}
    	if(StringUtils.isNotEmptyString(bussinesscertno)){
    		map.put("bussinesscertno",bussinesscertno);
    	}
    	List<BussAndAccountBean> accountList = fundAccountService.queryAccountList(map);
		model.addAttribute("page", accountList);
		model.addAttribute("accNo", accNo);
		model.addAttribute("custNo", custNo);
		model.addAttribute("bussinessname", bussinessname);
		model.addAttribute("bussinesscertno", bussinesscertno);
		return "fss/account/internetAccountList";
    }
    
    
}
