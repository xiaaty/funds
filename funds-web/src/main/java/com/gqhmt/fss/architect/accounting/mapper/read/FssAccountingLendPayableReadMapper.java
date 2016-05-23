package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayable;

public interface FssAccountingLendPayableReadMapper extends ReadMapper<FssAccountingLendPayable> {
	public List<FssAccountingLendPayable> queryFssAccountingLendPayable(Map map);
}
