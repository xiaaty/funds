package com.gqhmt.extServInter.dto;

import com.gqhmt.annotations.AutoMapping;

/**
 * Filename:    com.gqhmt.extServInter.dto.Response
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 16:04
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
public class Response {

    @AutoMapping(value = "mchnChild",isParent = true)
    private String mchn;

    @AutoMapping("seqNo")
    private String seq_no;

    private String signature;

    private String resp_code;

    public String getMchn() {
        return mchn;
    }

    public void setMchn(String mchn) {
        this.mchn = mchn;
    }

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    private String resp_msg;

    private Object plain;

    public Object getPlain() {
        return plain;
    }

    public void setPlain(Object plain) {
        this.plain = plain;
    }
}
