package com.gqhmt.core.util;


import com.gqhmt.sys.entity.MenuEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private final Map<String,List<Map<String, String>>> menuMaps = new ConcurrentHashMap<>();

    private final Map<String,Map<String,MenuEntity>> menuMap = new ConcurrentHashMap<>();
    private final Map<String,List<MenuEntity>>  menus = new ConcurrentHashMap<>();




	public static AuthMenu getInstance() {
		// TODO Auto-generated method stub
		return authMenu;
	}


    public void addUserMenu(String loginName,List<Map<String, String>> menuList){
        if(this.menuMaps.containsKey(loginName) && this.menuMaps.get(loginName) != null){
            this.menuMaps.remove(loginName);
            this.menus.remove(loginName);
            this.menuMap.remove(loginName);
        }
        this.menuMaps.put(loginName,menuList);

        String authMenuId = ResourceUtil.getValue("config.appContext","authMenuId");

        if(authMenuId == null && "".equals(authMenuId)){
            return;
        }

        List<MenuEntity> menuEntities=new ArrayList<>();
        Map<String,MenuEntity> maps = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {
            //菜单系统自行处理
            Map<String, String> map = menuList.get(i);
            if("0".equals(map.get("isShow"))){
                continue;
            }

            String  parentId = map.get("parentId");
            MenuEntity menuEntity=new MenuEntity();
            menuEntity.setMenuName(map.get("name"));
            menuEntity.setMenuUrl(map.get("href"));
            menuEntity.setId(map.get("id"));
            menuEntity.setParentId(parentId);
            menuEntity.setParma(map.get("isShow"));
            menuEntity.setShow("1".equals(map.get("isShow"))?true:false);
            if(parentId.equals(authMenuId)){
                menuEntities.add(menuEntity);
            }
            maps.put(menuEntity.getId(),menuEntity);
        }

        for (int i = 0; i < menuList.size(); i++) {
            Map<String, String> map = menuList.get(i);
            if("0".equals(map.get("isShow"))){
                continue;
            }
            String  parnetId = map.get("parentId");
            String  id = map.get("id");
            if(parnetId.equals(authMenuId)){
                continue;
            }
            MenuEntity menu = maps.get(id);
            MenuEntity menu1 = maps.get(parnetId);
            if(menu1 != null){
                menu1.addMenu(menu);
            }
        }

        menus.put(loginName,menuEntities);
        menuMap.put(loginName,maps);
    }

    public void remove(String loginName){
        if(this.menuMaps.containsKey(loginName)) {
            this.menuMaps.remove(loginName);
            this.menus.remove(loginName);
            this.menuMap.remove(loginName);
        }
    }


	/**
	 * 
	 * author:jhz
	 * time:2016年3月4日
	 * function：得到菜单列表
	 */
    public String getMenu( String  loginName,String context,String url){
    	List<MenuEntity> menues=this.menus.get(loginName);
    	 //循环菜单项，初始化菜单
        return this.getHtml(menues,context,url).toString();
    }

    public StringBuffer getHtml(List<MenuEntity> func, String context, String url){


        StringBuffer sb = new StringBuffer();
        if(func == null || func.size()<=0){
            return sb;
        }
        sb.append("<ul>");
        for(MenuEntity menu : func){
            if(menu == null){
                continue;
            }
            LogUtil.debug(this.getClass(),"tag:"+url+"___"+menu.getMenuUrl());
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
        boolean flag = false;

        if(menu.getMenuUrl() == null || "".equals(menu.getMenuUrl())){
            return  false;
        }

        if(check(url,menu.getMenuUrl())){
            flag = true;
        }

        return flag;
    }

    private boolean check(String url, String menuUrl) {

        if(url.equals(menuUrl)){
            return true;
        }

        if(url.contains(menuUrl)){
            return true;
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
