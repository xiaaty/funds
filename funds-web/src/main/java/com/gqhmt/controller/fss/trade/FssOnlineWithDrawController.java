package com.gqhmt.controller.fss.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.controller.fss.trade.FssTradeApplyController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/23 16:46
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  于泳      1.0     1.0 Version
 */
@Controller
public class FssOnlineWithDrawController {

	@Resource
	private FssTradeProcessService fssTradeProcessService;
	@Resource
	private FundOrderService fundOrderService;
	/**
	 * jhz
	 *
	 * @param request
	 * @param model
	 * @param map
	 * @param type
	 * @return
     * @throws Exception
     */
    @RequestMapping(value = "/trade/{type}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map, @PathVariable String  type) throws Exception {
    	if(StringUtils.equals("1104",type)){
			map.put("type", "1402");
		}else{
			map.put("type", type);
		}
		map.put("parentId", "0");
		List<TradeProcessEntity> list=fssTradeProcessService.listTradeProcess(map);
        model.addAttribute("page", list);
        model.put("map", map);
		return "/fss/trade/onlineWithDraw/online_with";
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
    @RequestMapping(value = "/trade/processChild/{parentId}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryTradeRecord(HttpServletRequest request, ModelMap model,@PathVariable Long parentId) throws Exception {
		// 增加数据展示
		List<TradeProcessEntity> list=fssTradeProcessService.childTradeProcess(parentId);
        model.addAttribute("page", list);
        return "/fss/trade/onlineWithDraw/trade_child";
    }
	/**
	 * jhz
	 * 提现失败，进行退票
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trade/processChild/{id}/reWithDraw",method = {RequestMethod.GET,RequestMethod.POST})
	public String reWithDraw(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws Exception {
		//查询提现流程
		TradeProcessEntity reWithDraw=fssTradeProcessService.findById(id);
		TradeProcessEntity paWithDraw=fssTradeProcessService.findById(reWithDraw.getParnetId());
		try {
			fssTradeProcessService.checkResult(paWithDraw);
		}catch (Exception e){
			LogUtil.error(this.getClass(),e.getMessage());
		}
		return "redirect:/trade/processChild/"+paWithDraw.getId();
	}
	/**
	 * jhz
	 * 重新收取提现手续费
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trade/processChild/{id}/charge",method = {RequestMethod.GET,RequestMethod.POST})
	public String charge(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws Exception {
		//查询收费流程
		TradeProcessEntity charge=fssTradeProcessService.findById(id);
		//查询总流程
		TradeProcessEntity parent=fssTradeProcessService.findById(charge.getParnetId());
		//提现流程
		TradeProcessEntity withDraw=fssTradeProcessService.findByParentIdAndActionType(parent.getActionType(),String.valueOf(parent.getId())).get(0);
		//得到提现订单
		FundOrderEntity orderEntity=fundOrderService.findfundOrder(withDraw.getOrderNo());
		//生成新的订单号
		charge.setOrderNo(fundOrderService.getOrderNo());
		fssTradeProcessService.updateTradeProcessEntity(charge);
		//调用收费接口
		fssTradeProcessService.charge(parent,orderEntity);
		return "redirect:/trade/processChild/"+parent.getId();
	}


}
