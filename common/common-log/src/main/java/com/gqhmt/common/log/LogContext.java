package com.gqhmt.common.log;

/**
 * Filename:    com.gqhmt.log.initLogContext
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/17 11:06
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/17  于泳      1.0     1.0 Version
 */
public class LogContext {

    private final ILog log;

    private final static LogContext logContext = new LogContext();

    public static void initLogContext(String appId){
        Log log1 = (Log) logContext.log;
        log1.setAppId(appId);
    }

    private LogContext(){
        log = new Log();
    }



    public static ILog getCurrentLog(){
        return logContext.log;
    }
}
