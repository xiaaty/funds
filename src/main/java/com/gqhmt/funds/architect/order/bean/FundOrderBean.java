package com.gqhmt.funds.architect.order.bean;

import java.math.BigDecimal;
import java.util.Date;

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

    private String orderNo;

    private int orderType;          //交易类型

    private String orderTypeName;   //交易类型中文

    private BigDecimal amt;  // 交易金额

    private BigDecimal chargeAmt; //交易手续费

    private int orderState; //交易状态类型

    private String  orderStateName;//交易状态中文

    private Date tradeDate;    //交易时间

    private String retMessage;

    private Date completeTime;


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
        String orderType = "";
        if(orderState == 2){
            orderType = "支付成功";
        }else if(orderState == 3){
            orderType = "支付失败";
        }else{
            orderType = "未支付";
        }
        return orderType;
    }

    public void setOrderStateName(String orderStateName) {
        this.orderStateName = orderStateName;
    }



    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
}
