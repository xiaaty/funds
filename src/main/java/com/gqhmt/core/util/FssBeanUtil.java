package com.gqhmt.core.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.core.util.FssBeanUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/31 11:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/31  于泳      1.0     1.0 Version
 */
public class FssBeanUtil extends BeanUtils {
//    private static Map<String,Method> methodMap = new ConcurrentHashMap<>();
    private static Map<String,PropertyDescriptor> propertyDescriptorMap= new ConcurrentHashMap();
    private static Map<String,List<PropertyDescriptor>> propertyDescriptorListMap = new ConcurrentHashMap<>();
    private static Map<String,Field> fieldMap = new ConcurrentHashMap<>();
    private static Map<String,List<Field>> fieldListMap = new ConcurrentHashMap<>();



    public static Object methodInvoke(Class class1,String methodName,Object instance,Object ... obj){
        Method method = null;
        if(obj != null){
            Class[] paramTypes = new Class[obj.length];
            for(int i= 0; i < obj.length;i++){
                Object object = obj[i];
                paramTypes[i] = object.getClass();
            }

            method = findMethod(class1,methodName,paramTypes);

        }else {
            method = findMethodWithMinimalParameters(class1,methodName);
        }

        if(method == null){
            return  null;
        }else {
            try {
                return method.invoke(instance,obj);
            } catch (IllegalAccessException e) {
                LogUtil.error(FssBeanUtil.class,e);
            } catch (InvocationTargetException e) {
                LogUtil.error(FssBeanUtil.class,e);
            }

            return  null;
        }
    }


    public static Method findMethodNew(Class class1,String methodName,Class... paramTypes){
        return findMethod(class1,methodName,paramTypes);
    }


    public static Field findField(Class class1,String name){
        Field field = fieldMap.get(class1.getName()+"."+name);
        if(field == null){
            try {
                Field field1 = class1.getDeclaredField(name);
                fieldMap.put(class1.getName()+"."+name,field1);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }

        return field;
    }


    public static List<Field> findField(Class class1){
        List<Field> list = fieldListMap.get(class1.getName());
        if(list == null){
            list = new ArrayList<>();
            synchronized (list){
                Field[] fields = class1.getDeclaredFields();
                for(Field field : fields){
                    list.add(field);
                    if(fieldMap.get(class1.getName()+"."+field.getName()) == null){
                        fieldMap.put(class1.getName()+"."+field.getName(),field);
                    }
                }

                if(!class1.getSuperclass().getName().equals("java.lang.Object")){
                    list.addAll(findField(class1.getSuperclass()));
                }
            }
            fieldListMap.put(class1.getName(),list);
        }
        return list;
    }

    public static PropertyDescriptor FindPropertyDescriptor(Class class1,String name){
        PropertyDescriptor propertyDescriptor = propertyDescriptorMap.get(class1.getName()+"."+name);
        if(propertyDescriptor == null){
            propertyDescriptor = getPropertyDescriptor(class1,name);
            propertyDescriptorMap.put(class1.getName()+"."+name,propertyDescriptor);
        }
        return propertyDescriptor;
    }

    public static List<PropertyDescriptor> FindPropertyDescriptor(Class class1){
        List<PropertyDescriptor> list = propertyDescriptorListMap.get(class1.getName());
        if(list == null){
            list = new ArrayList<>();
            synchronized(list){
                PropertyDescriptor[] propertyDescriptor = getPropertyDescriptors(class1);
                for(PropertyDescriptor propertyDescriptor1 : propertyDescriptor){
                    list.add(propertyDescriptor1);
                    if(propertyDescriptorMap.get(class1.getName()+"."+propertyDescriptor1.getName()) == null){
                        propertyDescriptorMap.put(class1.getName()+"."+propertyDescriptor1.getName(),propertyDescriptor1);
                    }
                }

//                if(!class1.getSuperclass().getName().equals("java.lang.Object")){
//                    list.addAll(FindPropertyDescriptor(class1.getSuperclass()));
//                }

                propertyDescriptorListMap.put(class1.getName(),list);
            }
        }

        return list;
    }


}
