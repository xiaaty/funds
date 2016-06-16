package com.gqhmt.fss.architect.channelDeploy.entity;

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
 * Create at:   16/6/03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/6/02  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_channel_bank")
public class ChannelBankEntity {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                     //id

    @Column(name = "option_name")
    private String optionName;          //配置项

    @Column(name = "option_value")
    private String optionValue;         //值

    @Column(name="org_id")
    private int orgId;                  //外键

    @Column(name="conf_of_people")
    private String confOfPeople;           //配置人

    @Column(name="create_time")
    @AutoDate
    private Date createTime;             //创建时间

    @Column(name="modify_time")
    @AutoDate
    private Date modifyTime;             //创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getConfOfPeople() {
        return confOfPeople;
    }

    public void setConfOfPeople(String confOfPeople) {
        this.confOfPeople = confOfPeople;
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
