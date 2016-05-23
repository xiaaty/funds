package com.gqhmt.sys.controller;


import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.ResourceUtil;
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


	@RequestMapping(value="/main")
	public String menu(HttpServletRequest request, HttpServletResponse response){

		return "main/main";
	}

	/*
     * 注销退出
     *
     * */
	@RequestMapping(value = "/logout")
	public String loginOut(HttpServletRequest request, ModelMap model) {

		//初始化
		request.getSession().invalidate();
		// 读取出借关联

		String logoutUrl = ResourceUtil.getValue("config.appContext","casLogoutUrl");
		String serverName = ResourceUtil.getValue("config.appContext","localServerName");
		logoutUrl += "?service=http://"+serverName;
		return "redirect:" + logoutUrl;
	}

/*	
	@RequestMapping(value="/index")
	public String indexpag(HttpServletRequest request, HttpServletResponse response){
		
		return "/index";
	}
		*/

	@RequestMapping(value = "/sys/cache/reloadView")
	public String cacheReloadVie(){

		return "sys/menu/cacheReload";
	}

	@RequestMapping(value = "/sys/cache/reload")
	public String cacheReload() throws FssException{
		Application.getInstance().reload();
		return "redirect:/sys/cache/reloadView" ;
	}
}
