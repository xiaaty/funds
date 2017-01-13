package com.gqhmt.tyzf.common.frame.util.log;

/**
 * Created by zhou on 2016/10/21.
 * Description:
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
