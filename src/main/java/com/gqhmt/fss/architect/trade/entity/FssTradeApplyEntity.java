package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:24
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_apply")
public class FssTradeApplyEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //int(11)        (NULL)           NO      PRI     (NULL)           select,insert,update,references
    private String apply_no;                                    //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  申请编号
    private String apply_type;                                  //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  1，充值，2，提现
    private String cust_no;                                     // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  客户编号
    private String user_no;                                     // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  用户编号
    private String business_no;                                 //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务编号
    private String busi_type ;                                  //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务类型，借款，出借，冠e通，
    private String acc_no ;                                     //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账户编号
    private String trade_amount ;                               // decimal(17,2)  (NULL)           NO              (NULL)           select,insert,update,references  交易金额
    private String real_trade_amount;                           //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  实际交易金额
    private String trade_charge_amount ;                        //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  交易手续费
    private String trade_state ;                                //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  交易状态，未交易，部分成功，成功，失败
    private String apply_state  ;                               //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  申请状态，新增，审核成功，，已交易，已回调通知
    private String mchn_parent ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String  mchn_child ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String create_time ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references  录入时间
    private String modify_time ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references
    private String seq_no     ;                                 //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  api业务交易流水号
}
