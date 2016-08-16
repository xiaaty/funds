package com.gqhmt.fss.architect.account.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/8/3.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/8/3.  xdw         1.0     1.0 Version
 */
public interface FuiouAccountInfoFileWriteMapper extends ReadAndWriteMapper<FuiouAccountInfoFileEntity> {

    public void addFuiouAccountInfoFileEntity(FuiouAccountInfoFileEntity file);
}
