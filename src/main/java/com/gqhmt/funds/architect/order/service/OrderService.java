package com.gqhmt.funds.architect.order.service;

import com.gqhmt.fss.architect.order.entity.Order;
import com.gqhmt.fss.architect.order.mapper.read.OrderReadDaoInter;
import com.gqhmt.fss.architect.order.mapper.write.OrderWriteDaoInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.order.service.OrderService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/10/27 15:36
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
@Service
public class OrderService {

    @Resource
    private OrderWriteDaoInter orderWriteDaoInter;

    @Resource
    private OrderReadDaoInter orderReadDaoInter;

    public void save(Order order){
        orderWriteDaoInter.insert(order);
    }
}
