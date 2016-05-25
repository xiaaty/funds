package com.gqhmt.extServInter.dto.account;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  于泳      1.0     1.0 Version
 */
public class CreateAccountDto extends SuperDto {


    @APIValidNull(errorCode = "90002006")
	private String cust_no;			//客户编号

	private String user_no;			//用户编号
	
	private String busi_no;			//业务编号

	@APIValidNull(errorCode = "90002009")
    private String mobile;			//手机号
	@APIValidNull(errorCode = "90002010")
    private String cert_no;			//身份证号
	@APIValidNull(errorCode = "90002011")
    private String name;			//客户姓名

    @APIValidNull(errorCode = "90002012")
    private String bank_id;			//银行类型

    @APIValidNull(errorCode = "90002013")
    private String bank_card;		//银行卡号

    @APIValidNull(errorCode = "90002014")
    private String city_id;			//开户地区
    
    private String contract_no;		//合同编号
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
    
}
