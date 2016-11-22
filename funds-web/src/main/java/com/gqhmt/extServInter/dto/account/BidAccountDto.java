package com.gqhmt.extServInter.dto.account;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/22
 * Description:标的开户实体类
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/22  keyulai      1.0     1.0 Version
 */
public class BidAccountDto extends SuperDto {


    @APIValidNull(errorCode = "90002006")
    private String cust_no;//借款人客户编号

    @APIValidNull(errorCode = "90002011")
    private String cust_name;//借款人客户姓名

    @APIValidNull(errorCode = "90002010")
    private String cert_no;	//身份证号

    @APIValidNull(errorCode = "90002022")
	private String contract_no;//借款合同号

    @APIValidNull(errorCode = "90002009")
    private String mobile_phone;//手机号

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }
}
