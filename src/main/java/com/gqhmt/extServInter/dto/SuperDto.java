package com.gqhmt.extServInter.dto;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.AutoMapping;


/**
 * Filename:    com.gqhmt.extServInter.dto.TransferDataSuperBean
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
public abstract class SuperDto {


    @AutoMapping(value = "mchnChild",isParent = true)
    @APIValidNull(errorCode = "90008101")
    private String mchn;

    @AutoMapping("seqNo")
    @APIValidNull(errorCode = "90008201")
    private String seq_no;

    @APIValidNull(errorCode = "90008301")
    private String signature;
    
    @APIValidNull(errorCode = "90008401")
    private String trade_type;
    
    


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



    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
}
