package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by yuyong on 15/4/6.
 */
public interface FuiouFtpColomFieldReadMapper extends ReadMapper<FuiouFtpColomField> {

    public List<Long> getOrder();

    public List<String> getReqCode(@Param("orderNo") String orderNo);

	public List<FuiouFtpColomField> getByState(@Param("state")int state);

	public List<FuiouFtpColomField> getByOrderNo(@Param("orderNo")String orderNo);

	public List<FuiouFtpColomField> selectByFileId(@Param("fileId")Long fileId);

}
