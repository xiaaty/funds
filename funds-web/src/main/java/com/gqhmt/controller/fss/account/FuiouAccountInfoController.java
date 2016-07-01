package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("id",id);
        List<FuiouAccountInfoEntity> accountInfoFailList1 = fuiouAccountInfoService.queryAccountFailInfoList(map1);
        FuiouAccountInfoEntity oAccountInfoFail = new FuiouAccountInfoEntity();
        if(accountInfoFailList1!=null && accountInfoFailList1.size()>0){
            oAccountInfoFail = accountInfoFailList1.get(0);
        }
     //  测试用
     //   String dateStr = "20150701";
     //   SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
     //   java.util.Date date=sdf.parse(dateStr);
     //   oAccountInfoFail.setTradingTime(date);
        boolean booleanType = ftpDownloadFileService.downloadFuiouAccount(oAccountInfoFail);
        String grabState = null;
        if(booleanType==true){
            grabState = "1";
        }else{
            grabState = "-1";
        }
        model.put("grabState",grabState);
        //return "fss/account/accountInfoFailList";
        model.addAttribute("grabState",grabState);
        return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/accountInfoFialList", model);
    }

}
