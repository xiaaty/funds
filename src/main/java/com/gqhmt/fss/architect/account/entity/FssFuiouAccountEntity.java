package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.account.entity.FssFuiouAccount
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 15:54
 * Description:
 * <p>富友账户类,记录富友开户情况,</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_fuiou_account")
public class FssFuiouAccountEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // int(11) NOT NULL AUTO_INCREMENT,

    @Column(name="cus_no")
    private String cusNo;                  // varchar(45) NOT NULL COMMENT '客户编号',

    @Column(name="user_no")
    private String userNo;                 //varchar(45) DEFAULT NULL COMMENT '用户编号',

    @Column(name="acc_no")
    private String accNo;                  // varchar(45) NOT NULL COMMENT '富友账户号，唯一，与富友对应',

    @Column(name="acc_user_name")
    private String accUserName;           // varchar(200) DEFAULT NULL COMMENT '客户姓名',

    @Column(name="bank_card_no")
    private String bankCardNo;            // varchar(45) DEFAULT NULL COMMENT '绑定银行卡 编号',

    @Column(name="create_time")
    private Date createTime;               // datetime NOT NULL,

    @Column(name="modify_time")
    private Date modifyTime;               // datetime NOT NULL,

    @Column(name="mchn_parent")
    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',

    @Column(name="mchn_child")
    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',

    @Column(name="has_open_acc_fuiou")
    private Integer hasOpenAccFuiou;     // varchar(45) NOT NULL DEFAULT '0' COMMENT '富友是否已经开户，0未开户，1已开户',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccUserName() {
        return accUserName;
    }

    public void setAccUserName(String accUserName) {
        this.accUserName = accUserName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMchnParent() {
        return mchnParent;
    }

    public void setMchnParent(String mchnParent) {
        this.mchnParent = mchnParent;
    }

    public String getMchnChild() {
        return mchnChild;
    }

    public void setMchnChild(String mchnChild) {
        this.mchnChild = mchnChild;
    }

    public Integer getHasOpenAccFuiou() {
        return hasOpenAccFuiou;
    }

    public void setHasOpenAccFuiou(Integer hasOpenAccFuiou) {
        this.hasOpenAccFuiou = hasOpenAccFuiou;
    }
}
