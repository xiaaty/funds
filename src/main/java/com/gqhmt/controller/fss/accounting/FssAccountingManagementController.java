package com.gqhmt.controller.fss.accounting;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingCompanyIncome;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayable;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayableDetail;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoanCompensatory;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebt;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebtDetail;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingMargin;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingMarginDetail;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingServiceCharge;
import com.gqhmt.fss.architect.accounting.service.FssAccountingChargeService;
import com.gqhmt.fss.architect.accounting.service.FssAccountingCompanyIncomeService;
import com.gqhmt.fss.architect.accounting.service.FssAccountingCompensatoryService;
import com.gqhmt.fss.architect.accounting.service.FssAccountingLendPayableService;
import com.gqhmt.fss.architect.accounting.service.FssAccountingLoandebtService;
import com.gqhmt.fss.architect.accounting.service.FssAccountingMarginService;
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
    private FssAccountingChargeService fssAccountingChargeService;
    @Resource
    private FssAccountingCompensatoryService fssAccountingCompensatoryService;
    @Resource
    private FssAccountingCompanyIncomeService fssAccountingCompanyIncomeService;
 
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
     * 逆服务费
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/servicecharge",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountingServiceCharge(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
        List<FssAccountingServiceCharge> chargelist = fssAccountingChargeService.queryFssAccountingChargeList(map);
        model.addAttribute("page", chargelist);
        model.put("map", map);
    	return "fss/accounting/charge/serviceChargeList";
    }
    
    /**
     * 借款代偿
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/traderecord/loanCompensatory",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountingCompensatory(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssAccountingLoanCompensatory> compensatorylist = fssAccountingCompensatoryService.queryFssAccountingCompensatoryList(map);
    	model.addAttribute("page", compensatorylist);
    	model.put("map", map);
    	return "fss/accounting/compensatory/compensatoryList";
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
    
    
    
    
    
}
