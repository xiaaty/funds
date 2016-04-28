package com.gqhmt.controller.fss.accounting;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssLendAssetDetailEntity;
import com.gqhmt.fss.architect.accounting.entity.FssLendAssetEntity;
import com.gqhmt.fss.architect.accounting.service.FssLendAssetService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月28日
 * Description:
 * <p>出借资产控制类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
@Controller
public class FssLendAssetController {
	@Resource
    private FssLendAssetService fssLendAssetSetrvice;
	

    /**
     * 
     * author:jhz
     * time:2016年4月28日
     * function：
     */
    @RequestMapping(value = "/accounting/list",method = {RequestMethod.GET,RequestMethod.POST})
 	@AutoPage
     public Object waterDetail(HttpServletRequest request,ModelMap model,@PathVariable Long id,@PathVariable String type,String startDate,String endDate) throws FssException{
    	Map map=new HashMap<>();
		List<FssLendAssetEntity> lendAsset = fssLendAssetSetrvice.getLendAsset(map);
 		model.addAttribute("startDate", startDate);
 		model.addAttribute("endDate", endDate);
 		model.addAttribute("page", lendAsset);
 		model.addAttribute("id",id);
 		return "fss/accounting/lendAsset";
     }
    
    /**
     * 
     * author:jhz
     * time:2016年4月28日
     * function：查看账目详情
     */
    @RequestMapping(value = "/accounting/{parentId}/detail",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
    public Object intenetAccountList(HttpServletRequest request,ModelMap model,@PathVariable Long parentId,@PathVariable String type) throws FssException {
    	List<FssLendAssetDetailEntity> detailByParentId = fssLendAssetSetrvice.getDetailByParentId(parentId);
		model.addAttribute("page", detailByParentId);
		return "fss/accounting/lendAssetDetail";
    }
    
    
}
