package com.gqhmt.fss.architect.asset.entity;

import javax.persistence.*;
import java.io.Serializable;

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
    private String cust_no ;                            // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String user_no ;                            //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String  acc_no ;                            // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String  debit_amount;                       // decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  借方金额，资产类，记录增加值，如投标记录，购买债权记录，预期收益记录
    private String credit_amount ;                      //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  贷方金额，记录减少值，如还款本金，还款利息。收益减少变更。
    private String balance  ;                           //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  账户余额
    private String  asset_no;                           //varchar(45)    utf8_general_ci  NO      UNI     (NULL)           select,insert,update,references  资产流水号
    private String seq_no   ;                           //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务流水号
    private String  order_no ;                          //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  支付交易流水号
    private String  asset_type;                         //int(11)        (NULL)           YES             (NULL)           select,insert,update,references
    private String  create_time ;                       // datetime       (NULL)           YES             (NULL)           select,insert,update,references
    private String  modify_time;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references
    private String  mchn_parent  ;                      //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
    private String  mchn_child   ;                      //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
}
