package com.gqhmt.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {

	@SuppressWarnings("rawtypes")
	public static void info(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isInfoEnabled()){
			log.info(message);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void info(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isInfoEnabled()){
			log.info(message,t);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void debug(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isDebugEnabled()){
			log.debug(message);
			
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void debug(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isDebugEnabled()){
			log.debug(message,t);
			
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void error(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isErrorEnabled()){
			log.error(message);
			
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void error(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isErrorEnabled()){
			log.error(message,t);
			
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void fatal(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isFatalEnabled()){
			log.fatal(message);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void fatal(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isFatalEnabled()){
			log.fatal(message,t);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void trace(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isTraceEnabled()){
			log.trace(message);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void trace(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isTraceEnabled()){
			log.trace(message,t);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void warn(Class class1,Object message){
		Log log =  LogFactory.getLog(class1);
		if(log.isWarnEnabled()){
			log.warn(message);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void warn(Class class1,Object message,Throwable t){
		Log log =  LogFactory.getLog(class1);
		if(log.isWarnEnabled()){
			log.warn(message,t);
		}
		
	}
}
