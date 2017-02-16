package com.gqhmt.log;

/**
 * Filename:    com.gqhmt.log.LogConstants
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/16 16:18
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/16  于泳      1.0     1.0 Version
 */
public class LogConstants {

    private LogConstants(){
        throw new IllegalAccessError("Utility class");
    }

    public static final String LOG_CONFIGURE_FILE = ".ConfigureFile";
    public static final String LOG_LOGGER = ".Logger";
    public static final String LOG_SYS_LOGGER = ".SysLogger";
    public static final String LOG_BIZ_LOGGER = ".BizLogger";

    public static final String LOG_INIT_EXIST_ERRDESC = "初始化日志组件失败:不应多次启动日志组件。";
    public static final String LOG_INIT_NOTREADY_ERRDESC = "初始化日志组件失败：未正确配置";
}
