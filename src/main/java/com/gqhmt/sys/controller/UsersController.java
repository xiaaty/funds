package com.gqhmt.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.service.UserService;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value="/sys/users")
    public String employeeManage(ModelMap model, @ModelAttribute(value="sysUsers")User sysUsers){
        List<User> list = userService.selectUsers(sysUsers);
        model.addAttribute("page", list);
        return "/sys/users/userList";
    }
}
