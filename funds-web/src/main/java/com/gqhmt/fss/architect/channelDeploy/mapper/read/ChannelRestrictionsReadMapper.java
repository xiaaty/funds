package com.gqhmt.fss.architect.channelDeploy.mapper.read;

import com.gqhmt.fss.architect.channelDeploy.entity.ChannelRestrictionsEntity;

import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/6/02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/6/02  xdw         1.0     1.0 Version
 */
public interface ChannelRestrictionsReadMapper {

    /**
     * 根据orgId 查询限额配置信息
     * @param orgId
     * @return List<ChannelRestrictionsEntity>
     * @throws
     */
    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntityListByOrgId(int orgId);

    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntity(ChannelRestrictionsEntity channelRestrictionsEntity);
}
