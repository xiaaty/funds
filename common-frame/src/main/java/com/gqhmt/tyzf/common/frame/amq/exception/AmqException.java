package com.gqhmt.tyzf.common.frame.amq.exception;

/**
 * Created by zhou on 2016/10/20.
 * Description:mq异常类
 */
public class AmqException extends Exception {

    private static final long serialVersionUID = 1L;

    public AmqException() {
    }

    public AmqException(String message) {
        super(message);
    }

    public AmqException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmqException(Throwable cause) {
        super(cause);
    }
}
