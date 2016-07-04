package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Create at:   2016/6/29.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/29.  xdw         1.0     1.0 Version
 */
@Controller
public class FuiouAccountInfoController {

    @Resource
    private FuiouAccountInfoService fuiouAccountInfoService;

    @Resource
    private FtpDownloadFileService ftpDownloadFileService;

    /**
     * 账户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/account/info/accountInfoList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws FssException, ParseException {
        //查询记录
        List<FuiouAccountInfoEntity> accountInfoList = fuiouAccountInfoService.queryAccountInfoList(map);

        Map<String,String> map1 = new HashMap<String,String>();
        //查询失败记录
        List<FuiouAccountInfoEntity> accountInfoFailList = fuiouAccountInfoService.queryAccountFailInfoList(map1);

        model.addAttribute("page", accountInfoList);
        model.addAttribute("failSize", accountInfoFailList.size());
        model.put("map", map);
        model.addAttribute("grabState",request.getParameter("grabState"));
        return "fss/account/accountInfoList";
    }







    @RequestMapping(value = "account/info/accountInfoFialList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoFialList(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@RequestParam(required=false,value="id")String id) throws FssException {
        //查询失败记录
        List<FuiouAccountInfoEntity> accountInfoFailList = fuiouAccountInfoService.queryAccountFailInfoList(map);
        model.addAttribute("page", accountInfoFailList);
        model.put("map", map);
        model.addAttribute("grabState",request.getParameter("grabState"));
        return "fss/account/accountInfoFailList";
    }

    @RequestMapping(value = "account/info/accountInfoEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoEdit(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@PathVariable String id) throws FssException, ParseException {
        //查询失败记录
        List<FuiouAccountInfoEntity> accountInfoFailList = fuiouAccountInfoService.queryAccountFailInfoList(map);
        FuiouAccountInfoEntity oAccountInfoFail = new FuiouAccountInfoEntity();
        if(accountInfoFailList!=null && accountInfoFailList.size()>0){
            oAccountInfoFail = accountInfoFailList.get(0);
        }else{
            if(map.get("tradingTime")!=null && map.get("tradingTime")!=""){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                oAccountInfoFail.setTradingTime(sdf.parse(map.get("tradingTime")));
                oAccountInfoFail.setTradeType(map.get("tradeType"));
            }else{
                return "redirect:"+request.getContextPath()+"/account/info/accountInfoFialList";
            }
        }
        boolean booleanType = ftpDownloadFileService.downloadFuiouAccount(oAccountInfoFail);

        //查询失败记录
        Map<String,String> map1 = new HashMap<String,String>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        map1.put("tradingTime",sdf.format(oAccountInfoFail.getTradingTime()));
        map1.put("tradeType",oAccountInfoFail.getTradeType());

        FuiouAccountInfoEntity accountInfoFail = fuiouAccountInfoService.queryAccountFailInfoList(map1).size() > 0 ? fuiouAccountInfoService.queryAccountFailInfoList(map1).get(0) : null;

        String grabState = null;
        if(booleanType == true){
            grabState = "1";
        }else{
            grabState = "-1";
            if(accountInfoFail == null || accountInfoFail.getId() == 0){
                oAccountInfoFail.setBooleanType(grabState);
                fuiouAccountInfoService.addFuiouAccountInfoEntity(oAccountInfoFail);
            }
        }
        model.put("grabState",grabState);
        model.addAttribute("grabState",grabState);

        if(accountInfoFailList!=null && accountInfoFailList.size()>0){
            return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/accountInfoFialList", model);
        }else{
            return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/accountInfoList", model);
        }
    }

    @RequestMapping(value = "account/info/accountInfoDelete/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoDelete(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@PathVariable String id) throws FssException, ParseException {
        fuiouAccountInfoService.deleteAccountFailInfo(id);
        return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/accountInfoFialList", model);
    }

}
