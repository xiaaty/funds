package com.gqhmt.sftp.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sftp.entity.FssCorporateEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:p2p法人平台开户文件
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  柯禹来      1.0     1.0 Version
 */
public interface FssCorporateReadMapper extends ReadMapper<FssCorporateEntity> {
	
	public List<FssCorporateEntity> queryCorporateList(Map map);
}
