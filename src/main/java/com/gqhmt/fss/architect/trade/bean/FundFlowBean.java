package com.gqhmt.fss.architect.trade.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金数据传输对象
 * com.gq.p2p.account.Bean
 * @className FundFlowBean<br/> 
 * @author com.gqhmt.fss.architect.trade.bean
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class FundFlowBean implements java.io.Serializable{

    private Integer fundType;
    private BigDecimal amount;
    private Date create_time;
	private String ym;
	public Integer getFundType() {
		return fundType;
	}
	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	
}
