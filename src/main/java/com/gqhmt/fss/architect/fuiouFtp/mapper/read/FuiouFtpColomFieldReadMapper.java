package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import java.util.List;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldReadMapper extends ReadMapper<FuiouFtpColomField> {

    public List<Long> getOrder();

    public List<String> getReqCode(String orderNo);

}
