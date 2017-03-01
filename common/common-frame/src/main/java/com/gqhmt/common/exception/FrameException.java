package com.gqhmt.common.exception;

/**
 * Filename:    com.gqhmt.common.exception.FrameException
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/21 16:12
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/21  于泳      1.0     1.0 Version
 */
public class FrameException extends ApplicationException{

    private String msgId;

    public FrameException() {
        super();
    }

    public FrameException(String message){
        super(message);
    }

    public FrameException(Throwable cause) {
        super(cause);
    }

    public FrameException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 平台异常，定位报文错误
     * @param message 异常信息
     * @param cause 异常链
     * @param msgId 报文Id
     */
    public FrameException(String message, Throwable cause, String msgId){
        this(message, cause);
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
