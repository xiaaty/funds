package com.gqhmt.fss.architect.accounting.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface FssCheckDateReadMapper extends ReadMapper<FssCheckDate> {

    public FssCheckDate queryInputDate();
}
