package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;
import com.gqhmt.fss.architect.loan.entity.FssSettleListEntity;
import com.gqhmt.fss.architect.loan.service.FssEnterAccountService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月15日
 * Description:
 * <p> 入账主表
 * <p> 账目详情
 * <p> 收费列表
 * 
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月15日  jhz      1.0     1.0 Version
 */
@Controller
public class FssEnterAccountController {
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	@Resource
	private FssEnterAccountService fssEnterAccountService;

	/**
	 * 
	 * author:jhz
	 * time:2016年3月15日
	 * function：入账主表
	 */						
	@RequestMapping(value = "/loan/enterAccount/list", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object enterAccountList(HttpServletRequest request, ModelMap model,String mchnChild,
					String resultState ,String tradeType) {
		Map<Object, Object> map = new HashMap<>();
		map.put("tradeType", tradeType);
		map.put("mchnChild", mchnChild);
		map.put("resultState", resultState);
		List<FssEnterAccountParentEntity> enterAccountEntities = fssEnterAccountService.getEnterAccountParentEntities(map);
		model.addAttribute("page", enterAccountEntities);
		model.addAttribute("map", map);
		return "fss/trade/trade_record/enterAccount_list";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：查看该批流水详情
	 */						  
	@RequestMapping(value = "/loan/enterAccount/{type}/{parentId}/detail", method = {RequestMethod.GET, RequestMethod.POST})
	public Object accountWater(HttpServletRequest request, ModelMap model,@PathVariable Long parentId,@PathVariable String type) {
		//通过流水好查询该批次的详情
		List<FssEnterAccountEntity> detail = fssEnterAccountService.getEnterAccounts(parentId);
		model.addAttribute("detail", detail);
		return "fss/trade/trade_record/enterAccount_detail";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：查看收费列表
	 * @throws FssException 
	 */
	@RequestMapping("/loan/enterAccount/{type}/{parentId}/detail/{id}/settleList")
	public String accountRecharge(HttpServletRequest request, ModelMap model, @PathVariable Long id,@PathVariable Long parentId,@PathVariable String type) throws FssException {
		List<FssSettleListEntity> settleList = fssEnterAccountService.getsettleList(id);
		model.addAttribute("settleList", settleList);
		return "fss/trade/trade_record/settleList";
	}

}