package com.gqhmt.sys.controller;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.sys.entity.MenuEntity;
import com.gqhmt.sys.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.sys.controller.MenuController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/19 7:46
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/19  于泳      1.0     1.0 Version
 */
@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    @AutoPage
    @RequestMapping(value = "/sys/menu/{pid}",method = RequestMethod.GET)
    public Object menuList(HttpServletRequest request,ModelMap model,@PathVariable Long pid){
        List<MenuEntity> menus  = menuService.findMenu(pid);
        System.out.println(menus.getClass().getName());
        model.addAttribute("page",menus);
        return "sys/menu/menuList";
    }

    /**
     * 
     * author:jhz
     * time:2016年1月29日
     * function：跳转到添加菜单
     */
    @RequestMapping(value = "/sys/menu/toAddMenu",method = RequestMethod.GET)
    public Object toAddMenu(HttpServletRequest request,ModelMap model){
    	 List<MenuEntity> menus  = menuService.findMenuAll();
    	 model.addAttribute("menus",menus);
    	return "sys/menu/menuAdd";
    }
    /**
     * 
     * author:jhz
     * time:2016年1月29日
     * function：添加菜单
     */
    @RequestMapping(value = "/sys/menu/addMenu",method = RequestMethod.POST)
    @ResponseBody
    public Object addMenu(HttpServletRequest request,MenuEntity menu){
    	menuService.addMenu(menu);
    	Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0000");
        map.put("message", "success");
		return map;
    }
}
