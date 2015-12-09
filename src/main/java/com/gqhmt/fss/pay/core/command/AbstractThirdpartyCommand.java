package com.gqhmt.fss.pay.core.command;


import com.gqhmt.fss.pay.core.configer.Config;
import com.gqhmt.fss.pay.exception.CommandParmException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuyonf on 15/3/29.
 */
public abstract class AbstractThirdpartyCommand implements ThirdpartyCommand {

    private static Map<String,Method> classMap = new ConcurrentHashMap<>();

    public void parsePublic(String apiName,Map map,Object... obj) throws CommandParmException{

        Set<String> publicSet  = (Set)getConfig().getValue("public");
        for(String s: publicSet){
            String noUse = (String)getConfig().getValue("public."+s+".noUse");
            if(noUse != null && noUse.contains(apiName)){
                continue;
            }
            Object value = getValue(apiName,"public",s,obj);
            if("null".equals(value)){
                break;
            }
            map.put(s,value);

        }
    }

    public void parseAPI(String apiName,Map map,Object... obj) throws CommandParmException{
        Set<String> apiSet = (Set)getConfig().getValue("api."+apiName);
        if(apiSet == null){
            return ;
        }
        for(String s: apiSet){
            Object value = getValue(apiName,"api."+apiName,s,obj);
            String must = (String)getConfig().getValue("api."+apiName+"."+s+".must");
            boolean isMust = false;
            if(must != null && Boolean.valueOf(must)){
                isMust = true;
            }
            if(isMust && value == null){
                throw new CommandParmException(s+"不允许为空");
            }
            if(value instanceof  Long || value instanceof  Integer){
                value = String.valueOf(value);
            }
            map.put(s,value);
        }

    }


    private Object getValue(String apiName,String apiKey,String keyName,Object... obj) throws CommandParmException{
        String source = (String)getConfig().getValue(apiKey+"."+keyName+".source");
        if(source == null){
            source = "class";
        }
        if("fixed".equals(source) || "properties".equals(source)){
            return getFixedValue(apiKey,keyName,obj);
        }else if("systemTimeLongValue".equals(source)){
            return getSystemTimeLongValue();
        }else if("api".equals(source)){
            return getAPIValue(apiName,apiKey, keyName, obj);
        }else if("string".equals(source)){
            return getStringValue(apiKey, keyName,obj);
        }else if("class".equals(source)){
            return getClassValue(apiKey,keyName,obj);
        }else if("list".equals(source)){
            return getListValue(apiKey,keyName,obj);
        }else if("mapKey".equals(source)){
            return getMapKey(apiKey,keyName,obj);
        }else if("null".equals(source)){
            return "";
        }else if("rsa".equals(source)){
            return getRsaValue(apiKey,keyName,obj);
        }else if("rsaMap".equals(source)){
           return getRsaMapValue(keyName,obj);
        }else if("amount".equals(source)){
            return getAmountValue(apiKey,keyName,obj);
        }else if("amountToLong100".equals(source)){
            return getAmountValueToLong100(apiKey,keyName,obj);
        }else if("doubleAmount".equals(source)){
            return getDoubleAmountValueT(apiKey,keyName,obj);
        }else if("systemDate".equals(source)){
            return new Date();
        }else if("map".equals(source)){
            return getMapValue(apiKey,keyName,obj);
        }else if("doubleAmountToLong100".equals(source)){
            return getDoubleAmountValueToLong100(apiKey, keyName, obj);
        }else if("listSingle".equals(source)){
            return getListSingle(apiKey, keyName, obj);
        }

        return null;
    }

    private Object getListSingle(String apiKey,String keyName,Object... obj) throws CommandParmException{
        Set<String> keySet = (Set)getConfig().getValue(apiKey+"."+keyName+"");
        List<Map<String,Object>> list = new ArrayList<>();
        for(String k:keySet){
            Map<String,Object> map = new HashMap<>();
            Object value  = getValue(null,apiKey+"."+keyName,k,obj);
            map.put(k,value);
            list.add(map);
        }

        return list;
    }

    private Object getMapKey(String apiKey,String keyName,Object... obj)throws CommandParmException{
        Map map = (Map)getParamValue(apiKey,keyName,obj);
        if(map == null){
            for(Object o:obj){
                if(o instanceof  Map){
                    return o;
                }
            }
        }
        if (map == null){
            throw new CommandParmException("参数错误");
        }

        Set<String> keySet = (Set)getConfig().getValue(apiKey+"."+keyName+"");
        String keyAttr = "";
        String valueAttr="";
        for(String s:keySet){
            String t =(String)getConfig().getValue(apiKey+"."+keyName+"."+s+".source");
            if("key".equals(t)){
                keyAttr = s;
            }else if("value".equals(t)){
                valueAttr = s;
            }
        }

        Set<String > set = map.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for(String s:set){
            Map<String,Object> newMap = new HashMap<>();
            newMap.put(keyAttr,s);
            newMap.put(valueAttr,map.get(s));
            list.add(newMap);
        }
        return list;

    }

    private String getAmountValue(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Object object = getParamValue(apiKey,keyName,obj);
        if(object == null){
            for(Object o:obj){
                if(o instanceof  BigDecimal){
                    return ((BigDecimal)o).toPlainString();
                }
            }
        }

        BigDecimal amount =(BigDecimal)object;
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new CommandParmException("交易金额必须大于0");
        }

        return amount.toPlainString();
    }

    private String getDoubleAmountValueToLong100(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Object object = getParamValue(apiKey,keyName,obj);
        BigDecimal amount = BigDecimal.ZERO;
        if(object == null){
            for(Object o:obj){
                if(o instanceof  Double){
                    amount =  new BigDecimal((Double)o);
                }
            }
        }else{
            amount =  new BigDecimal((Double)object);
        }

        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new CommandParmException("交易金额必须大于0");
        }

        BigDecimal decimal = amount.multiply(new BigDecimal("100"));

        return decimal.toPlainString();
    }


    private String getAmountValueToLong100(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Object object = getParamValue(apiKey,keyName,obj);
        BigDecimal amount = BigDecimal.ZERO;
        if(object == null){
            for(Object o:obj){
                if(o instanceof  BigDecimal){
                    amount =  ((BigDecimal)o);
                }
            }
        }else{
            amount = (BigDecimal)object;
        }


        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new CommandParmException("交易金额必须大于0");
        }
        BigDecimal decimal = amount.multiply(new BigDecimal("100"));
        return String.valueOf(decimal.longValue());
    }

    private String getDoubleAmountValueT(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Object object = getParamValue(apiKey,keyName,obj);
        BigDecimal amount = BigDecimal.ZERO;
        if(object == null){
            for(Object o:obj){
                if(o instanceof  Double){
                    amount =  new BigDecimal((Double)o);
                }
            }
        }else{
            amount =  new BigDecimal((Double)object);
        }

        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new CommandParmException("交易金额必须大于0");
        }
        return amount.toPlainString();
    }

    private  Map<String,Object> getMapValue(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Set<String> keySet = (Set)getConfig().getValue(apiKey+"."+keyName+"");
        String muli = (String)getConfig().getValue(apiKey+"."+keyName+".muli");
        boolean isMuli = false;
        if(muli!= null && "true".equals(muli)){
            isMuli = true;
        }
        Object object = getParamValue(apiKey,keyName,obj);
        Map<String,Object> map = new HashMap<>();
        for(String k:keySet){
//            Object value = "";
//            if(isMuli){
//                value = getValue(null,apiKey+"."+keyName,k,obj);
//            }else{
//                value  = getValue(null,apiKey+"."+keyName,k,object);
//            }
//            if(value instanceof  Long || value instanceof  Integer){
//                value = String.valueOf(value);
//            }
//            map.put(k,value);
        }

        return map;
    }

    private  List<Map<String,Object>> getListValue(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        Set<String> keySet = (Set)getConfig().getValue(apiKey+"."+keyName+"");
        String muli = (String)getConfig().getValue(apiKey+"."+keyName+".muli");
        boolean isMuli = false;
        if(muli!= null && "true".equals(muli)){
            isMuli = true;
        }
        Object object = getParamValue(apiKey,keyName,obj);
        List<Map<String,Object>> list = new ArrayList<>();
        if(object instanceof  List){
            for(Object o:(List)object){
                Map<String,Object> map = new HashMap<>();
                for(String k:keySet){
                    //代扣实现，传递参数修改，后期调整看看那个接口还会出问题，再定实现策略
                    String value  = (String)getValue(null,apiKey+"."+keyName,k,o);
                    map.put(k,value);
                }
                list.add(map);
            }

        }else{
            Map<String,Object> map = new HashMap<>();
            for(String k:keySet){
                Object value = "";
                if(isMuli){
                    value = getValue(null,apiKey+"."+keyName,k,obj);
                }else{
                    value  = getValue(null,apiKey+"."+keyName,k,object);
                }
                if(value instanceof  Long || value instanceof  Integer){
                    value = String.valueOf(value);
                }
                map.put(k,value);
            }
            list.add(map);
        }

        return list;
    }

    private  String getRsaValue(String apiKey,String keyName, Object... obj){
        Object object = getParamValue(apiKey,keyName,obj);
        String tmp = "";
        if(object != null){
            tmp  = (String)object;
        }
        if(object == null){
            for(Object o:obj){
                if(o instanceof  String){
                    tmp =  (String)o;
                    break;
                }
                if(o instanceof  Map){
                    tmp =(String)((Map) o).get(keyName);
                    break;
                }
            }
        }

//        return RsaUtil.encrypt(tmp, CryptUtil.DQ_PUBLIC_KEY);
        return "";
    }

    private  String getStringValue(String apiKey,String keyName,Object ... obj) throws CommandParmException{
        if(obj == null){
            throw new CommandParmException("参数错误");
        }
        Object classInstance = getParamValue(apiKey,keyName,obj);
        if(classInstance == null){
            for(Object o:obj){
                if(o instanceof  String){
                    return (String)o;
                }
            }
        }
        return (String)classInstance;
    }

    private  String getRsaMapValue( String keyName, Object... obj){
        Map map  = null;
        for(Object o:obj){
            if(o instanceof  Map){
                map = (Map)o;
                break;
            }
        }
//        if(map != null){
////            return RsaUtil.encrypt((String)map.get(keyName), CryptUtil.DQ_PUBLIC_KEY);
//        }

        return null;

    }

    private Object getClassValue(String apiKey, String keyName, Object... obj) throws CommandParmException{
        String className = (String)getConfig().getValue(apiKey+"."+keyName+".class");
        String key = (String)getConfig().getValue(apiKey+"."+keyName+".key");
        if(obj == null){
            throw new CommandParmException("参数错误");
        }
        Object classInstance = getParamValue(apiKey,keyName,obj);

        if(classInstance == null){

            for(Object o:obj){
                if(o == null){
                    continue;
                }
                if(o.getClass().getName().equals(className)){
                    classInstance = o;
                    break;
                }
            }
        }

        if(key == null){
            return "";
        }
        if(key.contains(".")){
          return  getMulipClassValue(className,key,classInstance);
        }
        return getSingleClassValue(className,key,classInstance);
    }

    public Object getSingleClassValue(String className,String key,Object obj){
        try {
            Method method = classMap.get(className+"."+key);
            if(method == null){
                Class class1 = Class.forName(className);
                method = class1.getMethod("get"+key.substring(0,1).toUpperCase()+key.substring(1));
                if(method != null){
                    classMap.put(className+"."+key,method);
                }
            }
            if(obj != null){
                return method.invoke(obj);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object getMulipClassValue(String className,String key,Object obj){
        String[] keySplit = key.split("\\.");
        String ccName=className;
        Object oo = obj;
        Object result = null;
        for(String keyNode:keySplit){
            result = getSingleClassValue(ccName,keyNode,oo);
            if(result == null){
                break;
            }

            ccName = result.getClass().getName();
            oo = result;
        }

        return result;
    }

    private String getAPIValue(String apiName,String apiKey, String keyName, Object... obj){
        String apiname = (String)getConfig().getValue(apiKey+"."+keyName+".api_name");
        obj = addParam(apiName,obj);
        return api(apiname,keyName,obj);
    }

    private String getFixedValue(String apiKey, String keyName, Object[] obj) {
        return (String)getConfig().getValue(apiKey+"."+keyName+".value");
    }

    private String getSystemTimeLongValue() {
        return String.valueOf(System.currentTimeMillis());
    }


    public  abstract Config getConfig();

    public abstract String api(String apiName,String keyName,Object... obj);

    private boolean check(Object... obj){
        return true;
    }

    protected Object[] addParam(Object o,Object ... objects){
        if(objects == null){
            objects = new Object[1];
            objects[0] = o;
        }else{
            Object[] oldObj = objects;
            objects = new Object[objects.length+1];
            for(int i = 0;i<oldObj.length;i++){
                objects[i] = oldObj[i];
            }
            objects[objects.length-1] = o;
        }

        return objects;
    }

    public Object getParamValue(String apiKey,String keyName,Object ... obj){
        String source = (String)getConfig().getValue(apiKey+"."+keyName+".paramIndex");
        if(source == null || "".equals(source)){
            return null;
        }
        int index  = Integer.parseInt(source);
        Object object = null;
        if(obj.length>index){
            object = obj[index];
        }
        return object;
    }
}
