package com.gqhmt.fss.architect.customer.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.customer.entity.FssCustomerEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 14:43
 * Description:
 * <p>新版客户表</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_customer")
public class FssCustomerEntity implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                             // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name="name")
    private String name;                                         // varchar(45) DEFAULT NULL COMMENT '客户姓名',

    @Column(name="mobile")
    private String mobile;                                       //` varchar(45) DEFAULT NULL COMMENT '客户手机号',

    @Column(name="cert_type")
    private Integer certType;                                    // varchar(45) DEFAULT NULL COMMENT '证件类型，1身份证',


    @Column(name="cert_no")
    private String certNo;                                      // varchar(45) DEFAULT NULL COMMENT '证件号码',

    @Column(name="user_id")
    private String userId;                                      // bigint(20) DEFAULT NULL COMMENT '冠e通用户表id',

    @Column(name="create_time")
    private Date createTime;                                    // varchar(45) DEFAULT NULL COMMENT '创建时间',

    @Column(name="modifyTime")
    private Date modify_time;                                    // varchar(45) DEFAULT NULL COMMENT '最后修改时间',

    @Column(name="cust_no")
    private String custNo;                                      // varchar(45) DEFAULT NULL COMMENT '客户编号唯一',

    @Column(name="user_no")
    private String userNo;                                      // varchar(45) DEFAULT NULL COMMENT '用户编号 唯一',

    @Column(name="is_auth_real_name")
    private Integer isAuthRealName;                               // varchar(45) DEFAULT NULL COMMENT '是否实名验证，0未验证，1富友方式验证，2接入身份证验证系统验证，4面对面验证，8图片上传验证',

    @Column(name="create_user_id")
    private Long createUserId;                                 // bigint(20) DEFAULT NULL,

    @Column(name="modify_user_id")
    private Long modifyUserId;                                 // bigint(20) DEFAULT NULL,

    @Column(name="modify_user_id")
    private String mchnParent;                             // varchar(45) DEFAULT NULL COMMENT '主商户号',

    @Column(name="modify_user_id")
    private String mchnChild;                                  // varchar(45) DEFAULT NULL COMMENT '子商户号',
}
