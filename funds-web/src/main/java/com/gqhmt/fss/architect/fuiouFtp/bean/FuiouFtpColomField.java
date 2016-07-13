package com.gqhmt.fss.architect.fuiouFtp.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuyonf on 15/4/6.
 */
@Entity
@Table(name="t_fuiou_ftp_field")
public class FuiouFtpColomField implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;                                      // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键\n',

    //from_account_Id

    @Column(name = "from_account_Id",updatable = false)
    private  Long fromAccountId;

    @Column(name="from_userName",updatable = false)
    private String  fromUserName ;                        // varchar(32) DEFAULT NULL COMMENT '原用户名\n',

    @Column(name="from_cn_userName",updatable = false)
    private String  fromCnUserName;                     // varchar(32) DEFAULT NULL COMMENT '原中文名称\n',

    //to_account_Id
    @Column(name="to_account_Id",updatable = false)
    private Long toAccountId;

    @Column(name="to_userName",updatable = false)
    private String toUserName;                           // varchar(32) DEFAULT NULL COMMENT '目标用户名\n',

    @Column(name="to_cn_userName",updatable = false)
    private String toCnUserName;                        // varchar(32) DEFAULT NULL COMMENT '目标中文名称\n',

    @Column(updatable = false)
    private BigDecimal amt;                               //double(11,2) DEFAULT NULL COMMENT '交易金额',

    @Column(updatable = false)
    private String rem;                                   // varchar(32) DEFAULT NULL COMMENT '备注\n',

    @Column(name="contract_no",updatable = false)
    private String contractNo;                           // varchar(32) DEFAULT NULL COMMENT '预授权合同号\n',

    @Column(name = "state")
    private int state;                                    // int(11) DEFAULT '1' COMMENT '1，新增，2提交中，3已提交，4结果处理中，5已完成',

    @Column(name="file_id")
    private Long fileId;                                 // bigint(20) DEFAULT NULL COMMENT 'file文件表id',

    @Column(name="seq_no")
    private String seqNo;                                // char(6) DEFAULT NULL COMMENT '文件序号\n',

    @Column(name="order_id",updatable = false)
    private Long orderId;                                    //

//    order_no
    @Column(name="order_no",updatable = false)
    private String orderNo;


    @Column(name="return_Code")
    private String returnCode;

    @Column(name="return_msg")
    private String returnMsg;

    @Column(name="business_code")
    private String businessCode;
    
    @Column(name="input_date",updatable = false)
    private Date inputDate;
    
    @Column(name="feild_order_no")
    private String feildOrderNo;
    
    @Column(name="feild_order_no_his")
    private String feildOrderNoHis;
    
    @Column(name="tender_id",updatable = false)
    private Long tenderId;

    @Column(name="type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromCnUserName() {
        return fromCnUserName;
    }

    public void setFromCnUserName(String fromCnUserName) {
        this.fromCnUserName = fromCnUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getToCnUserName() {
        return toCnUserName;
    }

    public void setToCnUserName(String toCnUserName) {
        this.toCnUserName = toCnUserName;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getFeildOrderNo() {
		return feildOrderNo;
	}

	public void setFeildOrderNo(String feildOrderNo) {
		this.feildOrderNo = feildOrderNo;
	}

	public String getFeildOrderNoHis() {
		return feildOrderNoHis;
	}

	public void setFeildOrderNoHis(String feildOrderNoHis) {
		this.feildOrderNoHis = feildOrderNoHis;
	}

	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}
    
}
