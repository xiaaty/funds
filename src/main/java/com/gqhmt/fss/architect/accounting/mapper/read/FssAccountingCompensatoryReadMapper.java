package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoanCompensatory;

public interface FssAccountingCompensatoryReadMapper extends ReadMapper<FssAccountingLoanCompensatory>{
	public List<FssAccountingLoanCompensatory> queryFssAccountingLoanCompensatory(Map map);
}
