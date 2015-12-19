package com.gqhmt.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.service.MenuService;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.RequestUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/sys/menu",method = RequestMethod.GET)
    public Object MenuList(HttpServletRequest request,ModelMap model){
        int pageNum= RequestUtil.getInt(request, "pageNum1", 0);
        pageNum=pageNum>0?pageNum: GlobalConstants.PAGE_SIZE;
        int cpage = RequestUtil.getInt(request, "pageNum",0);
        PageHelper.startPage(cpage, pageNum);
        List<Menu> menus  = menuService.findMenuAll();
        model.addAttribute("page",menus);
        return "/sys/menu/menuList";
    }
}
