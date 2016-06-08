package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingCompanyIncome;

public interface FssAccountingCompanyIncomeReadMapper extends ReadMapper<FssAccountingCompanyIncome>{
	public List<FssAccountingCompanyIncome> queryFssAccountingCompanyIncome(Map map);
}
