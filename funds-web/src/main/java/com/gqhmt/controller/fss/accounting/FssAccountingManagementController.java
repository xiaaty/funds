package com.gqhmt.controller.fss.accounting;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.*;
import com.gqhmt.fss.architect.accounting.service.*;
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
 * Filename:    com.gqhmt.controller.fss.trade.FssTradeApplyController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/23 16:46
 * Description:交易记录控制器
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  柯禹来      1.0     1.0 Version
 */
@Controller
public class FssAccountingManagementController {

    @Resource
    private FssAccountingLendPayableService fssAccountingLendPayableService;
    @Resource
    private FssAccountingLoandebtService fssAccountingLoandebtService;
    @Resource
    private FssAccountingMarginService fssAccountingMarginService;
    @Resource
    private FssLendAssetService fssLendAssetSetrvice;
    @Resource
    private FssAccountingCompensatoryService fssAccountingCompensatoryService;
    @Resource
    private FssAccountingCompanyIncomeService fssAccountingCompanyIncomeService;
    @Resource
    private FssFreezeService fssFreezeService;
    @Resource
    private FssFundsFlowService fssFundsFlowService;
    @Resource
    private FssCapitalFlowService fssCapitalFlowService;

    /**
     * 出借应付款列表
     * @param request
     * @param model
     * @param map
     * @param fssAccountingLendPayable
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/lendpayable",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryLendPayableList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,FssAccountingLendPayable fssAccountingLendPayable) throws FssException {
        List<FssAccountingLendPayable> lendpayablelist = fssAccountingLendPayableService.queryFssAccountingLendPayableList(map);
        model.addAttribute("page", lendpayablelist);
        model.put("map", map);
    	return "fss/accounting/lendpayable/lendPayableList";
    }
    
    /**
     * 出借应付款详细
     * @param request
     * @param model
     * @param map
     * @param fssAccountingLendPayableDetail
     * @param accountingNo
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/lendpayable/{accountingNo}/detail",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryLendPayableDetail(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,FssAccountingLendPayableDetail fssAccountingLendPayableDetail,@PathVariable String accountingNo) throws FssException {
    	map.put("accountingNo", accountingNo);
    	List<FssAccountingLendPayableDetail> lendPayableList = fssAccountingLendPayableService.queryFssAccountingLendPayableDetailList(map);
        model.addAttribute("page", lendPayableList);
        model.put("map", map);
        return "fss/accounting/lendpayable/lendPayableDetail";
    }
    
    /**
     * 借款负债列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/loandebt",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryLoandebtList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
        List<FssAccountingLoandebt> loandebtlist = fssAccountingLoandebtService.queryFssAccountingLoandebtList(map);
        model.addAttribute("page", loandebtlist);
        model.put("map", map);
    	return "fss/accounting/loandebt/loandebtList";
    }
    
    /**
     * 借款负债详细列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/loandebt/{accountingNo}/detail",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryLoandebtDetailList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,@PathVariable String accountingNo) throws FssException {
    	map.put("accountingNo", accountingNo);
    	List<FssAccountingLoandebtDetail> loandebtdetaillist = fssAccountingLoandebtService.queryFssAccountingLoandebtDetail(map);
    	model.addAttribute("page", loandebtdetaillist);
    	model.put("map", map);
    	return "fss/accounting/loandebt/loandebtDetailList";
    }
    
    /**
     * 保证金列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/margin",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountingMarginList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
        List<FssAccountingMargin> marginlist = fssAccountingMarginService.queryFssAccountingMarginList(map);
        model.addAttribute("page", marginlist);
        model.put("map", map);
    	return "fss/accounting/margin/marginList";
    }
    
    /**
     * 保证金列表明细
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/margin/{accountingNo}/detail",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountingMarginList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,@PathVariable String accountingNo) throws FssException {
    	map.put("accountingNo", accountingNo);
    	List<FssAccountingMarginDetail> loandebtdetaillist = fssAccountingMarginService.queryFssAccountingMarginDetail(map);
    	model.addAttribute("page", loandebtdetaillist);
    	model.put("map", map);
    	return "fss/accounting/margin/marginDetailList";
    }
    /**
     * 
     * author:jhz
     * time:2016年4月28日
     * function：出借资产展示
     */
    @RequestMapping(value = "/accounting/lendAsset/list",method = {RequestMethod.GET,RequestMethod.POST})
 	@AutoPage
     public Object lendAssetList(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException{
    	model.put("map", map);
		List<FssLendAssetEntity> lendAsset = fssLendAssetSetrvice.getLendAsset(map);
 		model.addAttribute("page", lendAsset);
 		return "fss/accounting/lendAsset/lendAsset";
     }
    
    /**
     * 
     * author:jhz
     * time:2016年4月28日
     * function：查看账目详情
     */
    @RequestMapping(value = "/accounting/lendAsset/{parentId}/detail",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object lendAssetDetail(HttpServletRequest request,ModelMap model,@PathVariable Long parentId) throws FssException {
    	List<FssLendAssetDetailEntity> detailByParentId = fssLendAssetSetrvice.getDetailByParentId(parentId);
		model.addAttribute("page", detailByParentId);
		return "fss/accounting/lendAsset/lendAssetDetail";
    }
    
    /**
     * 公司收入
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/companyincome",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountingCompanyIncome(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssAccountingCompanyIncome> companyincomelist = fssAccountingCompanyIncomeService.queryFssAccountingCompanyIncomeList(map);
    	model.addAttribute("page", companyincomelist);
    	model.put("map", map);
    	return "fss/accounting/companyincome/companyincomeList";
    }
    /*
     * 
     * author:jhz
     * time:2016年5月3日
     * function：资金冻结展示
     */
    @RequestMapping(value = "/accounting/freeze/list",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object freeze(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException{
    	model.put("map", map);
    	 List<FssAccountingFreeze> freezeList = fssFreezeService.getLendAsset(map);
    	model.addAttribute("page", freezeList);
    	return "fss/accounting/freeze/freeze_list";
    }
    
    /**
     * author:jhz
     * time:2016年5月3日
     * function：资金冻结详情
     */
    @RequestMapping(value = "/accounting/freeze/{parentId}/detail",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object freezeDetail(HttpServletRequest request,ModelMap model,@PathVariable Long parentId) throws FssException {
    	List<FssAccountingFreezeDetail> detailByParentId = fssFreezeService.getDetailByParentId(parentId);
    	model.addAttribute("page", detailByParentId);
    	return "fss/accounting/freeze/freeze_detai";
    }
    /**
     * 
     * author:jhz
     * time:2016年5月3日
     * function：资金流水结展示
     */
    @RequestMapping(value = "/accounting/fundFlow/list",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object fundFlow(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException{
    	model.put("map", map);
    	List<FssFundsFlowEntity> fundsFlow = fssFundsFlowService.getFundsFlow(map);
    	model.addAttribute("page", fundsFlow);
    	return "fss/accounting/fundFlow/fundFlow_list";
    }

    /**
     * jhz
     * 客户资金流水总表，记录客户所有资金流水
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/accounting/capitalFlow/list",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object capitalFlow(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException{
    	model.put("map", map);
        List<FssAccountCapitalFlow> capitalFlow=fssCapitalFlowService.getCapitalFlow(map);
    	model.addAttribute("page", capitalFlow);
    	return "fss/accounting/capitalFlow/capitalFlow_list";
    }
}
