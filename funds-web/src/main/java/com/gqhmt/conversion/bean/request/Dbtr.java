package com.gqhmt.conversion.bean.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Req")
@XmlType(name = "", propOrder = {})
public class Dbtr {

	private String Nm;
	private String PorO;
	private String BirthDt;
	private String PrvcOfBirth;
	private String CityOfBirth;
	private String CtryOfBirth;
	private String IDType;
	private String IDNo;
	private String ContactNo;
	private String ContractNo;

	public String getNm() {
		return Nm;
	}

	public void setNm(String nm) {
		Nm = nm;
	}

	public String getPorO() {
		return PorO;
	}

	public void setPorO(String porO) {
		PorO = porO;
	}

	public String getBirthDt() {
		return BirthDt;
	}

	public void setBirthDt(String birthDt) {
		BirthDt = birthDt;
	}

	public String getPrvcOfBirth() {
		return PrvcOfBirth;
	}

	public void setPrvcOfBirth(String prvcOfBirth) {
		PrvcOfBirth = prvcOfBirth;
	}

	public String getCityOfBirth() {
		return CityOfBirth;
	}

	public void setCityOfBirth(String cityOfBirth) {
		CityOfBirth = cityOfBirth;
	}

	public String getCtryOfBirth() {
		return CtryOfBirth;
	}

	public void setCtryOfBirth(String ctryOfBirth) {
		CtryOfBirth = ctryOfBirth;
	}

	public String getIDType() {
		return IDType;
	}

	public void setIDType(String IDType) {
		this.IDType = IDType;
	}

	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String IDNo) {
		this.IDNo = IDNo;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}

	public String getContractNo() {
		return ContractNo;
	}

	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
}
