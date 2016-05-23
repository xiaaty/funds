package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssWaterEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.account.service.FssWaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
public class FssAccountController {
	@Resource
    private FssAccountService fssAccountService;
	@Resource
	private FssWaterService fssWaterService;

    /**
     * 
     * author:jhz
     * time:2016年1月26日
     * function：查看流水详情
     * @throws FssException 
     */
    @RequestMapping(value = "/accounts/{type}/list/{id}/water",method = {RequestMethod.GET,RequestMethod.POST})
 	@AutoPage
     public Object waterDetail(HttpServletRequest request,ModelMap model,@PathVariable Long id,@PathVariable Integer type,String startDate,String endDate) throws FssException{
    	List<FssWaterEntity> waterDetails = fssWaterService.queryWaterDetail(id,startDate,endDate);
 		model.addAttribute("startDate", startDate);
 		model.addAttribute("endDate", endDate);
 		model.addAttribute("page", waterDetails);
 		model.addAttribute("id",id);
 		return "fss/account/WaterDetail";
     }
    
    /**
     * 账户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/account/{type}/list",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object intenetAccountList(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map,@PathVariable String type) throws FssException {
    	map.put("type", type);
    	List<BussAndAccountBean> accountList = fssAccountService.queryAccountList(map);
		model.addAttribute("page", accountList);
		 model.put("map", map);
		return "fss/account/internetAccountList";
    }
    
}
