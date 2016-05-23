package com.gqhmt.fss.architect.fuiouFtp.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldWriteMapper extends ReadAndWriteMapper<FuiouFtpColomField> {
	public void updateByFileSeqId(FuiouFtpColomField fuiouFtpColomField);
}
