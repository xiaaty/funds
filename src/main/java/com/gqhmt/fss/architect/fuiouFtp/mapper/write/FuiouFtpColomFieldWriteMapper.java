package com.gqhmt.fss.architect.fuiouFtp.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldWriteMapper extends ReadAndWriteMapper<FuiouFtpColomField> {

	void updateByFileSeqId(String fileSeq, String reqCode, String msg);

	public void saveOrUpdateAll(@Param("fuiouCollection") List<FuiouFtpColomField> fuiyoulist);
}
