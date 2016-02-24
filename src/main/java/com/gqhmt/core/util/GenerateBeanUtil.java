package com.gqhmt.core.util;


import com.gqhmt.annotations.AutoDate;
import com.gqhmt.annotations.AutoDateType;
import com.gqhmt.annotations.AutoMapping;
import com.gqhmt.core.FssException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.core.util.GenericBeanUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/15 18:45
 * Description:
 * <p>
 *     实体bean生成,仅仅对数据实体bean有效,需要实现set get方法,
 *     对于业务类bean,此类不支持
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/15  于泳      1.0     1.0 Version
 */
public class GenerateBeanUtil {

    private static Map<String,Class> classMap = new ConcurrentHashMap<>();
    private static Map<String,Method> methodMap = new ConcurrentHashMap<>();
    private static Map<String,Field> fieldMap = new ConcurrentHashMap<>();


    /**
     * 生成类的实例化--数据实体bean,自动对存在注解的属性自动注入
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> T GenerateClassInstance(Class<T> tClass) throws Exception {
        try {
            T t = tClass.newInstance();
           List<Field> fields = getFields(tClass);
            for(Field field:fields){
                fieldMethod(tClass,field,t);
            }
            return t;
        } catch (InstantiationException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        } catch (IllegalAccessException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        }

        throw new Exception("Don't generate instance ");
    }

    /**
     * 生成类的实例化--数据实体bean,对来源类与生成类存在相同属性,自动获取该值,
     * <span>本功能请注意,属性相同,不一定表示为同一属性.如特殊,需要重新赋值</span>
     * @param tClass
     * @param sourceObj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T GenerateClassInstance(Class<T> tClass,Object  sourceObj) throws Exception {
        T t = GenerateClassInstance(tClass);

        Class<Object> sourceClass = (Class<Object>) sourceObj.getClass();
        List<Field> fields = getFields(sourceClass);
        for(Field field:fields){
            sourceFidleMethod(tClass,sourceClass,field,sourceObj,t);
        }
        return t;
    }

    /**
     * 复制类--数据实体bean,对来源类与生成类存在相同属性,自动获取该值,
     * <span>本功能请注意,属性相同,不一定表示为同一属性.如特殊,需要重新赋值</span>
     * @param targerObj
     * @param sourceObj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T GenerateClassInstance(Object targerObj,Object  sourceObj) throws Exception {
        Class tClass = targerObj.getClass();
        Class<Object> sourceClass = (Class<Object>) sourceObj.getClass();
        List<Field> fields = getFields(sourceClass);
        for(Field field:fields){
            sourceFidleMethod(tClass,sourceClass,field,sourceObj,targerObj);
        }
        return (T) targerObj;
    }

    /**
     * 对生产新的实例赋值
     * @param tClass
     * @param sourceClass
     * @param sourceField
     * @param sourceObj
     * @param targerObj
     */
    private static void sourceFidleMethod(Class tClass,Class sourceClass,Field sourceField,Object sourceObj,Object targerObj) throws FssException {
        Object value = getSourceFieldObjValue(sourceClass,sourceField,sourceObj);

        if(value == null) return;

        Field targerField = null;
        try {
            targerField = getField(tClass,sourceField.getName(),sourceField);
        } catch (FssException e) {
            return;
        }

        try {
            Method method = getFieldSetMethod(tClass,targerField);
            method.invoke(targerObj,value);
        } catch (Exception e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        }
    }

    private static Field getField(Class tClass, String name,Field sourceField) throws FssException {

        try {
            Field targetField = tClass.getDeclaredField(sourceField.getName());
            return targetField;
        } catch (NoSuchFieldException e) {
        }

        AutoMapping autoMapping = sourceField.getAnnotation(AutoMapping.class);
        if(autoMapping != null){
            String mappingValue = autoMapping.value();
            try {
                Field field = tClass.getDeclaredField(mappingValue);
                return field;
            } catch (NoSuchFieldException e) {
            }
        }

        throw new FssException("don't find field");
    }

    /**
     * 获取类的值,原始传过来的实例值,并赋予到需要创建的类上.
     * @param sourceClass
     * @param sourceField
     * @param sourceObj
     * @return
     */
    private static Object getSourceFieldObjValue(Class sourceClass,Field sourceField,Object sourceObj){
        String  name = sourceField.getName();
        String methodName = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
        try {
            //未来加 map存储
            Method method = sourceClass.getMethod(methodName);
            return  method.invoke(sourceObj);
        } catch (NoSuchMethodException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        } catch (InvocationTargetException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        } catch (IllegalAccessException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        }

        return null;
    }

    /**
     * 注解类型,field
     * @param tClass
     * @param field
     * @param targetObj
     */
    private static void fieldMethod(Class tClass,Field field,Object targetObj){
        Annotation [] annotations = field.getAnnotations();
        for(Annotation annotation:annotations){
//            System.out.println("注解:"+annotation.annotationType().getName());
            if(annotation.annotationType().getName().equals("com.gqhmt.annotations.AutoDate")){
                AutoDate autoDate = (AutoDate) annotation;
                fieldMethodAutoDate(tClass,field,targetObj,autoDate.type());
            }

        }

    }


    /**
     * autoDate注解自动注入值
     * @param tClass
     * @param field
     * @param targetObj
     * @param type
     */
    private static void fieldMethodAutoDate(Class tClass, Field field, Object targetObj, AutoDateType type){
        System.out.println(type);

        try{

            Date date = new Date();
            switch (type){
                case DATE_TIME:
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,date);
                    break;
                case DATE_CHAR_8:
                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,df.format(date));
                    break;
                case DATE_CHAR_10:
                    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,df1.format(date));
                    break;
                case TIME_CHAR_6:
                    DateFormat df2 = new SimpleDateFormat("hhmmss");
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,df2.format(date));
                    break;
                case TIME_CHAR_8:
                    DateFormat df3 = new SimpleDateFormat("hh:mm:ss");
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,df3.format(date));
                    break;
                case DATE_TIME_14:
                    DateFormat df4 = new SimpleDateFormat("yyyyMMddhhmmss");
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,df4.format(date));
                    break;
                case DATE:
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,new java.sql.Date(date.getTime()));
                    break;
                case TIME:
                    methodInvoke(getFieldSetMethod(tClass,field),targetObj,new java.sql.Time(date.getTime()));
                    break;
                default:
                    break;

            }
        }catch (Exception e){
            LogUtil.error(GenerateBeanUtil.class,e);
        }


    }

    /**
     * 获取method方法
     * @param tClass
     * @param field
     * @return
     * @throws Exception
     */
    private static Method getFieldSetMethod(Class tClass,Field field) throws Exception {
        String  name = field.getName();
        String methodName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
        try {
            //未来加 map存储
            Method method = tClass.getMethod(methodName,field.getType());
            return  method;
        } catch (NoSuchMethodException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        }

        throw  new Exception("Don't such method");
    }

    /**
     * method invoke
     * @param method
     * @param instance
     * @param parmam
     */
    private static void methodInvoke(Method method,Object instance,Object ... parmam){
        try {
            method.invoke(instance,parmam);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static List<Field> getFields(Class tClass){

        if(tClass == null) return null;

        List<Field> list = new ArrayList();

        Field[]  fields = tClass.getDeclaredFields();
        for(Field field:fields){
            if(!fieldMap.containsKey(tClass.getName()+"."+field.getName())){
                fieldMap.put(tClass.getName()+"."+field.getName(),field);
            }

            list.add(field);
        }

        Class superClass  = tClass.getSuperclass();
        if( superClass != null &&  !superClass.getName().equals("java.lang.Object")){
            list.addAll(getFields(superClass));
        }

        return list;

    }

}
