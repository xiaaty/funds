package com.gqhmt.sys.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.beans
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/13 18:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/13  于泳      1.0     1.0 Version
 */
public class MenuFunc implements Serializable{

//    private final long

    private String funcId;
    private String funcName;
    private String funcUrl;
    private List<MenuFunc> child ;
    private boolean isChild;
    private String icoClass;

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    public List<MenuFunc> getChild() {
        return child;
    }

    public void setChild(List<MenuFunc> child) {
        this.child = child;
    }

    public void setIsChild(boolean isChild){
        this.isChild = isChild;
    }

    public boolean getIsChild(){
        return isChild;
    }

    public void addChild(MenuFunc func){
        child.add(func);
    }

    @Override
    public String toString() {
        return funcName+"/"+funcUrl+":"+child;
    }

    public String getIcoClass() {
        return icoClass;
    }

    public void setIcoClass(String icoClass) {
        this.icoClass = icoClass;
    }
}
