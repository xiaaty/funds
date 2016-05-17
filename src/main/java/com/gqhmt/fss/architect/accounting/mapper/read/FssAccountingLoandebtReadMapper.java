package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebt;

public interface FssAccountingLoandebtReadMapper extends ReadMapper<FssAccountingLoandebt> {
	public List<FssAccountingLoandebt> queryFssAccountingLoandebt(Map map);
}
