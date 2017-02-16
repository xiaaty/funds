package com.gqhmt.log;

/**
 * Filename:    com.gqhmt.log.Ilogger
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/16 10:52
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/16  于泳      1.0     1.0 Version
 */
public interface ILog {

     void logMes(String Model,Class tClass,Object object,int logLevel);

     void logMes(String Model,Object object,int logLevel);

//    void info(Object object);
//
//    void info(Class tClass,Object object);
//
//    void info(String model,Object ojb);
//
//    void info(String model,Class tClass,Object ojb);
//
//    void warn(Object object);
//
//    void warn(Class tClass,Object object);
//
//    void warn(String model,Object ojb);
//
//    void warn(String model,Class tClass,Object ojb);
//
//    void error(Object object);
//
//    void error(Class tClass,Object object);
//
//    void error(String model,Object ojb);
//
//    void error(String model,Class tClass,Object ojb);
//
//    void debug(Object object);
//
//    void debug(Class tClass,Object object);
//
//    void debug(String model,Object ojb);
//
//    void debug(String model,Class tClass,Object ojb);
}
