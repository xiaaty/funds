package com.gqhmt.fss.architect.order.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.order.entity.FssSeqOrder
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 15:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_seq_order")
public class FssSeqOrderEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "seq_no")
    private String seqNo;                              // varchar(45) NOT NULL COMMENT '交易流水号',


    @Column(name = "cust_no")
    private String custNo;                             // varchar(45) DEFAULT NULL COMMENT '客户编号',


    @Column(name = "user_no")
    private String userNo;                             // varchar(45) DEFAULT NULL COMMENT '用户编号',


    @Column(name = "acc_no")
    private String accNo;                              // varchar(45) DEFAULT NULL COMMENT '账务账户编号',


    @Column(name = "trade_type")
    private String tradeType;                          // varchar(45) NOT NULL COMMENT '交易类型，1账户，2充值，3，提现，4标的，5收费，6转账，7，暂未定义 ，8资产查询，9其他',


    @Column(name = "trade_type_child")
    private String tradeTypeChild;                    // varchar(45) DEFAULT NULL COMMENT '见，kvcode表',


    @Column(name = "amount")
    private BigDecimal amount;                          // decimal(17,2) DEFAULT NULL COMMENT '操作资金金额',


    @Column(name = "charge_amount")
    private BigDecimal chargeAmount;                   // decimal(17,2) DEFAULT NULL COMMENT '本次交易手续费',


    @Column(name = "api_no")
    private String apiNo;                              // varchar(45) NOT NULL COMMENT '接口编号',


    @Column(name = "mchn_parent")
    private String mchnParent;                         // varchar(45) NOT NULL COMMENT '大商户编号',


    @Column(name = "mchn_child")
    private String mchnChild;                          // varchar(45) NOT NULL COMMENT '子商户号',


    @Column(name = "trade_date")
    private String tradeDate;                          // char(8) DEFAULT NULL COMMENT '交易日期',


    @Column(name = "trade_time")
    private String tradeTime;                          // char(6) DEFAULT NULL COMMENT '交易时间',


    @Column(name = "state")
    private Integer state;                              // int(11) NOT NULL DEFAULT '1' COMMENT '交易状态，1，交易接收，2交易成功，3交易失败，1000交易关闭',


    @Column(name = "return_state")
    private Integer returnState;                       //  int(11) NOT NULL DEFAULT '0' COMMENT '0，不需回调通知，1待回调通知，2已回调通知—具体参数，见kvcode表。',


    @Column(name = "create_time")
    private Date createTime;                           // datetime DEFAULT NULL COMMENT '创建时间',


    @Column(name = "modify_time")
    private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',


    @Column(name = "memo")
    private String  memo;                               //varchar(45) DEFAULT NULL,

    @Column(name = "trade_param")
    private String tradeParam;                         // blob COMMENT '交易参数存储字段',
}
