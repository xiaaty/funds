package com.gqhmt.pay.core.factory;

import com.gqhmt.pay.core.Context.FssContext;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.exception.PayChannelNotSupports;

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
    private static Map<String,Config> configs=  new ConcurrentHashMap();

    private  static final String configFile = "config.payChannel";

    /**
     * 初始化配置工厂类
     */
    static{
        Map<String,String> map = FssContext.getContextMap("config");
        Set<String> set = map.keySet();
        for(String tmp :set){
            String  className = map.get(tmp);
            if(className == null || "".equals(className)){
                continue;
            }
            try {
                Class aClass = Class.forName(className);
                Config config = (Config) aClass.newInstance();
                configs.put(tmp,config);
            } catch (Exception e) {
                configs.put(tmp,null);
            }
        }
    }

    private ConfigFactory(){

    }

    public static ConfigFactory getConfigFactory(){
        return factory;
    }

    public Config getConfig(String type) throws PayChannelNotSupports {

        Config config = configs.get(type);
        if(config == null){
            throw new PayChannelNotSupports("交易渠道不支持");
        }
        return config;

    }
}
