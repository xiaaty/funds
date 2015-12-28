package com.gqhmt.business.architect.loan.mapper.read;

import java.util.List;

import com.gqhmt.business.architect.loan.entity.InvestmentInfo;
import com.gqhmt.core.mybatis.ReadMapper;

public interface InvestmentReadMapper extends ReadMapper<InvestmentInfo> {

	public List<InvestmentInfo> queryMonthRepaymentList();
	
}
