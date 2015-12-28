package com.gqhmt.fss.pay.core.Context;

import com.gqhmt.core.util.ResourceUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.fss.pay.core.Context.FssContext
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/28 14:53
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/28  于泳      1.0     1.0 Version
 */
public class FssContext {
    private static Map<String,String> configs=  new ConcurrentHashMap();

    private static Map<String,Map<String,String>> configMap=  new ConcurrentHashMap();
    private  static final String configFile = "config.FssContext";

    /**
     * 初始化配置工厂类
     */
    static{
        Map<String,String> map = ResourceUtil.list(configFile);
        configs.putAll(map);
        Set<String> set = map.keySet();
        for(String tmp :set){
            String [] t = tmp.split("\\.");
            Map<String,String> map1 = configMap.get(t[0]);
            if(map1 == null){
                map1 = new HashMap<>();
                configMap.put(t[0],map1);
            }
            map1.put(t[1],configs.get(tmp));

        }
    }

    public  static String getContextValue(String key){
        return configs.get(key);
    }

    public static Map<String,String> getContextMap(String key){
        Map<String,String> tmpMap = configMap.get(key);
        if(tmpMap == null){
            return null;
        }

        Map<String,String> newMap = new HashMap<>();

        newMap.putAll(tmpMap);
        return newMap;
    }
}
