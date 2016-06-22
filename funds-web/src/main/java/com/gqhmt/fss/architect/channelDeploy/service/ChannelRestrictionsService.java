package com.gqhmt.fss.architect.channelDeploy.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelRestrictionsEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelRestrictionsReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelRestrictionsWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/02.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/02.  xdw         1.0     1.0 Version
 */
@Service
public class ChannelRestrictionsService {

    @Resource
    private ChannelRestrictionsReadMapper channelRestrictionsReadMapper;

    @Resource
    private ChannelRestrictionsWriteMapper channelRestrictionsWriteMapper;

    /**
     * 根据orgId 查询限额配置信息
     * @param orgId
     * @return List<ChannelRestrictionsEntity>
     * @throws
     */
    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntityListByOrgId(int orgId) throws FssException {
        return channelRestrictionsReadMapper.getChannelRestrictionsEntityListByOrgId(orgId);
    }

    /**
     * 查询限额配置信息
     * @param channelRestrictionsEntity
     * @return List<ChannelRestrictionsEntity>
     * @throws
     */
    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntity(ChannelRestrictionsEntity channelRestrictionsEntity) throws FssException{
        return channelRestrictionsReadMapper.getChannelRestrictionsEntity(channelRestrictionsEntity);
    }

    /**
     * 添加限额配置信息
     * @param channelRestrictionsEntity
     * @return channelBankEntity
     * @throws
     */
    public ChannelRestrictionsEntity addRestrictionsEntity(ChannelRestrictionsEntity channelRestrictionsEntity) throws FssException{
        channelRestrictionsWriteMapper.addRestrictionsEntity(channelRestrictionsEntity);
        return channelRestrictionsEntity;
    }
}
