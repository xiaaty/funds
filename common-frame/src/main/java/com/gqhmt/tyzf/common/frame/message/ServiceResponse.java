package com.gqhmt.tyzf.common.frame.message;

/**
 * Created by zhou on 2016/10/21.
 * Description:响应报文对象
 */
public class ServiceResponse  implements IServiceObject {
    private byte[] response = null;//byte[]类型响应信息
    private MsgObject mo = null;
    private ServiceParameter servPara = new ServiceParameter();

    private String sendQueueName;


    @Override
    public int getMessageType() {
        return 0;
    }

//    @Override
//    public boolean getSignInTag() {
//        return false;
//    }

    @Override
    public ServiceParameter getServPara() {
        return servPara;
    }

    @Override
    public void release() {
        if(this.servPara!=null){
            this.servPara=null;
        }
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public MsgObject getMo() {
        return mo;
    }

    public void setMo(MsgObject mo) {
        this.mo = mo;
    }

    public void setServPara(ServiceParameter servPara) {
        this.servPara = servPara;
    }

    public String getSendQueueName() {
        return sendQueueName;
    }

    public void setSendQueueName(String sendQueueName) {
        this.sendQueueName = sendQueueName;
    }
}
