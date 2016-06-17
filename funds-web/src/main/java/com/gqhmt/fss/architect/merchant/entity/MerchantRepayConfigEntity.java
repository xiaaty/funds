package com.gqhmt.fss.architect.merchant.entity;

import com.gqhmt.annotations.AutoDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/16.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/16.  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_mchn_repayconfig")
public class MerchantRepayConfigEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                         //id

    @Column(name = "mchn_no")
    private String mchnNo;                 //商户号

    @Column(name = "trade_type")
    private String tradeType;              //交易类型

    @Column(name = "repay_type")
    private String repayType;              //回盘类型

    @Column(name = "mchn_url")
    private String mchnUrl;                //商户地址

    @Column(name = "repay_className")
    private String repayClassname;         //回盘地址 -> classAddress

    @Column(name = "create_time")
    @AutoDate
    private Date createTime;                //创建时间

    @Column(name = "modify_time")
    @AutoDate
    private Date modifyTime;                //修改时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMchnNo() {
        return mchnNo;
    }

    public void setMchnNo(String mchnNo) {
        this.mchnNo = mchnNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getMchnUrl() {
        return mchnUrl;
    }

    public void setMchnUrl(String mchnUrl) {
        this.mchnUrl = mchnUrl;
    }

    public String getRepayClassname() {
        return repayClassname;
    }

    public void setRepayClassname(String repayClassname) {
        this.repayClassname = repayClassname;
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
}
