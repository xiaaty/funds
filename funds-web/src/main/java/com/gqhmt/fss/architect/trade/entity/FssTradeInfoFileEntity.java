package com.gqhmt.fss.architect.trade.entity;

import com.gqhmt.annotations.DateType;
import com.gqhmt.annotations.DateTypeEnum;
import com.gqhmt.annotations.NumberType;
import com.gqhmt.annotations.NumberTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/6.  xdw         1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_info_file")
public class FssTradeInfoFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "file_name")             //文件名
    private String fileName;

    @Column(name = "create_time")           //创建时间
    private Date createTime;

    @Column(name = "upload_sts")            //抓取状态 0为失败 1为成功
    private String uploadSts;

    @Column(name = "file_path")             //file文件本地路径
    private String filePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUploadSts() {
        return uploadSts;
    }

    public void setUploadSts(String uploadSts) {
        this.uploadSts = uploadSts;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
