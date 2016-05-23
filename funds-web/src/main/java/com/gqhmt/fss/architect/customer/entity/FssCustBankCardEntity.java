package com.gqhmt.fss.architect.customer.entity;

import javax.persistence.*;

/**
 * Filename:    com.gqhmt.fss.architect.customer.entity.FssCustomerBankCardEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 15:24
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */

@Entity
@Table(name="t_gq_fss_bank_card_info")
public class FssCustBankCardEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;                                    // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private String cust_no;                             // varchar(45) DEFAULT NULL COMMENT '客户表编号，系统唯一，不可更改',

    @Column(name="cert_type")
    private Integer certType;                          // int(11) NOT NULL COMMENT '证件类型',

    @Column(name="cert_no")
    private String certNo;                             // varchar(45) NOT NULL COMMENT '证件号码',

    @Column(name="bank_type")
    private String bankType;                            // int(11) NOT NULL COMMENT '所属银行',

    @Column(name="card_no")
    private String cardNo;                             ///varchar(45) NOT NULL COMMENT '银行卡号',

    @Column(name = "bankArea")
    private String bankArea;                           // varchar(45) DEFAULT NULL COMMENT '开户支行',

    @Column(name = "area")
    private String area;                               // int(11) DEFAULT NULL COMMENT '本地开户地区',

    @Column(name="user_no")
    private String userNo;                             // varchar(45) DEFAULT NULL COMMENT '用户编号，系统唯一，不可更改',

    @Column(name = "bank_type_third")
    private String bankTypeThird;                       // varchar(45) DEFAULT NULL COMMENT '第三方银行编码',

    @Column(name="area_third")
    private String  areaThird;                         // varchar(45) DEFAULT NULL COMMENT '第三方 地区码',

    @Column(name = "bank_card_no")
    private String  bankCardNo;                       // varchar(45) DEFAULT NULL COMMENT '银行卡编号，系统唯一，不可更改',

    @Column(name = "mchn_parent")
    private String  mchnParent;                        // varchar(45) NOT NULL COMMENT '大商户号',

    @Column(name="mchn_child")
    private String mchnChild;                          // varchar(45) NOT NULL COMMENT '子商户号',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBankTypeThird() {
        return bankTypeThird;
    }

    public void setBankTypeThird(String bankTypeThird) {
        this.bankTypeThird = bankTypeThird;
    }

    public String getAreaThird() {
        return areaThird;
    }

    public void setAreaThird(String areaThird) {
        this.areaThird = areaThird;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
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
}
