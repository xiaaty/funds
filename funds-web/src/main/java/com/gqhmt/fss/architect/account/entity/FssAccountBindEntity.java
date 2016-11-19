package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author kyl
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/27.
 * Description:账户映射表实体类
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/3.  kyl        1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_account_bind")
public class FssAccountBindEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "busi_id")
    private Long busiId;//个人结算账户（冠E通客户id），标的账户（bid_id）

    @Column(name = "busi_type")
    private Integer busiType;//账户类型,1借款账户、2线下出借账户、3线上出借账户、96应付款账户、50归集账户，60，费用账户，70运营账户，90标的账户

    @Column(name = "acc_no")
    private String accNo;//支付系统账户编号

    @Column(name = "status")
    private String status;

    @Column(name = "trade_type")
    private String tradeType;

    @Column(name = "seq_no")
    private String seqNo;

    @Column(name = "open_acc_time")
    private String openAccTime;

    @Column(name = "contract_no")
    private String contractNo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "chnl")
    private String chnl;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "moblie")
    private String moblie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getOpenAccTime() {
        return openAccTime;
    }

    public void setOpenAccTime(String openAccTime) {
        this.openAccTime = openAccTime;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public String getChnl() {
        return chnl;
    }

    public void setChnl(String chnl) {
        this.chnl = chnl;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }
}
