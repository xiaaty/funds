package com.gqhmt.controller.fss.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
public class FssTradeApplyController {

    @Resource
    private FssTradeApplyService fssTradeApplyService;

    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}",method = {RequestMethod.GET})
    @AutoPage
    public String queryMortgageeList(HttpServletRequest request, ModelMap model, FssTradeApplyEntity tradeApply, @PathVariable Integer  type,@PathVariable String bus) throws Exception {
        if("".equals(tradeApply.getAccNo())){
            tradeApply.setAccNo(null);
        }
        if("".equals(tradeApply.getTradeState())){
            tradeApply.setTradeState(null);
        }
        tradeApply.setApplyType(type);
        tradeApply.setBusiType(bus);
        List<FssTradeApplyEntity> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(tradeApply);
        model.addAttribute("page", tradeApplyList);
        model.addAttribute("tradeapply", tradeApply);
        return "fss/trade/mortgaee_list";
    }
}
