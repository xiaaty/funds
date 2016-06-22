package com.gqhmt.fss.architect.channelDeploy.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelBankEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelBankReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelBankWriteMapper;
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
public class ChannelBankService {

    @Resource
    private ChannelBankReadMapper channelBankReadMapper;

    @Resource
    private ChannelBankWriteMapper channelBankWriteMapper;

    /**
     * 根据orgId 查询银行配置信息
     * @param orgId
     * @return List<ChannelBankEntity>
     * @throws
     */
    public List<ChannelBankEntity> getChannelBankEntityListByOrgId(int orgId) throws FssException {
        return channelBankReadMapper.getChannelBankEntityListByOrgId(orgId);
    }

    /**
     * 查询银行配置信息
     * @param channelBankEntity
     * @return List<ChannelBankEntity>
     * @throws
     */
    public List<ChannelBankEntity> getChannelBankEntity(ChannelBankEntity channelBankEntity) throws FssException{
        return channelBankReadMapper.getChannelBankEntity(channelBankEntity);
    }

    /**
     * 添加银行配置信息
     * @param channelBankEntity
     * @return List<ChannelBankEntity>
     * @throws
     */
    public ChannelBankEntity addBankEntity(ChannelBankEntity channelBankEntity) throws FssException{
        channelBankWriteMapper.addBankEntity(channelBankEntity);
        return channelBankEntity;
    }

}
