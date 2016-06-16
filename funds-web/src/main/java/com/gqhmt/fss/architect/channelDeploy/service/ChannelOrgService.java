package com.gqhmt.fss.architect.channelDeploy.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelOrgEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelOrgReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelOrgWriteMapper;
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
public class ChannelOrgService {

    @Resource
    private ChannelOrgReadMapper channelOrgReadMapper;

    @Resource
    private ChannelOrgWriteMapper channelOrgWriteMapper;

    /**
     * 查询商户交易渠道org信息集合
     * @param
     * @return List<ChannelOrgEntity>
     * @throws
     */
    public List<ChannelOrgEntity> getChannelOrgList()throws FssException {
        return channelOrgReadMapper.getChannelOrgEntityList();
    }

    /**
     * 根据id 查询商户交易渠道org信息
     * @param id
     * @return ChannelOrgEntity
     * @throws
     */
    public ChannelOrgEntity getChannelOrgEntityById(int id) throws FssException{ return channelOrgReadMapper.getChannelOrgEntityById(id); }

    public ChannelOrgEntity updateChannelOrgEntity(ChannelOrgEntity channelOrgEntity) throws FssException{
        channelOrgWriteMapper.updateChannelOrgEntity(channelOrgEntity);
        return channelOrgReadMapper.getChannelOrgEntityById(channelOrgEntity.getId());
    }

}
