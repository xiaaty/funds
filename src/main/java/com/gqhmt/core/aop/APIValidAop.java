package com.gqhmt.core.aop;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.APIValidUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Filename:    com.gqhmt.core.aop.APIValidAop
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:08
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
@Component
@Aspect
public class APIValidAop {

    public APIValidAop() {
    }

    @Pointcut("execution(* com.gqhmt.extServInter.service.*.impl.*.excute(..))")
    public void point(){

    }

    @Around("point()")
    public Object validAround(ProceedingJoinPoint joinPoint) throws FssException {
        Response response = null;
        String code = "0000";
        //校验
        Object[] objects = joinPoint.getArgs();
        Class class1 = (Class) joinPoint.getTarget();
        for(Object obj:objects){
            if(obj instanceof SuperDto){
                code = APIValidUtil.valid((SuperDto) obj);
            }
        }
        if(!"0000".equals(code)){
            response = new Response();
            response.setResp_code(code);
            return response;
        }
        try {
            response = (Response)joinPoint.proceed();
        } catch (Throwable throwable) {
            LogUtil.error(this.getClass(),throwable);
            code = "90099998";
        }

        return response;
    }


}
