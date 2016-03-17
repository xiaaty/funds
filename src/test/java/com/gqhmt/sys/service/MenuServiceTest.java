package com.gqhmt.sys.service;

import com.gqhmt.TestService;
import com.gqhmt.core.util.Application;
import com.gqhmt.sys.entity.MenuEntity;
import com.gqhmt.util.ServiceLoader;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.service.MenuServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:54
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */

public class MenuServiceTest extends TestService{

    @Resource
    private MenuService menuService;

    @Test
    public void selectAllMenu(){
        List<MenuEntity> list = menuService.findMenuAll();

        assert list.size()>0;
    }

    @Test
    public void getMenuText(){
        Application application = ServiceLoader.get(Application.class);
        String menu = application.getMenu("","");

        assert menu != null && !"".equals(menu);

    }

}
