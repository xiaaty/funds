package com.gqhmt.fss.architect.asset.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name = "cust_no")
    private String custNo;                                     // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "user_no")
    private String userNo  ;                                   //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "acc_no")
    private String accNo   ;                                   //varchar(45)    utf8_general_ci  NO      UNI     (NULL)                   select,insert,update,references

    @Column(name = "acc_asset")
    private BigDecimal accSsset   ;                                //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户总资产

    @Column(name = "acc_banlance")
    private BigDecimal accBanlance ;                               // decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户余额

    @Column(name = "acc_avai")
    private BigDecimal accAvai  ;                                  //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户可用余额

    @Column(name = "acc_freeze")
    private BigDecimal accFreeze;                                  //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  账户冻结金额

    @Column(name = "acc_invest")
    private BigDecimal  accInvest ;                             //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  投资本金总额

    @Column(name = "acc_invest_repay")
    private BigDecimal accInvestRepay;                            // decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已回本金总额

    @Column(name = "acc_prose_yield")
    private BigDecimal accProseYield  ;                           //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  预期收益总额

    @Column(name = "acc_income")
    private BigDecimal accIncome      ;                            //decimal(11,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已获收益总额

    @Column(name = "acc_prose_yield_drop")
    private BigDecimal accProseYieldDrop;                        //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  预期收益调整平账总额

    @Column(name = "acc_stay_principal")
    private BigDecimal accStayPrincipal ;                         //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  待还本金

    @Column(name = "acc_also_principal")
    private String accAlsoPrincipal ;                         //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已还本金
    @Column(name = "acc_also_principal")
    private BigDecimal  accStayInterest;                       //decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  待还利息
    @Column(name = "acc_also_principal")
    private BigDecimal accAlsoInterest;                           // decimal(17,2)  (NULL)           YES             (NULL)                   select,insert,update,references  已还利息

    @Column(name = "create_time")
    private Date createTime   ;                              //datetime       (NULL)           YES             (NULL)                   select,insert,update,references

    @Column(name = "modify_time")
    private Date modifyTime  ;                               //datetime       (NULL)           YES             (NULL)                   select,insert,update,references

    @Column(name = "mchn_parent")
    private String mchnParent ;                                //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "mchn_child")
    private String mchnChild  ;                                //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
}
