package com.gqhmt.tyzf.common.frame.message;

/**
 * Created by zhou on 2016/10/21.
 * Description:请求报文对象
 */
public class ServiceRequest implements IServiceObject {

    // 报文文本
    private String requestMsg;

    // 报文对象
    private MsgObject mo = null;

    //activemq的correlationId
    private String correlationId;

    //参数对象
    private ServiceParameter servPara = new ServiceParameter();

    @Override
    public int getMessageType() {
        return 0;
    }

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

    /**
     * 获取服务ID
     * @return
     */
    public String getExecId() {
        return servPara.execId;
    }

    /**
     * 设置服务ID
     * @param execId
     */
    public void setExecId(String execId) {
        this.servPara.execId = execId;
    }

    /**
     * 克隆
     * @return
     */
    public ServiceRequest clone(){
        ServiceRequest serviceRequest = new ServiceRequest();
        try {
            serviceRequest.setMo(mo.clone(MsgObject.initSP));
        } catch (Exception e) {
            return null;
        }
        serviceRequest.setServPara((ServiceParameter) serviceRequest .getServPara().clone());
        return serviceRequest;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public void setMo(MsgObject mo) {
        this.mo = mo;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public MsgObject getMo() {
        return mo;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setServPara(ServiceParameter servPara) {
        this.servPara = servPara;
    }
}
