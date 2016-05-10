package com.gqhmt.sftp.mapper.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sftp.entity.FssProjectCallbackEntity;

/**
 * Filename:    com.gqhmt.quartz.mapper.write.FssQuartzWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 10:15
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
public interface FssProjectCallbackReadMapper extends ReadMapper<FssProjectCallbackEntity> {
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月10日
	 * function：通过parent_id得到对象列表
	 */
	public List<FssProjectCallbackEntity> getByParentId(@Param("parentId") Long parentId);
}
