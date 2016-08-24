package com.gqhmt.fss.architect.depos.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.depos.entity.FssProjectInfoEntity;

/**
 *
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月6日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  jhz      1.0     1.0 Version
 */
public interface FssProjectInfoReadMapper extends ReadMapper<FssProjectInfoEntity> {
	/**
	 *
	 * author:jhz
	 * time:2016年5月10日
	 * function：通过parent_id得到对象列表
	 */
	public List<FssProjectInfoEntity> getByParentId(@Param("parentId") Long parentId);



	public List<FssProjectInfoEntity> queryFssProjectInfoList(Map map);

	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：根据状态查询项目信息列表
	 */
	public List<FssProjectInfoEntity> queryItemsInfosByStatus(@Param("status")String status);
	/**
	 * jhz
	 * 查询项目编号是否唯一
	 * @param itemNo
	 * @return
	 */
	public FssProjectInfoEntity getByItemNo(@Param("itemNo")String itemNo);


}
