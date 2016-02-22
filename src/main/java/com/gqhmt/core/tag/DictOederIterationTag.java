package com.gqhmt.core.tag;

import com.gqhmt.core.util.Application;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.*;

/**
 * Filename:    com.gqhmt.core.tag.DictOederIterationTag
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/15 09:52
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/15  于泳      1.0     1.0 Version
 */
public class DictOederIterationTag extends BodyTagSupport{
    private String var;
    private Iterator<?> iterator;
    private String dictOrder;
    private String separator;

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar(){
        return var;
    }

    public void setIterator(Iterator<?> iterator) {
        this.iterator = iterator;
    }


    @Override
    public int doAfterBody() throws JspException {
        if (this.process()) {
            return EVAL_BODY_AGAIN;
        } else {
            return EVAL_PAGE;
        }

    }

    @Override
    public int doStartTag() throws JspException {
        if (this.process())
            return EVAL_BODY_INCLUDE;
        else
            return EVAL_PAGE;
    }

    public void setDictOrder(String dictOrder) {
        this.dictOrder  = dictOrder;
        String dictOrderValue = Application.getInstance().getDictOrderValue(dictOrder);
        List<DictOrderTagEntity> list = new ArrayList<>();



        String[] order = dictOrderValue.split(",");
        for(String tmp:order){
            String value = Application.getInstance().getDictName(tmp);
            if(value == null || "".equals(value)){
                continue;
            }

            list.add(new DictOrderTagEntity(tmp,value));
        }

        this.iterator =list.iterator();

    }

    private boolean process(){
        if (null == this.iterator || !this.iterator.hasNext()) return  false;

        DictOrderTagEntity entity = (DictOrderTagEntity) iterator.next();
        pageContext.setAttribute(var,entity);
        return true;
    }


    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public class DictOrderTagEntity {
        private String key;
        private String value;

        public DictOrderTagEntity(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}


