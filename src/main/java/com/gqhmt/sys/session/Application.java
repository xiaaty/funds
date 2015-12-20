package com.gqhmt.sys.session;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.service.MenuService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
            com.gqhmt.util.LogUtil.debug(this.getClass(),"tag:"+url+"___"+menu.getMenuUrl());
            if(checkMenu(url,menu)){
                sb.append(" <li class='active'>");
            }else{
                sb.append(" <li class=''>");
            }

            sb.append("<a href='");
            if(!menu.getMenuUrl().substring(0,1).equals("#")) {
                sb.append(context);
            }
            sb.append(parse(menu,url));//添加链接
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

    private boolean checkMenu(String url, Menu menu) {
        if(menu.getParma()==null){
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

    private String parse(Menu menu,String url){
        if(menu.getParma() == null || "".equals(menu.getParma())){
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

    private String replaceUrlValue(Menu menu, String url) {
       /* if(checkMenu(url,menu)){
            return url;
        }
*/
        return relpaceDefaultValue(menu);
    }


    private String relpaceDefaultValue(Menu menu){
        String[] param = menu.getParma().split(",");
        String[] paramValue = menu.getParmaDefaule().split(",");
        String url  = menu.getMenuUrl();

        for(int i =0;i<param.length;i++){
            String paramTmp = param[i];
            url = url.replace(paramTmp,paramValue[i]);
        }


        return url;

    }
}
