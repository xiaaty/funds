package com.gqhmt.core.tag;

import org.apache.taglibs.standard.tag.common.fmt.FormatDateSupport;

import javax.servlet.jsp.JspTagException;
import java.util.Date;

/**
 * Filename:    com.gqhmt.core.tag.ForrmatDateTag
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/15 14:57
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/15  于泳      1.0     1.0 Version
 */
public class FormatDateTag extends FormatDateSupport {
    public FormatDateTag() {

    }

    public void setValue(Date value) throws JspTagException {
        this.value = value;
        if(this.type == null){
            setPattern();
        }


    }

    public void setType(String type) throws JspTagException {
        this.type = type;
        setPattern();
    }

    public void setDateStyle(String dateStyle) throws JspTagException {
        this.dateStyle = dateStyle;
    }

    public void setTimeStyle(String timeStyle) throws JspTagException {
        this.timeStyle = timeStyle;
    }

    public void setPattern(){
        if("date".equals(type)){
            this.pattern = "yyyy-MM-dd";
        }else if("time".equals(type)){
            this.pattern = "HH:mm:ss";
        }else {
            this.pattern = "yyyy-MM-dd HH:mm:ss";
        }
    }

    public void setTimeZone(Object timeZone) throws JspTagException {
        this.timeZone = timeZone;
    }
}
