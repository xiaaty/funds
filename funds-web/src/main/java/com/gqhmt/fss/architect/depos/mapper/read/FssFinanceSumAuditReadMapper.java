package com.gqhmt.fss.architect.depos.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.depos.entity.FssSumAuditEntity;

public interface FssFinanceSumAuditReadMapper extends ReadMapper<FssSumAuditEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年5月10日
	 * function：通过parent_id得到对象列表
	 */
	public List<FssSumAuditEntity> getByParentId(@Param("parentId") Long parentId);
	
	public List<FssSumAuditEntity> querySumAuditList(Map map);

}
