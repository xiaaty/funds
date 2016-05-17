package com.gqhmt.fss.architect.accounting.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingServiceCharge;


public interface FssAccountingChargeReadMapper extends ReadMapper<FssAccountingServiceCharge>{
	public List<FssAccountingServiceCharge> queryFssAccountingServiceCharge(Map map);
}
