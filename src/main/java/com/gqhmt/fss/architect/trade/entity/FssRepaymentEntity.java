package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTransApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:31
 * Description:回款
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_repayment")
public class FssRepaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name = "parent_id") 
	private Long  parentId ;    
	
    @Column(name = "acc_no") 
    private String  accNo ;     //账号          
    
    @Column(name = "trade_type")
    private String tradeType ;  //交易类型    

    @Column(name = "create_time")
    private Date createTime;    //创建时间      

    @Column(name = "motify_time")
    private Date motifyTime;   //修改时间          

    @Column(name = "amt")
    private BigDecimal amt;     //还款金额 

    @Column(name = "state")
    private String state ;  //执行状态
    
    @Column(name = "result_state")
    private String resultState ;  //还款划扣结果
    
    @Column(name = "seq_no")
    private String seqNo;	//流水号
    
    @Column(name = "serial_number")
    private String serialNumber;//序列号
    
    @Column(name = "contract_id")
    private String contractId;//合同id
    
    @Column(name = "contract_no")
    private String contractNo;//合同id
    
    @Column(name = "mchn_parent")
    private String mchnParent ; //父商户号       

    @Column(name = "mchn_child")
    private String mchnChild ;  //子商户号
    
    @Column(name = "remark")
    private String remark ;  //备注
    
    @Column(name = "resp_code")
    private String respCode ;  //交易标识
    
    @Column(name = "resp_msg")
    private String respMsg ;  //异常信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getMotifyTime() {
		return motifyTime;
	}

	public void setMotifyTime(Date motifyTime) {
		this.motifyTime = motifyTime;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getMchnParent() {
		return mchnParent;
	}

	public void setMchnParent(String mchnParent) {
		this.mchnParent = mchnParent;
	}

	public String getMchnChild() {
		return mchnChild;
	}

	public void setMchnChild(String mchnChild) {
		this.mchnChild = mchnChild;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResultState() {
		return resultState;
	}

	public void setResultState(String resultState) {
		this.resultState = resultState;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	
}
