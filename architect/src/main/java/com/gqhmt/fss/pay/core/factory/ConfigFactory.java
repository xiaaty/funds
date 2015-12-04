package com.gqhmt.fss.pay.core.factory;

import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.fss.pay.core.configer.Config;
import com.gqhmt.fss.pay.exception.PayChannelNotSupports;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.thirdparty.factory
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/4/20 10:53
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/4/20  于泳      1.0     1.0 Version
 */
public class ConfigFactory {
    public static ConfigFactory factory = new ConfigFactory();
    private static Map<Integer,Config> configs=  new ConcurrentHashMap();

    private  static final String configFile = "config.payChannel";

    /**
     * 初始化配置工厂类
     */
    static{
        Map<String,String> map = ResourceUtil.list(configFile);
        Set<String> set = map.keySet();
        for(String tmp :set){
            String  className = map.get(tmp);
            try {
                Class aClass = Class.forName(className);
                Config config = (Config) aClass.newInstance();
                configs.put(Integer.parseInt(tmp.substring(0,tmp.lastIndexOf(".")+1)),config);
            } catch (Exception e) {
                configs.put(Integer.parseInt(tmp.substring(0,tmp.lastIndexOf(".")+1)),null);
            }
        }
    }

    private ConfigFactory(){

    }

    public static ConfigFactory getConfigFactory(){
        return factory;
    }

    public Config getConfig(int type) throws PayChannelNotSupports {

        Config config = configs.get(type);
        if(config == null){
            throw new PayChannelNotSupports();
        }
        return config;

    }
}
