package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.sys.entity.DictOrderEntity;
import com.gqhmt.sys.service.SystemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	@Resource
    private SystemService systemService;

   
	/**
	 * author:柯禹来
	 * function:借款代扣
	 */
	@RequestMapping(value = "/repayment/repaymentlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentList(HttpServletRequest request,ModelMap model,FssRepaymentEntity repayment) throws Exception {
		List<FssRepaymentEntity> repaymentlist = fssRepaymentService.queryFssRepaymentEntity(repayment);
		model.addAttribute("page", repaymentlist);
		model.addAttribute("repayment", repayment);
		return "fss/trade/repaymentlist";
	}
	
}
