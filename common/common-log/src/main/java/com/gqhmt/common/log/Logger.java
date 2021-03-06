package com.gqhmt.common.log;

import ch.qos.logback.classic.Level;

/**
 * Filename:    Logger
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/16 14:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/16  于泳      1.0     1.0 Version
 */
public class Logger {

    private final static ILog log = LogContext.getCurrentLog();

//    public static void info(Object object) {
//
//    }

    //    public static void info(String model, Object ojb) {
//
//    }

    public static void info(Class tClass, Object object) {
        info(null,tClass,object);
    }


    public static void info(String model, Class tClass, Object object) {
        log.logMes(model,tClass,object, Level.INFO_INT);
    }

//    public static void warn(Object object) {
//    }

//    public static void warn(String model, Object ojb) {
//
//    }

    public static void warn(Class tClass, Object object) {
        warn(null,tClass,object);
    }



    public static void warn(String model, Class tClass, Object object) {
        log.logMes(model,tClass,object, Level.WARN_INT);
    }

//    public  static void error(Object object) {
//
//    }
//
//    public static void error(String model, Object ojb) {
//
//    }

    public static void error(Class tClass, Object object) {
        error(null,tClass,object);
    }



    public static void error(String model, Class tClass, Object object) {
        log.logMes(model,tClass,object, Level.ERROR_INT);
    }

//    public static void debug(Object object) {
//
//    }
//
//    public  static void debug(String model, Object ojb) {
//
//    }

    public  static void debug(Class tClass, Object object) {
        debug(null,tClass,object);
    }



    public  static void debug(String model, Class tClass, Object object) {
        log.logMes(model,tClass,object, Level.DEBUG_INT);
    }
}
