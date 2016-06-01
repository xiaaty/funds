package com.gqhmt.extServInter.dto.loan;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

import java.util.List;

public class RepaymentDto extends SuperDto{

	@APIValidNull(errorCode = "90004030")
	private List<RepaymentChildDto> repay_list;


	public List<RepaymentChildDto> getRepay_list() {
		return repay_list;
	}

	public void setRepay_list(List<RepaymentChildDto> repay_list) {
		this.repay_list = repay_list;
	}
}
