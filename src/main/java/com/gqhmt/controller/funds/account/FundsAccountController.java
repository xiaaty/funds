package com.gqhmt.controller.funds.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.pay.service.IFundsTrade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filename:    com.gqhmt.sys.controller.MenuController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015年12月21日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/02/16  jhz      1.0     1.0 Version
 */
@Controller
public class FundsAccountController {
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FundSequenceService fundSequenceService;
	@Resource
	private IFundsTrade fundsTradeImpl;


	/**
	 * author:jhz
	 * time:2016年2月16日
	 * function：fund账号管理
	 */
	@RequestMapping(value = "/funds/accountBusinessList/{custId}", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object acountList(HttpServletRequest request, ModelMap model, @PathVariable Integer custId,
							 String customerName, String creatTime, String modifyTime) {
		Map<Object, Object> accMap = new HashMap<>();
		if (creatTime != null && !creatTime.equals("")) {
			creatTime = creatTime + " 00:00:00";
		}
		if (modifyTime != null && !modifyTime.equals("")) {
			modifyTime = modifyTime + " 23:59:59";
		}
		accMap.put("customerName", customerName);
		accMap.put("creatTime", creatTime);
		accMap.put("modifyTime", modifyTime);
		accMap.put("custId", custId);
		List<FundAccountCustomerBean> acountList = fundAccountService.findAcountList(accMap);
		model.addAttribute("page", acountList);
		model.addAttribute("accMap", accMap);
//    	System.out.println(custId+"****************");
		if (custId > 100) {
			//帐号管理
			return "funds/account/accountList";
		} else {
			//对公账户列表
			return "funds/account/accountBusinessList";
		}
	}

	/**
	 * author:jhz
	 * time:2016年2月16日
	 * function：查看流水详情
	 */
	@RequestMapping(value = "/funds/account/accountWater/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object accountWater(HttpServletRequest request, ModelMap model, @PathVariable Integer id, Integer accountType, Integer busiType,
							   Integer actionType, String startDate, String endDate) {
		Map<Object, Object> fasMap = new HashMap<>();
		if (startDate != null && !startDate.equals("")) {
			startDate = startDate + " 00:00:00";
		}
		if (endDate != null && !endDate.equals("")) {
			endDate = endDate + " 23:59:59";
		}
		fasMap.put("busiType", busiType);
		fasMap.put("id", id);
		fasMap.put("accountType", accountType);
		fasMap.put("actionType", actionType);
		fasMap.put("startDate", startDate);
		fasMap.put("endDate", endDate);
		List<FundAccountSequenceBean> selectAccountSequenceList = fundSequenceService.selectAccountSequenceList(fasMap);


		model.addAttribute("fasMap", fasMap);
		model.addAttribute("page", selectAccountSequenceList);
		return "funds/account/accountWater";
	}

	/**
	 * author:jhz
	 * time:2016年2月18日
	 * function：跳转到为指定的客户提现页面
	 */
	@RequestMapping("/funds/acount/businessAccountWithdraw/{withHoldId}")
	public String businessAccountWithdraw(HttpServletRequest request, ModelMap model, @PathVariable Integer withHoldId) {
		FundAccountCustomerBean accountWithdraw = fundAccountService.fundAccountCustomerById(withHoldId);
		model.addAttribute("acct", accountWithdraw);
		return "funds/account/withDraw/account_business_withdraw";
	}

	/**
	 * author:jhz
	 * time:2016年2月24日
	 * function：为指定的对公账户提现
	 *
	 * @throws IOException
	 */
	@RequestMapping("/funds/acount/AccountWithdraw")
	@ResponseBody
	public Object withdraw(HttpServletRequest request, HttpServletResponse response, ModelMap modell, Integer custId, int businessType, BigDecimal amount) throws FssException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			fundsTradeImpl.withdrawApply(custId, businessType, null, amount, null);
			map.put("tips", "提现成功!!");

		} catch (FssException e) {
			LogUtil.error(this.getClass(), e.getMessage(), e);
			map.put("tips", "提现失败!!" + e.getMessage());
		}
		return map;
	}

	/**
	 * author:jhz
	 * time:2016年2月18日
	 * function： 跳转到为指定的客户代扣提现页面
	 */
	@RequestMapping("/funds/acount/custAccountWithhold/{withHoldId}")
	public String accountRecharge(HttpServletRequest request, ModelMap model, @PathVariable Integer withHoldId) {
		FundAccountCustomerBean accountWithdraw = fundAccountService.fundAccountCustomerById(withHoldId);
		model.addAttribute("acct", accountWithdraw);
		return "funds/account/withHold/account_withhold";
	}

	/**
	 * author:jhz
	 * time:2016年2月25日
	 * function：为指定的客户代扣
	 *
	 * @throws IOException
	 */
	@RequestMapping(value = "/funds/acount/withhold", method = RequestMethod.POST)
	@ResponseBody
	public Object updateRechargeAccount(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer custId, int businessType, BigDecimal amount) throws FssException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
//                List<BankCardinfoEntity> bankCardinfoList = new ArrayList<BankCardinfoEntity>();
//                bankCardinfoList = bankCardinfoService.queryInvestmentByCustId(custId);
//                if(bankCardinfoList == null && bankCardinfoList.size() < 1){
//                    throw new Exception("客户无对应的银行信息");
//                }
		try {
			fundsTradeImpl.withholdingApply(custId, businessType, null, amount, null);
			map.put("tips", "提现成功!!");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e.getMessage(), e);
			map.put("tips", "代扣失败!!" + e.getMessage());
		}
		return map;
	}

	/**
	 * author:jhz
	 * time:2016年2月18日
	 * function： 跳转到为指定的客户代付页面
	 */
	@RequestMapping("/funds/acount/custAccountWithdraw/{withDrawId}")
	public String toAccountWithdraw(HttpServletRequest request, ModelMap model, @PathVariable Integer withDrawId) {
		FundAccountCustomerBean accountWithdraw = fundAccountService.fundAccountCustomerById(withDrawId);
		model.addAttribute("acct", accountWithdraw);
		return "funds/account/withDraw/account_withdraw";
	}

	/**
	 * author:jhz
	 * time:2016年2月18日
	 * function： 为指定的客户代付提现
	 *
	 * @throws IOException
	 */
	@RequestMapping("/funds/acount/withDraw")
	@ResponseBody
	public Object accountWithdraw(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer custId, int businessType, BigDecimal amount) throws FssException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			fundsTradeImpl.withdrawApply(custId, businessType, null, amount, null);
			map.put("tips", "提现成功!!");

		} catch (Exception e) {
			LogUtil.error(this.getClass(), e.getMessage(), e);
			map.put("tips", "提现失败!!" + e.getMessage());
		}

		return map;
	}
}