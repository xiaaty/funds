package com.gqhmt.tyzf.common.frame.exception;

/**
 * Created by zhou on 2016/10/20.
 * Description:框架异常
 */
public class FrameException extends Exception {

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

}
