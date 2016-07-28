package com.gqhmt.controller.fss.channelDeploy;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelMerchantEntity;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelOrgEntity;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelRestrictionsEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelRestrictionsReadMapper;
import com.gqhmt.fss.architect.channelDeploy.service.*;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelBankEntity;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
 * Create at:   16/5/31
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/5/31  xdw         1.0     1.0 Version
 */
@Controller
public class dealChannelController {

//    @Resource
//    private ChannelService channelService;

    @Resource
    private ChannelOrgService channelOrgService;

    @Resource
    private ChannelMerchantService channelMerchantService;

    @Resource
    private ChannelBankService channelBankService;

    @Resource
    private ChannelRestrictionsService channelRestrictionsService;

    //交易渠道配置展示页面
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/channelList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object goChanneDeployList(HttpServletRequest request, ModelMap model) throws FssException {

        List<ChannelOrgEntity> channelEntityList = null;
        channelEntityList = channelOrgService.getChannelOrgList();

        model.addAttribute("page",channelEntityList);

        return "/fss/channelDeploy/channelList";
    }

    //渠道配置页页面
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/channelEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object goChannelEdit(HttpServletRequest request, ModelMap model,@PathVariable int id) throws FssException {

        ChannelOrgEntity  channelOrgEntity = null;
        channelOrgEntity = channelOrgService.getChannelOrgEntityById(id);

        model.addAttribute("channelOrgEntity",channelOrgEntity);

        return "/fss/channelDeploy/deploy/channelEdit";
    }

    //渠道商户配置页面
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/merchant/channelMerchantEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object goHannelMerchantEdit(HttpServletRequest request, ModelMap model, @PathVariable int id) throws FssException {

        List<ChannelMerchantEntity> merchantEntityList = channelMerchantService.getMerchantEntityListByOrgId(id);

        model.addAttribute("page",merchantEntityList);

        return "/fss/channelDeploy/deploy/merchant/channelMerchantEdit";
    }

    //限额配置页面
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/restrictions/restrictionsEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object goRestrictionsEdit(HttpServletRequest request, ModelMap model,@PathVariable int id) throws FssException {

        List<ChannelRestrictionsEntity> channelRestrictionsEntityList = channelRestrictionsService.getChannelRestrictionsEntityListByOrgId(id);

        model.addAttribute("page",channelRestrictionsEntityList);

        return "/fss/channelDeploy/deploy/restrictions/restrictionsEdit";
    }

    //支持银行
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/supportBank/bankEdit/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object goBankEdit(HttpServletRequest request, ModelMap model,@PathVariable int id) throws FssException {

        List<ChannelBankEntity> channelBankEntityList = channelBankService.getChannelBankEntityListByOrgId(id);

        model.addAttribute("page",channelBankEntityList);

        return "/fss/channelDeploy/deploy/supportBank/bankEdit";
    }

    //异步检查渠道商户重复
    @RequestMapping(value = "/channelDeploy/deploy/merchant/channelAjaxMerchantEdit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object checkOptionNameAndOptionValue(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, FssException {

        Map<String, String> map = new HashMap<String, String>();

        int orgId = Integer.parseInt(request.getParameter("orgId"));
        String optionName = request.getParameter("optionName");
        String optionValue = request.getParameter("optionValue");
        response.setContentType("text/text; charset=utf-8");

        ChannelMerchantEntity channelMerchantEntity = new ChannelMerchantEntity();
        channelMerchantEntity.setOptionName(optionName);
        channelMerchantEntity.setOptionValue(optionValue);
        channelMerchantEntity.setOrgId(orgId);
        List<ChannelMerchantEntity> chanMerList =channelMerchantService.getMerchantNameOrValue(channelMerchantEntity);

        if(chanMerList!=null && chanMerList.size()>0){
            map.put("message", "配置项/属性值已存在!");
        }else{
            map.put("message", "success");
        }

        map.put("code", "0000");
        map.put("optionName", optionName);
        map.put("optionValue",optionValue);

       return map;
    }

    //渠道商户添加
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/merchant/addChannelMerchant",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object addChannelMerchant(HttpServletRequest request, ModelMap model, ChannelMerchantEntity channelMerchantEntity, @RequestParam Map<String, String> map) throws FssException {
        //登陆人
        String loginName = null;
        Assertion assertion = AssertionHolder.getAssertion();
        loginName = assertion.getPrincipal().getName();
        channelMerchantEntity.setConfOfPeople(loginName);

        int orgId = channelMerchantEntity.getOrgId();

        ChannelMerchantEntity chanMerEntity = channelMerchantService.addMerchantEntity(channelMerchantEntity);


        return "redirect:"+request.getContextPath()+"/channelDeploy/deploy/merchant/channelMerchantEdit/"+orgId;
    }

    //异步检查支持银行重复
    @RequestMapping(value = "/channelDeploy/deploy/merchant/channelAjaxBankEdit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object checkOptionName(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException,FssException {

        Map<String, String> map = new HashMap<String, String>();

        int orgId = Integer.parseInt(request.getParameter("orgId"));
        String optionName = request.getParameter("optionName");
        String optionValue = request.getParameter("optionValue");
        response.setContentType("text/text; charset=utf-8");
        ChannelBankEntity channelBankEntity = new ChannelBankEntity();
        channelBankEntity.setOptionName(optionName);
        channelBankEntity.setOrgId(orgId);
        List<ChannelBankEntity> chanBankList =channelBankService.getChannelBankEntity(channelBankEntity);

        if(chanBankList!=null && chanBankList.size()>0){
            map.put("message", "配置项已存在!");
        }else{
            map.put("message", "success");
        }

        map.put("code", "0000");
        map.put("optionName", optionName);
        map.put("optionValue",optionValue);

        return map;
    }

    //支持银行保存
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/supportBank/addChannelBank",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object addChannelBank(HttpServletRequest request, ModelMap model, ChannelBankEntity channelBankEntity, @RequestParam Map<String, String> map) throws FssException {
        //登陆人
        String loginName = null;
        Assertion assertion = AssertionHolder.getAssertion();
        loginName = assertion.getPrincipal().getName();
        channelBankEntity.setConfOfPeople(loginName);

        int orgId = channelBankEntity.getOrgId();

        ChannelBankEntity chanBankEntity = channelBankService.addBankEntity(channelBankEntity);


        return "redirect:"+request.getContextPath()+"/channelDeploy/deploy/supportBank/bankEdit/"+orgId;
    }

    //异步检查限额配置
    @RequestMapping(value = "/channelDeploy/deploy/restrictions/channelAjaxRestrictionsEdit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object checkOptionNameToRes(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException, FssException {

        Map<String, String> map = new HashMap<String, String>();

        int orgId = Integer.parseInt(request.getParameter("orgId"));
        String optionName = request.getParameter("optionName");
        String optionBank = request.getParameter("optionBank");
        String optionType = request.getParameter("optionType");
        String optionValue = request.getParameter("optionValue");
        response.setContentType("text/text; charset=utf-8");
        ChannelRestrictionsEntity channelRestrictionsEntity = new ChannelRestrictionsEntity();
        channelRestrictionsEntity.setOptionName(optionName);
        channelRestrictionsEntity.setOrgId(orgId);
        List<ChannelRestrictionsEntity> chanRestrictionsList =channelRestrictionsService.getChannelRestrictionsEntity(channelRestrictionsEntity);

        if(chanRestrictionsList!=null && chanRestrictionsList.size()>0){
            map.put("message", "配置项已存在!");
        }else{
            map.put("message", "success");
        }

        map.put("optionName", optionName);
        map.put("optionBank", optionBank);
        map.put("optionType", optionType);
        map.put("optionValue",optionValue);

        return map;
    }

    //限额配置添加
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/channelDeploy/deploy/restrictions/addChannelRestrictions",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object addChannelRestrictions(HttpServletRequest request, ModelMap model, ChannelRestrictionsEntity channelRestrictionsEntity, @RequestParam Map<String, String> map) throws FssException {
        //登陆人
        String loginName = null;
        Assertion assertion = AssertionHolder.getAssertion();
        loginName = assertion.getPrincipal().getName();
        channelRestrictionsEntity.setConfOfPeople(loginName);

        int orgId = channelRestrictionsEntity.getOrgId();

        ChannelRestrictionsEntity chanRestrictionsEntity = channelRestrictionsService.addRestrictionsEntity(channelRestrictionsEntity);


        return "redirect:"+request.getContextPath()+"/channelDeploy/deploy/restrictions/restrictionsEdit/"+orgId;
    }

    //异步配检保存Org
    @RequestMapping(value = "/channelDeploy/deploy/Org/channelAjaxOrgEdit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateOrgEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException ,FssException{
        Map<String, String> map = new HashMap<String, String>();

        int id = Integer.parseInt(request.getParameter("id"));
        String channelCondition = request.getParameter("channelCondition");
        String channelType = request.getParameter("channelType");
        String channelPaymentMode = request.getParameter("channelPaymentMode");
        response.setContentType("text/text; charset=utf-8");
        ChannelOrgEntity channelOrgEntity = new ChannelOrgEntity();
        channelOrgEntity.setId(id);
        channelOrgEntity.setChannelCondition(channelCondition);
        channelOrgEntity.setChannelType(channelType);
        channelOrgEntity.setChannelPaymentMode(channelPaymentMode);


        channelOrgEntity = channelOrgService.updateChannelOrgEntity(channelOrgEntity);

        model.addAttribute("channelOrgEntity",channelOrgEntity);

        return channelOrgEntity;
    }
}
