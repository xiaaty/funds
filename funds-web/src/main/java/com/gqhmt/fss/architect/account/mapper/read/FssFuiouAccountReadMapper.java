package com.gqhmt.fss.architect.account.mapper.read;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.bean.FssFuiouAccountBean;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;

/**
 * Filename:    com.gqhmt.fss.architect.account.mapper.read.FssFuiouAccountReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
public interface FssFuiouAccountReadMapper extends ReadMapper<FssFuiouAccountEntity> {

	public FssFuiouAccountBean getAccountByCentNo(@Param(value="certNo") String certNo);
	
	public FssFuiouAccountEntity getByAccNo(@Param(value="accNo") String accNo);
}
