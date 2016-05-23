package com.gqhmt.extServInter.dto.trade;

import com.gqhmt.extServInter.dto.Response;

public class WithholdResponse extends Response  {
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
