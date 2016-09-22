package com.gqhmt.fss.architect.account.service;

import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.mapper.read.FuiouAccountInfoReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FuiouAccountInfoWriteMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
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

    //查询AccountInfoEntity
    public List<FuiouAccountInfoEntity> queryAccountInfoList(Map<String,String> map) throws ParseException {
        return fuiouAccountInfoReadMapper.queryAccountInfoList(map);
    }

    //查询 AccountInfoEntity
    public List<FuiouAccountInfoEntity> queryAccountAllInfoList(Map<String, String> map) {
        return fuiouAccountInfoReadMapper.queryAccountAllInfoList(map);
    }

    //手动抓取失败文件
    public void updateFuiouAccountInfoEntity(FuiouAccountInfoEntity file) {
        fuiouAccountInfoWriteMapper.updateFuiouAccountInfoEntity(file);
    }

    public void deleteAccountFailInfo(String id) {
        fuiouAccountInfoWriteMapper.deleteFuiouAccountInfoEntity(id);
    }

    public FuiouAccountInfoEntity seleteOne(FuiouAccountInfoEntity accountInfo) {
        return fuiouAccountInfoReadMapper.selectOne(accountInfo);
    }

    public void addFuiouAccountInfoList(List<FuiouAccountInfoEntity> accInfoList) {

        if(!CollectionUtils.isEmpty(accInfoList)){
            fuiouAccountInfoWriteMapper.insertList(accInfoList);
        }

    }
}
