package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.io.Serializable;

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
    private String cust_no;                 //        varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  客户编号
    private String account_no;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账户编号
    private String water_type;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  流水大类，1充值，2提现，3投标，4还款，5债权转让，6费用收取，9其他
    private String trade_type;              //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账务类型
    private String debit_amount;            //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  借方金额，账户收益，计入减值，及出账资金，如转出，提现，
    private String credit_amount;           //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  贷方资金，记录增值，入账资金，如转入，充值
    private String banlance;                // bigint(20)     (NULL)           NO              0                select,insert,update,references  余额，计入增方，及，正值
    private String create_time;             //datetime       (NULL)           NO              (NULL)           select,insert,update,references  交易时间
    private String  modify_time;            //datetime       (NULL)           NO              (NULL)           select,insert,update,references  最后修改时间
    private String sumary;                  //varchar(200)   utf8_general_ci  YES             (NULL)           select,insert,update,references  交易摘要
    private String currency;                // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  交易币种
    private String order_no;                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  交易流水订单号
    private String token;                   // varchar(45)    utf8_general_ci  YES     UNI     (NULL)           select,insert,update,references  记账标识，防止重复数据流水记录

    private String o_account_id;            //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  来源账户账号
    private String  seq_no;                 //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务订单号
    private String pay_channel;             //int(11)        (NULL)           YES             (NULL)           select,insert,update,references  交易渠道
    private String booking_state;           //int(11)        (NULL)           YES             (NULL)           select,insert,update,references  记账状态，0已记账，1未记账
    private String water_no;                //varchar(45)    utf8_general_ci  YES     UNI     (NULL)           select,insert,update,references  流水编号，唯一
    private String mchn_parent;             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String mchn_child ;             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
}
