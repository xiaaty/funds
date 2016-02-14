package com.gqhmt.core.tag;

import com.gqhmt.core.util.Application;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Filename:    com.gqhmt.core.tag.DictViewTag
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/14 13:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/14  于泳      1.0     1.0 Version
 */
public class DictViewTag extends TagSupport {

    private String key;

    @Override
    public int doStartTag() throws JspException {


        try {
            JspWriter out = pageContext.getOut();
            out.print(Application.getInstance().getDictName(key));
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return super.SKIP_BODY;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
