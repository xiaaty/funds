package com.gqhmt.fss.architect.accounting.entity;

import javax.persistence.*;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/10
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/10  jhz     1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_check_date")
public class FssCheckDate {
    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;				//自增主键

    @Column(name = "order_date")
    private String orderDate;   		//对账订单表用

    @Column(name = "input_date")
    private String inputDate;   		//对账ftp_filed用

    @Column(name = "input_user_state")
    private String inputUserState;   		    //状态

    @Column(name = "order_user_state")
    private String orderUserState;   		//状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getInputUserState() {
        return inputUserState;
    }

    public void setInputUserState(String inputUserState) {
        this.inputUserState = inputUserState;
    }

    public String getOrderUserState() {
        return orderUserState;
    }

    public void setOrderUserState(String orderUserState) {
        this.orderUserState = orderUserState;
    }
}
