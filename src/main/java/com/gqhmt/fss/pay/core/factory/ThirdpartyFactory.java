package com.gqhmt.fss.pay.core.factory;


import com.gqhmt.fss.pay.core.Context.FssContext;
import com.gqhmt.fss.pay.core.command.CommandResponse;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuyonf on 15/3/29.
 */
public class ThirdpartyFactory {

    private static Map<Integer,ThirdpartyAbstractFactory> factory=  new ConcurrentHashMap();

    /**
     * 初始化配置工厂类
     */
    static{
        Map<String,String> map = FssContext.getContextMap("factory");
        Set<String> set = map.keySet();
        for(String tmp :set){

            String  className = map.get(tmp);
            if(className == null || "".equals(className)){
                continue;
            }
            try {

                Class aClass = Class.forName(className);

                ThirdpartyAbstractFactory factory1 = (ThirdpartyAbstractFactory) aClass.newInstance();

                factory.put(Integer.parseInt(tmp),factory1);

            } catch (Exception e) {

                factory.put(Integer.parseInt(tmp),null);

            }
        }
    }



    public static CommandResponse command(Integer thirdPartyType, String commandEnum, FundOrderEntity order, Object... obj){
        return factory.get(thirdPartyType).getCommand().command(commandEnum,order,obj);
    }
}
