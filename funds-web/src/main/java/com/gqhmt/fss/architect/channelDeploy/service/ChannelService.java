package com.gqhmt.fss.architect.channelDeploy.service;

import com.gqhmt.fss.architect.channelDeploy.entity.ChannelBankEntity;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelMerchantEntity;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelOrgEntity;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelRestrictionsEntity;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelBankReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelMerchantReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelOrgReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.read.ChannelRestrictionsReadMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelBankWriteMapper;
import com.gqhmt.fss.architect.channelDeploy.mapper.write.ChannelMerchantWriteMapper;
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
 * Create at:   16/6/02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/6/02  xdw         1.0     1.0 Version
 */
@Service
public class ChannelService {

    @Resource
    private ChannelOrgReadMapper channelOrgReadMapper;

    @Resource
    private ChannelMerchantReadMapper channelMerchantReadMapper;

    @Resource
    private ChannelBankReadMapper channelBankReadMapper;

    @Resource
    private ChannelRestrictionsReadMapper channelRestrictionsReadMapper;

    @Resource
    private ChannelMerchantWriteMapper channelMerchantWriteMapper;

    @Resource
    private ChannelBankWriteMapper channelBankWriteMapper;

    @Resource
    private ChannelRestrictionsWriteMapper channelRestrictionsWriteMapper;

    /**
     * 查询商户交易渠道org信息集合
     * @param
     * @return List<ChannelOrgEntity>
     * @throws
     */
    public List<ChannelOrgEntity> getChannelOrgList(){
        return channelOrgReadMapper.getChannelOrgEntityList();
    }

    /**
     * 根据id 查询商户交易渠道org信息
     * @param id
     * @return ChannelOrgEntity
     * @throws
     */
    public ChannelOrgEntity getChannelOrgEntityById(int id){ return channelOrgReadMapper.getChannelOrgEntityById(id); }

    /**
     * 根据orgId 查询交易渠道商户配置信息
     * @param orgId
     * @return ChannelOrgEntity
     * @throws
     */
    public List<ChannelMerchantEntity> getMerchantEntityListByOrgId(int orgId){
        return channelMerchantReadMapper.getMerchantListByOrgId(orgId);
    }

    /**
     * 根据orgId 查询银行配置信息
     * @param orgId
     * @return List<ChannelBankEntity>
     * @throws
     */
    public List<ChannelBankEntity> getChannelBankEntityListByOrgId(int orgId) {
        return channelBankReadMapper.getChannelBankEntityListByOrgId(orgId);
    }

    /**
     * 根据orgId 查询限额配置信息
     * @param orgId
     * @return List<ChannelRestrictionsEntity>
     * @throws
     */
    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntityListByOrgId(int orgId){
        return channelRestrictionsReadMapper.getChannelRestrictionsEntityListByOrgId(orgId);
    }

    /**
     * 添加交易渠道商户配置信息
     * @param
     * @return
     * @throws
     */
    public ChannelMerchantEntity addMerchantEntity(ChannelMerchantEntity channelMerchantEntity){
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
    public List<ChannelMerchantEntity> getMerchantNameOrValue(ChannelMerchantEntity channelMerchantEntity){
        return channelMerchantReadMapper.getMerchantNameOrValue(channelMerchantEntity);
    }

    /**
     * 查询银行配置信息
     * @param channelBankEntity
     * @return List<ChannelBankEntity>
     * @throws
     */
    public List<ChannelBankEntity> getChannelBankEntity(ChannelBankEntity channelBankEntity){
        return channelBankReadMapper.getChannelBankEntity(channelBankEntity);
    }

    /**
     * 添加银行配置信息
     * @param channelBankEntity
     * @return List<ChannelBankEntity>
     * @throws
     */
    public ChannelBankEntity addBankEntity(ChannelBankEntity channelBankEntity){
        channelBankWriteMapper.addBankEntity(channelBankEntity);
        return channelBankEntity;
    }

    /**
     * 查询限额配置信息
     * @param channelRestrictionsEntity
     * @return List<ChannelRestrictionsEntity>
     * @throws
     */
    public List<ChannelRestrictionsEntity> getChannelRestrictionsEntity(ChannelRestrictionsEntity channelRestrictionsEntity){
        return channelRestrictionsReadMapper.getChannelRestrictionsEntity(channelRestrictionsEntity);
    }

    /**
     * 添加限额配置信息
     * @param channelRestrictionsEntity
     * @return channelBankEntity
     * @throws
     */
    public ChannelRestrictionsEntity addRestrictionsEntity(ChannelRestrictionsEntity channelRestrictionsEntity){
        channelRestrictionsWriteMapper.addRestrictionsEntity(channelRestrictionsEntity);
        return channelRestrictionsEntity;
    }

}
