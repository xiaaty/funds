package com.gqhmt.controller.fss.fstp;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.sftp.entity.FinanceSumEntity;
import com.gqhmt.fss.architect.sftp.entity.FssProjectCallbackEntity;
import com.gqhmt.fss.architect.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.fss.architect.sftp.service.FssBidFinanceService;
import com.gqhmt.fss.architect.sftp.service.FssProjectInfoCallBackService;
import com.gqhmt.fss.architect.sftp.service.FssProjectInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * Create at:   16/5/09 9:46
 * Description:Fstp控制器
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  柯禹来      1.0     1.0 Version
 */
@Controller
public class FssFstpController {

    @Resource
    private FssProjectInfoService fssProjectInfoService;
    @Resource
    private FssProjectInfoCallBackService fssProjectInfoCallBackService;
    @Resource
    private FssBidFinanceService fssBidFinanceService;

    /**
     * 项目信息列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/projectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryProjectInfoList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
        List<FssProjectInfoEntity> projectlist = fssProjectInfoService.queryFssProjectList(map);
        model.addAttribute("page", projectlist);
        model.put("map", map);
    	return "fss/fstp/project/projectInfoList";
    }
    
    /**
     * 项目信息回盘列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/projectcallback",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryProjectInfoCallBackList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssProjectCallbackEntity> projectcallbacklist = fssProjectInfoCallBackService.queryFssProjectCallBackList(map);
    	model.addAttribute("page", projectcallbacklist);
    	model.put("map", map);
    	return "fss/fstp/project/projectInfoBackplat";
    }
    
    /**
     * 标的财务汇总文件列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/bidFinanceTotals",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String querybidFinanceTotals(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FinanceSumEntity> bidfinancelist = fssBidFinanceService.queryBidFinanceList(map);
    	model.addAttribute("page", bidfinancelist);
    	model.put("map", map);
    	return "fss/fstp/bid/bidFinanceTotals";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
