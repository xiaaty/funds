package com.gqhmt.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.gqhmt.sys.beans.MenuFunc;
import com.gqhmt.sys.beans.SysAuth;
import com.gqhmt.sys.beans.SysAuthFunc;
import com.gqhmt.sys.beans.SysUsers;


public class Auth {
	private static Pattern pattern = Pattern.compile(GlobalConstants.EXCLUDE_ONLY_LOGIN);
	
	
	public static List<MenuFunc> getSessionMenu(HttpServletRequest request,String roleIds){
//		if(StringUtils.isEmpty(roleIds)) return null;
//		Map<Long,List<SysAuthFunc>> menuMap=new LinkedHashMap<Long,List<SysAuthFunc>>();
//		List<SysAuthFunc> authFuncs=null;
//		List<SysAuthFunc> leftMenu=null;
//        boolean isAdmin = roleIds.indexOf(GlobalConstants.ROLE_SUPPER_ID)>=0?true:false;
//        boolean isSuperBussines = roleIds.indexOf(GlobalConstants.ROLE_BUSINESS_SUPPER_ID)>=0?true:false;
//
//		@SuppressWarnings("unchecked")
//		Map<Integer, List<SysAuthFunc>> roleMap = (Map<Integer, List<SysAuthFunc>>)Application.getInstance().getAppKey(request,GlobalConstants.ROLE_URL_MAP);
//        Set<Long> roleSet = new LinkedHashSet<>();
//		for(String roleId : roleIds.split(",")){
//			if (roleId != null && roleId.length() > 0) {
//				authFuncs=roleMap.get(Integer.parseInt(roleId));
//            if(authFuncs==null) continue;
//            for(SysAuthFunc sysAuthFunc:authFuncs){
//                roleSet.add(sysAuthFunc.getFuncId());
//            }
//        }
//		}
//
//        for(SysAuthFunc sysAuthFunc:GlobalConstants.funcMap.values()){
//            if(sysAuthFunc.getType() == 3 || isAdmin || (isSuperBussines && sysAuthFunc.getType() == 2)){
//                if(sysAuthFunc.getParentId()==0){
//                    leftMenu=new ArrayList<SysAuthFunc>();
//                    menuMap.put(sysAuthFunc.getFuncId(),leftMenu);
//                    continue;
//                }
//                if(menuMap.containsKey(sysAuthFunc.getParentId())&&sysAuthFunc.getIsMenu()==1){
//                    menuMap.get(sysAuthFunc.getParentId()).add(sysAuthFunc);
//                }
//            }
//
//        }
//
//		return menuMap;

       LogUtil.debug(Auth.class,GlobalConstants.allMenu.toString());
        List<MenuFunc> list = getMenuList(GlobalConstants.allMenu,roleIds);
        return list;

	}

    private static List<MenuFunc> getMenuList(List<SysAuthFunc> list,String roleIds){

        List<MenuFunc> returnList  = new ArrayList<>();
        for(SysAuthFunc func : list){
            if (roleIds.indexOf(GlobalConstants.ROLE_SUPPER_ID) >= 0) {

            }if(roleIds.indexOf(GlobalConstants.ROLE_BUSINESS_SUPPER_ID) >= 0){
                if(func.getType() == 1){
                    continue;
                }
            }
            MenuFunc menuFunc = new MenuFunc();
            menuFunc.setIsChild(func.getIsChild());
            menuFunc.setFuncId(func.getFuncId());
            menuFunc.setFuncName(func.getFuncName());
            menuFunc.setFuncUrl(func.getFuncUrl());
            if(func.getIsChild()){
                menuFunc.setChild(getMenuList(func.getChild(),roleIds));
            }
            menuFunc.setIcoClass(func.getIcoClass());

            returnList.add(menuFunc);
        }
        LogUtil.debug(Auth.class,returnList);
        return returnList;
    }



	 public static  boolean isAdmin(SysUsers sysUsers){
		   return sysUsers.getRoleIds().indexOf(GlobalConstants.ROLE_SUPPER_ID) >= 0;
	   }
	   public static  boolean isAdmin(HttpServletRequest request){
		   SysUsers sysUsers = (SysUsers) request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
		   return isAdmin(sysUsers);
	   }
	
	public static boolean checkAuth(HttpServletRequest request, String url) {
		SysUsers sysUsers = (SysUsers) request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
		return checkAuth(request, url, sysUsers);
	}
	
	public static boolean checkAuth(HttpServletRequest request, String url, SysUsers sysUsers) {
		if(sysUsers.getRoleIds()==null){
			return Boolean.FALSE;
		}else{
			if (sysUsers.getRoleIds().indexOf(GlobalConstants.ROLE_SUPPER_ID) >= 0) {
				return Boolean.TRUE;
			}if(sysUsers.getRoleIds().indexOf(GlobalConstants.ROLE_BUSINESS_SUPPER_ID) >= 0){
               Map<Long,SysAuthFunc> sysAuthFuncMap = GlobalConstants.funcMap;
                for(SysAuthFunc func: sysAuthFuncMap.values()){
                    if(func.getType() == 1 && url.equals(func.getFuncUrl())){
                        return false;
                    }
                }

                return Boolean.TRUE;
            } else {
				Matcher matcher = pattern.matcher(url); //是否仅需要登录即可
				if (matcher.find()){
					return Boolean.TRUE;
				}else{
				@SuppressWarnings("unchecked")
				Map<Integer, List<SysAuthFunc>> roleMap = (Map<Integer, List<SysAuthFunc>>)Application.getInstance().getAppKey(request,GlobalConstants.ROLE_URL_MAP);
					for (String roleId : sysUsers.getRoleIds().split(",")) {
						if (roleId != null && roleId.length() > 0) {
							List<SysAuthFunc> list = (List<SysAuthFunc>) roleMap.get(Integer.valueOf(roleId));
							if (list == null || list.isEmpty()) {
								break;
							}
							for (SysAuthFunc sysAuthFunc : list) {
								if (StringUtils.isNotEmptyString(sysAuthFunc.getFuncUrl())&&sysAuthFunc.getFuncUrl().equalsIgnoreCase(url)) {
									return Boolean.TRUE;
								}
							}

						}
					}
				}
				return Boolean.FALSE;
			}
		}
	}
}
