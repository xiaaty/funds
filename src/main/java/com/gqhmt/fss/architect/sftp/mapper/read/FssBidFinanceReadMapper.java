package com.gqhmt.fss.architect.sftp.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.sftp.entity.FinanceSumEntity;

public interface FssBidFinanceReadMapper extends ReadMapper<FinanceSumEntity> {
	public List<FinanceSumEntity> queryFssFinanceSumList(Map map);	
}
