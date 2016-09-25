package com.gqhmt.fss.architect.account.entity;

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
 * Create at:   2016/08/03.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/08/03.  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_fuiou_account_info_file")
public class FuiouAccountInfoFileEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trade_type")
    private String tradeType;           //交易类型：  1.冻结/解冻 - DJJD . 2 .转账 - zz . 3.划拨 - HB . 4.委托充值 - WTCZ . 5.委托提现 - WTTX . 6.预授权交易 - YSQ

    //不存储创建时间，仅做取文件用， 数据库自动生成创建时间;
    private Date createTime;

    @Column(name = "remark")
    private String remark;              //备注

    @Column(name = "boolean_type")
    private String booleanType;         //抓取状态 -1为抓取失败， 其他为抓取成功

    @Column(name = "file_path")
    private String filePath;            //文件本地路径

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBooleanType() {
        return booleanType;
    }

    public void setBooleanType(String booleanType) {
        this.booleanType = booleanType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
