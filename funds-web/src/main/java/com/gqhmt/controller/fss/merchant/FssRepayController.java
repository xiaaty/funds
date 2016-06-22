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
    @RequestMapping(value = "/sys/busi/merchantRepayList/{mchnNo}",method = RequestMethod.GET)
    //@RequestMapping(value = "/sys/busi/merchantRepayList",method = RequestMethod.GET)
    @AutoPage
    public Object merchantRepayList(HttpServletRequest request, ModelMap model, String mchnNo){
            System.out.println("-----------------------------merchantRepayList---------------------------------");
        Map<String, Object> param =  new HashMap<String, Object>();
        param.put("mchnNo", mchnNo);
       /* List<MerchantEntity> merchantEntityList = merchantService.findBusinessList(param);
        String returnId="0";
        if(Integer.parseInt(id)>0){
            MerchantEntity findMerchantEntityById = merchantService.findBusinessById(Long.parseLong(parentId));
            returnId = findMerchantEntityById.getParentId();
        }*/
        List<MerchantRepayConfigEntity> merchantRepayConfigEntityList = null;
        if(mchnNo == null || "".equals(mchnNo)){
            System.out.println("---------------------true-----------------------");
            merchantRepayConfigEntityList = merchantService.getMerchantRepayConfigEntityList();
        } else {
            System.out.println("---------------------false-----------------------");
            merchantRepayConfigEntityList = merchantService.getMerchantRepayConfigEntityListByMchnNo(mchnNo);
        }

        //model.addAttribute("page", merchantRepayConfigEntityList);
        //model.addAttribute("returnId", returnId);
        return "sys/busi/merchantRepayList";
    }
}
