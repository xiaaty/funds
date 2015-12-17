package com.gqhmt.sys.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gqhmt.sys.beans.MenuFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gqhmt.util.Auth;
import com.gqhmt.util.Encriptor;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.StringUtils;
import com.gqhmt.sys.beans.SysLog;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.mapper.write.SysLogWriteMapper;
import com.gqhmt.sys.service.SysUsersService;

@Controller
public class LoginController {
		@Resource
		SysUsersService sysUsersService;
		@Resource
		SysLogWriteMapper sysLogWriteMapper;
		
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public String login(@RequestParam(value="loginName",required=false,defaultValue="")String loginName,
				@RequestParam(value="loginPwd",required=false,defaultValue="")String loginPwd,
				@RequestParam(value="verifyCode",required=false,defaultValue="")String verifyCode,HttpServletRequest request,ModelMap model){
			if(!(GlobalConstants.getSession(request,"verifyCode")+"").equalsIgnoreCase(verifyCode)){
				model.addAttribute("errorCode","0001");
				return "redirect:"+GlobalConstants.USER_HOME;
			}
			SysUsers sysUsers = sysUsersService.selectSysUsersByLoginName(loginName.trim());
			if (sysUsers != null && Encriptor.getMD5(loginPwd.trim()).equalsIgnoreCase(sysUsers.getPassword())) {
				if(sysUsers.getIsDel()==GlobalConstants.DEL){
					model.addAttribute("errorCode","0004");
					return "redirect:"+GlobalConstants.USER_HOME;
				}
				if(StringUtils.isEmpty(sysUsers.getRoleIds())){
					model.addAttribute("errorCode","0002");
					return "redirect:"+GlobalConstants.USER_HOME;
				}
				GlobalConstants.setSession(request, GlobalConstants.SESSION_EMP, sysUsers);
				List<MenuFunc> sessionMenu=Auth.getSessionMenu(request,sysUsers.getRoleIds());

				if(sessionMenu==null || sessionMenu.size()==0){
					model.addAttribute("errorCode","0005");
					return "redirect:"+GlobalConstants.USER_HOME;
				}
//				sysLogWriteMapper.insertSysLog(new SysLog(request,sysUsers));
				GlobalConstants.setSession(request, GlobalConstants.SESSION_MENU, sessionMenu);
				if(sessionMenu.size() >= 0){
                    return "redirect:/main";
				}
			}
			model.addAttribute("errorCode","0003");
			return "redirect:"+GlobalConstants.USER_HOME;
		}

        public String getUrl(MenuFunc func){
            if(func.getIsChild() == false){
                return func.getFuncUrl();
            }
            return getUrl(func.getChild().get(0));
        }

	@RequestMapping("/main")
	public String menu(){

		return "main";
	}
		
}
