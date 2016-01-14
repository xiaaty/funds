package com.gqhmt.funds.architect.job.mapper.write;

import com.gqhmt.core.mybatis.WriteMapper;
import com.gqhmt.funds.architect.job.bean.FuiouFtpColomField;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldWriteMapper extends WriteMapper<FuiouFtpColomField> {

	void updateByFileSeqId(String fileSeq, String reqCode, String msg);

}
