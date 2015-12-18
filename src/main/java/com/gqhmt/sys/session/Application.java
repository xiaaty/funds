package com.gqhmt.sys.session;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.sys.beans.MenuFunc;
import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.service.MenuService;


public class Application {
	
	
	private static Application application = new Application();


	private Application(){
        init();

    }
	public static Application getInstance() {
		return application;
	}

	private final Map<Long,Menu> menuMap = new ConcurrentHashMap<>();
    private final List<Menu> menus = Collections.synchronizedList(new ArrayList<Menu>());

    private void init(){
        synchronized (this){
            update();
        }
        LogUtil.debug(this.getClass(),menus.toString());
        LogUtil.debug(this.getClass(),menuMap.toString());
    }

    public void reload(){
        synchronized (this){
            menuMap.clear();
            menus.clear();

            update();
        }
    }

    private void update(){
        MenuService menuService = com.gqhmt.util.ServiceLoader.get(MenuService.class);
        List<Menu> menus = menuService.findMenuAll();
        //循环菜单项，初始化菜单
        for(Menu menu:menus){
            menuMap.put(menu.getId(),menu);
            if(menu.getParent_id() == 0){
                this.menus.add(menu);
            }
        }

        for(Menu menu:menus){
            if(menu.getParent_id() == 0){
                continue;
            }
            Long parentId = menu.getParent_id();
            Menu menu1 = menuMap.get(parentId);
            if(menu1 != null){
                menu1.addMenu(menu);
            }
        }
    }

    public String getMenu(String context,String url){
        return this.getHtml(menus,context,url).toString();
    }

    public StringBuffer getHtml(List<Menu> func, String context, String url){

        StringBuffer sb = new StringBuffer();
        if(func.size()<=0){
            return sb;
        }
        sb.append("<ul>");
        for(Menu menu : func){
            com.gqhmt.util.LogUtil.debug(this.getClass(),"tag:"+url+"___"+menu.getMenurl());
            if(checkMenu(url,menu.getMenurl())){
                sb.append(" <li class='active'>");
            }else{
                sb.append(" <li class=''>");
            }

            sb.append("<a href='");
            if(!menu.getMenurl().substring(0,1).equals("#")) {
                sb.append(context);
            }
            sb.append(menu.getMenurl());//添加链接
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

    private boolean checkMenu(String url, String menurl) {
        return false;
    }
}
