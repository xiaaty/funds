package com.gqhmt.fss.pay.fuiou.command;


import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.fss.pay.core.channel.PayChannel;
import com.gqhmt.fss.pay.core.command.AbstractThirdpartyCommand;
import com.gqhmt.fss.pay.core.command.CommandResponse;
import com.gqhmt.fss.pay.core.command.ThirdpartyCommand;
import com.gqhmt.fss.pay.core.configer.Config;
import com.gqhmt.fss.pay.core.factory.ConfigFactory;
import com.gqhmt.fss.pay.exception.PayChannelNotSupports;
import com.gqhmt.fss.pay.fuiou.connection.ConnectionFuiou;
import com.gqhmt.fss.pay.fuiou.util.SecurityUtils;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;

import java.util.*;


/**
 * Created by yuyonf on 15/3/29.
 */
public class FuiouCommand extends AbstractThirdpartyCommand implements ThirdpartyCommand {

    private  static Config config = null;

    private static final String setPath="com.fuiou.cToFuiou";

    static {
        try {
            config =  ConfigFactory.getConfigFactory().getConfig(PayChannel.PAY_CHANNEL_FUIOU);
        } catch (PayChannelNotSupports payChannelNotSupports) {
            LogUtil.error(FuiouCommand.class,payChannelNotSupports);
        }
    }




    public CommandResponse command(String apiKey, FundOrderEntity fundOrderEntity, Object... object) {
        boolean isConnection = config.isConnection();
        CommandResponse response=null;
        if(!isConnection){
            response = new CommandResponse();
            response.setCode("0000");
            response.setMsg("成功");
            return response;
        }

        String apiName = ResourceUtil.getValue(setPath,apiKey);

        if(apiName!=null){
            return excute(apiName,fundOrderEntity,object);
        }

        response = new CommandResponse();

        if("".equals(apiName)){
            response.setCode("100000");
            response.setMsg("fuiou not realize");
            return response;
        }
        return response;
    }




    public CommandResponse excute(String apiName,FundOrderEntity fundOrderEntity,Object... object){
        CommandResponse response = new CommandResponse();
        Map<String,String> jsonMap = new HashMap<>();
        object = addParam(fundOrderEntity,object);
        super.parsePublic(apiName,jsonMap,object);
        super.parseAPI(apiName, jsonMap, object);
        Set<String> keySet  = jsonMap.keySet();
        List<String> keyList = new ArrayList<>();
        for(String tmp:keySet){
            keyList.add(tmp);
        }

        Collections.sort(keyList);

        String keySign = "";
        for(String key:keyList){
            String value = jsonMap.get(key);
            String noSign = (String)config.getValue("api."+apiName+"."+key+".noSign");
            if(noSign != null && "true".equals(noSign)){
                continue;
            }
            if(value == null || "null".equals(value)) value = "";
            keySign += value + "|";
        }

        keySign = keySign.substring(0,keySign.length()-1);

        String signature = SecurityUtils.sign(keySign);
        jsonMap.put("signature",signature);

        String url = (String)config.getValue("api."+apiName+".url");
        if(url == null){
            url = config.getURL();
        }

        Map resultMap = ConnectionFuiou.send(url+apiName,jsonMap);//ConnectionDaqian.send(config.getURL(), jsonMap);

        String resultCode = (String)resultMap.get("resp_code");
        response.setMap(resultMap);
        if(resultMap == null){
            response.setCode("9900");
            response.setThirdReturnCode(resultCode);
            response.setMsg("连接富友失败");
            return response;
        }

        if("0000".equals(resultCode) || "5343".equals(resultCode)){
            response.setCode("0000");
            response.setThirdReturnCode(resultCode);
            response.setMsg("成功");
        }else if("0001".equals(resultCode)){
            response.setCode("0001");
            response.setThirdReturnCode(resultCode);
            response.setMsg("需要短信确认");
        }else if("3201".equals(resultCode) || (resultCode!= null && resultCode.length() == 4 && "98".equals(resultCode.substring(0, 2)))){
            response.setCode("0009");
            response.setThirdReturnCode(resultCode);
            response.setMsg("富友内部操作错误，需手动核对");
        }else{
            response.setCode("0100");
            response.setThirdReturnCode(resultCode);
//            String msg = GlobalConstants.fuiouRezultCodeMap.get(resultCode);
//            response.setMsg("富友返回："+msg==null?"未知错误"+resultCode:msg );
        }
        return response;
    }

    @Override
    public Config getConfig() {
        return config;
    }

    @Override
    public String api(String apiName, String keyName, Object... obj) {
        return null;
    }
}
