package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.mapper.read.MenuReadMapper;
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
public class MenuService {

    @Resource
    private MenuReadMapper menuReadMapper;

    public List<Menu> findMenuAll(){
        return  this.menuReadMapper.selectAll();
    }

    public List<Menu> findMenu(Long pId){
        return this.menuReadMapper.selectAllMenuByParentId(pId);
    }

}
