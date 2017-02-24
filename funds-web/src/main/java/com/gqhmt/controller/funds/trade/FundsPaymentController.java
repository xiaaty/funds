package com.gqhmt.controller.funds.trade;

import com.beust.jcommander.internal.Maps;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
	private FssTradeProcessService fssTradeProcessService;

	
	
	/**
//	 * author:柯禹来
//	 * function:借款代扣主表信息
//	 */
//	@RequestMapping(value = "/repayment/repaymentlist",method = {RequestMethod.GET,RequestMethod.POST})
//	@AutoPage
//	public String queryRepaymentList(HttpServletRequest request,ModelMap model,FssRepaymentParentEntity repayment) throws Exception {
//		List<FssRepaymentParentEntity> repaymentlist = fssRepaymentService.queryRepaymentParents(repayment);
//		model.addAttribute("page", repaymentlist);
//		model.addAttribute("repayment", repayment);
//		return "fss/trade/repaymentlist";
//	}
	/**
	 * author:jhz
	 * function:借款人代扣
	 */
	@RequestMapping(value = "/repayment/repaymentlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws Exception {

		map.put("fundType","14010007");
		map.put("parentId","0");
		List<TradeProcessEntity> list=fssTradeProcessService.listTradeProcess(map);

		model.addAttribute("page", list);
		model.put("map", map);
		return "fss/trade/repayment/repaymentlist";
	}
	/**
	 * jhz
	 * 查看审核数据
	 * @param request
	 * @param model
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/repayment/processChild/{parentId}",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentDetail(HttpServletRequest request, ModelMap model,@PathVariable Long parentId,@RequestParam Map<String, String> map) throws Exception {
		map.put("parentId",parentId.toString());
		// 增加数据展示
		List<TradeProcessEntity> list=fssTradeProcessService.listTradeProcess(map);
		model.addAttribute("page", list);
		model.put("map", map);
		return "/fss/trade/repayment/repayment_detail";
	}
	/**
	 * jhz
	 * 重新转账
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trade/repayment/{id}/transfer",method = {RequestMethod.GET,RequestMethod.POST})
	public String reTransfer(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws Exception {
		// 增加数据展示
		TradeProcessEntity entity=fssTradeProcessService.findById(id);
		//查询富友，看该笔交易成功失败状态
		fssTradeProcessService.reTransfer(entity);
		return "/fss/trade/repayment/repayment_detail";
	}
//	/**
//	 * author:柯禹来
//	 * function:借款代扣明细
//	 */
//	@RequestMapping(value = "/repayment/repaymentdetail/{parentId}",method = {RequestMethod.GET,RequestMethod.POST})
//	@AutoPage
//	public String queryRepaymentDetail(HttpServletRequest request,ModelMap model,FssRepaymentEntity repayment,@PathVariable Long parentId) throws Exception {
//		if(parentId!=null){
//			repayment.setParentId(parentId);
//		}
//		List<FssRepaymentEntity> repaymentlist = fssRepaymentService.queryFssRepaymentEntity(repayment);
//		model.addAttribute("page", repaymentlist);
//		model.addAttribute("repayment", repayment);
//		return "fss/trade/repaymentdetaillist";
//	}
	
}
