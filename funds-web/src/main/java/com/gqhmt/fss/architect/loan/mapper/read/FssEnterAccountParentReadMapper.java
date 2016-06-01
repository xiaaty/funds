package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月15日
 * Description:	入账子表mapper
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月15日  jhz      1.0     1.0 Version
 */
public interface FssEnterAccountParentReadMapper extends ReadMapper<FssEnterAccountParentEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月26日
	 * function：得到主表对象
	 */
	FssEnterAccountParentEntity getEnterAccountParent(Map<String, String> map);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月26日
	 * function：得到主表列表
	 */
	List<FssEnterAccountParentEntity> getEnterAccountParentList(Map<Object, Object> map);
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：根据交易状态查询主表列表
	 */
	List<FssEnterAccountParentEntity> getEnterAccountParentByState();

	
}
