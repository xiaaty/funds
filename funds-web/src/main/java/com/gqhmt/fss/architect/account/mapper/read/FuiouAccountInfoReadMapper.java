package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;

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
 * Create at:   2016/6/27.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/27.  xdw         1.0     1.0 Version
 */
public interface FuiouAccountInfoReadMapper {

    //查询金账户对账文件记录
    public List<FuiouAccountInfoEntity> queryAccountInfoList(Map<String,String> map);

    //查询抓取失败的金账户对账文件记录
    public List<FuiouAccountInfoEntity> queryAccountFailInfoList(Map<String, String> map);

}
