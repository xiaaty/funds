package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* <h1>代扣审核控制类</h1>
* @author 柯禹来
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsPaymentController {
	
	@Resource
	private FssRepaymentService fssRepaymentService;


	
	
	/**
	 * author:柯禹来
	 * function:借款代扣主表信息
	 */
	@RequestMapping(value = "/repayment/repaymentlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentList(HttpServletRequest request,ModelMap model,FssRepaymentParentEntity repayment) throws Exception {
		List<FssRepaymentParentEntity> repaymentlist = fssRepaymentService.queryRepaymentParents(repayment);
		model.addAttribute("page", repaymentlist);
		model.addAttribute("repayment", repayment);
		return "fss/trade/repaymentlist";
	}
    
	/**
	 * author:柯禹来
	 * function:借款代扣明细
	 */
	@RequestMapping(value = "/repayment/repaymentdetail/{parentId}",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentDetail(HttpServletRequest request,ModelMap model,FssRepaymentEntity repayment,@PathVariable Long parentId) throws Exception {
		if(parentId!=null){
			repayment.setParentId(parentId);
		}
		List<FssRepaymentEntity> repaymentlist = fssRepaymentService.queryFssRepaymentEntity(repayment);
		model.addAttribute("page", repaymentlist);
		model.addAttribute("repayment", repayment);
		return "fss/trade/repaymentdetaillist";
	}
	
}