package com.gqhmt.fss.architect.account.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 银行卡信息实体bean
 * 
 */
@Entity
@Table(name = "t_gq_bank_info")
public class BankCardinfoEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	//客户id
	private Integer custId;
	//银行简称
	private String bankLongName;
	private String bankSortName;
	//银行卡号
	private String bankNo;
	//人银行卡 1 个人 2 公司
	private Integer isPersonalCard;
	private Date createTime;
	private Integer createUserId;
	private Date modifyTime;
	private Integer modifyUserId;
	//绑定银行卡编号
	private String cardIndex;

    private String certNo ;
    private String mobile ;
	private String certName;
	
	//开户行地区代码(富友开户用)
	private String cityId;
	
	//开户行行别(富友开户用)
	private String parentBankId;

	private Integer changeState;

	private String memo;


	
	
	

	// Property accessors
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "cust_id")
	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	@Column(name = "bank_long_name", length = 100)
	public String getBankLongName() {
		return this.bankLongName;
	}

	public void setBankLongName(String bankLongName) {
		this.bankLongName = bankLongName;
	}

	@Column(name = "bank_sort_name", length = 20)
	public String getBankSortName() {
		return this.bankSortName;
	}

	public void setBankSortName(String bankSortName) {
		this.bankSortName = bankSortName;
	}

	@Column(name = "bank_no", length = 20)
	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "is_personal_card")
	public Integer getIsPersonalCard() {
		return this.isPersonalCard;
	}

	public void setIsPersonalCard(Integer isPersonalCard) {
		this.isPersonalCard = isPersonalCard;
	}

	@Column(name = "CREATE_TIME", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "MODIFY_TIME", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "MODIFY_USER_ID")
	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Column(name = "card_index", length = 100)
	public String getCardIndex() {
		return this.cardIndex;
	}

	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex;
	}
	
	@Column(name = "cert_no", length = 20)
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    @Column(name = "mobile", length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Column(name = "cert_Name", length = 30)
    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }
    
    
    @Column(name = "city_id", length = 4)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    
    @Column(name = "parent_bank_id", length = 4)
    public String getParentBankId() {
        return parentBankId;
    }

    public void setParentBankId(String parentBankId) {
        this.parentBankId = parentBankId;
    }


	@Column(name = "change_state",insertable =  false)
	public Integer getChangeState() {
		return changeState;
	}

	public void setChangeState(Integer changeState) {
		this.changeState = changeState;
	}

	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
