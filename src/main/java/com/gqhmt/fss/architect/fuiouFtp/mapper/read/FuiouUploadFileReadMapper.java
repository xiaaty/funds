package com.gqhmt.fss.architect.fuiouFtp.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by yuyonf on 15/4/6.
 */
public interface FuiouUploadFileReadMapper extends ReadMapper<FuiouUploadFile> {

    public List<FuiouUploadFile> list(@Param("state") int state);

    public List<Integer> list(String order);

    public List<FuiouUploadFile> listAll(String orderNo);

}
