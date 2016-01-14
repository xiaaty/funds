package com.gqhmt.business.architect.invest.mapper.read;

import com.gqhmt.business.architect.invest.entity.InvestmentInfo;
import com.gqhmt.core.mybatis.ReadMapper;

import java.util.List;

public interface InvestmentReadMapper extends ReadMapper<InvestmentInfo> {

	public List<InvestmentInfo> queryMonthRepaymentList();
	
}
