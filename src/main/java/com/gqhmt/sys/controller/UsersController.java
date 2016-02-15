package com.gqhmt.sys.controller;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.controller.UsersController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/27 13:12
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/27  于泳      1.0     1.0 Version
 */
@Controller
public class UsersController {

    @Resource
    private UserService userService;

    /**
     *
     * author:jhz
     * time:2016年1月29日
     * function：展示用户列表
     */
    @AutoPage
    @RequestMapping(value = "/sys/user/users",method = RequestMethod.GET)
    public Object UsersList(HttpServletRequest request, ModelMap model, User user){
        List<User> usersList =userService.selectUsers(user);
        System.out.println(usersList.getClass().getName()+"===============");
//        GqPageInfo pageInfo = new GqPageInfo(usersList);
        model.addAttribute("page",usersList);
        return "sys/users/userList";
    }
}
