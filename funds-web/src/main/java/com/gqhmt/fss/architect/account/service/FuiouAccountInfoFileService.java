package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;
import com.gqhmt.fss.architect.account.mapper.read.FuiouAccountInfoFileReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FuiouAccountInfoFileWriteMapper;
import com.gqhmt.quartz.service.FtpDownloadFileService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
 * Create at:   2016/8/3.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/8/3.  xdw         1.0     1.0 Version
 */
@Service
public class FuiouAccountInfoFileService {
    @Resource
    private FuiouAccountInfoFileReadMapper fuiouAccountInfoFileReadMapper;

    @Resource
    private FuiouAccountInfoFileWriteMapper fuiouAccountInfoFileWriteMapper;

    @Resource
    private FtpDownloadFileService ftpDownloadFileService;

    //查询 AccountInfoEntity
    public List<FuiouAccountInfoFileEntity> queryAccountInfoFileList(Map<String, String> map) {
        return fuiouAccountInfoFileReadMapper.queryAccountInfoFileList(map);
    }

    //查询 AccountInfoEntity
    public List<FuiouAccountInfoFileEntity> queryAccountInfoFile(String createfileDate, String tradeType) {
        return fuiouAccountInfoFileReadMapper.queryAccountInfoFile(createfileDate,tradeType);
    }

    //查询 查询成功的对账文件
    public List<FuiouAccountInfoFileEntity> querySucceedAccInfoFiltList(Map<String, String> map) {
        map.put("booleanType","1");
        List<FuiouAccountInfoFileEntity> listFileEntity = this.queryAccountInfoFileList(map);

        return listFileEntity;

    }

    public void addFuiouAccountInfoFileEntity(FuiouAccountInfoFileEntity file) {
        fuiouAccountInfoFileWriteMapper.addFuiouAccountInfoFileEntity(file);
    }

    public void updateFuiouAccountInfoFileEntity(FuiouAccountInfoFileEntity file) {
        fuiouAccountInfoFileWriteMapper.updateByPrimaryKey(file);
    }

    public List<FuiouAccountInfoFileEntity> queryFailAccInfoFileList(Map<String, String> map) {
        map.put("booleanType","-1");
        List<FuiouAccountInfoFileEntity> listFileEntity = this.queryAccountInfoFileList(map);

        return listFileEntity;
    }

    public FuiouAccountInfoFileEntity getFileEntity(Date createTime, String tradeType){

        FuiouAccountInfoFileEntity fileEntity;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(createTime);

        List<FuiouAccountInfoFileEntity> acctounInfoFileList = this.queryAccountInfoFile(dateStr,tradeType);
        if(!CollectionUtils.isEmpty(acctounInfoFileList)){
            fileEntity = acctounInfoFileList.get(0);
        }else{
            fileEntity = new FuiouAccountInfoFileEntity();
            fileEntity.setCreatefileDate(dateStr);
            fileEntity.setTradeType(tradeType);
        }
        return fileEntity;
    }

    //抓取FTP 对账文件
    public boolean downFileAccountInfo(FuiouAccountInfoFileEntity fileEntity) throws FssException {

        if(fileEntity.getId()!=0 && "1".equals(fileEntity.getBooleanType())){
            LogUtil.info(this.getClass(),"抓取文件: "+fileEntity.getTradeType()+fileEntity.getCreatefileDate()+"已经抓取成功");
            return true;
        }

        if(!ftpDownloadFileService.downloadFuiouAccount(fileEntity)){
            LogUtil.info(this.getClass(),"文件: "+fileEntity.getTradeType()+fileEntity.getCreatefileDate()+" 下载到本地失败，请检查文件是否存在！");
            return false;
        }

        boolean paseType = ftpDownloadFileService.parseFileFuiouAcount(fileEntity);

        return paseType;
    }
}
