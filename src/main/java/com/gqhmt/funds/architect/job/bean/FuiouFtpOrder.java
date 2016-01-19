package com.gqhmt.funds.architect.job.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.fuiou.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/9 17:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/9  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_fuiou_ftp_order")
public class FuiouFtpOrder  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;                                    // bigint(20) NOT NULL主键

    @Column(name = "order_no",updatable = false)
    private String orderNo;                            //varchar(30) NULL订单号

    @Column(name = "file_size",insertable =  false)
    private int fileSize = 1;                              //int(11) NULL产生文件数量

    @Column(name="file_status",insertable =  false)
    private int fileStatus;

    @Column(name = "upload_status" ,insertable =  false)
    private int uploadStatus = 1;                          //int(10) unsigned zerofill NULL上传状态，1，新增，2部分上传，3全部上传

    @Column(name = "download_status" ,insertable =  false)
    private int downloadStatus = 1;                        //int(11) NULL回盘文件状态，1未回盘，2，部分回盘，4全部回盘，8存在拒盘文件

    @Column(name = "result_status" ,insertable =  false)
    private int resultStatus = 1;                          //int(11) NULL最后处理结果，1未处理，2待处理，3全部处理完成

    @Column(insertable =  false)
    private int result;                                 //int(11) NULL1，成功，2失败，3部分成功

    @Column
    private int type;

    @Column(name="return_result_status",insertable = false)
    private int retrunResultStatus;

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    @Column(name="input_date",insertable = false,updatable = false)
    private Date inputDate ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRetrunResultStatus() {
        return retrunResultStatus;
    }

    public void setRetrunResultStatus(int retrunResultStatus) {
        this.retrunResultStatus = retrunResultStatus;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }
}
