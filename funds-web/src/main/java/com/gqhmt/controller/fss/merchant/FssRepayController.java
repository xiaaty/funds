package com.gqhmt.controller.fss.merchant;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.entity.MerchantRepayConfigEntity;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/16.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/16.  xdw         1.0     1.0 Version
 */
@Controller
public class FssRepayController {

    @Resource
    private MerchantService merchantService;

    /**
     * 商户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/merchantRepayList/{mchnNo}",method = {RequestMethod.POST, RequestMethod.GET})
    @AutoPage
    public Object merchantRepayList(HttpServletRequest request, ModelMap model, @PathVariable String mchnNo, @RequestParam(value = "edit", required = false) boolean edit,@RequestParam(value = "id", required = false) String id){
        List<MerchantRepayConfigEntity> merchantRepayConfigEntityList = null;
        //判断是从回盘配置进入， 还是从商户进入
        if(mchnNo == null || "".equals(mchnNo)|| "-1".equals(mchnNo)){
            merchantRepayConfigEntityList = merchantService.getMerchantRepayConfigEntityList();
        } else {
            merchantRepayConfigEntityList = merchantService.getMerchantRepayConfigEntityListByMchnNo(mchnNo);
        }

        model.addAttribute("page", merchantRepayConfigEntityList);
        model.addAttribute("mchnNo",mchnNo);
        model.addAttribute("edit",edit);
        model.addAttribute("id",id);
        return "sys/busi/merchantRepayList";
    }

    /**
     * 修改商户回盘配置
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/merchantRepay",method = {RequestMethod.POST, RequestMethod.GET})
    @AutoPage
    public Object updateMerchantRepay(HttpServletRequest request, ModelMap model,@RequestParam(value = "mchnNoVal", required = false) String mchnNo, MerchantRepayConfigEntity merchantRepayConfigEntity,@RequestParam(value = "edit", required = false) boolean edit,@RequestParam(value = "id", required = false) String id){
        merchantService.updateMerchantRepayConfigEntity(merchantRepayConfigEntity);
        return "redirect:"+request.getContextPath()+"/sys/busi/merchantRepayList/"+mchnNo;
    }

    /**
     * 修改商户回盘配置
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/busi/addMerchantRepay",method = {RequestMethod.POST, RequestMethod.GET})
    @AutoPage
    public Object addMerchantRepay(HttpServletRequest request, ModelMap model,@RequestParam(value = "mchnNoVal", required = false) String mchnNo , MerchantRepayConfigEntity merchantRepayConfigEntity,@RequestParam(value = "edit", required = false) boolean edit){
        merchantService.addMerchantRepayConfigEntity(merchantRepayConfigEntity);
        return "redirect:"+request.getContextPath()+"/sys/busi/merchantRepayList/"+mchnNo;
    }



}
