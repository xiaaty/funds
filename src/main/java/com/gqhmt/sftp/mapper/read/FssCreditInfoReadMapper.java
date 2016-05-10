package com.gqhmt.sftp.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayable;
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
public interface FssCreditInfoReadMapper extends ReadMapper<FssCreditInfoEntity> {
	public FssCreditInfoEntity getParentId(@Param("parentId") Long parentId);
}
