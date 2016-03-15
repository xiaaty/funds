package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;

import java.util.List;
import java.util.Map;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldReadMapper extends ReadMapper<FuiouFtpColomField> {

    public List<FuiouFtpColomField> list(int state);
    
    public List<FuiouFtpColomField> listAll();

    public List<FuiouFtpColomField> list(String orderNo);
    
    public Map<String,FuiouFtpColomField> list(Long fileID);


    public List<Long> getOrder(int state);

    public List<String> getReqCode(String orderNo);

    public List<FuiouFtpColomField> getFieldByOrderId(Long id);

}
