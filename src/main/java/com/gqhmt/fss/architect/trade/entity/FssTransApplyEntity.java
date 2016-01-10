package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTransApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:31
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trans_apply")
public class FssTransApplyEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                            // bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
    private String apply_no ;                   // varchar(45)    utf8_general_ci  NO      UNI     (NULL)                   select,insert,update,references
    private String cust_no;                     //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  转账申请方 客户编号
    private String user_no ;                    //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String  acc_no ;                    //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  转账申请方 账户账号
    private String amount  ;                    //decimal(17,2)  (NULL)           NO              (NULL)                   select,insert,update,references  交易总金额
    private String trade_sum;                   // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  交易总笔数
    private String apply_type;                  //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  满标转账，还款转账，流标解冻，债权转让，费用收取，收取费收取,运营返现转账
    private String trade_date;                  // char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String trade_time ;                 //char(6)        utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String busi_type;                   //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  业务类型
    private String busi_no   ;                  //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  业务编号
    private String create_time;                 // datetime       (NULL)           NO              (NULL)                   select,insert,update,references
    private String modify_time;                 //datetime       (NULL)           YES             (NULL)                   select,insert,update,references
    private String mchn_parent ;                //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String mchn_child ;                 //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
}
