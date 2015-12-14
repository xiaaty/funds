package com.gqhmt.fss.architect.order.entity;



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
	
	private Long id;
	private Long accountId;
    private Long toAccountId;             //to_account_id
	private String orderNo;
	private Integer orderType;
	private Long orderFrormId;
	private Integer orderSource;
	private BigDecimal orderAmount;
	private Integer orderState;
	private String retCode;
	private String retMessage;
	private Date createTime;
	private Date lastModifyTime;
//    private ThirdPartyType thirdPartyType;
    private BigDecimal chargeAmount;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "account_id" ,updatable = false)
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "order_no", length = 30)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "order_type",updatable = false)
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

//    @Column(name = "thirdparty_type",updatable = false,nullable = false)
//    @Type(type = "com.gq.funds.type.ThirdPartyUserType")
//    public ThirdPartyType getThirdPartyType() {
//        return thirdPartyType;
//    }
//
//    public void setThirdPartyType(ThirdPartyType thirdPartyType) {
//        this.thirdPartyType = thirdPartyType;
//    }

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
