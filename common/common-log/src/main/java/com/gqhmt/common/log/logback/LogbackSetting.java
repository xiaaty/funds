package com.gqhmt.common.log.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Filename:    LogbackSetting
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/17 16:51
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/17  于泳      1.0     1.0 Version
 */
public class LogbackSetting {

    public static void setLevel(int level){
        Logger logger = LoggerFactory.getLogger("ROOT");
        if(logger == null){

        }


    }


    public static void setLogContext(String context){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        try {
            InputStream is = new ByteArrayInputStream(context.getBytes("utf-8"));
            configurator.doConfigure(is);
        } catch (JoranException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }
}
