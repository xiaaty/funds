package com.gqhmt.extServInter.dto.loan;

import java.util.List;

import com.gqhmt.extServInter.dto.SuperDto;

public class RepaymentDto extends SuperDto{
	
	private List<RepaymentChildDto> list;

	public List<RepaymentChildDto> getList() {
		return list;
	}

	public void setList(List<RepaymentChildDto> list) {
		this.list = list;
	}
	
}
