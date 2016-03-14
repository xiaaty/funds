package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanTradeService;
import com.gqhmt.pay.service.trade.IFundsTrade;

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
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月11日
 * Description:
 * <p>抵押权人付款
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月11日  jhz      1.0     1.0 Version
 */
@Controller
public class FssLoanTradeController {
	@Resource
	private FssLoanTradeService fssLoanTradeService;
	@Resource
	private IFundsTrade fundsTradeImpl;


	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人付款
	 */
	@RequestMapping(value = "/fss/loan/trade/borrow", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object mortgagree(HttpServletRequest request, ModelMap model,String mchnChild,
					String seqNo ,String contractId, String creatTime, String modifyTime) {
		Map<Object, Object> map = new HashMap<>();
		if (creatTime != null && !creatTime.equals("")) {
			creatTime = creatTime + " 00:00:00";
		}
		if (modifyTime != null && !modifyTime.equals("")) {
			modifyTime = modifyTime + " 23:59:59";
		}
		map.put("contractId", contractId.trim());
		map.put("mchnChild", mchnChild.trim());
		map.put("creatTime", creatTime);
		map.put("modifyTime", modifyTime);
		map.put("seqNo", seqNo.trim());
		List<FssLoanEntity> findMortgrageePayment = fssLoanTradeService.findBorrowerLoan(map);
		model.addAttribute("page", findMortgrageePayment);
		model.addAttribute("map", map);
			return "fss/loan/trade/trade_audit/borrowerloan";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	@RequestMapping(value = "/fss/loan/trade/borrowWithDraw", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object accountWater(HttpServletRequest request, ModelMap model,String mchnChild,
			String seqNo ,String contractId, String creatTime, String modifyTime) {
		Map<Object, Object> map = new HashMap<>();
		if (creatTime != null && !creatTime.equals("")) {
			creatTime = creatTime + " 00:00:00";
		}
		if (modifyTime != null && !modifyTime.equals("")) {
			modifyTime = modifyTime + " 23:59:59";
		}
		map.put("contractId", contractId.trim());
		map.put("mchnChild", mchnChild.trim());
		map.put("creatTime", creatTime);
		map.put("modifyTime", modifyTime);
		map.put("seqNo", seqNo.trim());
		fssLoanTradeService.getBorrowWithDraw(map);
		model.addAttribute("map", map);
//		model.addAttribute("page", selectAccountSequenceList);
		return "fss/loan/trade/trade_audit/borrowWithDraw";
	}



	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：查看收费列表
	 */
	@RequestMapping("/fss/loan/trade/feeList/{loanId}")
	public String accountRecharge(HttpServletRequest request, ModelMap model, @PathVariable Long loanId) {
		List<FssFeeList> findFeeList = fssLoanTradeService.findFeeList(loanId);
		model.addAttribute("feeList", findFeeList);
		return "fss/loan/trade/trade_audit/feeList.jsp";
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

//	/**
//	 * author:jhz
//	 * time:2016年2月18日
//	 * function： 跳转到为指定的客户代付页面
//	 */
//	@RequestMapping("/funds/acount/custAccountWithdraw/{withDrawId}")
//	public String toAccountWithdraw(HttpServletRequest request, ModelMap model, @PathVariable Integer withDrawId) {
//		FundAccountCustomerBean accountWithdraw = fundAccountService.fundAccountCustomerById(withDrawId);
//		model.addAttribute("acct", accountWithdraw);
//		return "funds/account/withDraw/account_withdraw";
//	}

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