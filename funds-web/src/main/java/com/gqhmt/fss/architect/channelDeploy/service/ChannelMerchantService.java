package com.gqhmt.fss.architect.channelDeploy.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelMerchantEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelMerchantReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelMerchantWriteMapper;
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
public class ChannelMerchantService {

    @Resource
    private ChannelMerchantReadMapper channelMerchantReadMapper;

    @Resource
    private ChannelMerchantWriteMapper channelMerchantWriteMapper;

    /**
     * 根据orgId 查询交易渠道商户配置信息
     * @param orgId
     * @return ChannelOrgEntity
     * @throws
     */
    public List<ChannelMerchantEntity> getMerchantEntityListByOrgId(int orgId) throws FssException {
        return channelMerchantReadMapper.getMerchantListByOrgId(orgId);
    }

    /**
     * 添加交易渠道商户配置信息
     * @param
     * @return
     * @throws
     */
    public ChannelMerchantEntity addMerchantEntity(ChannelMerchantEntity channelMerchantEntity) throws FssException{
        channelMerchantWriteMapper.addMerchantEntity(channelMerchantEntity);
        return channelMerchantEntity;
    }

    /**
     *查询交易渠道商户配置信息
     * @param channelMerchantEntity
     * @return ChannelOrgEntity
     * @throws
     */
    public List<ChannelMerchantEntity> getMerchantEntity(ChannelMerchantEntity channelMerchantEntity){
        return channelMerchantReadMapper.getMerchantEntity(channelMerchantEntity);
    }

    /**
     * 根据optionName或者optionValue 查询交易渠道商户配置信息
     * @param channelMerchantEntity
     * @return List<ChannelMerchantEntity>
     * @throws
     */
    public List<ChannelMerchantEntity> getMerchantNameOrValue(ChannelMerchantEntity channelMerchantEntity) throws FssException {
        return channelMerchantReadMapper.getMerchantNameOrValue(channelMerchantEntity);
    }
}
