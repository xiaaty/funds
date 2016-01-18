package com.gqhmt.fss.transferDataBean;

import com.gqhmt.annotations.AutoMapping;

/**
 * Filename:    com.gqhmt.fss.transferDataBean.TransferDataSuperBean
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:11
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  于泳      1.0     1.0 Version
 */
public abstract class TransferDataSuperBean {


    @AutoMapping(value = "mchnChild",isParent = true)
    private String mchn;


    @AutoMapping("seqNo")
    private String seq_no;

    public String getMchn() {
        return mchn;
    }

    public void setMchn(String mchn) {
        this.mchn = mchn;
    }




    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    private String signature;

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }
}
