package com.gqhmt.util;

import com.gqhmt.sys.beans.MenuFunc;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Filename:    com.gqhmt.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/13 18:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/13  于泳      1.0     1.0 Version
 */
public class MenuTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private  List<MenuFunc> menuMap;
    private Integer fid;


    public StringBuffer getHtml(List<MenuFunc> func,String context,String url){

        StringBuffer sb = new StringBuffer();
        if(func.size()<=0){
            return sb;
        }
        sb.append("<ul>");
        for(MenuFunc menuFunc : func){
            LogUtil.debug(this.getClass(),"tag:"+url+"___"+menuFunc.getFuncUrl());
            if(Integer.parseInt(menuFunc.getFuncId()) == fid){
                sb.append(" <li class='active'>");
            }else{
                sb.append(" <li class=''>");
            }

            sb.append("<a href='");
            if(!menuFunc.getFuncUrl().equals("#")) {
                sb.append(context);
            }
            sb.append(menuFunc.getFuncUrl());//添加链接
            sb.append("' title='");
            sb.append(menuFunc.getFuncName());
            sb.append("'>");
            if(menuFunc.getIcoClass()!= null && !"".equals(menuFunc.getIcoClass())){
                sb.append("<i class='fa fa-lg fa-fw ");
                sb.append(menuFunc.getIcoClass());
                sb.append(" '></i>");
                sb.append("<span class='menu-item-parent'>");
                sb.append(menuFunc.getFuncName());
                sb.append("</span>");
            }else{
                sb.append(menuFunc.getFuncName());
            }

            sb.append("</a>");

            if(menuFunc.getIsChild()){
                sb.append(getHtml(menuFunc.getChild(),context,url));
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb;
    }


    @SuppressWarnings("static-access")
    public int doStartTag() {
        ServletRequest servletRequest = pageContext.getRequest();
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String context = request.getContextPath();
        String url  =  request.getServletPath();
        if (url.lastIndexOf(".") > 0) {
            url = url.substring(0, url.lastIndexOf("."));
        }
        String urlType = request.getParameter("urlType");
        if(urlType != null && !"".equals(urlType)){
            url = url+"?urlType="+urlType;
        }

        try {
            StringBuffer sb = getHtml(menuMap,context,url);
            JspWriter out = pageContext.getOut();
            out.print(sb.toString());
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return super.SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

    public List<MenuFunc> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(List<MenuFunc> menuMap) {
        this.menuMap = menuMap;
    }


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public void release() {
        super.release();
    }

}
