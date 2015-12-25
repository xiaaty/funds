package com.gqhmt.fss.architect.job.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.job.bean.FuiouUploadFile;

/**
 * Created by yuyonf on 15/4/6.
 */
public interface FuiouUploadFileReadMapper extends ReadMapper<FuiouUploadFile> {

    public List<FuiouUploadFile> list(int state);

    public List<Integer> list(String order);

    public List<FuiouUploadFile> listAll(String orderNo);

}
