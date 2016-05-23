package com.gqhmt.fss.architect.customer.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.customer.entity.FssAreaMappingEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月1日
 * Description:
 * <p>出借系统映射地区表mapper
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月1日  jhz      1.0     1.0 Version
 */
public interface FssAreaMappingReadMapper extends ReadMapper<FssAreaMappingEntity> {
	 /**
     * 
     * author:jhz
     * time:2016年4月7日
     * function：查询所有的地区映射数据
     */
	List<FssAreaMappingEntity> findAllAreaMapping();

}
