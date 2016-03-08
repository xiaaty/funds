package com.gqhmt.funds.architect.order.bean;

import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.funds.architect.order.bean.FundOrderBean
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 15:12
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  于泳      1.0     1.0 Version
 */
public class FundOrderBean {

    private int orderType;          //交易类型

    private String orderTypeName;   //交易类型中文

    private BigDecimal amt;  // 交易金额

    private BigDecimal chargeAmt; //交易手续费

    private int orderState; //交易状态类型

    private String  orderStateName;//交易状态中文

    private String  tradeDate;    //交易时间


    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeName() {
        String  typeName = "";

        if(this.orderType == 1 || this.orderType == 2){
            typeName = "充值";
        }else if(this.orderType == 9 || this.orderType == 12){
            typeName = "提现";
        }else{
            typeName = "未知";
        }


        return typeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(BigDecimal chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderStateName() {
        return orderStateName;
    }

    public void setOrderStateName(String orderStateName) {
        this.orderStateName = orderStateName;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
}
