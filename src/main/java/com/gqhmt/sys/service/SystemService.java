package com.gqhmt.sys.service;

import com.gqhmt.fss.architect.merchant.entity.Business;
import com.gqhmt.fss.architect.merchant.mapper.write.RestApiWriteMapper;
import com.gqhmt.sys.entity.DictMain;
import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.mapper.read.MenuReadMapper;
import com.gqhmt.sys.mapper.read.SystemReadMapper;
import com.gqhmt.sys.mapper.write.SystemWriteMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    
    
    
    public List<DictMain> queryDictmain(DictMain dictmain){
        return  this.systemReadMapper.selectDictmain(dictmain);
    }

    
    public void insertDictmain(DictMain dict) {
//    	this.systemWriteMapper.insertSelective(dict);
    	this.systemWriteMapper.insertDictmain(dict);
	}
    
    
    public List<DictMain> getDictmainById(String dictId){
    	return this.systemReadMapper.getDictMainById(dictId);
    }
    
    public void updateDict(DictMain dict) {
//    	systemWriteMapper.updateByPrimaryKeySelective(dict);
    	systemWriteMapper.updateDictMain(dict);
	}
    
    public void delteDict(String dictId) {
    	systemWriteMapper.delteDictMain(dictId);
    }
    
}
