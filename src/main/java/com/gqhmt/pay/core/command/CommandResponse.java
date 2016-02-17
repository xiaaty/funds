package com.gqhmt.pay.core.command;

import java.util.Map;

/**
 * Created by yuyonf on 15/3/29.
 */
public class CommandResponse{
    private String code;                //0000成功，0001第三方需要短信验证，0002，网络连接超时，0003，第三方返回错误
    private String thirdReturnCode;     //第三方返回错误码
    private String msg;

    private Map<String,Object> map;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getThirdReturnCode() {
        return thirdReturnCode;
    }

    public void setThirdReturnCode(String thirdReturnCode) {
        this.thirdReturnCode = thirdReturnCode;
    }
}
