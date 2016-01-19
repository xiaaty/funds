package com.gqhmt.funds.architect.mapping.service;

import com.gqhmt.funds.architect.mapping.bean.FuiouBankCodeEntity;
import com.gqhmt.funds.architect.mapping.mapper.read.FuiouBankCodeReadMapper;
import com.gqhmt.funds.architect.mapping.mapper.write.FuiouBankCodeWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
public class FuiouBankCodeService {

    @Resource
    private FuiouBankCodeReadMapper fuiouBankCodeReadMapper;
    @Resource
    private FuiouBankCodeWriteMapper fuiouBankCodeWriteMapper;

    public void insert(FuiouBankCodeEntity entity){
    	fuiouBankCodeWriteMapper.insertSelective(entity);
    }

    /**
     * 根据条件查询返回所有银行代码列表
     * @param FuiouBankCodeEntity
     * @return
     * @throws AppException
     */
    public List<FuiouBankCodeEntity> queryFuiouBankCodeList(FuiouBankCodeEntity entity) {
    	return fuiouBankCodeReadMapper.select(entity);
    }
    
    public FuiouBankCodeEntity selectOne(FuiouBankCodeEntity entity) {
    	return fuiouBankCodeReadMapper.selectOne(entity);
    }
    
    /**
     * 判断银行code是否存在
     * @param code
     * @return
     */
    public Boolean queryFuiouBankCodeValueByCode(String code){
    	FuiouBankCodeEntity entity = new FuiouBankCodeEntity();
    	entity.setBankCode(code);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return false;
    	} else {
    		return true;
    	}
    }
    
    
    /**
     * 根据银行名称取得对应的银行代码
     * @param value
     * @return
     */
    public String queryFuiouBankCodeByValue(String value){
    	FuiouBankCodeEntity entity = new FuiouBankCodeEntity();
    	entity.setBankName(value);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return "";
    	} else {
    		return entity.getBankCode();
    	}
    }
    
    /**
     * 根据银行code取得对应的银行名称
     * @param code
     * @return
     */
    public String queryFuiouBankValueByCode(String code){
    	FuiouBankCodeEntity entity = new FuiouBankCodeEntity();
    	entity.setBankCode(code);
    	entity = selectOne(entity);
    	if(null == entity) { 
    		return "";
    	} else {
    		return entity.getBankName();
    	}
    }
	
}
