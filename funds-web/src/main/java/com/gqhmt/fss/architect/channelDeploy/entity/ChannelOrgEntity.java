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
 * Create at:   16/6/02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/6/02  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_channel_org")
public class ChannelOrgEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                     //id

    @Column(name = "channel_code")
    private String channelCode;         //渠道编码

    @Column(name = "channel_name")
    private String channelName;         //渠道名称

    @Column(name = "channel_condition")
    private String channelCondition;    //渠道状态

    @Column(name = "channel_type")
    private String channelType;        //渠道方式

    @Column(name = "channel_payment_mode")
    private String channelPaymentMode;  //渠道支付模式

    @Column(name = "create_time")
    @AutoDate
    private Date createTime;            //创建时间

    @Column(name = "modify_time")
    @AutoDate
    private Date modifyTime;            //修改时间

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getChannelCode()
    {
        return channelCode;
    }

    public void setChannelCode(String channelCode)
    {
        this.channelCode = channelCode;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getChannelCondition()
    {
        return channelCondition;
    }

    public void setChannelCondition(String channelCondition)
    {
        this.channelCondition = channelCondition;
    }

    public String getChannelType()
    {
        return channelType;
    }

    public void setChannelType(String channelType)
    {
        this.channelType = channelType;
    }

    public String getChannelPaymentMode()
    {
        return channelPaymentMode;
    }

    public void setChannelPaymentMode(String channelPaymentMode)
    {
        this.channelPaymentMode = channelPaymentMode;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime)
    {
        this.modifyTime = modifyTime;
    }

}
