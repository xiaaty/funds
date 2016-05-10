package com.gqhmt.sftp.mapper.read;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.sftp.entity.FssCreditInfoEntity;
import com.gqhmt.sftp.entity.FssItemsInfoEntity;

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
public interface FssItemInfoReadMapper extends ReadMapper<FssItemsInfoEntity> {
	public FssItemsInfoEntity getParentId(@Param("parentId") Long parenId);
}
