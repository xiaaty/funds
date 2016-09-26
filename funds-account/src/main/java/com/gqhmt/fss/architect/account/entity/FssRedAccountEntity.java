package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 红包账户实体类
 */
@Entity
@Table(name = "t_gq_fss_red_account")
public class FssRedAccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cust_id")
    private long custId;    //客户编号

    @Column(name = "account_name")
    private String accountName;//账户名称

    @Column(name = "account_type")
    private String accountType;      //账户类型

    @Column(name = "trade_type")
    private String tradeType;   //交易类型

    @Column(name = "create_time")
    private Date createTime; //创建时间

    @Column(name = "creater")
    private String creater; //创建人

    @Column(name = "modify_time")
    private Date modifyTime; //修改时间

    @Column(name = "updater")
    private String updater; //修改人

    @Column(name = "is_valid")
    private String isValid; //是否有效


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
