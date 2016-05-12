package com.gqhmt.sftp.mapper.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sftp.entity.FssSumAuditEntity;

public interface FssFinanceSumAuditReadMapper extends ReadMapper<FssSumAuditEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年5月10日
	 * function：通过parent_id得到对象列表
	 */
	public List<FssSumAuditEntity> getByParentId(@Param("parentId") Long parentId);

}
