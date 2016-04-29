package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayableDetail;

public interface FssAccountingLendPayableDetailReadMapper extends ReadMapper<FssAccountingLendPayableDetail> {
	public List<FssAccountingLendPayableDetail> queryFssAccountingLendPayableDetail(Map map);
}
