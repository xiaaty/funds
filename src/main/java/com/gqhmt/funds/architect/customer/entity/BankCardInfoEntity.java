package com.gqhmt.funds.architect.customer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 银行卡信息实体bean
 * 
 */
@Entity
@Table(name = "t_gq_bank_info")
public class BankCardInfoEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//客户id
	@Column(name = "cust_id",updatable = false)
	private Integer custId;
	//银行名称
	@Column(name = "bank_long_name",updatable = false)
	private String bankLongName;
	//银行简称
	@Column(name = "bank_sort_name",updatable = false)
	private String bankSortName;
	//银行卡号
	@Column(name = "bank_no",updatable = false)
	private String bankNo;
	//人银行卡 1 个人 2 公司
	@Column(name = "is_personal_card",updatable = false)
	private Integer isPersonalCard;
	//身份证号码
	@Column(name = "cert_No",updatable = false)
    private String certNo ;
	//手机号码
	@Column(name = "mobile",updatable = false)
    private String mobile ;
	//银行卡对应的客户名字
	@Column(name = "cert_Name",updatable = false)
	private String certName;
	//绑定银行卡编号
	@Column(name = "card_index",updatable = false)
	private String cardIndex;
	//开户行地区代码(富友开户用)
	@Column(name = "city_id",updatable = false)
	private String cityId;
	
	//开户行行别(富友开户用)
	@Column(name = "parent_bank_id",updatable = false)
	private String parentBankId;
	
	//创建时间
	@Column(name = "CREATE_TIME",updatable = false)
	private Date createTime;
	//创建者
	@Column(name = "CREATE_USER_ID",updatable = false)
	private Integer createUserId;
	//修改时间
	@Column(name = "MODIFY_TIME",updatable = false)
	private Date modifyTime;
	//修改者
	@Column(name = "MODIFY_USER_ID",updatable = false)
	private Integer modifyUserId;
	//是否删除(0:已删除  1：未删除)
	@Column(name = "deleted",updatable = false)
	private Integer isDel;
	//变更状态，0，已绑定，1变更中，2变更失败
	@Column(name = "change_state",updatable = false)
	private Integer changeState;
	//备注
	@Column(name = "memo",updatable = false)
	private String memo;
	
	//来源
	@Column(name = "source",updatable = false)
	private String source;
	
	// Property accessors
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

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
