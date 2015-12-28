package com.gqhmt.fss.architect.job.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.job.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.job.mapper.read.FuiouUploadFileReadMapper;
import com.gqhmt.fss.architect.job.mapper.write.FuiouUploadFileWriteMapper;

/**
 * Filename:    com.fuiou.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 15:15
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */

@Service
public class FuiouUploadFileService {
    @Resource
    private FuiouUploadFileReadMapper fuiouUploadFileReadMapper;
    @Resource
    private FuiouUploadFileWriteMapper fuiouUploadFileWriteMapper;

    public void insert(FuiouUploadFile fuiouUploadFile){
    	fuiouUploadFileWriteMapper.insertSelective(fuiouUploadFile);
    }
    
    public void update(FuiouUploadFile fuiouUploadFile) {
    	fuiouUploadFileWriteMapper.updateByPrimaryKeySelective(fuiouUploadFile);
    }
    
    public void update(List<FuiouUploadFile> fuiouUploadFiles){
        for (FuiouUploadFile fuiouUploadFile : fuiouUploadFiles) {
        	update(fuiouUploadFile);
		}
    }
    
    public void delete(Long id){
    	fuiouUploadFileWriteMapper.deleteByPrimaryKey(id);
    }

    public List<FuiouUploadFile> list(int status){
        return fuiouUploadFileReadMapper.list(status);
    }

    public List<Integer> list(String orderNo){
        return fuiouUploadFileReadMapper.list(orderNo);
    }

    public List<FuiouUploadFile> listAll(String orderNo){
        return fuiouUploadFileReadMapper.listAll(orderNo);
    }
    public FuiouUploadFile add(String businessCode,String mCode,int size,String sysdate,String fileSeqNo,BigDecimal sum,String orderNo){
        FuiouUploadFile file = new FuiouUploadFile();
        file.setBusinessCode(businessCode);
        file.setDetailNum(size);
        file.setEntrustDate(sysdate);
        file.setmCode(mCode);
        file.setNo(fileSeqNo);
        file.setTotleAmt(sum);
        file.setState(1);
        file.setOrderNo(orderNo);
        insert(file);
        return file;
    }
}
