package com.gqhmt.fss.architect.account.mapper.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffEntity;

/**
 * @author zhaoenyue
 */
public interface FssAccountBalanceDiffWriteMapper extends ReadAndWriteMapper<FssAccountBalanceDiffEntity> {

	int batchInsertOrUpdate(@Param("list")List<FssAccountBalanceDiffEntity> list);
	
}
