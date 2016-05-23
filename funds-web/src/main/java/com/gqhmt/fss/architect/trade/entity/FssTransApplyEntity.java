package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name = "apply_no")
    private String applyNo ;                   // varchar(45)    utf8_general_ci  NO      UNI     (NULL)                   select,insert,update,references

    @Column(name = "cust_no")
    private String custNo;                     //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  转账申请方 客户编号

    @Column(name = "user_no")
    private String userNo ;                    //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "acc_no")
    private String  accNo ;                    //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references  转账申请方 账户账号

    @Column(name = "amount")
    private BigDecimal amount  ;                    //decimal(17,2)  (NULL)           NO              (NULL)                   select,insert,update,references  交易总金额

    @Column(name = "trade_sum")
    private Integer tradeSum;                   // int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  交易总笔数

    @Column(name = "apply_type")
    private Integer applyType;                  //int(11)        (NULL)           NO              (NULL)                   select,insert,update,references  满标转账，还款转账，流标解冻，债权转让，费用收取，收取费收取,运营返现转账

    @Column(name = "trade_date")
    private String tradeDate;                  // char(8)        utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "trade_time")
    private String tradeTime ;                 //char(6)        utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "busi_type")
    private Integer busiType;                   //int(11)        (NULL)           YES             (NULL)                   select,insert,update,references  业务类型

    @Column(name = "busi_no")
    private String busiNo   ;                  //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  业务编号

    @Column(name = "create_time")
    private Date createTime;                 // datetime       (NULL)           NO              (NULL)                   select,insert,update,references

    @Column(name = "modify_time")
    private Date modifyTime;                 //datetime       (NULL)           YES             (NULL)                   select,insert,update,references

    @Column(name = "mchn_parent")
    private String mchnParent ;                //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references

    @Column(name = "mchn_child")
    private String mchnChild ;                 //varchar(45)    utf8_general_ci  NO              (NULL)                   select,insert,update,references
}
