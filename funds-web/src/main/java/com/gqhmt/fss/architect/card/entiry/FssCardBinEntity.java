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
 * Create at:   2016/5/27
 * Description:还款划扣
 * <p>银行卡卡表实体类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/5/27  jhz     1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_card_bin")
public class FssCardBinEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "organ_code")
    private String organCode;        //机构代码

    @Column(name = "bank_name")
    private String bankName;        //银行名称

    @Column(name = "card_name")
    private String cardName;            // 卡名',

    @Column(name = "length")
    private int length;            //长度

    @Column(name = "take_value")
    private String takeValue;            //取值

    @Column(name = "card_type")
    private String cardType;            //卡种',

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getTakeValue() {
        return takeValue;
    }

    public void setTakeValue(String takeValue) {
        this.takeValue = takeValue;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
