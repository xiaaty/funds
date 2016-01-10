package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_record")
public class FssTradeRecordEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                //int(11)        (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
    private String cust_no;                         //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String user_no ;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String acc_no;                          //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String trade_type;                      //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  交易类型，充值，提现
    private String amount  ;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  交易金额
    private String charge  ;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  手续费
    private String thirdparyt_charge;               //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  第三方收费
    private String trade_date ;                     // char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String trade_time ;                     // char(6)        utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String apply_no  ;                      //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  申请编号
    private String settle_type ;                    //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  提现时效。0：T+0，1：T+1
    private String trade_state  ;                   // int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  交易状态，未交易，已交易，已退回
    private String trade_result ;                   //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  交易结果，未交易，交易成功，交易失败，交易退票
    private String sumary      ;                    // varchar(3000)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  交易描述
    private String create_time  ;                   //datetime       (NULL)           YES             (NULL)                   select,insert,update,references
    private String modify_time  ;                   //datetime       (NULL)           YES             (NULL)                   select,insert,update,references
    private String mchn_parent  ;                   //qvarchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String mchn_child   ;                   //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String order_no    ;                    // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
}
