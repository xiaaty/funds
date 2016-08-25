package com.gqhmt.core.aop;

import com.gqhmt.core.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Calendar;

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

    @Pointcut("execution(* com.gqhmt.*.architect.*.service.*.*(..))")
    public void point(){

    }

    @Around("point()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        Long startTime = Calendar.getInstance().getTimeInMillis();
        Object targetClass = null;
        String methodName = null;
        Object[] objects = null;
        try {
//            objects = joinPoint.getArgs();
            targetClass = joinPoint.getTarget();
            methodName = joinPoint.getSignature().getName();
            obj = joinPoint.proceed();
        }catch (Throwable e){
            LogUtil.error(this.getClass(),e);
            throw e;
        }

        Long endTime = Calendar.getInstance().getTimeInMillis();
        LogUtil.info(targetClass.getClass(),"方法执行:"+methodName+":执行时间:"+(endTime-startTime));

        return obj;
    }

//    public void logEnd(JoinPoint joinPoint){
//        if(LogUtil.isDebug(this.getClass())){
//            long start =  startTime.get();
//            long end = new Date().getTime();
//            long time = end - start;
//            LogUtil.debug(this.getClass(),"method-time:"+Thread.currentThread()+":"+joinPoint.getSignature()+":"+time);
//        }
//
//
//    }

}
