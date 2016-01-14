package com.gqhmt.funds.architect.job.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.job.bean.FuiouUploadFile;

import java.util.List;

/**
 * Created by yuyonf on 15/4/6.
 */
public interface FuiouUploadFileReadMapper extends ReadMapper<FuiouUploadFile> {

    public List<FuiouUploadFile> list(int state);

    public List<Integer> list(String order);

    public List<FuiouUploadFile> listAll(String orderNo);

}
