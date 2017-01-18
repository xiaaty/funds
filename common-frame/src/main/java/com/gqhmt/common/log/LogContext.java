package com.gqhmt.common.log;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filename:    com.gqhmt.common.log.LogContext
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/1/17 11:08
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/1/17  于泳      1.0     1.0 Version
 */
public class LogContext {
    /**系统实例描述*/
    private String appInstDesc = null;

    protected String prefix = "";


    private void log( Object o, int logLevel) {
        Logger log = null;
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        if(stack.length == 0){
            log = LoggerFactory.getLogger(this.getClass());
        }else{

        }
        switch (logLevel){
            case Level.DEBUG_INT:
                if(o instanceof Exception){
                  log.debug(appInstDesc + o,o);
                  break;
                }
                log.debug(appInstDesc + o);
                break;
            case Level.INFO_INT:
                if(o instanceof Exception){
                    log.info(appInstDesc + o,o);
                    break;
                }
                log.info(appInstDesc + o);
                break;
            case Level.ERROR_INT:
                if(o instanceof Exception){
                    log.error(appInstDesc + o,o);
                    break;
                }
                log.error(appInstDesc + o);
                break;
        }

    }


}
