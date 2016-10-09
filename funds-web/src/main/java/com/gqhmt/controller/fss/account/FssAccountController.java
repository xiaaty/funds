package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.service.SystemService;
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
    private FssMappingService fssMappingService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private SystemService systemService;
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
	 * 映射配置列表信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account/redaccountlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public Object redAccountList(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException {
		List<FssMappingEntity> list = fssMappingService.queryRedAccountList(map);
		model.addAttribute("page", list);
		model.put("map", map);
		return "fss/account/mappingList";
	}

	/**
	 * 添加映射配置
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
     */
	@RequestMapping(value = "/account/addRedAccount", method = {RequestMethod.GET, RequestMethod.POST})
	public Object AddAccountInfo(HttpServletRequest request, ModelMap model) throws FssException {
		List<DictEntity> list= systemService.getDictList();
		//获取商户列表
		model.addAttribute("list",list);
		return "fss/account/addMapping";
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
	 * 保存映射配置信息
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
		String remark = map.get("remark");
		String mappingType = map.get("mappingType");
		String tradeType = map.get("tradeType");
		String sort = map.get("sort");
		Map<String, String> map2 = new HashMap<String, String>();
		try {
			FundAccountEntity account = fundAccountService.getFundAccount(Long.valueOf(custId),0);
			if(account!=null){
				FssMappingEntity entity=fssMappingService.getMappingByCustId(custId);
				if(entity==null){
					//判断排序号是否存在
					FssMappingEntity mappingEntity=fssMappingService.getMappingBySort(sort);
					if(mappingEntity==null){
						fssMappingService.saveRedAccount(custId,remark,creator,mappingType,tradeType,sort);
						map2.put("code", "0000");
						map2.put("message", "success");
					}else{
						map2.put("code", "1003");
						map2.put("message", "success");
					}
				}else{
					map2.put("code", "1002");
					map2.put("message", "success");
				}
			}else{
				map.put("code", "1004");
				map.put("message","success");
			}

		} catch (FssException e) {//保存失败
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map2;
	}

	/**
	 * 删除映射配置信息
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
			fssMappingService.delRedAccountById(id);
			map.put("code", "0000");
			map.put("message", "删除成功");
		} catch (FssException e) {
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map;
	}

	/**
	 * 修改映射配置状态（是否有效）
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws FssException
     */

	@RequestMapping(value = "/account/updateMapping/{id}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object RedAccountUpdate(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws FssException {
		FssMappingEntity mappingEntity =fssMappingService.getMappingEntityById(id);
		List<DictEntity> list= systemService.getDictList();
		//获取商户列表
		model.addAttribute("list",list);
		model.addAttribute("mappingEntity", mappingEntity);
		return "fss/account/updateMapping";
	}

	/**
	 * 修改保存映射信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/account/updateAndSaveRedAccount", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object accountUpdate(HttpServletRequest request, @RequestParam Map<String, String> map) throws FssException {
		HttpSession session=  request.getSession();
		String updater = (String)session.getAttribute("userName");
		String id = map.get("id");
		String mappingType = map.get("mappingType");
		String tradeType = map.get("tradeType");
		String isValid = map.get("isValid");
		String sort = map.get("sort");
		Map<String, String> map2 = new HashMap<String, String>();
		try {
			fssMappingService.updateMappingValid(id,mappingType,tradeType,isValid,sort,updater);
			map2.put("code", "0000");
			map2.put("message", "success");
		} catch (FssException e) {
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map2;
	}
}
