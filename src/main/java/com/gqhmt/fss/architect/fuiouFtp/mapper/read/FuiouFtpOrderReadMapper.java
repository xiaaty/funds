package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.github.pagehelper.Page;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.bean.FundOrder;

import java.util.List;

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

    public Page listAll(FundOrder fundOrder);
}
