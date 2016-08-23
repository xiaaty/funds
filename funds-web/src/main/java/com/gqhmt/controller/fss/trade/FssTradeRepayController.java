package com.gqhmt.controller.fss.trade;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.loan.service.FssLoanService;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/6.  xdw         1.0     1.0 Version
 * 交易回盘
 */
@Controller
public class FssTradeRepayController {
    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FssLoanService fssLoanService;

    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @RequestMapping(value = "/trade/tradeRepay/ftpOrder",method = {RequestMethod.POST,RequestMethod.GET})
    @AutoPage
    public Object tradeRepayFtpOrderList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map){
        List<FuiouFtpOrder> fuiouFtpOrderList = fuiouFtpOrderService.selectFuiouFtpOrderList(map);
        model.addAttribute("page",fuiouFtpOrderList);
        model.put("map", map);
        return "fss/trade/trade_Repay/tradeFtpOrderList";
    }

//    @RequestMapping(value = "/trade/tradeRepay/loan",method = {RequestMethod.POST,RequestMethod.GET})
//    @AutoPage
//    public Object tradeRepayLoanList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map){
//        List<FssLoanEntity> fssLoanEntityList = fssLoanService.selectFssLoanList(map);
//        model.addAttribute("page",fssLoanEntityList);
//        model.put("map", map);
//        return "fss/trade/trade_Repay/tradeLoanList";
//    }

    @RequestMapping(value = "/trade/tradeRepay/ftpField/{orderNo}",method = {RequestMethod.POST,RequestMethod.GET})
    @AutoPage
    public Object tradeRepayFuiouFtpFieldList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map, @PathVariable String orderNo){
        if(null != orderNo){
            map.put("orderNo",orderNo);
        }
        List<FuiouFtpOrder> fuiouFtpOrderList = fuiouFtpOrderService.selectFuiouFtpOrderList(map);
        if(fuiouFtpOrderList.size()>0){
            model.addAttribute("fuiouFtpOrder",fuiouFtpOrderList.get(0));
        }
        List<FuiouFtpColomField> fuiouFtpFieldList = fuiouFtpColomFieldService.selectFuiouFtpFieldList(map);
        model.addAttribute("page",fuiouFtpFieldList);
        model.put("map", map);
        return "fss/trade/trade_Repay/tradeFuiouFtpField";
    }
    
    //失败重试(目前只针对余额不足的情况进行处理)
    @RequestMapping(value = "/trade/tradeRepay/ftpField/failureRetry",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object failureRetry(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException ,FssException{
    	
    	try {
    		Long orderId = Long.valueOf(request.getParameter("orderId"));
            Long fieldId = Long.valueOf(request.getParameter("fieldId"));
            //初始化t_fuiou_ftp_order状态
            fuiouFtpOrderService.failureRetry(orderId);
            //初始化t_fuiou_ftp_field状态
            fuiouFtpColomFieldService.failureRetry(fieldId);
		} catch (Exception e) {
			return "fail";
		}

        return "success";
    }

}
