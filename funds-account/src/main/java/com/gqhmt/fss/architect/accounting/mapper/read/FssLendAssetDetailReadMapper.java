package com.gqhmt.fss.architect.accounting.mapper.read;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssLendAssetDetailEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月28日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月28日  jhz      1.0     1.0 Version
 */
public interface FssLendAssetDetailReadMapper extends ReadMapper<FssLendAssetDetailEntity>{
	
	/**
	 * 
	 * author:jhz
	 * time:2016年4月28日
	 * function：通过parent_id得到详情表信息集合
	 */
	List<FssLendAssetDetailEntity> getDetailByParentId(@Param("parentId")Long parentId);
	
	
}
