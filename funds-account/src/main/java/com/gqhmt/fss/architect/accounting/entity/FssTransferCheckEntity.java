package com.gqhmt.fss.architect.accounting.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/24
 * Description:划拨对账表实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/24  jhz     1.0     1.0 Version
 */
public class FssTransferCheckEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // bigint

    @Column(name="seq_no")
    private String seqNo;                  // '交易流水号

    @Column(name="trade_date")
    private String tradeDate;                  // 交易时间

    @Column(name="acc_water")
    private String accWater;                  // 记账流水

    @Column(name="acc_date")
    private String accDate;           // 记账日期

    @Column(name="trade_amt")
    private BigDecimal tradeAmt;            // 交易金额(元)

    @Column(name="from_account")
    private String fromAccount;     // 出账账户',

    @Column(name="from_acc_name")
    private String fromAccName;     // 出账用户名称

    @Column(name="to_account")
    private String toAccount;     // 入账用户

    @Column(name="to_acc_name")
    private String toAcName;     // 入账用户名

    @Column(name="to_user_name")
    private String toUserNname;     //入账用户名称

    @Column(name="contract_no")
    private String contractNo;     // 业务合同号

    @Column(name="item_no")
    private String itemNo;     // 项目编号

    @Column(name="remark")
    private String remark;     // 备注

    @Column(name="trade_status")
    private String tradeStatus;     // 状态

    @Column(name="create_time")
    private Date createTime;        //创建时间

    @Column(name="modify_time")
    private Date modifyTime;        //修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToAcName() {
        return toAcName;
    }

    public void setToAcName(String toAcName) {
        this.toAcName = toAcName;
    }

    public String getToUserNname() {
        return toUserNname;
    }

    public void setToUserNname(String toUserNname) {
        this.toUserNname = toUserNname;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
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

    public String getAccWater() {
        return accWater;
    }

    public void setAccWater(String accWater) {
        this.accWater = accWater;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromAccName() {
        return fromAccName;
    }

    public void setFromAccName(String fromAccName) {
        this.fromAccName = fromAccName;
    }


}
