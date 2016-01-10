package com.gqhmt.fss.architect.asset.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.asset.entity.FssAssetEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:51
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_asset")
public class FssAssetEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
    private String cust_no;                                     // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String user_no  ;                                   //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String acc_no   ;                                   //varchar(45)    utf8_general_ci  NO      UNI     (NULL)                   select,insert,update,references
    private String acc_asset   ;                                //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户总资产
    private String acc_banlance ;                               // decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户余额
    private String acc_avai  ;                                  //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户可用余额
    private String acc_freeze;                                  //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户冻结金额

    private String     acc_invest ;                             //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  投资本金总额
    private String acc_invest_repay;                            // decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已回本金总额
    private String acc_prose_yield  ;                           //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  预期收益总额
    private String acc_income      ;                            //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已获收益总额
    private String acc_prose_yield_drop;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  预期收益调整平账总额
    private String acc_stay_principal ;                         //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  待还本金
    private String acc_also_principal ;                         //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已还本金

    private String     acc_stay_interest;                       //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  待还利息

    private String acc_also_interest;                           // decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已还利息
    private String create_time   ;                              //datetime       (NULL)           YES             (NULL)                   select,insert,update,references
    private String modify_time  ;                               //datetime       (NULL)           YES             (NULL)                   select,insert,update,references
    private String mchn_parent ;                                //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String mchn_child  ;                                //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
}
