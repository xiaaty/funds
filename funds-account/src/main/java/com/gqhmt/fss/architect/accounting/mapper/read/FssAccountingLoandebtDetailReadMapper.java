package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebtDetail;

public interface FssAccountingLoandebtDetailReadMapper extends ReadMapper<FssAccountingLoandebtDetail> {
	public List<FssAccountingLoandebtDetail> queryFssAccountingLoandebtDetail(Map map);
}
