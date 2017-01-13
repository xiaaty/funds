package com.gqhmt.tyzf.common.frame.message;

/**
 * Created by zhou on 2016/10/21.
 * Description:报文消息参数
 */
public class ServiceParameter {

    //执行服务ID
    public String execId = null;

    public Object clone() {
        ServiceParameter para = new ServiceParameter();
        para.execId = this.execId;
        return para;
    }

}
