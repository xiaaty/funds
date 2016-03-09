package com.gqhmt.fss.architect.customer.entity;
import javax.persistence.*;
import java.util.Date;

/**
 * Filename:    com.gq.funds.entity.ChangeCardEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/30 16:01
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/30  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_changeCard_record")
public class FssChangeCardEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "cust_id",updatable = false)
    private Long custId;                               // bigint(20) DEFAULT NULL COMMENT '客户id',

    @Column(name="cert_type",updatable = false)
    private int certType;                              // int(11) DEFAULT NULL COMMENT '证件类型	',

    @Column(name="cert_no",updatable = false)
    private String certNo;                             // varchar(45) DEFAULT NULL COMMENT '证件号码',

    @Column(name="bank_type",updatable = false)
    private String bankType;                           // varchar(45) DEFAULT NULL COMMENT '银行类别',

    @Column(name="bank_name",updatable = false)
    private String bankName;                           // varchar(45) DEFAULT NULL COMMENT '银行名称',

    @Column(name = "bank_city",updatable = false)
    private String bankCity;                                // varchar(45) DEFAULT NULL COMMENT '地区码\n',

    @Column(name = "bank_add")
    private String bankAdd;

    @Column(name="card_no",updatable = false)
    private String cardNo;                             // varchar(45) DEFAULT NULL COMMENT '银行卡号',

    @Column(name = "cust_name",updatable = false)
    private String custName;                           // varchar(45) DEFAULT NULL COMMENT '客户姓名',


    @Column(name = "state")
    private int state;                                  // int(11) DEFAULT NULL COMMENT '状态，1变更中，2成功，3失败',

    @Column(name = "trade_state")
    private int tradeState;                            // int(11) DEFAULT NULL COMMENT '交易状态，1申请，2图片已上传，3数据传到富友，4富友返回成功 or 失败，5，同步到银行卡信息表中',

    @Column(name="b_bank_info_id",updatable = false)
    private Long bBankInfoId;                        // bigint(20) DEFAULT NULL COMMENT '变更前银行卡信息表id',

    @Column(name="a_bank_info_id",insertable = false)
    private Long aBankInfoId;                        // bigint(20) DEFAULT NULL COMMENT '变更后银行信息表id',

    @Column(name="create_time",updatable = false)
    private Date createTime;                           // datetime DEFAULT NULL COMMENT '录入时间',

    @Column(name="modify_time")
    private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',

    @Column(name="create_user_id",updatable = false)
    private Long createUserId;                        // bigint(20) DEFAULT NULL COMMENT '申请人',

    @Column(name="order_no",insertable = false)
    private String orderNo;

    @Column(name="file_path",updatable = false)
    private String filePath;

    @Column(name = "resp_code",insertable = false)
    private String respCode;

    @Column(name = "resp_msg",insertable = false)
    private String respMsg;

    @Column(name = "type")
    private Integer type;

    @Column(name = "mobile")
    private String mobile;


    @Column(name="pass_time",insertable = false)
    private Date passTime;

    @Column(name = "pass_user_id",insertable = false)
    private Long passUserId;

    @Column(name="effect_time",insertable = false)
    private Date effectTime;

    @Column(name="seq_no")
    private String seqNo;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }



    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTradeState() {
        return tradeState;
    }

    public void setTradeState(int tradeState) {
        this.tradeState = tradeState;
    }

    public Long getbBankInfoId() {
        return bBankInfoId;
    }

    public void setbBankInfoId(Long bBankInfoId) {
        this.bBankInfoId = bBankInfoId;
    }

    public Long getaBankInfoId() {
        return aBankInfoId;
    }

    public void setaBankInfoId(Long aBankInfoId) {
        this.aBankInfoId = aBankInfoId;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }



    public String getBankAdd() {
        return bankAdd;
    }

    public void setBankAdd(String bankAdd) {
        this.bankAdd = bankAdd;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Long getPassUserId() {
        return passUserId;
    }

    public void setPassUserId(Long passUserId) {
        this.passUserId = passUserId;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}
