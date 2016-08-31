package com.gqhmt.controller.fss.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoFileService;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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

    @Resource
    private FuiouAccountInfoFileService fuiouAccountInfoFileService;

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
        List<FuiouAccountInfoFileEntity> accInfoFileFail = fuiouAccountInfoFileService.queryFailAccInfoFileList(map1);

        model.addAttribute("page", accountInfoList);
        model.addAttribute("failSize", accInfoFileFail.size());
        model.put("map", map);
        model.addAttribute("grabState",request.getParameter("grabState"));
        return "fss/account/accountInfoList";
    }







    @RequestMapping(value = "account/info/failAccInfoFileList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoFialList(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@RequestParam(required=false,value="id")String id) throws FssException {
        String createFileDate = null;
        if(!StringUtils.isEmpty(map.get("createFileDate"))){
            createFileDate = map.get("createFileDate");
            map.put("createFileDate",map.get("createFileDate").replaceAll("-",""));
        }

        //查询失败记录
        List<FuiouAccountInfoFileEntity> failAccInfoList = fuiouAccountInfoFileService.queryFailAccInfoFileList(map);

        if(createFileDate!=null){
            map.put("createFileDate",createFileDate);
        }
        model.addAttribute("page", failAccInfoList);
        model.put("map", map);
        model.addAttribute("grabState",request.getParameter("grabState"));
        return "fss/account/accountInfoFailList";
    }

    @RequestMapping(value = "account/info/accountInfoEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoEdit(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@PathVariable String id) throws FssException, ParseException {
        String tradeType = null;
        //查询失败记录
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("id",id);
        map1.put("booleanType","-1");
        List<FuiouAccountInfoFileEntity> accountInfoFailList = fuiouAccountInfoFileService.queryFailAccInfoFileList(map1);
        FuiouAccountInfoFileEntity failAccInfoFile = new FuiouAccountInfoFileEntity();
        if(!CollectionUtils.isEmpty(accountInfoFailList)){
            failAccInfoFile = accountInfoFailList.get(0);
        }else{
            if(!StringUtils.isEmpty(map.get("createFileDate")) && !StringUtils.isEmpty(map.get("tradeType"))){
                String date = map.get("createFileDate");

                if(!("-1".equals(id))){
                    model.addAttribute("createFileDate",date);
                }

                String createFileDate = date.replaceAll("-","");
                failAccInfoFile.setCreateFileDate(createFileDate);
                failAccInfoFile.setTradeType(map.get("tradeType"));
            }else{
                return "redirect:"+request.getContextPath()+"/account/info/failAccInfoFileList";
            }
        }
        boolean booleanType = ftpDownloadFileService.downloadFuiouAccount(failAccInfoFile);

        String grabState = null;
        if(booleanType == true){
            grabState = "1";
        }else{
            grabState = "-1";
        }
        if(!StringUtils.isEmpty(map.get("tradeType")) && !("-1".equals(id))){
            model.put("tradeType",map.get("tradeType"));
        }
        model.put("grabState",grabState);
        model.addAttribute("grabState",grabState);

        if(!CollectionUtils.isEmpty(accountInfoFailList)){
            return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/failAccInfoFileList", model);
        }else{
            return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/accountInfoList", model);
        }
    }

    @RequestMapping(value = "account/info/accountInfoDelete/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object accountInfoDelete(HttpServletRequest request, ModelMap model, @RequestParam(required=false) Map<String, String> map,@PathVariable String id) throws FssException, ParseException {
        fuiouAccountInfoService.deleteAccountFailInfo(id);
        return new ModelAndView("redirect:"+request.getContextPath()+"/account/info/failAccInfoFileList", model);
    }

}
