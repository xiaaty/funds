package com.gqhmt.fss.architect.accounting.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 对账表
 * @author jhz
 */
@Entity
@Table(name = "t_gq_fss_check_accounting")
public class FssCheckAccountingEntity {

    @Id
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;				//自增主键

    @Column(name = "order_no")
    private String orderNo;   		//交易流水号

    @Column(name = "trade_time")
    private String tradeTime;    	//交易时间

    @Column(name = "accounting_no")
    private String accountingNo; 		//记账流水

	@Column(name = "accounting_time")
    private String accountingTime;    	//记账日期

    @Column(name = "recharge_way")
    private String rechargeWay;        //充值方式

    @Column(name = "amount")
    private String amount;  //交易金额(元)

	@Column(name = "cust_id")
	private String custId;        //账户

	@Column(name = "acc_no")
	private String accNo;        //账户

	@Column(name = "acc_name")
	private String accName;        //用户名

	@Column(name = "user_name")
	private String userName;       //用户名称

	@Column(name = "to_cust_id")
	private String toCustId;        //入账账户

	@Column(name = "to_acc_no")
	private String toAccNo;        //入账账户

	@Column(name = "to_acc_name")
	private String toAccName;        //入账账户

	@Column(name = "to_user_name")
	private String toUserName;        //入账用户名称

	@Column(name = "contract_no")
	private String contractNo;        //业务合同号

	@Column(name = "item_no")
	private String itemNo;        //项目编号

	@Column(name = "remark")
	private String remark;        //备注

	@Column(name = "status")
	private String status;        //状态

	@Column(name = "trade_type")
	private String tradeType;        //交易类型

	@Column(name = "accounting_status")
	private String accountingStatus;        //是否对账

	@Column(name = "accounting_result")
	private String accountingResult;        //对账结果

	@Column(name="abnormal_state")
	private String abnormalState; //异常状态,'98080001'正常,'98080002'异常

	@Column(name = "create_time")
	private Date createTime;		//创建时间

    @Column(name = "modify_time")
    private Date modifyTime;		//更新时间

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


	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(String accountingNo) {
		this.accountingNo = accountingNo;
	}

	public String getAccountingTime() {
		return accountingTime;
	}

	public void setAccountingTime(String accountingTime) {
		this.accountingTime = accountingTime;
	}

	public String getRechargeWay() {
		return rechargeWay;
	}

	public void setRechargeWay(String rechargeWay) {
		this.rechargeWay = rechargeWay;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}

	public String getToAccName() {
		return toAccName;
	}

	public void setToAccName(String toAccName) {
		this.toAccName = toAccName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getAccountingStatus() {
		return accountingStatus;
	}

	public void setAccountingStatus(String accountingStatus) {
		this.accountingStatus = accountingStatus;
	}

	public String getAccountingResult() {
		return accountingResult;
	}

	public void setAccountingResult(String accountingResult) {
		this.accountingResult = accountingResult;
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getToCustId() {
		return toCustId;
	}

	public void setToCustId(String toCustId) {
		this.toCustId = toCustId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getAbnormalState() {
		return abnormalState;
	}

	public void setAbnormalState(String abnormalState) {
		this.abnormalState = abnormalState;
	}
}
