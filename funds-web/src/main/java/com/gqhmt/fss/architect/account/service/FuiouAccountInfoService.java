package com.gqhmt.fss.architect.account.service;

import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.mapper.write.FuiouAccountInfoWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/27.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/27.  xdw         1.0     1.0 Version
 */
@Service
public class FuiouAccountInfoService {
    @Resource
    FuiouAccountInfoWriteMapper fuiouAccountInfoWriteMapper;

    //添加AccountInfoEntity 到数据库
    public void addFuiouAccountInfoEntity(FuiouAccountInfoEntity file){
        fuiouAccountInfoWriteMapper.addFuiouAccountInfoEntity(file);
    }
}
