package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.mapper.read.FuiouUploadFileReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.mapper.write.FuiouUploadFileWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;



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

    public void insert(FuiouUploadFile fuiouUploadFile)throws FssException{
    	fuiouUploadFileWriteMapper.insertSelective(fuiouUploadFile);
    }
    
    public void update(FuiouUploadFile fuiouUploadFile)throws FssException {
    	fuiouUploadFileWriteMapper.updateByPrimaryKeySelective(fuiouUploadFile);
    }
    
    public void update(List<FuiouUploadFile> fuiouUploadFiles)throws FssException{
        for (FuiouUploadFile fuiouUploadFile : fuiouUploadFiles) {
        	update(fuiouUploadFile);
		}
    }
    
    public void delete(Long id)throws FssException{
    	fuiouUploadFileWriteMapper.deleteByPrimaryKey(id);
    }

    public List<FuiouUploadFile> list(int status)throws FssException{
    	
        return fuiouUploadFileReadMapper.selectByStatus(status);
    }

    public List<Integer> list(String orderNo)throws FssException{
        return fuiouUploadFileReadMapper.list(orderNo);
    }

    public List<FuiouUploadFile> listAll(String orderNo)throws FssException{
    	FuiouUploadFile fuiouUploadFile=new FuiouUploadFile();
    	fuiouUploadFile.setOrderNo(orderNo);
        return fuiouUploadFileReadMapper.select(fuiouUploadFile);
    }
    public FuiouUploadFile add(String businessCode,String mCode,int size,String sysdate,String fileSeqNo,BigDecimal sum,String orderNo)throws FssException{
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
    
    public void saveOrUpdateAll(List<FuiouUploadFile> fuiouUploadFile)throws FssException{
    	fuiouUploadFileWriteMapper.insertList(fuiouUploadFile);
    }
    
    public void delete(FuiouUploadFile fuiouUploadFile)throws FssException{
    	fuiouUploadFileWriteMapper.delete(fuiouUploadFile);
    }
    
    
}
