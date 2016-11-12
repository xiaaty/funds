package com.gqhmt.conversion.bean.response;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="req")
@XmlType(name = "", propOrder = {})
public class ReqContentResponse {

    private RequestHeader requestHeader;
    private RequestMsgResponse requestMsg;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public RequestMsgResponse getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(RequestMsgResponse requestMsg) {
        this.requestMsg = requestMsg;
    }
}
