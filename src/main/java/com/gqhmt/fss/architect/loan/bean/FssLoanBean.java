package com.gqhmt.fss.architect.loan.bean;

/**
 * 线下代扣导出数据传输对象
 */
public class FssLoanBean {
//    客户姓名、身份证号、合同号、账户、金额
	private String custName;          
	private String certNo;          
    private String accNo;                            
    private String contractNo;
    private String contractAmt;                            
    private String mchnChild;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractAmt() {
		return contractAmt;
	}
	public void setContractAmt(String contractAmt) {
		this.contractAmt = contractAmt;
	}
	public String getMchnChild() {
		return mchnChild;
	}
	public void setMchnChild(String mchnChild) {
		this.mchnChild = mchnChild;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
}
