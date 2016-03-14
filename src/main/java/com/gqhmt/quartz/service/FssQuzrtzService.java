package com.gqhmt.quartz.service;

import com.gqhmt.quartz.entity.FssQuartzJobEntity;
import com.gqhmt.quartz.mapper.read.FssQuartzReadMapper;
import com.gqhmt.quartz.mapper.write.FssQuartzWriteMapper;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.service.FssQuzrtzService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 10:23
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Service
public class FssQuzrtzService {

    @Resource
    private FssQuartzReadMapper quartzReadMapper;

    @Resource
    private FssQuartzWriteMapper quartzWriteMapper;


    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;


    public List<FssQuartzJobEntity> findAll(){
        return  this.quartzReadMapper.selectAll();
    }

    public void insert(FssQuartzJobEntity entity){
        this.quartzWriteMapper.insertSelective(entity);
    }

    public void insertList(List<FssQuartzJobEntity> list){
        this.quartzWriteMapper.insertList(list);
    }









}
