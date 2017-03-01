package com.gqhmt.common.log;

import ch.qos.logback.classic.Level;
import org.slf4j.*;

/**
 * Filename:    Log
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

    private String  appId = "System";

    public void setAppId(String appId){
        this.appId = appId;
    }

    public void logMes(String model,Class tClass,Object object,int logLevel){

        if(model == null || "".equals(model)){
            model = "other";
        }

        org.slf4j.Logger logger = LoggerFactory.getLogger(tClass);

        switch (logLevel){
            case Level.INFO_INT | Level.ALL_INT:
                if(!logger.isInfoEnabled()) break;

                if(object instanceof  Exception){
                    Exception e = (Exception) object;
                    logger.info(appId+" ["+model+"] "+""+e.getMessage(),e);
                }else{
                    logger.info(appId+" ["+model+"] "+""+object);
                }

                break;
            case Level.WARN_INT | Level.ALL_INT:
                if(!logger.isWarnEnabled()) break;

                if(object instanceof  Exception){
                    Exception e = (Exception) object;
                    logger.warn(appId+" ["+model+"] "+""+e.getMessage(),e);
                }else{
                    logger.warn(appId+" ["+model+"] "+""+object);
                }
                break;
            case Level.ERROR_INT | Level.ALL_INT:
                if(!logger.isWarnEnabled()) break;

                if(object instanceof  Exception){
                    Exception e = (Exception) object;
                    logger.error(appId+" ["+model+"] "+""+e.getMessage(),e);
                }else{
                    logger.error(appId+" ["+model+"] "+""+object);
                }
                break;
            case Level.DEBUG_INT | Level.ALL_INT:
                if(!logger.isDebugEnabled()) break;

                if(object instanceof  Exception){
                    Exception e = (Exception) object;
                    logger.debug(appId+" ["+model+"] "+""+e.getMessage(),e);
                }else{
                    logger.debug(appId+" ["+model+"] "+""+object);
                }
                break;

        }
    }


}
