package com.gqhmt.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.core.util.Logger
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 13:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
public class LogUtil {

    private final static Map<String, Logger> loggerMap  = new ConcurrentHashMap();

    public final static void info(Class class1,String message){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.info(message);

    }

    public final static void info(Class class1,String message,Throwable throwable){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.info(message,throwable);

    }

    public final static void debug(Class class1,String message){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.debug(message);
    }

    public final static void debug(Class class1,String message,Throwable throwable){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.debug(message,throwable);
    }

    public final static void error(Class class1,String message){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.error(message);
    }

    public final static void error(Class class1,String message,Throwable throwable){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.error(message,throwable);
    }

    public final static void error(Class class1,Throwable throwable){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        logger.error(throwable.getMessage(),throwable);
    }

    public final static boolean isDebug(Class class1){
        Logger logger = loggerMap.get(class1.getName());
        if(logger == null){
            logger = LoggerFactory.getLogger(class1);
            loggerMap.put(class1.getName(),logger);
        }

        return logger.isDebugEnabled();
    }






}
