package com.gqhmt.fss.architect.fuiouFtp.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuyonf on 15/4/6.
 */

@Entity
@Table(name="t_fuiou_ftp_file")
public class FuiouUploadFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;                                    // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键\n',

    @Column(name="business_code" ,updatable = false)
    private String businessCode;                       // varchar(32) DEFAULT NULL COMMENT '业务代码\n',

    @Column(name="entrust_date" ,updatable = false)
    private String entrustDate;                        //char(8) DEFAULT NULL COMMENT '委托日期\n',

    @Column(name="mCode" ,updatable = false)
    private String mCode;                               // varchar(32) DEFAULT NULL COMMENT '商户代码\n',

    @Column(name="detail_num" ,updatable = false)
    private int detailNum;                             // int(11) DEFAULT NULL COMMENT '明细数量\n',

    @Column(name="totle_amt" ,updatable = false)
    private BigDecimal totleAmt;                       // decimal(11,2) DEFAULT NULL COMMENT '汇总金额\n',

    @Column(updatable = false)
    private String no;                                  // char(4) DEFAULT NULL COMMENT '当日文件序号\n',
    @Column
    private int state;                                  // int(11) DEFAULT NULL COMMENT '状态，1新增，2文件已传送到ftp，3回盘文件已取回，4已完成文件分析处理\n',

    @Column(name="order_no" ,updatable = false)
    private String orderNo;                            //订单号

    @Column(name="input_date",insertable = false,updatable = false)
    private Date inputDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public BigDecimal getTotleAmt() {
        return totleAmt;
    }

    public void setTotleAmt(BigDecimal totleAmt) {
        this.totleAmt = totleAmt;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(int detailNum) {
        this.detailNum = detailNum;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
}
