package com.gqhmt.common.log.lobback;

import ch.qos.logback.classic.Level;
import com.gqhmt.common.log.logback.LogbackSetting;
import org.testng.annotations.Test;

/**
 * Filename:    com.gqhmt.common.log.lobback.LogbackSettingTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/17 17:00
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/17  于泳      1.0     1.0 Version
 */
public class LogbackSettingTest {


    @Test
    public void setLevelTest(){
//        LogbackSetting.setLogContext("<configuration>" +
//                "    <appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\">" +
//                "        <encoder>" +
//                "            <pattern>%d %5p [%t] %c{1}:%M:%L - %m%n</pattern>" +
//                "        </encoder>" +
//                "    </appender>" +
//                "" +
//                "    <root level=\"info\">" +
//                "        <appender-ref ref=\"STDOUT\" />" +
//                "    </root>" +
//                "</configuration>");
        LogbackSetting.setLevel(Level.INFO_INT);

        LogbackSetting.logtest();


    }




}
