package com.gqhmt.core.aop;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.util.StringUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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
    public Object validAround(ProceedingJoinPoint joinPoint) {

        Response response = null;
        String code = "90099999";
        SuperDto dto = null;

        try {
            dto = getArgsDto(joinPoint);
            Object targetClass = joinPoint.getTarget();
            String methodName = joinPoint.getSignature().getName();
            validMch(targetClass,methodName,dto);       //校验商户
            validData(dto);
            //生成交易订单
            response = (Response)joinPoint.proceed();
        } catch (Throwable throwable) {
            LogUtil.error(this.getClass(),throwable);
            String codeTmp = throwable.getMessage();
            String codeValue = Application.getInstance().getDictName(codeTmp == null?"":codeTmp);
            if(codeValue != null && !"".equals(codeValue)){
                code = codeTmp;
            }
        }

        if(response == null){
            try {
                response = GenerateBeanUtil.GenerateClassInstance(Response.class,dto);
                response.setResp_code(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                GenerateBeanUtil.GenerateClassInstance(response,dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 数据校验
     * @param dto
     */
    private void validData(SuperDto dto) {
        Class<SuperDto> dtoClass = (Class<SuperDto>) dto.getClass();
        Class<SuperDto> superDtoClass = (Class<SuperDto>) dtoClass.getSuperclass();
        Field[] fields  = dtoClass.getDeclaredFields();
        Field[] superFields= superDtoClass.getDeclaredFields();


    }




    /**
     * 商户校验
     * @param obj
     * @param method
     * @param dto
     * @return
     * @throws FssException
     */
    private String validMch(Object obj ,String method,SuperDto dto) throws FssException {
        String  result = "90099999";
        String  mchn = "";
        try {
            Class superDtoClass = getEntityClass(dto,SuperDto.class);
            Field superField= superDtoClass.getDeclaredField("mchn");
            //校验是否为空
            validIsNull(superDtoClass,superField,dto,"getMchn");
            //校验权限
            //校验ip
        } catch (NoSuchFieldException e) {
            throw  new FssException("90099998",e);
        }


        return result;
    }

    /**
     * 空值校验
     * @param superDtoClass
     * @param superField
     * @param obj
     * @param methodName
     * @throws FssException
     */
    private void validIsNull(Class superDtoClass, Field superField,Object obj,String methodName) throws FssException {
        APIValidNull api = superField.getAnnotation(APIValidNull.class);
        if(api != null ){
            try {
                String value = (String) MethodUtils.invokeMethod(obj,methodName,null);
                if( StringUtils.isEmpty(value)){
                    throw  new FssException(api.errorCode());
                }
            } catch (NoSuchMethodException e) {
                throw new FssException("90099998",e);
            } catch (IllegalAccessException e) {
                throw new FssException("90099998",e);
            } catch (InvocationTargetException e) {
                throw new FssException("90099998",e);
            }
        }

    }


    /**
     * 获取类类型
     * @param obj
     * @param target
     * @return
     * @throws FssException
     */

    public Class getEntityClass(Object obj,Class target) throws FssException {
        Class orgClass = obj instanceof Class ? (Class) obj: obj.getClass();
        if(target == null){
            return orgClass;
        }else if( target.getName().equals(orgClass.getName())){
            return orgClass;
        }else if("java.lang.Object".equals(orgClass.getName())){
            throw  new FssException("90099998");
        }else{
            return this.getEntityClass(orgClass.getSuperclass(),target);
        }
    }

    /**
     * 获取参数dto,如果不存在,就返回系统异常
     * @param joinPoint
     * @return
     * @throws FssException
     */

    private SuperDto getArgsDto(ProceedingJoinPoint joinPoint) throws FssException {
        Object[] objects = joinPoint.getArgs();
        for(Object obj:objects){
            if(obj instanceof SuperDto){
                return (SuperDto) obj;
            }
        }
        throw new FssException("90099998");
    }
}