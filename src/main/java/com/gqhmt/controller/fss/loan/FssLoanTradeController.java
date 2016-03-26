package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.cost.ICost;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Filename: com.gqhmt.extServInter.dto.account.CreateAccountByFuiou Copyright:
 * Copyright (c)2016 Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2016年3月11日 Description: 代付审核
 *         <p>
 *         借款人放款
 *         <p>
 *         借款人提现
 *         <p>
 *         代偿人付款
 *         <p>
 *         出借赎回 Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2016年3月11日 jhz 1.0 1.0 Version
 */
@Controller
public class FssLoanTradeController {
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
	private IFundsTrade fundsTradeImpl;
	@Resource
	private FssTradeRecordService fssTradeRecordService;
	@Resource
	private ICost cost;

	/**
	 * 
	 * author:jhz time:2016年3月11日 function：借款人放款
	 */
	@RequestMapping(value = "/loan/trade/{type}", method = { RequestMethod.GET, RequestMethod.POST })
	@AutoPage
	public Object loanList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map,
			@PathVariable String type) {
		if (map != null) {
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map.put("endTime", endTime != null ? endTime.replace("-", "") : null);

		} else {
			map = new HashMap<>();

		}
		map.put("type", type);
		List<FssLoanEntity> list = fssLoanService.findBorrowerLoan(map);
		model.addAttribute("page", list);
		model.put("map", map);
		return "fss/trade/trade_audit/borrowerloan";
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：点击代扣跳转到代扣页面
	 */
	@RequestMapping("/loan/trade/{type}/toWithHold/{id}")
	public String withhold(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		// 把交易状态 修改为‘代扣中’
		model.addAttribute("loan", fssLoanEntityById);
		return "fss/trade/trade_audit/loanWithHold";
	}

	/**
	 * 
	 * author:jhz time:2016年3月18日 function：添加到抵押权人代扣
	 * 
	 * @throws FssException
	 *             "10100001"代扣充值
	 */
	@RequestMapping("/loan/trade/{type}/withHold")
	public String withholdApply(HttpServletRequest request, ModelMap model, @PathVariable String type, FssLoanEntity fssLoanEntity){
		fssLoanEntity.setStatus("10050002");
		try {
			fssLoanService.update(fssLoanEntity);
			fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, "10100001");
			fssTradeRecordService.insertTradeRecord(type,1);
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
		}
		
		return "redirect:/loan/trade/"+type;
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：转给借款人
	 */
	@RequestMapping("/loan/trade/{type}/transfer/{id}")
	public String transfer(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);

		try {
			fundsTradeImpl.transefer(fssLoanEntityById.getMortgageeAccNo(), fssLoanEntityById.getAccNo(),
					fssLoanEntityById.getPayAmt(), GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC, fssLoanEntityById.getId(),
					GlobalConstants.NEW_BUSINESS_MT);
			fssLoanEntityById.setStatus("10050002");
			fssLoanService.update(fssLoanEntityById);
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			model.addAttribute("erroMsg", e.getMessage());
		}

		// todo 结果返回前台页面,消息提示
		return "redirect:/fss/loan/trade/borrow";
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：收费
	 */
	@RequestMapping("/loan/trade/{type}/charge/{id}")
	public String charge(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);

		if (fssLoanEntityById == null) {
			// todo 处理前台页面消息提示内容
		}

		List<FssFeeList> fssFeeLists = fssLoanService.getFeeList(id);
		if (fssFeeLists == null || fssFeeLists.size() == 0) {
			// todo 处理前台页面消息提示内容
		} else {

			for (FssFeeList fssFeeList : fssFeeLists) {
				try {
					FundOrderEntity fundOrderEntity = cost.cost(fssLoanEntityById.getLoanPlatform(),
							fssLoanEntityById.getAccNo(), fssFeeList.getFeeType(), fssFeeList.getFeeAmt(),
							fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST);
					// todo 修改费用状态.
				} catch (FssException e) {
					e.printStackTrace();

				}
			}

			// todo 如果全部成功,修改记录收费状态并进入回盘记录表中,失败返回页面,继续处理
			// fssLoanEntityById.setStatus("10050002");
			// fssLoanService.update(fssLoanEntityById);
		}

		return "redirect:/fss/loan/trade/borrow";
	}

	/**
	 * 
	 * author:jhz time:2016年3月11日 function：查看收费列表
	 */				
	@RequestMapping("/loan/trade/{type}/{loanId}/feeList")
	public String accountRecharge(HttpServletRequest request, ModelMap model, @PathVariable Long loanId,@PathVariable String type) {
		List<FssFeeList> findFeeList = fssLoanService.getFeeList(loanId);
		model.addAttribute("feeList", findFeeList);
		return "fss/trade/trade_audit/feeList";
	}

}