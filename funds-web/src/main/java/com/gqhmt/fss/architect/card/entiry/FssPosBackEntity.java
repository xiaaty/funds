package com.gqhmt.fss.architect.card.entiry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/27
 * Description:pos签约回调
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/27  jhz     1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_pos_back")
public class FssPosBackEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "user_name")
    private String userName;        //客户姓名

    @Column(name = "mobile_no")
    private String mobileNo;        //手机号

    @Column(name = "bank_no")
    private String bankNo;            // 银行卡号',

    @Column(name = "credt_no")
    private String credtNo;            // 身份证号',

    @Column(name = "contract_no")
    private String contractNo;            //回调编号，在富友是唯一的,

    @Column(name = "contract_st")
    private String contractSt;            //协议状态；0未生效（注：签约失败）；1已生效（注：签约成功）；2 待验证（注：等待确认）',

    @Column(name = "user_name_acntIsVerif")
    private int userNameAcntIsVerif;       //卡号户名验证结果;1通过 0 未通过',

    @Column(name = "bank_no_acntIsVerif")
    private int bankNoAcntIsVerif;            //卡号密码验证结果;1通过 0 未通过',',

    @Column(name = "credt_no_acntIsVerif")
    private int credtNoAcntIsVerif;            //户名证件号验证结果;1通过 0 未通过'

    @Column(name = "mobile_no_acntIsVerif")
    private int mobileNoAcntIsVerif;            //手机号验证结果1通过 0 未通过',

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "state")
    private String state;   //是否已使用；98010001，是；98010002：否

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCredtNo() {
        return credtNo;
    }

    public void setCredtNo(String credtNo) {
        this.credtNo = credtNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getContractSt() {
        return contractSt;
    }

    public void setContractSt(String contractSt) {
        this.contractSt = contractSt;
    }

    public int getUserNameAcntIsVerif() {
        return userNameAcntIsVerif;
    }

    public void setUserNameAcntIsVerif(int userNameAcntIsVerif) {
        this.userNameAcntIsVerif = userNameAcntIsVerif;
    }

    public int getBankNoAcntIsVerif() {
        return bankNoAcntIsVerif;
    }

    public void setBankNoAcntIsVerif(int bankNoAcntIsVerif) {
        this.bankNoAcntIsVerif = bankNoAcntIsVerif;
    }

    public int getCredtNoAcntIsVerif() {
        return credtNoAcntIsVerif;
    }

    public void setCredtNoAcntIsVerif(int credtNoAcntIsVerif) {
        this.credtNoAcntIsVerif = credtNoAcntIsVerif;
    }

    public int getMobileNoAcntIsVerif() {
        return mobileNoAcntIsVerif;
    }

    public void setMobileNoAcntIsVerif(int mobileNoAcntIsVerif) {
        this.mobileNoAcntIsVerif = mobileNoAcntIsVerif;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
}
