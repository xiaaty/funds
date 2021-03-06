package com.gqhmt.controller.fss.fstp;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.entity.FssAccountFileEntity;
import com.gqhmt.fss.architect.depos.entity.FssBusinessTradeEntity;
import com.gqhmt.fss.architect.depos.entity.FssCorporateEntity;
import com.gqhmt.fss.architect.depos.entity.FssCreditInfoEntity;
import com.gqhmt.fss.architect.depos.entity.FssFinanceSumEntity;
import com.gqhmt.fss.architect.depos.entity.FssProjectCallbackEntity;
import com.gqhmt.fss.architect.depos.entity.FssProjectInfoEntity;
import com.gqhmt.fss.architect.depos.entity.FssSftpRecordEntity;
import com.gqhmt.fss.architect.depos.entity.FssSumAuditEntity;
import com.gqhmt.fss.architect.depos.service.FssAccountFileService;
import com.gqhmt.fss.architect.depos.service.FssBidFinanceService;
import com.gqhmt.fss.architect.depos.service.FssBusiTradeService;
import com.gqhmt.fss.architect.depos.service.FssCorporateService;
import com.gqhmt.fss.architect.depos.service.FssCreditInfoService;
import com.gqhmt.fss.architect.depos.service.FssProjectInfoCallBackService;
import com.gqhmt.fss.architect.depos.service.FssProjectInfoService;
import com.gqhmt.fss.architect.depos.service.FssDeposRecordService;
import com.gqhmt.fss.architect.depos.service.FssSumAuditService;
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
    @Resource
    private FssCreditInfoService fssCreditInfoService;
    @Resource
    private FssSumAuditService fssSumAuditService;
    @Resource
    private FssDeposRecordService fssDeposRecordService;
    @Resource
    private FssAccountFileService fssAccountFileService;
    @Resource
    private FssBusiTradeService fssBusiTradeService;
    @Resource
    private FssCorporateService fssCorporateService;

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
    	List<FssFinanceSumEntity> bidfinancelist = fssBidFinanceService.queryBidFinanceList(map);
    	model.addAttribute("page", bidfinancelist);
    	model.put("map", map);
    	return "fss/fstp/bid/bidFinanceTotals";
    }
    
    /**
     * 标的放款明细文件列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/bidLoanDetails",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryBidLoanDetails(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssCreditInfoEntity>creditInfolist = fssCreditInfoService.queryFssCreditInfoEntityList(map);
    	model.addAttribute("page", creditInfolist);
    	model.put("map", map);
    	return "fss/fstp/bid/bidLoanDetails";
    }
    
    /**
     * 标的财务汇总审核回盘文件表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/bidFinanceSumAudit",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryBidSumAudit(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssSumAuditEntity>creditInfolist = fssSumAuditService.querySumAuditList(map);
    	model.addAttribute("page", creditInfolist);
    	model.put("map", map);
    	return "fss/fstp/bid/bidSumAudit";
    }
    
    /**
     * sftp数据上传记录列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/mainRecordInfo",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMainRecordInfo(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssSftpRecordEntity>creditInfolist = fssDeposRecordService.findSftpRecordList(map);
    	model.addAttribute("page", creditInfolist);
    	model.put("map", map);
    	return "fss/fstp/bid/fstpRecordList";
    }
    
    /**
     * 查看数据上传数据详细
     * @param request
     * @param model
     * @param map
     * @param type
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/impRecordDetail/{type}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryImpRecordDetail(HttpServletRequest request, ModelMap model,@PathVariable Integer  type) throws FssException {
    	if(type==1){//项目信息列表
    		return "redirect:/fstp/projectInfo";  
    	}else if(type==2){//项目回盘信息列表
    		return "redirect:/fstp/projectcallback";  
    	}else if(type==3){//标的财务汇总文件列表
    		return "redirect:/fstp/bidFinanceTotals";  
    	}else if(type==4){//标的放款明细文件列表
    		return "redirect:/fstp/bidLoanDetails";  
    	}else if(type==5){//标的财务汇总审核回盘文件表
    		return "redirect:/fstp/bidFinanceSumAudit";  
    	}else if(type==6){//P2P个人平台开户文件列表
    		return "redirect:/fstp/p2pAccountFile";  
    	}else if(type==7){//p2p商户交易
    		return "redirect:/fstp/p2pBusinessTrade";  
    	}else if(type==8){//p2p法人平台开户文件
    		return "redirect:/fstp/p2pCorporate"; 
    	}else{
    		return "fss/fstp/bid/fstpRecordList";
    	}
    }
    
    /**
     * P2P个人平台开户文件列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/p2pAccountFile",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryAccountFileList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
        List<FssAccountFileEntity> accfilelist = fssAccountFileService.queryItemsInfos(map);
        model.addAttribute("page", accfilelist);
        model.put("map", map);
    	return "fss/fstp/acc/p2pAccountFile";
    }
    
    /**
     * p2p商户交易
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/p2pBusinessTrade",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryBusinessTradeList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssBusinessTradeEntity> busitradelist = fssBusiTradeService.queryBusinessTrade(map);
    	model.addAttribute("page", busitradelist);
    	model.put("map", map);
    	return "fss/fstp/acc/p2pBusiniessTrade";
    }
    
    /**
     * p2p法人平台开户文件列表
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fstp/p2pCorporate",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryCorporateList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws FssException {
    	List<FssCorporateEntity> corporatelist = fssCorporateService.queryCorporateList(map);
    	model.addAttribute("page", corporatelist);
    	model.put("map", map);
    	return "fss/fstp/acc/p2pCorporate";
    }
    
}
