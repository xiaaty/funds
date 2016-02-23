package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.funds.architect.trade.bean.WithholdApplyBean;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithholdApplyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* <h1>代扣管理控制类</h1>
* <p>
* 提现申请列表查询 提现申请审核
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsWithholdApplyController {
	@Resource
	private WithholdApplyService withholdApplyService;
   
	/**
	 * 
	 * author:jhz
	 * time:2016年2月18日
	 * function：根据条件查询并返回所有代扣申请列表信息
	 */
	@RequestMapping("/withholdApply/queryWithholdList")
	@AutoPage
	public String queryWithholdList(HttpServletRequest request,
			ModelMap model, WithholdApplyBean withholdBean) {
			List<WithholdApplyEntity> withHoldList= withholdApplyService.queryWithHoldList(withholdBean);
			model.addAttribute("withholdBean", withholdBean);
			model.addAttribute("page", withHoldList);
		return "funds/account/withHold/withhold_list";
	}
	 
    
}
