package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.TokenProccessor;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.cost.ICost;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	@Resource
    private UserService userService;

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
		List<FssLoanEntity> list2 =new ArrayList<>();
		for (FssLoanEntity fssLoanEntity : list) {
			String userNo = fssLoanEntity.getUserNo();
			if(userNo!=null&&!"".equals(userNo)){
				
			User userById = userService.getUserById(Long.parseLong(userNo));
			fssLoanEntity.setUserNo(userById.getUserName());
			}
			list2.add(fssLoanEntity);
		}
		model.addAttribute("page", list2);
		model.put("map", map);
		return "fss/trade/trade_audit/borrowerloan";
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：点击代扣跳转到代扣页面
	 */
	@RequestMapping("/loan/trade/{type}/toWithHold/{id}")
	public String withhold(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
//		System.out.println("在FormServlet中生成的token："+token);
		request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		//通过userNo查询用户对象
//		 User userById = userService.getUserById(Long.parseLong(fssLoanEntityById.getUserNo()));
		// 把交易状态 修改为‘代扣中’
		model.addAttribute("loan", fssLoanEntityById);
//		model.addAttribute("user", userById);
		return "fss/trade/trade_audit/loanWithHold";
	}

	/**
	 * 
	 * author:jhz time:2016年3月18日 function：添加到抵押权人代扣
	 * @throws InterruptedException 
	 * 
	 * @throws FssException
	 *             "10100001"代扣充值
	 */
	@RequestMapping("/loan/trade/{type}/withHold/{id}")
	@ResponseBody
	public Object withholdApply(HttpServletRequest request, ModelMap model, @PathVariable String type, @PathVariable Long id, BigDecimal payAmt,String token) throws InterruptedException{
		Map<String, String> map = new HashMap<String, String>();
		FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);
		fssLoanEntity.setStatus("10050002");
		String server_token  = (String) request.getSession().getAttribute("token");
		request.getSession().removeAttribute("token");
		if(token.equals(server_token)){
		try {
			fssLoanService.update(fssLoanEntity);
			fssLoanEntity.setPayAmt(payAmt);
			fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, "10100001",type);
			//1 代扣，2 提现
			fssTradeRecordService.insertTradeRecord(1);
			map.put("code", "0000");
	        map.put("message", "success");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
		}
		}else{
			map.put("code", "0001");
	        map.put("message", "请勿重复提交!");
		}
		return map;
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