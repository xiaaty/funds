package com.gqhmt.log;

/**
 * Filename:    com.gqhmt.log.Log
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/16 16:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/16  于泳      1.0     1.0 Version
 */
public class Log implements  ILog {

    private String  appId = "";


    public void logMes(String model,Class tClass,Object object,int logLevel){
        switch (logLevel){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void logMes(String model, Object object, int logLevel) {
        this.logMes(model,null,object,logLevel);
    }


}
