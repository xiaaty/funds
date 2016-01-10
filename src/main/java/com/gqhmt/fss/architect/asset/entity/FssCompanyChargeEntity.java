package com.gqhmt.fss.architect.asset.entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.fss.architect.asset.entity.FssCompanyChargeEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:57
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_company_charge")
public class FssCompanyChargeEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
    private String cust_no;                             //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String  user_no ;                           // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String acc_no ;                             //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String amount  ;                            // decimal(17,2)  (NULL)           NO              (NULL)                   select,insert,update,references
    private String from_acc_no;                         // varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  来源账户编号
    private String from_cust_no;                        //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  来源客户编号
    private String charge_type ;                        //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  收取类型，1收费，2退费
    private String  funds_type ;                        // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  账务类型，借款人手续费，收取准时还款保证金，归还准还是还款保证金，咨询费，出借补息差，债权转让手续费。。。。
    private String  trade_date ;                        //char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String  trade_time;                         //char(6)        utf8_general_ci  NO              (NULL)                   select,insert,update,references
    private String  trade_state;                        // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  收费状态，1预计录，2收费提交，3收费成功，4收费失败
    private String  sumary ;                            // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String  create_time;                        //datetime       (NULL)           NO              (NULL)                   select,insert,update,references
    private String  modify_time;                        // datetime       (NULL)           NO              (NULL)                   select,insert,update,references
    private String  mchn_parent;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
    private String  mchn_child ;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
}
