package com.gqhmt.fss.architect.account.mapper.write;

import java.util.List;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffFullDataEntity;

import org.apache.ibatis.annotations.Param;

/**
 * @author zhaoenyue
 */
public interface FssAccountBalanceDiffFullDataWriteMapper extends ReadAndWriteMapper<FssAccountBalanceDiffFullDataEntity> {
	
	/**
	 * 初始化全量数据
	 */
	void initFullData();
	
	/**
	 * 根据custId批量更新完成校验状态
	 * @param custIdList
	 */
	void batchSetValidate(@Param("custIdList") List<String> custIdList);
	
}
