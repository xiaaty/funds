package com.gqhmt.funds.architect.order.entity;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 */
@Entity
@Table(name = "t_gq_fund_order")
public class FundOrderEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_id" ,updatable = false)
	private Long accountId;				//出账账户编号',
	
	@Column(name="to_account_id",updatable = false)
    private Long toAccountId;             //入账账户编号',

	@Column(name = "order_no", length = 30)
	private String orderNo;				//订单编号(特定生成)

	@Column(name = "order_type",updatable = false)
	private Integer orderType;			//订单类型(1-充值 2-提现 3-代偿 4-投标 5-转账 6-还款 7-流标)
	
	@Column(name = "order_frorm_id",updatable =  false)
	private Long orderFrormId;				//'订单来源编号',
	
	@Column(name = "order_source",updatable =  false)
	private Integer orderSource;		//来源类型
	
	@Column(name = "order_amount", precision = 18, scale = 2,updatable =  false)
	private BigDecimal orderAmount;		//订单金额

	@Column(name = "charge_amount",updatable = false)
	private BigDecimal chargeAmount;
	
	@Column(name = "order_state")
	private Integer orderState;				//订单状态(1-提交 2-成功 3-失败 4-无响应,5待校验码验证，6异步处理，7第三方返回值为空，需验证)',
	
	private String retCode;				//'第三方返回代码',
	
	private String retMessage;			//第三方返回信息
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", updatable =  false)
	private Date createTime;			//订单创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modify_time")
	private Date lastModifyTime;			//最后更新时间
	
	@Column(name = "thirdparty_type",updatable = false,nullable = false)
    private String thirdPartyType;			//第三方支付类型

	@Column(name="cust_id",updatable = false)
	private Long custId;       // bigint(20) DEFAULT NULL COMMENT '出账（转账）入账（其他交易）客户id',

	@Column(name="lend_no",updatable = false)
	private String  lendNo; //varchar(45) DEFAULT NULL COMMENT '出账（转账）入账（其他交易）出借编号，线上客户为空',

	@Column(name="to_cust_id",updatable = false)
	private Long  toCustId; //bigint(20) DEFAULT NULL COMMENT '入账（转账）客户id',

	@Column(name="to_pend_no",updatable = false)
	private String toPendNo; //varchar(45) DEFAULT NULL COMMENT '入账（转账）出借编号',

	@Column(name="loan_cust_id",updatable = false)
	private Long loanCustId; //bigint(20) DEFAULT NULL COMMENT '对应借款标的借款人客户id，非抵押权人，原始借款人客户id',

	@Column(name="loan_no",updatable = false)
	private String loanNo; //varchar(45) DEFAULT NULL COMMENT '投标，满标，回款，等等对应借款合同编号',

	@Column(name="new_order_type",updatable = false)
	private String newOrderType; //char(10) DEFAULT '' COMMENT '新资金类型',

	@Column(name="trade_type",updatable = false)
	private String tradeType; //char(10) DEFAULT '' COMMENT '交易类型',


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Long getOrderFrormId() {
		return this.orderFrormId;
	}

	public void setOrderFrormId(Long orderFrormId) {
		this.orderFrormId = orderFrormId;
	}

	
	public Integer getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	@Column(name = "ret_code", length = 10)
	public String getRetCode() {
		return this.retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	@Column(name = "ret_message", length = 150)
	public String getRetMessage() {
		return this.retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

    public String getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(String thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }


    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
