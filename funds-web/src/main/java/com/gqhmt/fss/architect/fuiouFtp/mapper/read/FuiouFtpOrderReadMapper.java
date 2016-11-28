package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.fuiou.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 11:53
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
public interface FuiouFtpOrderReadMapper extends ReadMapper<FuiouFtpOrder> {

    public List<FuiouFtpOrder> listFile();

    public List<FuiouFtpOrder> listUpload();

    public List<FuiouFtpOrder> listDownload();

    public List<FuiouFtpOrder> listResultStatus();

    public List<FuiouFtpOrder> listResult();

    public List<FuiouFtpOrder> listNotReturn();

    public List<FuiouFtpOrder> listAbort();

    public List<FuiouFtpOrder> listNoReturnResult();

    public List<FuiouFtpOrder> selectFuiouFtpOrderList(Map<String, String> map);

    public List<FuiouFtpOrder> queryOrderNoListByDate(String orderDate);

}
