package com.gqhmt.fss.architect.account.mapper.write;

import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;

import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/27.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/27.  xdw         1.0     1.0 Version
 */
public interface FuiouAccountInfoWriteMapper {

    //添加金账户对账文件记录
    public void addFuiouAccountInfoEntity(FuiouAccountInfoEntity file);

    //手动抓取失败文件
    public void updateFuiouAccountInfoEntity(FuiouAccountInfoEntity file);

    //刪除fpt不存在的文件记录
    public void deleteFuiouAccountInfoEntity(String id);
}
