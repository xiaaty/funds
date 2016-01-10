package com.gqhmt.fss.architect.asset.entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "cust_no")
    private String custNo;                             //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "user_no")
    private String  userNo ;                           // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "acc_no")
    private String accNo ;                             //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "amount")
    private String amount  ;                            // decimal(17,2)  (NULL)           NO              (NULL)                   select,insert,update,references

    @Column(name = "from_acc_no")
    private String fromAccNo;                         // varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  来源账户编号

    @Column(name = "from_cust_no")
    private String fromCustNo;                        //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  来源客户编号

    @Column(name = "charge_type")
    private Integer chargeType ;                        //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  收取类型，1收费，2退费

    @Column(name = "funds_type")
    private Integer  fundsType ;                        // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  账务类型，借款人手续费，收取准时还款保证金，归还准还是还款保证金，咨询费，出借补息差，债权转让手续费。。。。

    @Column(name = "trade_date")
    private String  tradeDate ;                        //char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "trade_time")
    private String  tradeTime;                         //char(6)        utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "trade_state")
    private Integer  tradeState;                        // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  收费状态，1预计录，2收费提交，3收费成功，4收费失败

    @Column(name = "sumary")
    private String  sumary ;                            // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "create_time")
    private Date createTime;                        //datetime       (NULL)           NO              (NULL)                   select,insert,update,references

    @Column(name = "modify_time")
    private Date  modifyTime;                        // datetime       (NULL)           NO              (NULL)                   select,insert,update,references

    @Column(name = "mchn_parent")
    private String  mchnParent;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "mchn_child")
    private String  mchnChild ;                        //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references
}
