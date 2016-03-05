package com.gqhmt.core.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gqhmt.sys.entity.MenuEntity;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月4日
 * Description:	单点登录得到资金结算系统菜单
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月4日  jhz      1.0     1.0 Version
 */
public class AuthMenu {
	
	private static AuthMenu authMenu = new AuthMenu();

	public static AuthMenu getInstance() {
		// TODO Auto-generated method stub
		return authMenu;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月4日
	 * function：得到菜单列表
	 */
    public String getMenu( List<MenuEntity> menus,String context,String url){
    	Map<String,MenuEntity> menuMap = new ConcurrentHashMap<>();
    	List<MenuEntity> menues=new ArrayList<MenuEntity>();
    	 //循环菜单项，初始化菜单
        for(MenuEntity menu:menus){
            menuMap.put(menu.getId(),menu);
            if(menu.getParentId().equals( "7bf96a0315834addaec5dab0befe6f75")){
                menues.add(menu);
            }
        }
        for(MenuEntity menu:menus){
            if(menu.getParentId().equals( "7bf96a0315834addaec5dab0befe6f75")){
                continue;
            }
            String parentId = menu.getParentId();
            MenuEntity menu1 = menuMap.get(parentId);
            if(menu1 != null){
                menu1.addMenu(menu);
            }
        }
        return this.getHtml(menues,context,url).toString();
    }

    public StringBuffer getHtml(List<MenuEntity> func, String context, String url){

        StringBuffer sb = new StringBuffer();
        if(func.size()<=0){
            return sb;
        }
        sb.append("<ul>");
        for(MenuEntity menu : func){
            com.gqhmt.util.LogUtil.debug(this.getClass(),"tag:"+url+"___"+menu.getMenuUrl());
            if(checkMenu(url,menu)){
                sb.append(" <li class='active'>");
            }else{
                sb.append(" <li class=''>");
            }

            sb.append("<a href='");
            if(!menu.getMenuUrl().equals("")&&menu.getMenuUrl()!=null) {
                sb.append(context);
                sb.append(menu.getMenuUrl());//添加链接
                sb.append(parse(menu,url));//添加链接
            }else{
            	 sb.append("javaScript:void(0)");
            }
            sb.append("' title='");
            sb.append(menu.getMenuName());
            sb.append("'>");
            /*if(menu.getIcoClass()!= null && !"".equals(menu.getIcoClass())){
                sb.append("<i class='fa fa-lg fa-fw ");
                sb.append(menu.getIcoClass());
                sb.append(" '></i>");
                sb.append("<span class='menu-item-parent'>");
                sb.append(menu.getMenuName());
                sb.append("</span>");
            }else{
                sb.append(menu.getMenuName());
            }*/

            sb.append(menu.getMenuName());

            sb.append("</a>");

            if(menu.getList().size()>0){
                sb.append(getHtml(menu.getList(),context,url));
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb;
    }

    private boolean checkMenu(String url, MenuEntity menu) {
        if(menu.getParma() == "0" || "0".equals(menu.getParma())){
            return url.equals(menu.getMenuUrl());
        }
        String[] param = menu.getParma().split(",");
        String[] menuUrlLength = menu.getMenuUrl().split("/");
        String[] urlLength = url.split("/");
        if (menuUrlLength.length != urlLength.length) return false;
        boolean flag = true;
        for(int i = 0;i<menuUrlLength.length;i++){
            String tmp = menuUrlLength[i];
            if(check(tmp,param))
                continue;

            String urlTmp = urlLength[i];

            if(!tmp.equals(urlTmp)){
                flag  =false;
                break;
            }

        }
        return flag;
    }

    private boolean check(String tmp, String[] param) {

        for(String t:param){
            if(t.equals(tmp)){
                return true;
            }
        }

        return false;
    }

    private String parse(MenuEntity menu, String url){
        if(menu.getParma() == "0" || "0".equals(menu.getParma())){
            return menu.getMenuUrl();
        }

        String returnUrl = null;
        String[] urlLength = url.split("/");
        String[] menuUrlLength = menu.getMenuUrl().split("/");
        if(urlLength.length == menuUrlLength.length){
            returnUrl = replaceUrlValue(menu,url);
        }else{
            returnUrl = relpaceDefaultValue(menu);
        }


        return returnUrl;
    }

    private String replaceUrlValue(MenuEntity menu, String url) {
       /* if(checkMenu(url,menu)){
            return url;
        }
*/
        return relpaceDefaultValue(menu);
    }


    private String relpaceDefaultValue(MenuEntity menu){
        if(menu == null || menu.getParma() == null||menu.getParmaDefaule()==null){
            return "";
        }
        String[] param = menu.getParma().split(",");
        String[] paramValue = menu.getParmaDefaule().split(",");
        String url  = menu.getMenuUrl();

        for(int i =0;i<param.length;i++){
            String paramTmp = param[i];
            url = url.replace(paramTmp,paramValue[i]);
        }
        return url;

    }

	

    /*======================================菜单初始化及应用结束========================================================*/
}
