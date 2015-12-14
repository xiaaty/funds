package com.gqhmt.fss.pay.core.factory;


import com.gqhmt.fss.pay.core.command.ThirdpartyCommand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuyonf on 15/3/29.
 */
public class ThirdpartyFactory {

    private static final Map<Integer,ThirdpartyCommand> thirdType = new ConcurrentHashMap();

    static{
////        DaqianFactory.getFactory();
//        thirdType.put(ThirdPartyType.DAQIAN, DaqianFactory.getFactory().getCommand());
//        thirdType.put(ThirdPartyType.FUIOU, FuiouFactory.getFactory().getCommand());
    }

//    public static CommandResponse command(Integer thirdPartyType, CommandEnum commandEnum, FundOrderEntity order, Object... obj){
//        return thirdType.get(thirdPartyType).command(commandEnum,order,obj);
//    }
}
