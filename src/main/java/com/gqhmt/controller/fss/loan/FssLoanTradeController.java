package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.pay.service.trade.IFundsTrade;
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
 * Create at:   2016年3月11日
 * Description:  代付审核
 * <p>借款人放款
 * <p>借款人提现
 * <p>代偿人付款
 * <p>出借赎回
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月11日  jhz      1.0     1.0 Version
 */
@Controller
public class FssLoanTradeController {
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
	private IFundsTrade fundsTradeImpl;


	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人放款
	 */
	@RequestMapping(value = "/fss/loan/trade/borrow", method = {RequestMethod.GET, RequestMethod.POST})
	@AutoPage
	public Object mortgagree(HttpServletRequest request, ModelMap model,String mchnChild,
					String seqNo ,String contractId, String creatTime, String modifyTime) {
		Map<Object, Object> map = new HashMap<>();
		if (creatTime != null && !creatTime.equals("")) {
			creatTime = creatTime + " 00:00:00";
			map.put("creatTime", creatTime);
		}
		if (modifyTime != null && !modifyTime.equals("")) {
			modifyTime = modifyTime + " 23:59:59";
			map.put("modifyTime", modifyTime);
		}
		map.put("contractId", contractId);
		map.put("mchnChild", mchnChild);
		map.put("seqNo", seqNo);
		List<FssLoanEntity> findMortgrageePayment = fssLoanService.findBorrowerLoan(map);
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
		map.put("contractId", contractId);
		map.put("mchnChild", mchnChild);
		map.put("creatTime", creatTime);
		map.put("modifyTime", modifyTime);
		map.put("seqNo", seqNo);
		fssTradeApplyService.getBorrowWithDraw(map);
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
		List<FssFeeList> findFeeList = fssLoanService.getFeeList(loanId);
		model.addAttribute("feeList", findFeeList);
		return "fss/loan/trade/trade_audit/feeList";
	}

}