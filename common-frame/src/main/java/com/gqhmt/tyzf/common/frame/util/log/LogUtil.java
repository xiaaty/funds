package com.gqhmt.tyzf.common.frame.util.log;

import ch.qos.logback.classic.Level;
import com.gqhmt.tyzf.common.frame.common.Constants;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MsgConstants;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhou on 2016/10/26.
 * Description:
 */
public class LogUtil {

    /**系统实例描述*/
    private String appInstDesc = null;

    protected String prefix = "";

    Logger logger = null;

    static LogUtil logUtil = null;

    public LogUtil(String prefix) throws FrameException {
        this.prefix = prefix;
        if (logUtil != null) {
            throw new FrameException(FrameConstans.LOG_INIT_ERR + LogConstants.LOG_INIT_EXIST_ERRDESC);
        }
        try {
            init();
            logUtil = this;
        } catch (Exception e) {
            throw new FrameException(FrameConstans.LOG_INIT_ERR + LogConstants.LOG_INIT_NOTREADY_ERRDESC,e);
        }
    }

    public static LogUtil getInstance() {
        return logUtil;
    }

    private void init() throws FrameException {
        //系统编号
//        String appSysID = (String) ConfigManager.getInstance().get(ConfigConstants.ADAPTER_SYSID);
        //系统实例号
        String appInstID = (String) ConfigManager.getInstance().get(Constants.ADAPTER_INSTID);
        //系统名称
        String sysName = (String) ConfigManager.getInstance().get(Constants.ADAPTER_NAME);
        if (appInstID != null)
            appInstDesc = sysName + "_" + Constants.ADAPTER_INSTID_DESC + "_" + appInstID + ":";
        else
            appInstDesc = "";

        if (ConfigManager.getInstance().get(prefix + LogConstants.LOG_SYS_LOGGER) != null)
            logger = LoggerFactory.getLogger((String) (ConfigManager.getInstance().get(prefix + LogConstants.LOG_SYS_LOGGER)));
        else
            logger = null;
    }

    public void info(Object content) {
        if(content==null)
            return;
        if (logger != null && logger.isInfoEnabled()){
            log(logger, content, Level.INFO_INT);
        }
    }

    @SuppressWarnings("deprecation")
    public void info(Object content,Object classname) {
        if(content==null)
            return;
        if(classname==null)
            classname = "";
        if (logger != null && logger.isInfoEnabled()){
            log(logger, getClassName(classname), Level.INFO_INT);
        }
    }

    @SuppressWarnings("deprecation")
    public void debug(Object content) {
        if(content==null)
            return;
        if (logger != null && logger.isDebugEnabled()){
            if(content instanceof MsgObject){
                MsgObject msgObject = (MsgObject)content;
                String msgId = msgObject.getRequestMsgParameter(MsgConstants.MsgID);
                log(logger, content, Level.DEBUG_INT,msgId);
            }else
                log(logger, content, Level.DEBUG_INT);
        }
    }

    public void debug(Object content,Object mo) {
        if(content==null)
            content = "null";
        if(mo==null)
            mo = "null";
        if (logger != null && logger.isDebugEnabled()){
            if (mo instanceof MsgObject) {
                String msgId = ((MsgObject) mo).getRequestMsgParameter(MsgConstants.MsgID);
                log(logger, content, Level.DEBUG_INT,msgId);
                log(logger, mo, Level.DEBUG_INT,msgId);
            }else {
                log(logger, content, Level.DEBUG_INT);
                log(logger, mo, Level.DEBUG_INT);
            }
        }
    }

    public void debug(Object content,String msgId) {
        if(msgId==null)
            msgId = "null";
        if(content==null)
            content = "null";
        if (logger != null && logger.isDebugEnabled()){
            log(logger, content, Level.DEBUG_INT,msgId);
        }
    }

    public void error(Object content) {
        if(content==null)
            return;
        if (logger != null && logger.isErrorEnabled())
            log(logger, content, Level.ERROR_INT);
    }

    public void error(Object exception,String msgId) {
        if(exception==null)
            return;
        if(msgId==null)
            msgId = "null";
        if (logger != null && logger.isErrorEnabled()){
            log(logger, exception, Level.ERROR_INT,msgId);
        }
    }

    public void error(Object exception,Object mo) {
        if(exception==null)
            return;
        if(mo==null)
            mo = "null";
        if (logger != null && logger.isErrorEnabled()){
            if(mo instanceof MsgObject){
                MsgObject msgObject = (MsgObject)mo;
                String msgId = msgObject.getRequestMsgParameter(MsgConstants.MsgID);
                log(logger, mo, Level.ERROR_INT,msgId);
                log(logger, exception, Level.ERROR_INT,msgId);
            }else{
                log(logger, mo, Level.ERROR_INT);
                log(logger, exception, Level.ERROR_INT);
            }
        }
    }

    private void log(Logger log, Object o, int logLevel) {
        if (log == null || o == null)
            return;
        if (o instanceof Exception) {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + o, (Throwable) o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + o, (Throwable) o);
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + o, (Throwable) o);
                    break;
                default:
                    break;
            }
        } else if (o instanceof String) {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + (String) o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + ((String) o));
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + ((String) o));
                    break;
                default:
                    break;
            }
        }else if(o instanceof MsgObject){
            switch(logLevel){
                case Level.DEBUG_INT:
                    log.debug(appInstDesc+((MsgObject)o).XmltoString());
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc+((MsgObject)o).XmltoString());
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc+((MsgObject)o).XmltoString());
                    break;
                default:
                    break;
            }
        }else {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + o);
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + o);
                    break;
                default:
                    break;
            }
        }
    }

    private void log(Logger log, Object o, int logLevel, String msgId) {
        if (log == null || o == null)
            return;
        if (o instanceof Exception) {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + "[MsgID: " + msgId + "]  ", (Throwable) o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + "[MsgID: " + msgId + "]  ", (Throwable) o);
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + "[MsgID: " + msgId + "]  ", (Throwable) o);
                    break;
                default:
                    break;
            }
        } else if (o instanceof String) {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + "[MsgID: " + msgId + "]  " + (String) o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + "[MsgID: " + msgId + "]  " + ((String) o));
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + "[MsgID: " + msgId + "]  " + ((String) o));
                    break;
                default:
                    break;
            }
        }
        else if(o instanceof MsgObject){
            switch(logLevel){
                case Level.DEBUG_INT:
                    log.debug(appInstDesc+"[MsgID: "+msgId+"]\n"+((MsgObject)o).XmltoString());
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc+"[MsgID: "+msgId+"]\n"+((MsgObject)o).XmltoString());
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc+"[MsgID: "+msgId+"]\n"+((MsgObject)o).XmltoString());
                    break;
                default:
                    break;
            }
        }
        else {
            switch (logLevel) {
                case Level.DEBUG_INT:
                    log.debug(appInstDesc + "[MsgID: " + msgId + "]  " + o);
                    break;
                case Level.INFO_INT:
                    log.info(appInstDesc + "[MsgID: " + msgId + "]  " + o);
                    break;
                case Level.ERROR_INT:
                    log.error(appInstDesc + "[MsgID: " + msgId + "]  " + o);
                    break;
                default:
                    break;
            }
        }
    }

    private String getClassName(Object classname) {
        if (classname instanceof Class)
            return ((Class) classname).getName();
        else if (classname instanceof String)
            return (String) classname;
        else
            return classname.getClass().getName();
    }
}
