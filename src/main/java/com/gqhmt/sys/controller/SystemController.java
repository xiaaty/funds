package com.gqhmt.sys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gqhmt.sys.beans.LogSearch;
import com.gqhmt.sys.beans.Roles;
import com.gqhmt.sys.beans.SysLog;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.mapper.read.SysLogReadMapper;
import com.gqhmt.sys.service.FuncListService;
import com.gqhmt.sys.service.RolesService;
import com.gqhmt.sys.service.SysAuthService;
import com.gqhmt.sys.service.SysUsersService;
import com.gqhmt.util.Application;
import com.gqhmt.util.Encriptor;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.Pager;
import com.gqhmt.util.RequestUtil;
import com.gqhmt.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class SystemController{
	@Autowired
	SysUsersService sysUsersService;
	@Autowired
	RolesService rolesService;
	@Autowired
	SysLogReadMapper sysLogReadMapper;
	@Autowired
	FuncListService funcListService;
	@Autowired
	SysAuthService sysAuthService;
	

	
	@RequestMapping(value="/sys/empmanage")
	public String employeeManage(HttpServletRequest request, ModelMap model, @ModelAttribute(value="sysUsers")SysUsers sysUsers){
		int pageNum=RequestUtil.getInt(request,"pageNum",0);
		pageNum=pageNum>0?pageNum:GlobalConstants.PAGE_SIZE;
		int cpage = RequestUtil.getInt(request, "cpage",0);
        PageHelper.startPage(cpage, pageNum);
        List<SysUsers> list = sysUsersService.selectSysUsers(sysUsers);
        PageInfo pageInfo = new PageInfo(list);
		Pager page = new Pager(cpage, pageInfo.getTotal(),pageNum);
		page.setLinkUrl(request.getContextPath()+"/sys/empmanage?totalRows="+pageInfo.getTotal());
		GlobalConstants.addSearchCondition(page,sysUsers,new String[]{"employeeNo","userName","deparement","leader","status"});
		
//		SysUsers curuser =(SysUsers) GlobalConstants.getSession(request, GlobalConstants.SESSION_EMP);
//		if(",1,".equals(curuser.getRoleIds())){
//			model.addAttribute("role","1");
//		}
//		model.addAttribute("list",list);
//		model.addAttribute("statusMap",GlobalConstants.empStatusMap);
//		model.addAttribute("pageNum",pageNum);
//		model.addAttribute("pageMap",GlobalConstants.pageMap);
//		model.addAttribute("search",sysUsers);
//		model.addAttribute("superRoleId",GlobalConstants.ROLE_SUPPER_ID);
//		request.setAttribute("pb",page);
		model.addAttribute("page", pageInfo);
	    model.addAttribute("accountMap", GlobalConstants.accountMap);
	    model.addAttribute("bankMap", GlobalConstants.bankMap);
		return "/system/empmanage";
	}
	
	
	
	
	@RequestMapping(value="/sys/addemp")
	public String addemp(HttpServletRequest request,ModelMap model,
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			@ModelAttribute(value="sysUsers")SysUsers sysUsers){
		String method=request.getMethod();
		model.addAttribute("sexMap",GlobalConstants.empSexMap);
		model.addAttribute("statusMap",GlobalConstants.empStatusMap);
	
		if(method.equalsIgnoreCase("post")){
			long userId=((SysUsers)GlobalConstants.getSession(request,GlobalConstants.SESSION_EMP)).getId();
			if(id>0){
				sysUsers.setModifyId(userId);
				sysUsersService.updateSysUsers(sysUsers);
				return "redirect:/sys/empmanage";
			}
			sysUsers.setCreateId(userId);
			if(sysUsers.getPassword()==null || sysUsers.getPassword().trim().equals("")){
				sysUsers.setPassword(Encriptor.getMD5("gqi.2009"));
			}
			else
				sysUsers.setPassword(Encriptor.getMD5(sysUsers.getPassword()));
			int res=sysUsersService.insertSysUsers(sysUsers);
			if(res>0){
				
				Application.getInstance().update(request.getSession(true).getServletContext(),
						GlobalConstants.USER_MAP,
						WebApplicationContextUtils.getWebApplicationContext(request.getSession(true).getServletContext()) ,sysUsers.getId()+"");
			}
			return "redirect:/sys/empmanage";
		}
		if(id>0)
			model.addAttribute("emp",sysUsersService.selectSysUsersById(id));
		else{
			SysUsers newSysUsers=new SysUsers();
			newSysUsers.setId(0);
			model.addAttribute("emp",newSysUsers);
		}
		return "/system/addemp";
	}
	
	@RequestMapping(value="/sys/resetpwd")
	public void resetpwd(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			@RequestParam(value="login",required=false,defaultValue="")String loginName){
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			if(id<=0 || StringUtils.isEmpty(loginName)){
				out.print(0);
			}else{
				sysUsersService.upPwd(Encriptor.getMD5("gqi.2009").trim(), id);
				out.print(1);
			}
			out.flush();
			out.close();
			return ;
		} catch (IOException e) {
		}
	}
	
	@RequestMapping(value="/sys/upempstatus")
	public String delemp(HttpServletRequest request,
			@RequestParam(value="id",required=false,defaultValue="0")int id){
		if(id>0){
			sysUsersService.updateEmpStatus(id);
		}
		return "redirect:/sys/empmanage";
	}
	
	@RequestMapping(value="/sys/rolemanage")
	public String roleManage(HttpServletRequest request,
			@ModelAttribute(value="roles")Roles roles,ModelMap model){
		int pageNum=RequestUtil.getInt(request,"pageNum",0);
		pageNum=pageNum>0?pageNum:GlobalConstants.PAGE_SIZE;
		int cpage = RequestUtil.getInt(request, "cpage",0);
        PageHelper.startPage(cpage, pageNum);
        List<Roles> list = rolesService.selectRolesList(roles);
        PageInfo pageInfo = new PageInfo(list);
		Pager page = new Pager(cpage, pageInfo.getTotal(),pageNum);
		page.setLinkUrl(request.getContextPath()+"/sys/rolemanage?totalRows="+pageInfo.getTotal());
		GlobalConstants.addSearchCondition(page, roles, new String[]{"roleName","status"});
		model.addAttribute("roles",rolesService.selectRolesList(roles));
		model.addAttribute("fMap",GlobalConstants.roleFeatureMap);
		model.addAttribute("search",roles);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageMap",GlobalConstants.pageMap);
		request.setAttribute("pb",page);
		return "/system/rolemanage";
	}
	
	@RequestMapping(value="/sys/addrole")
	public String addRole(HttpServletRequest request,ModelMap model,
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			@ModelAttribute(value="roles")Roles roles){
		String method=request.getMethod();
		model.addAttribute("roleMap",GlobalConstants.roleMap);
		model.addAttribute("fMap",GlobalConstants.roleFeatureMap);
		if(method.equalsIgnoreCase("post")){
			long userId=((SysUsers)GlobalConstants.getSession(request,GlobalConstants.SESSION_EMP)).getId();
			if(id>0){
				roles.setModifyId(userId);
				rolesService.updateRole(roles);
				return "redirect:/sys/rolemanage";
			}
			roles.setCreateId(userId);
			int res=rolesService.inserRole(roles);
			if(res>0){
				ServletContext servletContext= request.getSession(true).getServletContext();
				ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
				Application.getInstance().update(servletContext, GlobalConstants.ROLE_MAP, ctx, null);
			}
			return "redirect:/sys/rolemanage";
		}
		if(id>0)
			model.addAttribute("role",rolesService.selectRoleById(id));
		return "/system/addrole";
	}
	
	@RequestMapping(value="/sys/delrole")
	@ResponseBody
	public void delRole(@RequestParam(value="rid",required=false,defaultValue="")String rid){
		if(StringUtils.isEmpty(rid))
			return ;
		rolesService.updateRoleDel(rid,GlobalConstants.DEL);
		return ;
	}
	
	@RequestMapping(value="/sys/permanage")
	public String personManage(HttpServletRequest request,ModelMap model,
			@ModelAttribute(value="sysUsers")SysUsers sysUsers){
		model.addAttribute("flag","1");
		model.addAttribute("action","/sys/permanage");
		model.addAttribute("sexMap",GlobalConstants.empSexMap);
		model.addAttribute("statusMap",GlobalConstants.empStatusMap);
		model.addAttribute("userMap",GlobalConstants.usersMap);
		String method=request.getMethod();
		if(method.equalsIgnoreCase("post")){
			if(sysUsers==null){
				model.addAttribute("message","数据不正确");
				return "/system/addemp";
			}
			sysUsersService.updateSysUsers(sysUsers);
			GlobalConstants.setSession(request,GlobalConstants.SESSION_EMP,sysUsersService.selectSysUsersById(sysUsers.getId()));
			model.addAttribute("message","修改成功");
		}
		model.addAttribute("emp",GlobalConstants.getSession(request,GlobalConstants.SESSION_EMP));
		return "/system/addemp";
	}
	
	@RequestMapping(value="/sys/uppwd")
	public String passwordManage(HttpServletRequest request,
			@RequestParam(value="id",required=false,defaultValue="")String id,
			@RequestParam(value="oldPwd",required=false,defaultValue="")String oldPwd,
			@RequestParam(value="newPwd",required=false,defaultValue="")String newPwd,
			@RequestParam(value="confirmPwd",required=false,defaultValue="")String confirmPwd,ModelMap model){
		
		String method=request.getMethod();
		model.addAttribute("id",id);
		if(method.equalsIgnoreCase("post")){
			SysUsers sysUsers=(SysUsers)GlobalConstants.getSession(request,GlobalConstants.SESSION_EMP);
			if(id!=null&& !"".equals(id)){
				sysUsers=sysUsersService.selectSysUsersById(Long.parseLong(id));
			}
			if(!Encriptor.getMD5(oldPwd.trim()).equalsIgnoreCase(
					sysUsers.getPassword())){
				model.addAttribute("message","旧密码不正确");
				return "/system/uppwd";
			}
			
			if(StringUtils.isNotEmptyString(newPwd)&&newPwd.trim().length()>0&&newPwd.equalsIgnoreCase(confirmPwd.trim())){
				String password=Encriptor.getMD5(newPwd.trim());
				sysUsersService.upPwd(password,sysUsers.getId());
				sysUsers.setPassword(password);
				model.addAttribute("message","密码修改成功");
				return "/system/uppwd";
			}
			model.addAttribute("message","修改密码失败");
		}
		return "/system/uppwd";
	}
	
	
	@RequestMapping(value="/sys/assignauth")
	public String assignauth(HttpServletRequest request,
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			@ModelAttribute(value="roles")Roles roles,
			ModelMap model){
		model.addAttribute("roles",rolesService.selectRolesListAll());
		model.addAttribute("fMap",GlobalConstants.roleFeatureMap);
		model.addAttribute("id",id);
		if(GlobalConstants.usersMap.get(id)!=null)
			model.addAttribute("empRoleIds",GlobalConstants.usersMap.get(id).getRoleIds());
		else
			return "/system/addrole";
		
		return "/system/assignauth";
	}
	
	@RequestMapping(value="/sys/assignauth.do")
	@ResponseBody
	public void updaterole(HttpServletRequest request,
			@RequestParam(value="id",required=false,defaultValue="0")int id){
			String rid=request.getParameter("rid");
			if(id>0&&StringUtils.isNotEmptyString(rid)){
				sysUsersService.updateUserRole(id,","+rid+",");
				GlobalConstants.usersMap.get(id).setRoleIds(","+rid+",");
			}
	}
	
	@RequestMapping(value="/sys/log")
	public String logManage(HttpServletRequest request,
			@ModelAttribute(value="logSearch")LogSearch logSearch,ModelMap model){
		int pageNum=RequestUtil.getInt(request,"pageNum",0);
		pageNum=pageNum>0?pageNum:GlobalConstants.PAGE_SIZE;
		int cpage = RequestUtil.getInt(request, "cpage",0);
        PageHelper.startPage(cpage, pageNum);
        List<SysLog> list = sysLogReadMapper.selectLogs1(logSearch);
        PageInfo pageInfo = new PageInfo(list);
		Pager page = new Pager(cpage, pageInfo.getTotal(),pageNum);
		page.setLinkUrl(request.getContextPath()+"/sys/log?totalRows="+pageInfo.getTotal());
		GlobalConstants.addSearchCondition(page, logSearch,new String[]{"loginName","department"});
		model.addAttribute("page", pageInfo);
	    model.addAttribute("accountMap", GlobalConstants.accountMap);
	    model.addAttribute("bankMap", GlobalConstants.bankMap);
		return "/system/log";
	}
    @RequestMapping(value="/sys/myLog")
    public String myLogManage(HttpServletRequest request,
                            @ModelAttribute(value="sysLog")SysLog sysLog,ModelMap model){
        int pageNum=RequestUtil.getInt(request,"pageNum",0);
        pageNum=pageNum>0?pageNum:GlobalConstants.PAGE_SIZE;
        int cpage = RequestUtil.getInt(request, "cpage",0);
        PageHelper.startPage(cpage, pageNum);
        SysUsers user  = (SysUsers) request.getSession().getAttribute(GlobalConstants.SESSION_EMP);
        List<SysLog> list = sysLogReadMapper.selectMyLogs(user.getId());
        PageInfo pageInfo = new PageInfo(list);
        Pager page = new Pager(cpage, pageInfo.getTotal(),pageNum);
        page.setLinkUrl(request.getContextPath()+"/sys/log?totalRows="+pageInfo.getTotal());
		model.addAttribute("page", pageInfo);
	    model.addAttribute("accountMap", GlobalConstants.accountMap);
	    model.addAttribute("bankMap", GlobalConstants.bankMap);
    return "/system/myLog";
    }
	
	@RequestMapping(value="/sys/rolefunc")
	public String rolefunc(HttpServletRequest request,ModelMap model,
			@RequestParam(value="id",required=false,defaultValue="0")int id){
		String method=request.getMethod();
		if(id>0){
			if(method.equalsIgnoreCase("post")){
				SysUsers sysUsers=(SysUsers)GlobalConstants.getSession(request,GlobalConstants.SESSION_EMP);
				String funcIds=request.getParameter("funcIds");
				if(StringUtils.isNotEmptyString(funcIds)){
					sysAuthService.delSysAuth(id, sysUsers.getId(),sysUsers.getModifyDate());
					int res=sysAuthService.insertSysAuth(id,funcIds,sysUsers.getId());
					if(res>0){
						ServletContext servletContext= request.getSession(true).getServletContext();
						ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
						Application.getInstance().update(servletContext, GlobalConstants.ROLE_URL_MAP, ctx, null);
					}
				}
				return "redirect:/sys/rolemanage";
			}
			model.addAttribute("roles",rolesService.selectRoleById(id));
			request.setAttribute("funcList",funcListService.selectFuncList(new HashMap<String, Object>()));
			request.setAttribute("sysRoleFuncMap",sysAuthService.selectRoleAuthByRoleId(id));
			model.addAttribute("roleId",id);
		}
		return "/system/rolefunc";
	}
	
	@RequestMapping(value="/json/check")
	public void check(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id",required=false,defaultValue="0")int id,
			@RequestParam(value="name",required=false,defaultValue="")String name,
			@RequestParam(value="value",required=false,defaultValue="")String value){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
				out.print(0);
			}else{
				Map<String,Object> map=new ConcurrentHashMap<String, Object>();
				map.put(name,value);
				map.put("id",id);
				out.print(sysUsersService.checkEmp(map));
			}
			out.flush();
			out.close();
		} catch (IOException e) {
		}
		
		
	}
	
}
