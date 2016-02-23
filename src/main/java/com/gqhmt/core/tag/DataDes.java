package com.gqhmt.core.tag;

import com.gqhmt.core.util.LogUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Filename:    com.gqhmt.core.tag.DataDes
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/23 14:16
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/23  于泳      1.0     1.0 Version
 */
public class DataDes extends TagSupport{

    private String value;

    @Override
    public int doStartTag() throws JspException {

        String  result="无";

        if(value != null){
            if(value.length()>11){
                result = value.substring(0,4)+"*****"+value.substring(value.length()-5);
            }else if(value.length() == 11){
                result = value.substring(0,2)+"*****"+value.substring(value.length()-4);
            }else if(value.length() > 6){
                result = value.substring(0,2)+"*****"+value.substring(value.length()-3);
            }else{
                result = value;
            }
        }

        try {
            JspWriter out = pageContext.getOut();
            out.print(result);
            out.flush();

        } catch (IOException ex) {
            LogUtil.error(this.getClass(),ex);
        }
        return super.SKIP_BODY;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
