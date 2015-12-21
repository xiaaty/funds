package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.Menu;
import com.gqhmt.sys.session.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class MenuServiceTest {

    @Resource
    private MenuService menuService;

    @Test
    public void selectAllMenu(){
        List<Menu> list = menuService.findMenuAll();

        assert list.size()>0;
    }

    @Test
    public void getMenuText(){
        String menu = Application.getInstance().getMenu("","");

        assert menu != null && !"".equals(menu);

    }

}
