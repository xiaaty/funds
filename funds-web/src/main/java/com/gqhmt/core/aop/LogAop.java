package com.gqhmt.core.aop;

import com.gqhmt.core.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Filename:    com.gqhmt.core.aop.LogAop
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 15:26
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
@Aspect
@Component
public class LogAop {

    private final static ThreadLocal<Long> startTime = new ThreadLocal<>();

    public LogAop(){
    }

    @Pointcut("execution(* com.gqhmt.fss.architect.*.service.*(..))")
    public void point(){

    }

    @Before("point()")
    public void log(JoinPoint joinPoint){

        if(LogUtil.isDebug(this.getClass())){
            startTime.set(new Date().getTime());
        }
        LogUtil.info(this.getClass(),"aop");
        LogUtil.info(joinPoint.getTarget().getClass(),"fss-method:"+joinPoint.getSignature());
    }

    @After("point()")
    public void logEnd(JoinPoint joinPoint){
        if(LogUtil.isDebug(this.getClass())){
            long start =  startTime.get();
            long end = new Date().getTime();
            long time = end - start;
            LogUtil.debug(this.getClass(),"method-time:"+Thread.currentThread()+":"+joinPoint.getSignature()+":"+time);
        }


    }

}
