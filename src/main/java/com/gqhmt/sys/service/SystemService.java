package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.entity.DictOrderEntity;
import com.gqhmt.sys.mapper.read.DictOrderReadMapper;
import com.gqhmt.sys.mapper.read.SystemReadMapper;
import com.gqhmt.sys.mapper.write.SystemWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.service.MenuService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:50
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */
@Service
public class SystemService {

    @Resource
    private SystemReadMapper systemReadMapper;
    @Resource
	private SystemWriteMapper systemWriteMapper;
    
    @Resource
    private DictOrderReadMapper dictOrderReadMapper;
    
    
    
    public List<DictEntity> queryDictmain(DictEntity dictmain){
        return  this.systemReadMapper.selectDictmain(dictmain);
    }

    public List<DictEntity> findALl(){
        return this.systemReadMapper.selectAll();
    }

    public DictEntity findDictmain(String id){
        return  this.systemReadMapper.getDictMainById(id);
    }

    
    public void insertDictmain(DictEntity dict) {
    	this.systemWriteMapper.insertDictmain(dict);
	}
    
    
    public DictEntity getDictmainById(String dictId){
    	return this.systemReadMapper.getDictMainById(dictId);
    }
    
    public void updateDict(DictEntity dict) {
    	systemWriteMapper.updateDictMain(dict);
	}
    
    public void delteDict(String dictId) {
    	systemWriteMapper.delteDictMain(dictId);
    }
    
    public List<DictOrderEntity> queryDictOrder(DictOrderEntity dictorder){
        return  this.dictOrderReadMapper.selectDictOrder(dictorder);
    }

    public List<DictOrderEntity> findALlDictOrder(){
        return this.dictOrderReadMapper.selectAll();
    }
    
    
    
}
