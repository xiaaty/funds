package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.pay.service.trade.IFundsTrade;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public Object mortgagree(HttpServletRequest request, ModelMap model,String mchnChild,String status,
					String seqNo ,String contractId, String creatTime, String modifyTime) {
		Map<Object, Object> map = new HashMap<>();
		if (creatTime != null && !creatTime.equals("")) {
			creatTime = creatTime + " 00:00:00";
		}
		if (modifyTime != null && !modifyTime.equals("")) {
			modifyTime = modifyTime + " 23:59:59";
		}
		map.put("creatTime", creatTime);
		map.put("modifyTime", modifyTime);
		map.put("contractId", contractId);
		map.put("mchnChild", mchnChild);
		map.put("seqNo", seqNo);
		map.put("status", status);
		List<FssLoanEntity> findMortgrageePayment = fssLoanService.findBorrowerLoan(map);
		model.addAttribute("page", findMortgrageePayment);
		model.addAttribute("map", map);
			return "fss/trade/trade_audit/borrowerloan";
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
		List<FssTradeApplyEntity> selectAccountSequenceList=fssTradeApplyService.getBorrowWithDraw(map);
		model.addAttribute("map", map);
		model.addAttribute("page", selectAccountSequenceList);
		return "fss/trade/trade_audit/borrowWithDraw";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：代扣
	 */
	@RequestMapping("/fss/loan/trade/withHold/{id}")
    public String withhold( HttpServletRequest request, @PathVariable Long id, ModelMap model) {
//		通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		 //把交易状态 修改为‘代扣中’
		 fssLoanEntityById.setStatus("10050002");
		 fssLoanService.update(fssLoanEntityById);
		 
        return "redirect:/fss/loan/trade/borrow";
    }
	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：转给借款人
	 */
	@RequestMapping("/fss/loan/trade/transfer/{id}")
	public String transfer( HttpServletRequest request, @PathVariable Long id, ModelMap model) {
//		通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		//跑批 修改交易状态
//		fssLoanEntityById.setStatus("10050002");
		fssLoanService.update(fssLoanEntityById);
		
		return "redirect:/fss/loan/trade/borrow";
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：收费
	 */
	@RequestMapping("/fss/loan/trade/charge/{id}")
	public String  charge( HttpServletRequest request, @PathVariable Long id, ModelMap model) {
//		通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		
		
		//跑批    收费 并 修改交易状态
		
		
//		fssLoanEntityById.setStatus("10050002");
//		fssLoanService.update(fssLoanEntityById);
		
		return "redirect:/fss/loan/trade/borrow";
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
		return "fss/trade/trade_audit/feeList";
	}
	
	/**
	 * author:柯禹来
	 * time:2016年3月11日
	 * function：借款人提现审核
	 */
	@RequestMapping("/fss/loan/trade/borrowerwithdraw/{id}")
	public Object borrowerwithdraw(HttpServletRequest request, ModelMap model,FssTradeApplyEntity tradeapply,@PathVariable Long id) {
		FssTradeApplyEntity tradeapplyentity=fssTradeApplyService.getFssTradeApplyEntityById(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("tradeapply",tradeapplyentity);
		model.addAttribute("bespokedate",sdf.format(tradeapplyentity.getBespokedate()));
		model.addAttribute("createTime",sdf.format(tradeapplyentity.getCreateTime()));
		model.addAttribute("modifyTime",sdf.format(tradeapplyentity.getModifyTime()));
		return "fss/trade/trade_audit/borrower_withdraw_check";
	}

//  审核不通过走回盘
//	审核通过,先进行处理，处理完成后走回盘	
	@RequestMapping("/fss/loan/trade/borrowWithDrawCheck")
	public boolean borrowWithDrawCheck(HttpServletRequest request, ModelMap model) throws FssException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Long id=Long.valueOf(request.getParameter("id"));
		String applyStatus=request.getParameter("applyStatus");
		String bespokedate=request.getParameter("bespokedate");
		if(StringUtils.isNoneBlank(applyStatus) && applyStatus.equals("4")){//通过
			FssTradeApplyEntity tradeapply=fssTradeApplyService.getFssTradeApplyEntityById(id);
			try {
				tradeapply.setBespokedate(sdf.parse(bespokedate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fssTradeApplyService.insertTradeRecord(tradeapply);
		}else{//不通过，添加回盘记录
			//————————todo----------
			//————————todo----------
		}
		return true;
	}
	
}