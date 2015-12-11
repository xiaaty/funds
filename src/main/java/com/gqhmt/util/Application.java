package com.gqhmt.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.gqhmt.sys.beans.Roles;
import com.gqhmt.sys.beans.SysAuthFunc;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.service.FuncListService;
import com.gqhmt.sys.service.RolesService;
import com.gqhmt.sys.service.SysAuthService;
import com.gqhmt.sys.service.SysUsersService;


public class Application {
	
	
	private static Application application = null;
	public static final ThreadLocal<Application> session = new ThreadLocal<Application>();

	public static Application getInstance() {
		application = session.get();
		if (application == null) {
			application = new Application();
			session.set(application);
		}
		return application;
	}
	
	/***
	 * 更新全局变量方法
	 * @param servletContext application对象
	 * @param key   全局对象的key
	 * @param ctx   对应于spring
	 * @param obj   仅仅更新某一个值是填写
	 */
	public void update(	ServletContext servletContext , String key, ApplicationContext ctx,String obj) {
		Map<String,Object> parameMap = new HashMap<String,Object>();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		this.runService(ctx, key, servletContext, parameMap,obj);
	}
	/***
	 *  重新加载某全局对象
	 * @param servletContext
	 * @param key   全局对象的key
	 * @param ctx   对应于spring
	 */
	public void reload(ServletContext servletContext , String key, ApplicationContext ctx) {
		Map<String,Object> parameMap = new HashMap<String,Object>();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		this.runService(ctx, key, servletContext, parameMap,null);

	}
	
	/***
	 *  获取全局变量的对象，目前application 未来可能是keyvalue 的nosql中获取
	 * @param request
	 * @param key
	 * @return
	 */
	public Object getAppKey(HttpServletRequest request,String key) {
		return request.getSession(true).getServletContext().getAttribute(key);
	}
	
	
	/***
	 * 
	 * @param ctx
	 * @param serviceName
	 * @param servletContext
	 * @param parameMap
	 * @param obj
	 */
	public void runService(ApplicationContext ctx, String serviceName, ServletContext servletContext, Map<String,Object> parameMap,String obj) {
		switch (serviceName) {
			case GlobalConstants.ROLE_MAP:
				this.runRoleMap(ctx, servletContext, parameMap);
				break;
			case GlobalConstants.FUNC_MAP:
				this.runFuncMap(ctx, servletContext, parameMap);
				break;
			case GlobalConstants.ROLE_URL_MAP:
				this.runAuth(ctx, servletContext);
				break;
			case GlobalConstants.USER_MAP:
				this.runUserMap(ctx, servletContext, parameMap,obj);
					break;
			default:
				break;
		}
	}
	
	/**
	 * 初始化启动时候加载全部全局对象
	 * @param ctx
	 * @param servletContext
	 * @param parameMap
	 */
	public void runALL(ApplicationContext ctx, ServletContext servletContext, Map<String,Object> parameMap) {
		this.runRoleMap(ctx, servletContext, parameMap);
		this.runFuncMap(ctx, servletContext, parameMap);
		this.runAuth(ctx, servletContext);
		this.runUserMap(ctx, servletContext, parameMap,null);
        this.runBankAccount(ctx);
	}

    private void runBankAccount(ApplicationContext ctx) {
//        AccountService service = ctx.getBean(AccountService.class);
//        List<AccountBankBean> bankBeans = service.getAccountBankList();
//        for(int i = 0;bankBeans != null && i<bankBeans.size();i++){
//            AccountBankBean bean = bankBeans.get(i);
//            LogUtil.debug(Application.class,"----"+bean.getId());
//            String name = GlobalConstants.bankMap.get(bean.getOpenBank())+"-"+bean.getAccountNo();
//            GlobalConstants.bankAccountMap.put(bean.getId(),name);
//        }
//
//        List<AccountThirdBean> thirdBeans = service.getAccountThirdList();
//        for(int i = 0;thirdBeans != null && i<thirdBeans.size();i++){
//            AccountThirdBean bean = thirdBeans.get(i);
//            GlobalConstants.thirdAccountMap.put(bean.getId(),GlobalConstants.thirdMap.get(bean.getThirdOrg())+"-"+bean.getAccountNo());
//        }

//        LogUtil.debug(Application.class,bankBeans);
//        LogUtil.debug(Application.class,thirdBeans);
    }

    /***
	 * 加载全部的角色
	 * @param ctx
	 * @param servletContext
	 * @param parameMap
	 */
	private void runRoleMap(ApplicationContext ctx, ServletContext servletContext, Map<String,Object> parameMap) {
		RolesService rolesService = (RolesService) ctx.getBean("rolesService");
		List<Roles> list = rolesService.selectRolesList(parameMap);
		GlobalConstants.roleMap.clear();
		for (Roles role : list) {
			GlobalConstants.roleMap.put(role.getId(), role.getRoleName());
		}
	}
	
	/**
	 * 加载所有的函数id与函数名称
	 * @param ctx
	 * @param servletContext
	 * @param parameMap
	 */
	private void runFuncMap(ApplicationContext ctx, ServletContext servletContext, Map<String,Object> parameMap) {
		FuncListService funcListService = (FuncListService) ctx.getBean("funcListService");
		List<SysAuthFunc> funcList = funcListService.selectFuncList(parameMap);
		GlobalConstants.funcMap.clear();
		for (SysAuthFunc func : funcList) {
			GlobalConstants.funcMap.put(func.getFuncId(),func);

		}

        for (SysAuthFunc func : funcList) {
            if(func.getParentId() == 0){
                GlobalConstants.allMenu.add(func);
            }else{
                if(func.getIsMenu() == 1){
                    SysAuthFunc pFunc = GlobalConstants.funcMap.get(func.getParentId());
                    pFunc.setIsChild(true);
                    pFunc.addChild(func);
                }

            }
        }

	}
	
	private void runAuth(ApplicationContext ctx, ServletContext servletContext) {
		SysAuthService sysAuthService = (SysAuthService) ctx.getBean("sysAuthService");
		servletContext.removeAttribute(GlobalConstants.ROLE_URL_MAP);
		servletContext.setAttribute(GlobalConstants.ROLE_URL_MAP,sysAuthService.selectRoleAuth());
	}
	
	private void runUserMap(ApplicationContext ctx, ServletContext servletContext, Map<String,Object> parameMap,String obj){
		SysUsersService sysUsersService=(SysUsersService)ctx.getBean("sysUsersService");
		if(obj!=null&&obj.length()>0){
			SysUsers sysUsers=sysUsersService.selectSysUsersById(Integer.parseInt(obj));
			GlobalConstants.usersMap.remove(sysUsers.getId());
			GlobalConstants.usersMap.put(sysUsers.getId(),sysUsers);
		}else{
			List<SysUsers> users=sysUsersService.selectSysUsers(parameMap);
			GlobalConstants.usersMap.clear();
			for(SysUsers sysUsers:users){
				GlobalConstants.usersMap.put(sysUsers.getId(),sysUsers);
			}
		}
	}
	
}
