package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTransApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:31
 * Description:还款代扣主表
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  柯禹来      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_repayment_parent")
public class FssRepaymentParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	@Column(name = "seq_no",updatable = false)
	private String seqNo;	//流水号
    @Column(name = "trade_type",updatable = false)
    private String tradeType ;  //交易类型    
    @Column(name = "trade_count",updatable = false)
    private Integer tradeCount;//交易总数量（条数）
    @Column(name = "success_count")
    private Integer successCount;//执行成功条数
    @Column(name = "filed_count")
    private Integer filedCount;//执行失败条数
    @Column(name = "state")
    private String state ;  //执行状态
    @Column(name = "result_state")
    private String resultState ;  //还款划扣结果
    @Column(name = "amt",updatable = false)
    private BigDecimal amt;     //还款金额 
    @Column(name = "pay_amt")
    private BigDecimal payAmt;     //实际代扣金额 
    @Column(name = "create_time",updatable = false)
    private Date createTime;    //创建时间      
    @Column(name = "motify_time")
    private Date motifyTime;   //修改时间          
    @Column(name = "mchn_parent",updatable = false)
    private String mchnParent ; //父商户号       
    @Column(name = "mchn_child",updatable = false)
    private String mchnChild ;  //子商户号
    @Column(name = "remark")
    private String remark ;  //备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFiledCount() {
		return filedCount;
	}

	public void setFiledCount(Integer filedCount) {
		this.filedCount = filedCount;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	
}
