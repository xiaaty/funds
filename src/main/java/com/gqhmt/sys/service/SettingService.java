package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.Settings;
import com.gqhmt.sys.mapper.read.SettingReadMapper;
import com.gqhmt.sys.mapper.write.SetttingWiterMapper;
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
public class SettingService {

    @Resource
    private SettingReadMapper settingReadMapper;
    @Resource
    private SetttingWiterMapper setttingWiterMapper;
    
    
    
    
    
    
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：得到系统配置列表
     */
    public List<Settings> settingList(Settings setting){
    	return settingReadMapper.findSettings(setting);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：得到需要修改的系统配置
     */
    public Settings findSettingById(Long id){
    	return settingReadMapper.selectByPrimaryKey(id);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：添加系统配置
     */
    public int insertSetting(Settings setting){
    	 return  setttingWiterMapper.insert(setting);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：修改系统配置
     */
    public int updateSetting(Settings setting){
    	return  setttingWiterMapper.updateByPrimaryKey(setting);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月14日
     * function：删除系统配置
     */
    public int deleteSetting(Long id){
    	 return setttingWiterMapper.deleteByPrimaryKey(id);
    }
    
    
    
}
