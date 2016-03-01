package com.gqhmt.funds.architect.job.mapper.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.job.bean.FuiouFtpOrder;

/**
 * Filename:    com.fuiou.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 11:53
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
public interface FuiouFtpOrderWriteMapper extends ReadAndWriteMapper<FuiouFtpOrder> {
	public void saveOrUpdateAll(@Param("fuiouFtpOrderCollection") List<FuiouFtpOrder> fuiyouftplist);
	
}
