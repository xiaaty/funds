package com.gqhmt.fss.architect.asset.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;

/**
 * Filename:    com.gqhmt.fss.architect.asset.mapper.read.FssAssetReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:59
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
public interface FssAssetReadMapper extends ReadMapper<FssAssetEntity>{
	
	public FssAssetEntity getAccountAssets(String custNo);
}
