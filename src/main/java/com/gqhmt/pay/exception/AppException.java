package com.gqhmt.pay.exception;

/**
 * 业务异常基类.
 * @author yangfei
 */
@SuppressWarnings("serial")
public class AppException extends RuntimeException {
	public static final String NO_RIGHTS_TO_ACCESS="no.rights.to.access";
	private String errorCode="";

	public AppException() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public AppException(String message) {
		super(message);
		this.errorCode=message;
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode=message;		
	}

	public AppException(Throwable cause) {
		super(cause);
	}
}
