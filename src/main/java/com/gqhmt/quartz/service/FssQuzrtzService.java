package com.gqhmt.quartz.service;

import com.gqhmt.quartz.entity.FssQuartzJobEntity;
import com.gqhmt.quartz.mapper.read.FssQuartzReadMapper;
import com.gqhmt.quartz.mapper.write.FssQuartzWriteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

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
    private FssQuartzReadMapper fssQuartzReadMapper;

    @Resource
    private FssQuartzWriteMapper fssQuartzWriteMapper;

    public List<FssQuartzJobEntity> findAll(){
        return  this.fssQuartzReadMapper.selectAll();
    }

    public void insert(FssQuartzJobEntity entity){
        this.fssQuartzWriteMapper.insertSelective(entity);
    }

    public void insertList(List<FssQuartzJobEntity> list){
        this.fssQuartzWriteMapper.insertList(list);
    }
    /**
     * 
     * author:jhz
     * time:2016年3月14日
     * function：修改
     */
	public void update(FssQuartzJobEntity fssQuartzJobEntity) {
		this.fssQuartzWriteMapper.updateByPrimaryKey(fssQuartzJobEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月14日
	 * function：根据ID查找
	 */
	public FssQuartzJobEntity selectByPrimaryKey(Long id) {
		return (FssQuartzJobEntity) this.fssQuartzWriteMapper.selectByPrimaryKey(id);
	}









}
