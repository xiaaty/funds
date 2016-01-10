package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.account.entity.FssWaterEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:18
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_water")
public class FssWaterEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;                      //            bigint(20)     (NULL)           NO      PRI     (NULL)           select,insert,update,references  主键

    @Column(name = "cust_no")
    private String custNo;                 //        varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  客户编号

    @Column(name = "acc_no")
    private String accNo;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账户编号

    @Column(name = "water_type")
    private Integer  waterType;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  流水大类，1充值，2提现，3投标，4还款，5债权转让，6费用收取，9其他

    @Column(name = "trade_type")
    private Integer tradeType;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账务类型

    @Column(name = "debit_amount")
    private BigDecimal debitAmount;            //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  借方金额，账户收益，计入减值，及出账资金，如转出，提现，

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;           //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  贷方资金，记录增值，入账资金，如转入，充值

    @Column(name = "banlance")
    private BigDecimal banlance;                // bigint(20)     (NULL)           NO              0                select,insert,update,references  余额，计入增方，及，正值

    @Column(name = "create_time")
    private Date createTime;             //datetime       (NULL)           NO              (NULL)           select,insert,update,references  交易时间

    @Column(name = "modify_time")
    private Date modifyTime;            //datetime       (NULL)           NO              (NULL)           select,insert,update,references  最后修改时间

    @Column(name = "sumary")
    private String sumary;                  //varchar(200)   utf8_general_ci  YES             (NULL)           select,insert,update,references  交易摘要

    @Column(name = "currency")
    private String currency;                // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  交易币种

    @Column(name = "order_no")
    private String orderNo;                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  交易流水订单号

    @Column(name = "token")
    private String token;                   // varchar(45)    utf8_general_ci  YES     UNI     (NULL)           select,insert,update,references  记账标识，防止重复数据流水记录

    @Column(name = "o_acc_no")
    private String oAccNo;            //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  来源账户账号

    @Column(name = "seq_no")
    private String  seqo;                 //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务订单号

    @Column(name = "pay_channel")
    private String payChannel;             //int(11)        (NULL)           YES             (NULL)           select,insert,update,references  交易渠道

    @Column(name = "booking_state")
    private String bookingState;           //int(11)        (NULL)           YES             (NULL)           select,insert,update,references  记账状态，0已记账，1未记账

    @Column(name = "water_no")
    private String waterNo;                //varchar(45)    utf8_general_ci  YES     UNI     (NULL)           select,insert,update,references  流水编号，唯一

    @Column(name = "mchn_parent")
    private String mchnParent;             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "mchn_child")
    private String mchnChild ;             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
}
