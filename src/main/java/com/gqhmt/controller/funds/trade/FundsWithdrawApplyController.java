package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.funds.architect.trade.bean.WithdrawApplyBean;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithdrawApplyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* <h1>提现管理控制类</h1>
* <p>
* 提现申请列表查询 提现申请审核
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsWithdrawApplyController {
	@Resource
	private WithdrawApplyService withdrawApplyService;
   
	/**
	 * 
	 * author:jhz
	 * time:2016年2月18日
	 * function：根据条件查询并返回所有代扣申请列表信息
	 * @throws Exception 
	 */
	@RequestMapping("/withdrawApply/queryWithdrawList")
	@AutoPage
	public String queryWithholdList(HttpServletRequest request,
			ModelMap model, WithdrawApplyBean withdrawApplyBean) throws Exception {
			List<WithholdApplyEntity> withDrawList= withdrawApplyService.queryWithdrawList(withdrawApplyBean);
			model.addAttribute("withdrawBean", withdrawApplyBean);
			model.addAttribute("page", withDrawList);
		return "funds/account/withDraw/withdraw_list";
	}
	 
    
}
