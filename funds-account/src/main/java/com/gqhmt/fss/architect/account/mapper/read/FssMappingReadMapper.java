package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:28
 * Description:红包账户
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  柯禹来      1.0     1.0 Version
 */
public interface FssMappingReadMapper extends ReadMapper<FssMappingEntity> {
	
	/**
	 * 获取红包账户信息
	 * @param map
	 * @return
	 */
	public List<FssMappingEntity> queryRedAccountList(Map map);
	public List<FssMappingEntity> findAllMapping();
	public List<FssMappingBean> getMappingByType(@Param("mappingType") String mappingType);
	public FssMappingEntity getMappingByCustId(Map map);
	public FssMappingEntity getMappingBySort(@Param("sort") String sort);
	public BigDecimal getBondSumAmount(@Param("mappingType") String mappingType);
	public List<FssMappingBean> getMobileList(@Param("mappingType") String mappingType);
	/**
	 * jhz
	 * 查寻设置百分比
	 * @param tradeType
	 * @return
	 * @throws FssException
	 */
	FssMappingEntity selectByTradeType(@Param("tradeType")String tradeType);

	}
