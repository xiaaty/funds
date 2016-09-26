package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssRedAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.account.service.FssRedAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class FssAccountController {
	@Resource
    private FssAccountService fssAccountService;
	@Resource
    private FssRedAccountService fssRedAccountService;
	@Resource
	private FundAccountService fundAccountService;
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

	/**
	 * 红包账户列表信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account/redaccountlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public Object redAccountList(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException {
		List<FssRedAccountEntity> redacclist = fssRedAccountService.queryRedAccountList(map);
		model.addAttribute("page", redacclist);
		model.put("map", map);
		return "fss/account/redAccountList";
	}

	/**
	 * 添加红包账户
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
     */
	@RequestMapping(value = "/account/addRedAccount", method = {RequestMethod.GET, RequestMethod.POST})
	public Object AddAccountInfo(HttpServletRequest request, ModelMap model) throws FssException {
		return "fss/account/addRedAccount";
	}

	/**
	 * 根据客户得到客户信息和账户信息
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/account/getCustomerById", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object AccountSave(HttpServletRequest request,ModelMap model) throws FssException {
		Map<String,String> map=new HashMap<String,String>();
		String custId=request.getParameter("custId");
		FundAccountEntity account = fundAccountService.getFundAccount(Long.valueOf(custId),0);
		if(account!=null){
			map.put("code", "0000");
			map.put("custName",account.getCustName());
		}else{
			map.put("code", "10002");
			map.put("message","该账户信息不存在！");
		}
		model.addAttribute("map",map);
		return map;
	}


	/**
	 * 保存账户信息
	 *
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/account/saveRedAccount", method = {RequestMethod.POST})
	@ResponseBody
	public Object redAccountSave(HttpServletRequest request, @RequestParam Map<String, String> map) throws FssException {
		HttpSession session=  request.getSession();
		String creator = (String)session.getAttribute("userName");
		String custId = map.get("custId");
		String accountName = map.get("accountName");
		Map<String, String> map2 = new HashMap<String, String>();
		try {
			fssRedAccountService.saveRedAccount(custId,accountName,creator);
			map2.put("code", "0000");
			map2.put("message", "success");
		} catch (FssException e) {//保存失败
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map2;
	}

	/**
	 * 删除红包账户信息
	 * @param request
	 * @return
	 * @throws FssException
     */
	@RequestMapping(value = "/account/deleteRedAccount", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object accountUpdate(HttpServletRequest request) throws FssException {
		long id=Long.valueOf(request.getParameter("id"));
		Map<String, String> map = new HashMap<String, String>();
		try {
			fssRedAccountService.delRedAccountById(id);
			map.put("code", "0000");
			map.put("message", "删除成功");
		} catch (FssException e) {
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map;
	}











}
