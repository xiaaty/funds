package com.gqhmt.funds.architect.customer.entity;

import javax.persistence.*;
import com.gqhmt.core.util.Application;
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
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//客户id
	@Column(name = "cust_id",updatable = false)
	private Integer custId;
	//银行名称
	@Column(name = "bank_long_name")
	private String bankLongName;
	//银行简称
	@Column(name = "bank_sort_name")
	private String bankSortName;
	//银行卡号
	@Column(name = "bank_no")
	private String bankNo;
	//人银行卡 1 个人 2 公司
	@Column(name = "is_personal_card",updatable = false)
	private Integer isPersonalCard;
	//身份证号码
	@Column(name = "cert_No",updatable = false)
    private String certNo ;
	//手机号码
	@Column(name = "mobile")
    private String mobile ;
	//银行卡对应的客户名字
	@Column(name = "cert_Name",updatable = false)
	private String certName;
	//绑定银行卡编号
	@Column(name = "card_index",updatable = false)
	private String cardIndex;
	//开户行地区代码(富友开户用)
	@Column(name = "city_id")
	private String cityId;
	
	//开户行行别(富友开户用)
	@Column(name = "parent_bank_id")
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
	@Column(name = "MODIFY_USER_ID")
	private Integer modifyUserId;
	//是否删除(0:已删除  1：未删除)
	@Column(name = "deleted")
	private Integer isDel;
	//变更状态，0，已绑定，1变更中，2变更失败
	@Column(name = "change_state")
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

	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getBankLongName() {
		return this.bankLongName;
	}

	public void setBankLongName(String bankLongName) {
		this.bankLongName = bankLongName;
	}

	public String getBankSortName() {
		return this.bankSortName;
	}

	public void setBankSortName(String bankSortName) {
		this.bankSortName = Application.getInstance().getDictName("9703"+this.getParentBankId());
	}

	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getIsPersonalCard() {
		return this.isPersonalCard;
	}

	public void setIsPersonalCard(Integer isPersonalCard) {
		this.isPersonalCard = isPersonalCard;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getCardIndex() {
		return this.cardIndex;
	}

	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex;
	}
	
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }
    
    
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    
    public String getParentBankId() {
        return parentBankId;
    }

    public void setParentBankId(String parentBankId) {
        this.parentBankId = parentBankId;
    }


	public Integer getChangeState() {
		return changeState;
	}

	public void setChangeState(Integer changeState) {
		this.changeState = changeState;
	}

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
