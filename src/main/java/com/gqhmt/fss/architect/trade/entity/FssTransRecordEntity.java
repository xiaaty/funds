package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trans_record")
public class FssTransRecordEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //bigint(20)     (NULL)           NO      PRI     (NULL)           select,insert,update,references
    private String cust_no;                             // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String user_no ;                            // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String acc_no ;                             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  转出账号
    private String apply_no ;                          //    varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  申请编号
    private String to_acc_no  ;                         //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  转入账号
    private String amount ;                             //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  转账金额
    private String trade_type;                          //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  交易类型，满标，还款，债权转让，收费，其他
    private String funds_type ;                         //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  账务类型
    private String trans_no  ;                          //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  转账编号
    private String trade_date ;                         // char(8)        utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String trade_time ;                         //char(6)        utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String create_time ;                        //datetime       (NULL)           YES             (NULL)           select,insert,update,references
    private String modify_time ;                        // datetime       (NULL)           YES             (NULL)           select,insert,update,references
    private String mchn_parent ;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String mchn_child  ;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references
}
