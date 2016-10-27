package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
public interface FuiouAccountInfoFileReadMapper extends ReadAndWriteMapper<FuiouAccountInfoFileEntity> {

    //查询金账户对账文件
    public List<FuiouAccountInfoFileEntity> queryAccountInfoFileList(Map<String, String> map);

    public List<FuiouAccountInfoFileEntity> queryAccountInfoFile(@Param("createfileDate") String createfileDate, @Param("tradeType") String tradeType);
}
