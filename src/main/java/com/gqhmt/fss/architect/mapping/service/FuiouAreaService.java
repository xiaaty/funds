package com.gqhmt.fss.architect.mapping.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.mapping.bean.FuiouAreaEntity;
import com.gqhmt.fss.architect.mapping.mapper.read.FuiouAreaReadMapper;
import com.gqhmt.fss.architect.mapping.mapper.write.FuiouAreaWriteMapper;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author guofu
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/03/30 16:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/03/30  guofu      1.0     1.0 Version
 */
@Service
public class FuiouAreaService {

    @Resource
    private FuiouAreaReadMapper fuiouAreaReadMapper;
    @Resource
    private FuiouAreaWriteMapper fuiouAreaWriteMapper;

    public void insert(FuiouAreaEntity entity){
    	fuiouAreaWriteMapper.insertSelective(entity);
    }
    
    /**
     * 根据条件查询返回所有地区代码列表
     * @param FuiouAreaEntity
     * @return
     * @throws AppException
     */
    public List<FuiouAreaEntity> queryFuiouAreaList(FuiouAreaEntity entity) {
    	return fuiouAreaReadMapper.selectAll();
    }
    
    /**
     * 根据条件查询返回所有地区代码列表
     * @param FuiouAreaEntity
     * @return
     * @throws AppException
     */
    public FuiouAreaEntity selectOne(FuiouAreaEntity entity) {
    	return fuiouAreaReadMapper.selectOne(entity);
    }
    
    /**
     * 判断地区code是否存在
     * @param code
     * @return
     */
    public Boolean queryFuiouAreaCodeValueByCode(String code){
    	FuiouAreaEntity entity = new FuiouAreaEntity();
    	entity.setAreaCode(code);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return false;
    	} else {
    		return true;
    	}
    }
    
    
    /**
     * 根据地区值取得对应的地区代码
     * @param value
     * @return
     */
    public String queryFuiouAreaCodeByValue(String value){
    	FuiouAreaEntity entity = new FuiouAreaEntity();
    	entity.setAreaValue(value);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return "";
    	} else {
    		return entity.getAreaCode();
    	}
    }
    
    /**
     * 根据地区代码取得对应的地区
     * @param value
     * @return
     */
    public String queryFuiouAreaValueByCode(String code){
    	FuiouAreaEntity entity = new FuiouAreaEntity();
    	entity.setAreaCode(code);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return "";
    	} else {
    		return entity.getAreaValue();
    	}
    }
	
}
