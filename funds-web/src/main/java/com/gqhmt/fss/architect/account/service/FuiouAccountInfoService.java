package com.gqhmt.fss.architect.account.service;

import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.mapper.read.FuiouAccountInfoReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FuiouAccountInfoWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Resource
    FuiouAccountInfoReadMapper fuiouAccountInfoReadMapper;

    //添加AccountInfoEntity 到数据库
    public void addFuiouAccountInfoEntity(FuiouAccountInfoEntity file){
        fuiouAccountInfoWriteMapper.addFuiouAccountInfoEntity(file);
    }

    //查询AccountInfoEntity
    public List<FuiouAccountInfoEntity> queryAccountInfoList(Map<String,String> map) throws ParseException {
       /* String tradingTime = map.get("tradingTime");
        if(tradingTime!=null && tradingTime!=""){
            tradingTime = tradingTime.replaceAll("-","");
        }
        map.put("tradingTime",tradingTime);*/
        return fuiouAccountInfoReadMapper.queryAccountInfoList(map);
    }

    //查询失败的AccountInfoEntity
    public List<FuiouAccountInfoEntity> queryAccountFailInfoList(Map<String, String> map) {
        return fuiouAccountInfoReadMapper.queryAccountFailInfoList(map);
    }

    //手动抓取失败文件
    public void updateFuiouAccountInfoEntity(FuiouAccountInfoEntity file) {
        fuiouAccountInfoWriteMapper.updateFuiouAccountInfoEntity(file);
    }

    public void deleteAccountFailInfo(String id) {
        fuiouAccountInfoWriteMapper.deleteFuiouAccountInfoEntity(id);
    }
}
