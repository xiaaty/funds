package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.entity.DictOrderEntity;
import com.gqhmt.sys.mapper.read.DictOrderReadMapper;
import com.gqhmt.sys.mapper.read.SystemReadMapper;
import com.gqhmt.sys.mapper.write.DictOrderWriteMapper;
import com.gqhmt.sys.mapper.write.SystemWriteMapper;
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
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
    
    @Resource
    private DictOrderWriteMapper dictOrderWriteMapper;
    
    
    
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
    
    public void insertDictOrder(DictOrderEntity dictorder) {
    	this.dictOrderWriteMapper.insertDictOrder(dictorder);
	}
    
    public DictOrderEntity getDictOrderById(Long id){
    	return this.dictOrderReadMapper.selectByPrimaryKey(id);
    }
    
    public void updateDictOrder(DictOrderEntity dictorder){
    	this.dictOrderWriteMapper.updateDictOrderById(dictorder);
    };
    
    
   public List<DictEntity> findDictList(){
       return  this.systemReadMapper.selectAll();
   }
    
   public List<DictEntity> findDictListByOrderList(String dictId){
	   List list=new ArrayList();
	   if(StringUtils.isNotEmptyString(dictId)){
		   String str[]=dictId.split(",");
		   for (int i = 0; i < str.length; i++){
			   list.add(str[i]);
			   }
	   }
	   return  this.systemReadMapper.selectDictByOrderList(list);
   }
   
}
