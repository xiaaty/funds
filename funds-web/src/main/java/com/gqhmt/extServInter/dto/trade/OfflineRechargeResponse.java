package com.gqhmt.extServInter.dto.trade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gqhmt.core.json.BigDecimalSerialize;
import com.gqhmt.extServInter.dto.Response;

import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.trade.WebOrderResponse
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/8
 * Description:线下充值回调
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/8  keyulai      1.0     1.0 Version
 */
public class OfflineRechargeResponse extends Response {
    private String chg_cd;//充值码

    private BigDecimal amt;//交易金额
    private String chg_dt;//充值码时间
    private String fy_acc_nm;//入账户名
    private String fy_bank;//入账银行
    private String fy_bank_branch;//支行信息
    private String fy_acc_no;//入账卡号
    private String desc_code;//错误描述
    public String getChg_cd() {
        return chg_cd;
    }
    public void setChg_cd(String chg_cd) {
        this.chg_cd = chg_cd;
    }


    @JsonSerialize(using = BigDecimalSerialize.class)
    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    public String getChg_dt() {
        return chg_dt;
    }
    public void setChg_dt(String chg_dt) {
        this.chg_dt = chg_dt;
    }
    public String getFy_acc_nm() {
        return fy_acc_nm;
    }
    public void setFy_acc_nm(String fy_acc_nm) {
        this.fy_acc_nm = fy_acc_nm;
    }
    public String getFy_bank() {
        return fy_bank;
    }
    public void setFy_bank(String fy_bank) {
        this.fy_bank = fy_bank;
    }
    public String getFy_bank_branch() {
        return fy_bank_branch;
    }
    public void setFy_bank_branch(String fy_bank_branch) {
        this.fy_bank_branch = fy_bank_branch;
    }
    public String getFy_acc_no() {
        return fy_acc_no;
    }
    public void setFy_acc_no(String fy_acc_no) {
        this.fy_acc_no = fy_acc_no;
    }
    public String getDesc_code() {
        return desc_code;
    }
    public void setDesc_code(String desc_code) {
        this.desc_code = desc_code;
    }
}
