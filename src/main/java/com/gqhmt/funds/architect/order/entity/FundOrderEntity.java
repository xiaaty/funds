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
	private Long accountId;
	@Column(name = "to_account_id" ,updatable = false)
	private Long toAccountId;             //to_account_id

	@Column(name = "order_no", length = 30,updatable = false)
	private String orderNo;

	@Column(name = "order_type",updatable = false)
	private Integer orderType;

	@Column(name = "order_frorm_id",updatable = false)
	private Long orderFrormId;

	@Column(name = "order_Source",updatable = false)
	private Integer orderSource;

	@Column(name = "order_amount",updatable = false)
	private BigDecimal orderAmount;

	private Integer orderState;
	private String retCode;
	private String retMessage;

	@Column(name = "order_frorm_id",updatable = false)
	private Date createTime;
	private Date lastModifyTime;
	@Column(name = "thirdparty_type",updatable = false,nullable = false)
    private String thirdPartyType;

	@Column(name = "order_frorm_id",updatable = false)
    private BigDecimal chargeAmount;

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

	@Column(name = "order_frorm_id",updatable =  false)
	public Long getOrderFrormId() {
		return this.orderFrormId;
	}

	public void setOrderFrormId(Long orderFrormId) {
		this.orderFrormId = orderFrormId;
	}

	@Column(name = "order_source",updatable =  false)
	public Integer getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	@Column(name = "order_amount", precision = 18, scale = 2,updatable =  false)
	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Column(name = "order_state")
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 0,updatable =  false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modify_time", length = 0)
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

    @Column(name="to_account_id",updatable = false)
    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }


    @Column(name = "charge_amount",updatable = false)
    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
