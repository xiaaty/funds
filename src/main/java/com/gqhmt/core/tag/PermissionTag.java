package com.gqhmt.core.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Filename:    com.gqhmt.core.tag.PermissionTag
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/23 14:42
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  于泳      1.0     1.0 Version
 */
public class PermissionTag extends TagSupport {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        boolean result = false;


        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();// 通过成员变量获取HttpServletRequest对象
        Object obj = request.getSession().getAttribute("permissionList");
        if(null != obj) {
            List<String> permissionList = (List<String>) obj;
            for (String permission : permissionList) {
                if(name.equals(permission)) {
                    result = true;
                    break;
                }
            }
        }
        return result? EVAL_BODY_INCLUDE : SKIP_BODY;//EVAL_BODY_INCLUDE代表执行自定义标签中的内容，SKIP_BODY代表不执行自定义标签中的内容。
    }
}
