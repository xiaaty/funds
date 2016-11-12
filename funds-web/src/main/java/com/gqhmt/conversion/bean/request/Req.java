package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="req")
@XmlType(name = "", propOrder = {})
public class Req {

    public Req() {}

    @XmlElement(name = "requestHeader")
    private List<RequestHeader> requestHeader;

    @XmlElement(name = "requestMsg")
    private List<RequestMsg> requestMsg;

    public List<RequestMsg> getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(List<RequestMsg> requestMsg) {
        this.requestMsg = requestMsg;
    }


    public List<RequestHeader> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(List<RequestHeader> requestHeader) {
        this.requestHeader = requestHeader;
    }

}
