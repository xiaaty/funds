package com.gqhmt.sys.controller;


import com.gqhmt.sys.beans.MenuFunc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

		@RequestMapping(value="/login",method=RequestMethod.POST)
		public String login(@RequestParam(value="loginName",required=false,defaultValue="")String loginName,
				@RequestParam(value="loginPwd",required=false,defaultValue="")String loginPwd,
				@RequestParam(value="verifyCode",required=false,defaultValue="")String verifyCode,HttpServletRequest request,ModelMap model){

			return "redirect:/main";
		}

        public String getUrl(MenuFunc func){
            if(func.getIsChild() == false){
                return func.getFuncUrl();
            }
            return getUrl(func.getChild().get(0));
        }

	@RequestMapping(value="/main")
	public String menu(HttpServletRequest request, HttpServletResponse response){

		return "main/main";
	}
		
}
