package com.gqhmt.fss.architect.asset.entity;

import javax.persistence.*;
import java.io.DataInput;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.asset.entity.FssAssetRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:55
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_asset_record")
public class FssAssetRecordEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //int(11)        (NULL)           NO      PRI     (NULL)           select,insert,update,references

    @Column(name = "cust_no")
    private String custNo ;                            // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "user_no")
    private String userNo ;                            //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references

    @Column(name = "acc_no")
    private String  accNo ;                            // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "debit_amount")
    private String  debitAmount;                       // decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  借方金额，资产类，记录增加值，如投标记录，购买债权记录，预期收益记录

    @Column(name = "credit_amount")
    private String creditAmount ;                      //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  贷方金额，记录减少值，如还款本金，还款利息。收益减少变更。

    @Column(name = "balance")
    private String balance  ;                           //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  账户余额

    @Column(name = "asset_no")
    private String  assetNo;                           //varchar(45)    utf8_general_ci  NO      UNI     (NULL)           select,insert,update,references  资产流水号

    @Column(name = "seq_no")
    private String seqNo   ;                           //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务流水号

    @Column(name = "order_no")
    private String  orderNo ;                          //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  支付交易流水号

    @Column(name = "asset_type")
    private Integer  assetType;                         //int(11)        (NULL)           YES             (NULL)           select,insert,update,references

    @Column(name = "create_time")
    private Date  createTime ;                       // datetime       (NULL)           YES             (NULL)           select,insert,update,references

    @Column(name = "modify_time")
    private Date modifyTime;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references

    @Column(name = "mchn_parent")
    private String  mchnParent  ;                      //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "mchn_child")
    private String  mchnChild   ;                      //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
}
