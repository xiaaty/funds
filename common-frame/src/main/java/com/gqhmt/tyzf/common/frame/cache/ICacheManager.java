package com.gqhmt.tyzf.common.frame.cache;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

import java.util.List;

/**
 * Created by zhou on 2016/10/24.
 * Description:数据库数据缓存数据
 */
public interface ICacheManager {

    /**
     * 生成流水号
     */
    String getSequenceNo(String type_id);

}
