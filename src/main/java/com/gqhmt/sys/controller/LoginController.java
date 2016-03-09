package com.gqhmt.sys.controller;


import com.gqhmt.sys.beans.MenuFunc;
import com.gqhmt.sys.entity.User;
import com.gqhmt.sys.service.UserService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Resource
	private ImageCaptchaService imageCaptchaService;
	
	@Resource
	private UserService  userService;

		@RequestMapping(value="/login",method=RequestMethod.POST)
		public Object login(@RequestParam(value="loginName",required=false,defaultValue="")String loginName,
				@RequestParam(value="loginPwd",required=false,defaultValue="")String loginPwd,
				@RequestParam(value="verifyCode",required=false,defaultValue="")String verifyCode,HttpServletRequest request,ModelMap model){

			Boolean result = Boolean.FALSE;

//			Boolean isResponseCorrect = imageCaptchaService.validateResponseForID("1", verifyCode);

//			if(!isResponseCorrect){
//				return "redirect:/";
//			}
//			User user=userService.getUserById(loginName,loginPwd);
			/*HttpSession session = request.getSession(); 
			if(user==null){
				session.setAttribute("user", user);
				return "redirect:/index";
			}else{
				session.setAttribute("user", user);
				return "redirect:/main";
			}*/
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
/*	
	@RequestMapping(value="/index")
	public String indexpag(HttpServletRequest request, HttpServletResponse response){
		
		return "/index";
	}
		*/
}
