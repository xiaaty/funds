package com.gqhmt.core.util;/**
 * Created by yuyonf on 15/11/28.
 */

import java.util.*;

/**
 * Filename:    com.gqhmt.core.util.ResourceUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/28 17:17
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/28  于泳      1.0     1.0 Version
 */
public class ResourceUtil {

    public static String getValue(String className,String key){
        ResourceBundle resource = ResourceBundle.getBundle(className, Locale.SIMPLIFIED_CHINESE);
        return resource.getString(key);
    }

    public static Map<String,String> list(String className){
        ResourceBundle resource = ResourceBundle.getBundle(className, Locale.SIMPLIFIED_CHINESE);
        Enumeration<String> e = resource.getKeys();
        final  Map<String,String> propertiesMap = new HashMap<>();
        while (e.hasMoreElements()){
            String key = e.nextElement();
            String value = resource.getString(key);
            propertiesMap.put(key,value);
        }
        return propertiesMap;
    }
}
