package com.gqhmt.sftp.mapper.read;

import java.util.List;
import java.util.Map;


import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sftp.entity.FssSftpRecordEntity;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月11日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月11日  jhz      1.0     1.0 Version
 */
public interface FssSftpRecordReadMapper extends ReadMapper<FssSftpRecordEntity> {
	
	/**
	 * 
	 * author:jhz
	 * time:2016年5月10日
	 * function：通过条件得到对象列表
	 */
	public List<FssSftpRecordEntity> getRecordList(Map<String,String> map);
}
